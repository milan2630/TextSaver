package com.example.milan.textsaver;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
        Button m = (Button) v;

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String temp = new String(m.getText().toString());
        String pNum = new String(temp.substring(temp.indexOf(":") - 1, temp.indexOf(":")));
        String pName = new String(temp.substring(indexOfSecondSpace(temp) + 1));
        intent.putExtra(STOREPERIOD, pNum + " " + pName);
        startActivity(intent);
    }

    //helper method for onClick
    public int indexOfSecondSpace(String inString) {
        String x = new String(inString.substring(inString.indexOf(" ") + 1));
        int temp = inString.indexOf(" ") + 1;
        return temp + x.indexOf(" ");
    }

    private void setAlarm() {
        Intent notificationIntent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager manager = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
        Calendar Blubber = Calendar.getInstance();
        int hour = 8;
        int minute = 9;
        Blubber.setTimeInMillis(System.currentTimeMillis());
        if (Blubber.get(Calendar.HOUR_OF_DAY) > hour || (Blubber.get(Calendar.HOUR_OF_DAY) == hour
                && Blubber.get(Calendar.MINUTE) > minute))
        {
            if (Blubber.get(Calendar.DAY_OF_YEAR) != 365 && Blubber.get(Calendar.DAY_OF_YEAR) != 366)
            {
                Blubber.set(Calendar.DAY_OF_YEAR, Blubber.get(Calendar.DAY_OF_YEAR) + 1);
            }
            else
            {
                Blubber.set(Calendar.DAY_OF_YEAR, 1);
                Blubber.set(Calendar.YEAR, Blubber.get(Calendar.YEAR) + 1);
            }
        }
        Blubber.set(Calendar.HOUR_OF_DAY, hour);
        Blubber.set(Calendar.MINUTE, minute);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, Blubber.getTimeInMillis(), 48 * 60 * 1000, pendingIntent);

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
