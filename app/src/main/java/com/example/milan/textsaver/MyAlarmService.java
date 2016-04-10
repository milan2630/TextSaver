package com.example.milan.textsaver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.os.IBinder;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
/**
 * Created by milan on 4/9/16.
 */
public class MyAlarmService extends Service {


        @Override
        public IBinder onBind (Intent arg0){
            return null;
        }

        @Override
        public void onCreate(){
            super.onCreate();
        }

        @SuppressWarnings("static-access")
        @Override
        public int onStartCommand(Intent intent, int flags, int startId){
            Intent openHome = new Intent(this.getApplicationContext(), TextSaver.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, openHome, 0);
            NotificationManager notificationManager = (NotificationManager) this.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder bob = new NotificationCompat.Builder(this.getApplicationContext());
            bob.setContentIntent(pendingIntent);
            bob.setSmallIcon(R.drawable.books);
            bob.setContentTitle("Homework Check");
            bob.setContentText("Did you have any homework?");
            notificationManager.notify(11, bob.build());
            return START_NOT_STICKY;
        }

        @Override
        public void onDestroy(){
            super.onDestroy();
        }


}
