package com.example.calrecord;

import android.media.MediaRecorder;

final public class SharedData
{
	
	static int _Rec_Type = android.media.MediaRecorder.AudioSource.VOICE_CALL  ;

	
	static String _Path = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/AndroidCAllRECORD/";
    static boolean _Started = false;
    static boolean _AutoStart = true;
    static boolean _Recording = false;
    
    static MediaRecorder _recorder = new MediaRecorder();
    static MediaRecorder _recorder_down = new MediaRecorder();
    static MediaRecorder _recorder_up = new MediaRecorder();
    
    SharedData() {    }
}
