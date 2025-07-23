package com.spam.spamdetection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private static final int SMS_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // App Introduction
        String introText = "SMS Reader uses AI and Machine Learning to detect spam messages and keep you cybersafe. Navigate the app to explore its features.";
        Toast.makeText(this, introText, Toast.LENGTH_LONG).show();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.POST_NOTIFICATIONS
            }, SMS_PERMISSION_CODE);
        }

        SpamDetector.initialize(this);  // Load model and vectorizer on app start

        // Buttons and Navigation
        Button openCheckMessageButton = findViewById(R.id.openCheckMessageActivity);
        openCheckMessageButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CheckMessageActivity.class);
            startActivity(intent);
        });

        Button openInbox = findViewById(R.id.inboxMessages);
        openInbox.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InboxActivity.class);
            startActivity(intent);
        });

        Button openTeamPageButton = findViewById(R.id.openTeamPage);
        openTeamPageButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TeamActivity.class);
            startActivity(intent);
        });
    }


}
