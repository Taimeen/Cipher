package com.spam.spamdetection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.SmsViewHolder> {

    private final Context context;
    private final List<SmsMessageModel> messages;
    private final OnMessageClickListener onMessageClickListener;

    public SmsAdapter(Context context, List<SmsMessageModel> messages, OnMessageClickListener listener) {
        this.context = context;
        this.messages = messages;
        this.onMessageClickListener = listener;
    }

    @NonNull
    @Override
    public SmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new SmsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmsViewHolder holder, int position) {
        SmsMessageModel message = messages.get(position);
        holder.senderTextView.setText("Sender: " + message.getSender());
        holder.bodyTextView.setText(message.getBody());
        holder.timeTextView.setText(message.getTime());


        holder.itemView.setOnClickListener(v -> onMessageClickListener.onMessageClick(message));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class SmsViewHolder extends RecyclerView.ViewHolder {
        TextView senderTextView, bodyTextView, timeTextView;

        public SmsViewHolder(@NonNull View itemView) {
            super(itemView);
            senderTextView = itemView.findViewById(R.id.senderTextView);
            bodyTextView = itemView.findViewById(R.id.bodyTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
        }
    }

    public interface OnMessageClickListener {
        void onMessageClick(SmsMessageModel message);
    }
}
