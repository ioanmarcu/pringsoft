package com.pringsoft.calendar;



import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		View.OnClickListener handler = new View.OnClickListener(){
	        public void onClick(View v) {
	            
	            switch (v.getId()) {

	                case R.id.btnShowNotification:
	                	showNotification();
	                    break;
	                
	                case R.id.btnCancelNotification:
	                	cancelNotification(0);
	                    break;
	            }
	        }
	    };
	        
	    // we will set the listeners
	    findViewById(R.id.btnShowNotification).setOnClickListener(handler);
	    findViewById(R.id.btnCancelNotification).setOnClickListener(handler);
	}

	@Override	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent mServiceIntent = new Intent(this, BackgroundService.class);
		startService(mServiceIntent);
		return super.onContextItemSelected(item);
	}
	
	public void newEvent(View view) {
		//new PostRequest(this).execute();
		Intent intent = new Intent(this, NewEvent.class);
		startActivity(intent);
	}
	
	public void listEvent(View view) {
		//new PostRequest(this).execute();
		Intent intent = new Intent(this, ListEvents.class);
		startActivity(intent);
	}
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressLint("NewApi")
	public void showNotification(){

		// define sound URI, the sound to be played when there's a notification
		Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		
		// intent triggered, you can add other intent for other actions
		Intent intent = new Intent(this, ListEvents.class);
		PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
		
		// this is it, we'll build the notification!
		// in the addAction method, if you don't want any icon, just set the first param to 0
		Notification mNotification = new Notification.Builder(this)
			
			.setContentTitle("New Post!")
			.setContentText("Here's an awesome update for you!")
			.setSmallIcon(R.drawable.ninja)
			.setContentIntent(pIntent)
			.setSound(soundUri)
			
			.addAction(R.drawable.ninja, "View", pIntent)
			.addAction(0, "Remind", pIntent)
			
			.build();
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// If you want to hide the notification after it was selected, do the code below
		// myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
		
		notificationManager.notify(0, mNotification);
	}
	
	public void cancelNotification(int notificationId){
		
		if (Context.NOTIFICATION_SERVICE!=null) {
            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
            nMgr.cancel(notificationId);
        }
	}
	


}
