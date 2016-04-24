package com.example.milan.textsaver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DisplayMessageActivity extends AppCompatActivity {

    private static final String STOREASSIGNMENT = "assignment.txt";// text file storing the assignment
    String n = new String();

    private EditText assigTxt; // declares an editable textbox
    private TextView p1;// declares a text object, un-editable
    private TextView p2;// declares a text object, un-editable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assigTxt = (EditText)findViewById(R.id.assigInput);//initializes editable textbox
        p1 = (TextView) findViewById(R.id.assignmentFin);//initializes non editable text object


        if (savedInstanceState == null) {                                           //This code sets the String n
            Bundle extras = getIntent().getExtras();                                //equal to whatever was passed in when the
            if (extras == null) {                                                   //activity was created
                n = null;                                                           //
            }                                                                       //I passed in the period number and the
            else {                                                                  //subject and the string n is later used
                n = extras.getString(TextSaver.STOREPERIOD);                        //as the header to the page
            }                                                                       //
        }                                                                           //
        else {                                                                      //
            n = (String) savedInstanceState.getSerializable(TextSaver.STOREPERIOD); //
        }                                                                           //


        p2 = (TextView) findViewById(R.id.textView2);//initializes non editable text object
        p2.setText("Period " + n.substring(0,1));//sets the header of the page to the String n (the period number and subject)

        fillAssig();
    }


    //method called on the click of the button "Save"
    //saves the text inputted into the textbox to the text file
    public void saveAssig(View v)
    {
        try {
            OutputStreamWriter sendOut = new OutputStreamWriter(openFileOutput(STOREASSIGNMENT +n.substring(0,1), 0));
            sendOut.write(assigTxt.getText().toString());
            sendOut.close();
        }
        catch(Throwable t)
        {}

        p1.setText(assigTxt.getText().toString());//sets the text to also appear in the non editable text box
        assigTxt.setVisibility(View.INVISIBLE);//makes the editable textbox disappear
        p1.setVisibility(View.VISIBLE);//makes the non editable text object appear, these last couple lines are to make it look more official

    }

    //method called on click of button "Edit"
    //editable textbox appears, non editable disappears
    public void enableEdit(View v)
    {
        p1.setVisibility(View.INVISIBLE);
        assigTxt.setVisibility(View.VISIBLE);

    }

    //method called on creation of activity
    //fills the assigment with whatever was in there before (gets the text from the text file and puts it in the text box)
    public void fillAssig()
    {

        try{
            InputStream in = openFileInput(STOREASSIGNMENT + n.substring(0,1));

            InputStreamReader tmp=new InputStreamReader(in);

            BufferedReader reader=new BufferedReader(tmp);

            String str;

            StringBuilder buf=new StringBuilder();

            while ((str = reader.readLine()) != null) {

                buf.append(str+" ");

            }

            in.close();

            p1.setText(buf.toString());
            assigTxt.setText(buf.toString());
            p1.setVisibility(View.INVISIBLE);
            assigTxt.setVisibility(View.VISIBLE);
        }
        catch (Throwable t){}
    }

    public void clearText(View v)
    {
        try {
            OutputStreamWriter sendOut = new OutputStreamWriter(openFileOutput(STOREASSIGNMENT +n.substring(0,1), 0));
            sendOut.write("");
            sendOut.close();
        }
        catch(Throwable t)
        {}
        assigTxt.setText("");
        p1.setText("");
        p1.setVisibility(View.INVISIBLE);
        assigTxt.setVisibility(View.VISIBLE);
    }




}
