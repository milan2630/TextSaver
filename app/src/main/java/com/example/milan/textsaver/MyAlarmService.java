package com.example.milan.textsaver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

/**
 * Created by milan on 4/9/16.
 */
public class MyAlarmService extends Service {


    public static final String MyPREFERENCES = "MyPrefs11";
    public static final String PNUM_KEY = "numKey11";
    SharedPreferences sharedpreferences;


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
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();


            SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
            int pNum = (shared.getInt(PNUM_KEY, 1));
            String n = new String(pNum + " APUSH");
            if(pNum < 9) {
                editor.putInt(PNUM_KEY, pNum + 1);
            }
            else
            {
                editor.putInt(PNUM_KEY, 1);
            }
            editor.commit();
            Intent openHome = TextSaver.sendToAssignmentActivity(this.getApplicationContext(), n);

            PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, openHome, PendingIntent.FLAG_CANCEL_CURRENT);
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
