package com.spam.spamdetection;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class BlocklistManager {
    private static final String PREFS_NAME = "blocklist_prefs";
    private static final String BLOCKLIST_KEY = "blocked_numbers";

    private SharedPreferences sharedPreferences;

    public BlocklistManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void addToBlocklist(String number) {
        Set<String> blocklist = getBlocklist();
        blocklist.add(number);
        saveBlocklist(blocklist);
    }

    public void removeFromBlocklist(String number) {
        Set<String> blocklist = getBlocklist();
        blocklist.remove(number);
        saveBlocklist(blocklist);
    }

    public boolean isBlocked(String number) {
        return getBlocklist().contains(number);
    }

    public Set<String> getBlocklist() {
        return sharedPreferences.getStringSet(BLOCKLIST_KEY, new HashSet<>());
    }

    private void saveBlocklist(Set<String> blocklist) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(BLOCKLIST_KEY, blocklist);
        editor.apply();
    }
}
