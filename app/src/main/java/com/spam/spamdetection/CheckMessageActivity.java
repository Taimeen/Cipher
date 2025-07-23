package com.spam.spamdetection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CheckMessageActivity extends AppCompatActivity {
    private EditText inputMessage;
    private Button checkButton, clearButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_message);

        inputMessage = findViewById(R.id.inputMessage);
        checkButton = findViewById(R.id.checkButton);
        clearButton = findViewById(R.id.clearButton);
        resultText = findViewById(R.id.resultText);

        // Check Button Logic
        checkButton.setOnClickListener(v -> {
            String message = inputMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                boolean isSpam = SpamDetector.isSpam(CheckMessageActivity.this, message);
                resultText.setVisibility(View.VISIBLE);
                resultText.setText(isSpam ? "Spam Detected!" : "Message is Safe.");
                resultText.setTextColor(getResources().getColor(isSpam ? android.R.color.holo_red_dark : android.R.color.holo_green_dark));
            } else {
                resultText.setVisibility(View.VISIBLE);
                resultText.setText("Please enter a message.");
                resultText.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
            }
        });

        // Clear Button Logic
        clearButton.setOnClickListener(v -> {
            inputMessage.setText(""); // Clear the input field
            resultText.setVisibility(View.GONE); // Hide the result text
        });
    }
}
