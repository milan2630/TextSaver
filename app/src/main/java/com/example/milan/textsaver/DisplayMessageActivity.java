package com.example.milan.textsaver;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.OutputStreamWriter;

public class DisplayMessageActivity extends AppCompatActivity {

    private static final String STOREASSIGNMENT = "assignment.txt";
    String n = new String();


    private EditText assigTxt;
    private TextView p1;
    private TextView p2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        



        assigTxt = (EditText)findViewById(R.id.assigInput);
        p1 = (TextView) findViewById(R.id.assignmentFin);



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                n = null;
            }
            else
            {
                n = extras.getString(TextSaver.STOREPERIOD);
            }
        }
        else
        {
            n = (String) savedInstanceState.getSerializable(TextSaver.STOREPERIOD);
        }

        p2 = (TextView) findViewById(R.id.textView2);
        p2.setText(n);

        fillAssig();




    }


    public void saveAssig(View v)
    {
        try {
            OutputStreamWriter sendOut = new OutputStreamWriter(openFileOutput(STOREASSIGNMENT
                    + n.substring(n.indexOf(":") - 1, n.indexOf(":")), 0));
            sendOut.write(assigTxt.getText().toString());
            sendOut.close();
        }
        catch(Throwable t)
        {}
        p1.setText(assigTxt.getText().toString());
        assigTxt.setVisibility(View.INVISIBLE);
        p1.setVisibility(View.VISIBLE);

    }

    public void enableEdit(View v)
    {
        p1.setVisibility(View.INVISIBLE);
        assigTxt.setVisibility(View.VISIBLE);
    }

    public void fillAssig()
    {

        try{
            InputStream in = openFileInput(STOREASSIGNMENT + n.substring(0,1));

            InputStreamReader tmp=new InputStreamReader(in);

            BufferedReader reader=new BufferedReader(tmp);

            String str;

            StringBuilder buf=new StringBuilder();

            while ((str = reader.readLine()) != null) {

                buf.append(str+"\n");

            }

            in.close();

            p1.setText(buf.toString());
            assigTxt.setText(buf.toString());
            p1.setVisibility(View.VISIBLE);
            assigTxt.setVisibility(View.INVISIBLE);
        }
        catch (Throwable t){}
    }




}
