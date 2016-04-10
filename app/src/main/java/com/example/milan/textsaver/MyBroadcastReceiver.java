package com.example.milan.textsaver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by milan on 4/9/16.
 */




    public class MyBroadcastReceiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent){

            Intent service1 = new Intent(context, MyAlarmService.class);
            context.startService(service1);


        }
    }

