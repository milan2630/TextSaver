package com.example.milan.textsaver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by milan on 4/9/16.
 */




    public class MyBroadcastReceiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent){


            Calendar calendar = Calendar.getInstance();
            if(calendar.get(Calendar.HOUR_OF_DAY) > 15 || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {}

            else
            {
                Intent service1 = new Intent(context, MyAlarmService.class);
                context.startService(service1);

            }
        }
    }

