package com.example.phone_book.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.example.phone_book.R;
import com.example.phone_book.SecondActivity;
import com.google.android.material.slider.BaseOnChangeListener;

public class NotificationUtils {

    private static final int REMINDER_NOTIFICATION_ID = 1138;
    private static final int REMINDER_PENDING_INTENT_ID = 3417;
    private static final String REMINDER_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";
    private static Boolean allow_or_not = true;

    public static void remindUserBecauseCharging(Context context) {
        if(allow_or_not!=true){

        }else {


            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel(
                        REMINDER_NOTIFICATION_CHANNEL_ID,
                        context.getString(R.string.main_notification_channel_name),
                        NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(mChannel);
            }

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, REMINDER_NOTIFICATION_CHANNEL_ID)
                    //.setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    //.setLargeIcon(largeIcon(context))
                    .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                    .setContentText(context.getString(R.string.charging_reminder_notification_body))
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(
                            context.getString(R.string.charging_reminder_notification_body)))
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setContentIntent(contentIntent(context))
                    .setAutoCancel(true);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                    && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
            }
            notificationManager.notify(REMINDER_NOTIFICATION_ID, notificationBuilder.build());
        }
    }
    private static PendingIntent contentIntent(Context context) {

        Intent startActivityIntent = new Intent(context, SecondActivity.class);

        return PendingIntent.getActivity(
                context,
                REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
    public static void allow(boolean x){
        allow_or_not = x;
    }
//    private static Bitmap largeIcon(Context context) {
//        // COMPLETED (5) Get a Resources object from the context.
//        Resources res = context.getResources();
//        // COMPLETED (6) Create and return a bitmap using BitmapFactory.decodeResource, passing in the
//        // resources object and R.drawable.ic_local_drink_black_24px
//        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_local_drink_black_24px);
//        return largeIcon;
//    }
}

