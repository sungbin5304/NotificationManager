# NotificationManager
NotificationManager for Android

## How to use?
1. Set Group name
   ``` kotlin
   NotificationManager.setGroupName("This is my Group.");
   ```

2. Create Notificaton Channel
   ``` kotlin
   NotificationManager.createChannel(getApplicationContext(), "Channel Name", "Channal Description");
   ```
   
3. Make Notification
   ``` kotlin
   NotificationManager.showNormalNotification(getApplicationContext(), 1, "Title", "Normal Notification");

   Or
   
   String[] box = new String[6];
   box[0] = "One";
   box[1] = "Two";
   box[2] = "Three";
   box[3] = "Four";
   box[4] = "Five";
   box[5] = "Six";
   NotificationManager.showInboxStyleNotification(getApplicationContext(), 2, "Title", "InBoxStyle Notification", box);
   ```
   
<hr/>

## Example
``` kotlin
NotificationManager.setGroupName("SungStarBook")
NotificationManager.createChannel(applicationContext, "Message Notification", "SungStarBook Message Notification")
NotificationManager.showNormalNotification(applicationContext, 1, remoteMessage!!.data["title"]!!, remoteMessage.data["message"]!!)
```

## All Methods
``` kotlin
@void setGroupName(String name)
@void showNormalNotification(Context context, int id, String title, String content)
@void showInboxStyleNotification(Context context, int id, String title, String content, String[] boxText)
@void deleteNotification(Context context, int id)
@int getSmallIcon()
```
