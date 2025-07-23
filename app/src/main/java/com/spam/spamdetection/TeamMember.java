package com.spam.spamdetection;

public class TeamMember {
    private String name;
    private String usn;
    private int imageResId;
    private String linkedInUrl; // Add LinkedIn URL

    public TeamMember(String name, String usn, int imageResId, String linkedInUrl) {
        this.name = name;
        this.usn = usn;
        this.imageResId = imageResId;
        this.linkedInUrl = linkedInUrl;
    }

    public String getName() {
        return name;
    }

    public String getUsn() {
        return usn;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getLinkedInUrl() {
        return linkedInUrl;
    }
}
