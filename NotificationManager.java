package com.sungbin.kakaotalk.bot;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.StringDef;
import android.support.v4.app.NotificationManagerCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NotificationManager {

    /**
     * Created by SungBin on 2018. 01. 07.
     */

    private static String GROUP_NAME = "undefined";

    public static void setGroupName(String name){
        GROUP_NAME = name;
    }

    public static void createChannel(Context context, String name, String description) {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            NotificationChannelGroup group1 = new NotificationChannelGroup(GROUP_NAME, GROUP_NAME);
            getManager(context).createNotificationChannelGroup(group1);

            NotificationChannel channelMessage = new NotificationChannel(Channel.NAME, name, android.app.NotificationManager.IMPORTANCE_DEFAULT);
            channelMessage.setDescription(description);
            channelMessage.setGroup(GROUP_NAME);
            channelMessage.setLightColor(Color.parseColor("#42a5f5"));
            channelMessage.enableVibration(true);
            channelMessage.setVibrationPattern(new long[]{0, 0});
            getManager(context).createNotificationChannel(channelMessage);
        }
    }

    private static android.app.NotificationManager getManager(Context context) {
        return (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static void showNormalNotification(Context context, int id, String title, String content) {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            Notification.Builder builder = new Notification.Builder(context, Channel.NAME)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true)
                    .setOngoing(true);
                getManager(context).notify(id, builder.build());
        }
        else{
            Notification.Builder builder = new Notification.Builder(context)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true)
                    .setOngoing(true);
            getManager(context).notify(id, builder.build());
        }
    }

    public static void showInboxStyleNotification(Context context, int id, String title, String content, String[] boxText) {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            Notification.Builder builder = new Notification.Builder(context, Channel.NAME)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true)
                    .setOngoing(true);
            Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
            inboxStyle.setBigContentTitle(title);
            inboxStyle.setSummaryText(content);

            for(String str : boxText) {
                inboxStyle.addLine(str);
            }

            builder.setStyle(inboxStyle);

            getManager(context).notify(id, builder.build());
        }
        else{
            Notification.Builder builder = new Notification.Builder(context)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true)
                    .setOngoing(true);
            Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
            inboxStyle.setBigContentTitle(title);
            inboxStyle.setSummaryText(content);

            for (String str : boxText) {
                inboxStyle.addLine(str);
            }

            builder.setStyle(inboxStyle);

            getManager(context).notify(id, builder.build());
        }
    }

    public static void deleteNotification(Context context, int id){
        NotificationManagerCompat.from(context).cancel(id);
    }

    private static int getSmallIcon() {
        return R.drawable.icon;
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            Channel.NAME
    })
    public @interface Channel {
        String NAME = "CHANNEL";
    }

}