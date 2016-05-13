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
            int hour;
            int minute;
            Calendar calendar = Calendar.getInstance();
            //Intent service1 = new Intent(context, MyAlarmService.class);
            //context.startService(service1);
            if(calendar.get(Calendar.HOUR_OF_DAY) > 15 || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        editor.putInt(HOUR, 8);
                        editor.putInt(MINUTE, 10);
                        hour = sharedpreferences.getInt(HOUR, 8);
                        minute = sharepreferences.getInt(MINUTE, 10);
                        AlarmManager manager = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);
                        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        //reset to 8:10 and setalarm
                    }

            else     //THIS PART IS NEEDED DO NOT DELETE IT
            {
                Intent service1 = new Intent(context, MyAlarmService.class);
                context.startService(service1);
                hour = sharedpreferences.getInt(HOUR, 8);
                minute = sharedpreferences.getInt(MINUTE, 10);
                minute += 47;
                editor.putInt(MINUTE, minute);
                AlarmManager manager = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                //minute +=47
                //setalarmagain
            }
        }
    }

