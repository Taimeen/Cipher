package com.spam.spamdetection;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private List<TeamMember> teamMemberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        recyclerView = findViewById(R.id.teamRecyclerView);

        // Create a list of team members
        teamMemberList = new ArrayList<>();
        teamMemberList.add(new TeamMember("Taimeen", "1B021CS062", R.drawable.logo, "https://www.linkedin.com/in/taimeen-bhanu-mulla-56807b293?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"));
        teamMemberList.add(new TeamMember("Shruthi", "USN: 1B021CS050", R.drawable.logo, "https://www.linkedin.com/in/shruthi-r-20296a306?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"));
        teamMemberList.add(new TeamMember("Shwetha", "USN: 1B021CS052", R.drawable.logo, "https://www.linkedin.com/in/shwetha-s-96865b337?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"));
        teamMemberList.add(new TeamMember("Tejaswini", "USN: 1B021CS063", R.drawable.logo,"https://www.linkedin.com/in/teja-swini-961885323?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"));

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        teamAdapter = new TeamAdapter(teamMemberList);
        recyclerView.setAdapter(teamAdapter);
    }
}
