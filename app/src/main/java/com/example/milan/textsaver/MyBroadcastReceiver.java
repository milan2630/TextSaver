package com.example.milan.textsaver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by milan on 4/9/16.
 */




    public class MyBroadcastReceiver extends BroadcastReceiver {
        public static final String MyPREFERENCES = "MyPrefs11";
        public static final String HOUR = "NOTIF_HOUR";
        public static final String MINUTE = "NOTIF_MINUTE";
        
        SharedPreferences sharedpreferences;
        public void onReceive(Context context, Intent intent){
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            int hour = 8;
            int minute = 10;
            Calendar calendar = Calendar.getInstance();
            //Intent service1 = new Intent(context, MyAlarmService.class);
            //context.startService(service1);
            if(calendar.get(Calendar.HOUR_OF_DAY) > 15 || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        hour = sharedpreferences.putInt(HOUR, 8);
                        hour = sharedpreferences.putInt(HOUR, 8);
                        
                        //reset to 8:10 and setalarm
                    }

            else     //THIS PART IS NEEDED DO NOT DELETE IT
            {
                Intent service1 = new Intent(context, MyAlarmService.class);
                context.startService(service1);
                //minute +=47
                //setalarmagain
            }
        }
    }

