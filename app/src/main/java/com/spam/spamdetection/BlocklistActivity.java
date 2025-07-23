package com.spam.spamdetection;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;

public class BlocklistActivity extends AppCompatActivity {
    private BlocklistManager blocklistManager;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocklist);

        blocklistManager = new BlocklistManager(this);

        ListView listView = findViewById(R.id.blocklistView);
        Set<String> blocklist = blocklistManager.getBlocklist();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(blocklist));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String number = adapter.getItem(position);
            blocklistManager.removeFromBlocklist(number);
            Toast.makeText(this, "Number removed from blocklist", Toast.LENGTH_SHORT).show();
            refreshBlocklist();
        });
    }

    private void refreshBlocklist() {
        Set<String> blocklist = blocklistManager.getBlocklist();
        adapter.clear();
        adapter.addAll(blocklist);
        adapter.notifyDataSetChanged();
    }
}
