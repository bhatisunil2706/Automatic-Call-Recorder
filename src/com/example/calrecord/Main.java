package com.example.calrecord;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class Main extends Activity
{
   	Button btn_start;
       /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState); setContentView(R.layout.main);
    	btn_start = (Button) findViewById(R.id.btn_start);
    	UpdateRecorderState();
        btn_start.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {              
//            	Toast.makeText(getBaseContext(),"Please enter both phone number and message.", 
//              Toast.LENGTH_SHORT).show();
            	if(!SharedData._Started) 
            	{ 
            		StartServicesAtStartUp.Start_CallRec(getBaseContext()); 
            	}
            	else 
            	{
            		StartServicesAtStartUp.Stop_CallRec(getBaseContext());
            	}
            	UpdateRecorderState();
            }
        });        

    	
    }

	private void UpdateRecorderState() 
	{
		if(SharedData._Started)
    	{
			btn_start.setText("Stop Recording");
			}
    	else
    	{
    		btn_start.setText("Start Recording");
    		}
	}
}