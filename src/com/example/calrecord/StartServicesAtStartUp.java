package com.example.calrecord;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;



public class StartServicesAtStartUp extends BroadcastReceiver
{
		public static Intent phoneStateListener;
        public void onReceive(Context context, Intent intent)
        {
                Log.d("DEBUG", "com.its.CallRecorder Initiated ...");
                Start_CallRec(context);
                Log.d("hello", "hello");
                Toast.makeText(context, "hiiiiiiiii", 1000).show();
        }
        
        public static void Start_CallRec(Context context)
        {
        	if(!SharedData._Started )
        	{
        		if(SharedData._AutoStart)
        		{
                  
                    Log.d("DEBUG", "com.its.CallRecorder Call Recorder Started ...");
                    TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    CallStateListener callStateListener = new CallStateListener();
                    tManager.listen(callStateListener,PhoneStateListener.LISTEN_CALL_STATE);
                    SharedData._Started = true;
                    Toast.makeText(context," Call Recording Started ... ", Toast.LENGTH_SHORT).show();
        		}	
        	}
        	else
        	{
                Toast.makeText(context," Call Recording Already Active.. ", Toast.LENGTH_SHORT).show();
        	}
        }

		public static void Stop_CallRec(Context context)
        {
        	if(SharedData._Started )
        	{
        		context.stopService(phoneStateListener);
               	Toast.makeText(context," Call Recording Stopped  ... ", Toast.LENGTH_SHORT).show();
               	SharedData._Started = false;               	
        	}
        	else
        	{
               	Toast.makeText(context," Call Recording Already Stopped  ... ",  Toast.LENGTH_SHORT).show();
        	}
        }

} 