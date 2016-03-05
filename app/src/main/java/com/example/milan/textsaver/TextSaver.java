package com.example.milan.textsaver;

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


public class TextSaver extends AppCompatActivity {

    private final static String STORETEXT="storetext.txt";
    public final static String STOREPERIOD="j";
    private final static String STORETEXT2="storethetext.txt";
    PeriodData p5 = new PeriodData(5, "PreCalc H");

    private EditText txtEditor;
    private EditText txtEditor2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_saver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        txtEditor=(EditText)findViewById(R.id.textbox);
        txtEditor2 = (EditText)findViewById(R.id.textboxNumeroDos);

        readFileInEditor();
        putTextIntoBox2();








    }

    public void saveClicked(View v)
    {
        try {

            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(STORETEXT + p5.periodNum, 0));

            out.write(txtEditor.getText().toString());

            out.close();

            Toast.makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG).show();

        }

        catch (Throwable t) {

            Toast.makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();

        }

    }

    public void save2Clicked(View v)
    {
        try{
            OutputStreamWriter x = new OutputStreamWriter(openFileOutput(STORETEXT2, 0));
            x.write(txtEditor2.getText().toString());
            x.close();
        }
       catch(Throwable t)
       {

       }


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
    public void readFileInEditor()

    {
        try {

            InputStream in = openFileInput(STORETEXT + p5.periodNum);


            if (in != null) {

                InputStreamReader tmp=new InputStreamReader(in);

                BufferedReader reader=new BufferedReader(tmp);

                String str;

                StringBuilder buf=new StringBuilder();

                while ((str = reader.readLine()) != null) {

                    buf.append(str+"\n");

                }

                in.close();

                txtEditor.setText(buf.toString());

            }

        }

        catch (java.io.FileNotFoundException e) {

// that's OK, we probably haven't created it yet

        }

        catch (Throwable t) {

            Toast

                    .makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG)

                    .show();

        }

    }
    public void putTextIntoBox2()
    {
        try
        {
            InputStream n = openFileInput(STORETEXT2);
            if(n != null)
            {
                InputStreamReader temp = new InputStreamReader(n);
                BufferedReader reader = new BufferedReader(temp);
                StringBuilder builder = new StringBuilder();
                String x;
                while((x = reader.readLine()) != null)
                {
                    builder.append(x + "\n");

                }
                txtEditor2.setText(builder);
            }

        }
        catch(Throwable t)
        {

        }
    }

    public void sendMessage(View v)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(STOREPERIOD, p5.periodNum + " " + p5.periodName);
        startActivity(intent);
    }
}
