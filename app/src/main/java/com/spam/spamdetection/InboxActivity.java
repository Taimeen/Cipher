package com.spam.spamdetection;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class InboxActivity extends AppCompatActivity {
    private static final int SMS_PERMISSION_CODE = 1;
    private static final int NOTIFICATION_PERMISSION_CODE = 2;

    private RecyclerView recyclerView;
    private SmsAdapter adapter;
    private BlocklistManager blocklistManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        blocklistManager = new BlocklistManager(this);
        recyclerView = findViewById(R.id.recyclerView);

        // Request SMS permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS
            }, SMS_PERMISSION_CODE);
        } else {
            loadInboxMessages();
        }

        // Request notification permission
        requestNotificationPermission();

        SpamDetector.initialize(this);

        FloatingActionButton fabBlocklist = findViewById(R.id.fab_blocklist);
        fabBlocklist.setOnClickListener(view -> {
            Intent intent = new Intent(this, BlocklistActivity.class);
            startActivity(intent);
        });
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    NOTIFICATION_PERMISSION_CODE
            );
        }
    }

    private void showNotification(String title, String content) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
            // Handle the case where notification permission is not granted
            return;
        }

        String channelId = "spam_detection_channel";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Spam Detection Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.logo) // Replace with your app's icon
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showNotification("Permission Granted", "SMS Permission has been granted.");
                loadInboxMessages();
            } else {
                showNotification("Permission Denied", "SMS Permission has been denied.");
            }
        }

        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showNotification("Permission Granted", "Notification permission granted.");
            } else {
                Toast.makeText(this, "Notification permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadInboxMessages() {
        try {
            List<SmsMessageModel> messages = SmsUtils.getAllMessages(this);

            if (messages == null || messages.isEmpty()) {
                showNotification("No Messages", "No messages found in your inbox.");
                return;
            }

            // Filter out blocked numbers
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                messages.removeIf(message -> blocklistManager.isBlocked(message.getSender()));
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

            adapter = new SmsAdapter(this, messages, message -> {
                boolean isSpam = SpamDetector.isSpam(this, message.getBody());

                if (isSpam) {
                    new AlertDialog.Builder(this)
                            .setTitle("Spam Detected!")
                            .setMessage("This message is from a suspected spam number: " + message.getSender() +
                                    ". Would you like to block this number?")
                            .setPositiveButton("Block", (dialog, which) -> {
                                blocklistManager.addToBlocklist(message.getSender());
                                showNotification("Blocked", "Number added to blocklist.");
                                loadInboxMessages(); // Refresh messages
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                } else {
                    showNotification("Message Safe", "This message is not spam.");
                }
            });

            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            showNotification("Error", "Error loading messages: " + e.getMessage());
        }
    }
}
