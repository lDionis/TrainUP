package com.ldionis.trainupapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by PC on 04.06.2016.
 */
public class Notification_receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeating_intent = new Intent(context,WaterControllActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder =new NotificationCompat.Builder(context);
                builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_bubble_chart_white_24dp);
        builder.setContentTitle("Нагадуємо!");
        builder.setContentText("Час поповнити організм водою!");
        builder.setAutoCancel(true);
        notificationManager.notify(100,builder.build());

    }
}