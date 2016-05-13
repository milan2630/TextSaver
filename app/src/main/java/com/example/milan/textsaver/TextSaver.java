package com.example.milan.textsaver;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;


public class TextSaver extends AppCompatActivity implements View.OnClickListener {

    public final static String STOREPERIOD = "j";//text file the period number and name is saved to

    Button period1, period2, period3, period4, period5, period6, period7, period8, period9; // declaration of button objects


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_saver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        period1 = (Button) this.findViewById(R.id.button);//button variable initialized
        period1.setOnClickListener(this);//allows the computer to know when a button is clicked
        period2 = (Button) this.findViewById(R.id.button2);
        period2.setOnClickListener(this);
        period3 = (Button) this.findViewById(R.id.button3);
        period3.setOnClickListener(this);
        period4 = (Button) this.findViewById(R.id.button4);
        period4.setOnClickListener(this);
        period5 = (Button) this.findViewById(R.id.button5);
        period5.setOnClickListener(this);
        period6 = (Button) this.findViewById(R.id.button6);
        period6.setOnClickListener(this);
        period7 = (Button) this.findViewById(R.id.button7);
        period7.setOnClickListener(this);
        period8 = (Button) this.findViewById(R.id.button8);
        period8.setOnClickListener(this);
        period9 = (Button) this.findViewById(R.id.button9);
        period9.setOnClickListener(this);
        setAlarm();





    }

    //method called when a button is clicked
    //sends you to a new activity, the DisplayMessageActivity
    //most of the code is used to pass in the period number and subject of the button that was clicked
    public void onClick(View v) {
        Button clicked = (Button) v;
        Intent x = sendToAssignmentActivity(this, parseInfoFromButtonText(clicked));
        startActivity(x);
    }

    public static Intent sendToAssignmentActivity(Context x, String extras)
    {
        Intent intent = new Intent(x, DisplayMessageActivity.class);
        intent.putExtra(TextSaver.STOREPERIOD, extras);
        return intent;
    }

    public static String parseInfoFromButtonText(Button button)
    {
        String temp = new String(button.getText().toString());
        String pNum = new String(temp.substring(temp.indexOf(":") - 1, temp.indexOf(":")));
        String pName = new String(temp.substring(indexOfSecondSpace(temp) + 1));
        return pNum + " " + pName;
    }

    //helper method for onClick
    public static int indexOfSecondSpace(String inString) {
        String x = new String(inString.substring(inString.indexOf(" ") + 1));
        int temp = inString.indexOf(" ") + 1;
        return temp + x.indexOf(" ");
    }

    private void setAlarm() {
        Intent notificationIntent = new Intent(this, MyBroadcastReceiver.class);
        //This intent states that, depending which action it is linked to, will run code in the MyBroadcast class
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        //This is basically saying that the system should wait for something to occur and when it does it should run that the NotificationIntent
        AlarmManager manager = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 46 * 60 * 1000, pendingIntent); // sets the alarm to go off every 47 minutes
        //^ This alarm is later cancelled after 3 o'clock and starts again at 8:09 the next day
        //Look at MyBroadcast Receiver class if interested, still working on it at the moment
        //take out junk and just setalarm to 8:10 once make sharedpreferences so it only works once boolean
    }

    public static int firstTimeAfter(int timeStart, int interval, int time, Context context)
    {
        int temp = timeStart;
            if((temp + interval) % 100 < 60)
            {
                temp += interval;
            }
            else
            {
                int add = ((temp % 100) + interval) - 60;
                temp = (((temp + 100) / 100) * 100) + add;
            }
        return temp;
    }

    public static int letsTry(int timeStart, int interval, int time, Context context)
    {
        int temp = timeStart;
        while(temp < time) {
            temp = firstTimeAfter(temp, 30, time, context);
            temp = firstTimeAfter(temp, 16, time, context);
        }
        return temp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_saver, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
