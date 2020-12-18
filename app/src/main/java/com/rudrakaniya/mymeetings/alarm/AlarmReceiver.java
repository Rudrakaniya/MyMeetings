package com.rudrakaniya.mymeetings.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.rudrakaniya.mymeetings.R;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmReceiver";
    private static final String CHANNEL_ID = "com.rudrakaniya.mymeetings.channelId";

    private String mLink, mTitle, mMessageText;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        //Extracting data from the intent
        mLink = intent.getStringExtra("link");
        mTitle = intent.getStringExtra("title");
        mMessageText = intent.getStringExtra("messageText");

        Log.d(TAG, "onReceive: Notification Intent received " + mTitle );

        Uri uri = Uri.parse(mLink);
        Intent linkIntent = new Intent(Intent.ACTION_VIEW, uri);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(linkIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context,CHANNEL_ID);

        Notification notification = builder.setContentTitle(mTitle)
                .setContentText(mMessageText)
                .setTicker("New Message Alert!")
                .setSmallIcon(R.mipmap.ic_launcher_app)
                .setTimeoutAfter(1000000)
                .setContentIntent(pendingIntent).build();



        notification.flags = Notification.FLAG_AUTO_CANCEL;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "MyMeetings",
                    IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify((int) System.currentTimeMillis(), notification);

    }
}
