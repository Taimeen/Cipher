package com.spam.spamdetection;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private List<TeamMember> teamMembers;

    public TeamAdapter(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_member_item, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        TeamMember member = teamMembers.get(position);
        holder.name.setText(member.getName());
        holder.usn.setText("USN: " + member.getUsn());
        holder.image.setImageResource(member.getImageResId());
        holder.linkedIn.setText("Visit LinkedIn Profile");
        holder.linkedIn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(member.getLinkedInUrl()));
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return teamMembers.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView name, usn, linkedIn;
        ImageView image;

        public TeamViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.memberName);
            usn = itemView.findViewById(R.id.memberUSN);
            image = itemView.findViewById(R.id.memberImage);
            linkedIn = itemView.findViewById(R.id.memberLinkedIn);
        }}
}
