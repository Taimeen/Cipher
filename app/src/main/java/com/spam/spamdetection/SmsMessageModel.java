package com.spam.spamdetection;

public class SmsMessageModel {
    private final String sender;
    private final String body;
    private final String time;

    public SmsMessageModel(String sender, String body,  String time) {
        this.sender = sender;
        this.body = body;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public String getBody() {
        return body;
    }

    public String getTime() {
        return time;
    }
}
