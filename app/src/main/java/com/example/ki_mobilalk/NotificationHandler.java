package com.example.ki_mobilalk;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

public class NotificationHandler {
    private static final int NOTIFICATION_ID = 111;
    private static final String CHANNEL_ID = "gasmeter";
    private static final long[] VIBRATION = {1000, 500, 1000, 500};

    private final NotificationManager manager;
    private final Context context;


    public NotificationHandler(Context context) {
        this.context = context;
        this.manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createChannel();
    }

    private void createChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID,
                            "Ez egy GAZ erteseites!!!!",
                            NotificationManager.IMPORTANCE_LOW);
            channel.enableVibration(true);
            channel.setVibrationPattern(VIBRATION);
            channel.setDescription("Nezd meg hogy all a gaz!");
            manager.createNotificationChannel(channel);
        }
    }
    public void sendNotification(String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.gazora)
                .setContentText(message)
                .setColorized(true)
                .setContentTitle("GASMETER");
        manager.notify(NOTIFICATION_ID, builder.build());
    }

    public void cancelNotification() {
        manager.cancel(NOTIFICATION_ID);
    }
}

