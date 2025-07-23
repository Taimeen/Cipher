
package com.spam.spamdetection;

        import android.content.Context;
        import android.database.Cursor;
        import android.net.Uri;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;
        import java.util.Locale;

public class SmsUtils {
    public static List<SmsMessageModel> getAllMessages(Context context) {
        List<SmsMessageModel> messages = new ArrayList<>();
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String sender = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                String time = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                long timestamp = Long.parseLong(time); // Convert the string timestamp to long
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
                String formattedTime = sdf.format(new Date(timestamp));
                messages.add(new SmsMessageModel(sender, body, formattedTime));
            } while (cursor.moveToNext());

            cursor.close();
        }
        return messages;
    }
}
