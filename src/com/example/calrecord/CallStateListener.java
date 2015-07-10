package com.example.calrecord;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.media.MediaRecorder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

//import com.lumitrend.netlogger.Logger;

public class CallStateListener extends PhoneStateListener {
	public void onCallStateChanged(int state, String incomingNumber)
	{
		super.onCallStateChanged(state, incomingNumber);
		switch(state)
		{
		case TelephonyManager.CALL_STATE_IDLE:
			if(SharedData._Recording) 
				{ 
					Recorders_Stop(); 
				}
			break;
		case TelephonyManager.CALL_STATE_RINGING:
			break;
		case TelephonyManager.CALL_STATE_OFFHOOK:
		{
			String CallDate = SanityDate();
			String CallNum = SanityNum(incomingNumber);
			String RootDir = SharedData._Path ;  
			String CallDir = SharedData._Path + CallNum + "/" ;
			String CallFile = SharedData._Path +  CallNum + "/" + CallNum + "-" + CallDate ;
			if(!SharedData._Recording)
			{
				SharedData._Recording = true;
				String med_state = android.os.Environment.getExternalStorageState();
				if(!med_state.equals(android.os.Environment.MEDIA_MOUNTED))
					{ 
					break;
					}

				File directory = null;
				directory = new File(RootDir + "text.txt" ).getParentFile();
				if (!directory.exists() && !directory.mkdirs())
					{
					break;
					}

				directory = new File(CallDir + "text.txt" ).getParentFile();
				if (!directory.exists() && !directory.mkdirs())
					{ 
					break; 
					
					}
				}

				Recoders_Init(CallFile);
				Recorder_Prepare();
			
			Log.v("DEBUG", TelephonyManager.CALL_STATE_OFFHOOK + " ITS.CallRecorder - Recording Started " + state);
			break;
		}
		}
	}

	private String SanityDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd-HHmmss");
		Date currentTime_1 = new Date();
		return formatter.format(currentTime_1);
	}

	private void Recorders_Stop() {
		try { 
			SharedData._recorder.stop(); SharedData._recorder.reset();
			//SharedData._recorder_down.stop(); SharedData._recorder_down.reset();
			//SharedData._recorder_up.stop();	SharedData._recorder_up.reset(); 
			}
		catch (IllegalStateException e) {}
		SharedData._Recording = false;
	}

	private void Recorder_Prepare() {
		try {
			SharedData._recorder.prepare(); SharedData._recorder.start(); 
			//SharedData._recorder_down.prepare(); SharedData._recorder_down.start(); 
			//SharedData._recorder_up.prepare(); SharedData._recorder_up.start(); 
			}
		catch (IllegalStateException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
	}

	private void Recoders_Init(String path) {
		String _ext = ".3gp";
		int out_format = MediaRecorder.OutputFormat.THREE_GPP;
		
		SharedData._recorder.setAudioSource(SharedData._Rec_Type);
		SharedData._recorder.setOutputFormat(out_format);
		SharedData._recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		SharedData._recorder.setOutputFile(path + "-both" + _ext);
		
		
		
	}
	private String SanityNum(String numstr)
	{
		String out = "";
		for(char ch : numstr.toCharArray())
		{
			switch(ch)
			{
			case ' ': 
				break;
			case '~': 
				break;
			case '!': 
				break;
			case '@': 
				break;
			case '#': 
				break;
			case '$': 
				break;
			case '%': 
				break;
			case '^': 
				break;
			case '&': 
				break;
			case '*': 
				break;
			case '(': 
				break;
			case ')': 
				break;
			case '-': 
				break;
			case '_': 
				break;
			case '=': 
				break;
			case '|': 
				break;
			default:
				out = out + ch;
			}
		}
		return out;
	}
} 