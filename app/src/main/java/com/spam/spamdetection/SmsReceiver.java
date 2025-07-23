package com.spam.spamdetection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                StringBuilder messageContent = new StringBuilder();
                for (Object pdu : pdus) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                    messageContent.append(sms.getMessageBody());
                }
                String message = messageContent.toString();
                boolean isSpam = SpamDetector.isSpam(context, message);
                Toast.makeText(context, isSpam ? "Spam Detected" : "Message is Safe", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
