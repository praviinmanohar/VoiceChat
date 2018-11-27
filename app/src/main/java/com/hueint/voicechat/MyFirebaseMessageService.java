package com.hueint.voicechat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by appit on 15/7/17.
 */

public class MyFirebaseMessageService extends FirebaseMessagingService {

    public MyFirebaseMessageService(){

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

       // sendNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());


    }

    private void sendNotification(String MessageBody, String title){

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("_TITLE", title);
        intent.putExtra("_BODY", MessageBody);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,23,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultring = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder noticationBuilder = new NotificationCompat.Builder(this);
        noticationBuilder.setContentTitle(title);
        noticationBuilder.setContentText(MessageBody);
        noticationBuilder.setAutoCancel(true);
        noticationBuilder.setSound(defaultring);
        noticationBuilder.setContentIntent(pendingIntent);


        NotificationManager notificationManager =  (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(23,noticationBuilder.build());

    }
}
