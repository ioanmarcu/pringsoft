package com.pringsoft.calendar;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class BackgroundService extends IntentService {

	SharedPreferences prefs;
	String dateTimeKey = "dataz";
	
	private int mInterval = 5000; // 5 seconds by default, can be changed later

	public BackgroundService() {
		super("BackgroundService");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.e("baa","cf2");
		Context ctx = getApplicationContext();
		prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		return super.onStartCommand(intent, flags, startId);
	}
		
	@Override
    protected void onHandleIntent(Intent workIntent) {
		Log.e("baa","cf3");
		long endTime = System.currentTimeMillis() + 5*1000;
		while (true)
	      while (System.currentTimeMillis() < endTime) {
	    	  Log.e("baa","s1");
	          synchronized (this) {
	              try {
	            	  Log.e("baa","s2");
	          		  new GetRequest(this).execute();
	                  wait(endTime - System.currentTimeMillis());
	              } catch (Exception e) {
	            	  Log.e("baa","s3" + e.getMessage());
	              }
	              Log.e("baa","s4");
	          }
	          Log.e("baa","s5");
	      }

    }

	public void done(String response) {
		setMessage(response);		
	}
	public void toastMe(String message)
	 {
	  Context context = getApplicationContext();
	  int duration = Toast.LENGTH_SHORT;
	  Toast toast = Toast.makeText(context, message, duration);
	  toast.show();
	 }
	private void setMessage(String message) {
		if (prefs == null)
			Log.wtf("baa", "prefs ii null");
		Log.e("baa", "cf4");
		toastMe(message);
		prefs.edit().putString(dateTimeKey, message).commit();
		
	}



}
