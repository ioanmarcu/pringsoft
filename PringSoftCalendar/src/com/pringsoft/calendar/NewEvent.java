package com.pringsoft.calendar;


import com.google.gson.Gson;
import com.model.Event;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.support.v4.app.NavUtils;

public class NewEvent extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_event);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_event, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void postData(View view) {
		String event = ((EditText) findViewById(R.id.event)).getText().toString();
		String date = ((DatePicker) findViewById(R.id.datePicker1)).getDayOfMonth() + "/" + (((DatePicker) findViewById(R.id.datePicker1)).getMonth() + 1) + "/" + ((DatePicker) findViewById(R.id.datePicker1)).getYear() ;
		String time = ((TimePicker) findViewById(R.id.timePicker1)).getCurrentHour() + ":" + ((TimePicker) findViewById(R.id.timePicker1)).getCurrentMinute();
		String location = ((EditText) findViewById(R.id.location)).getText().toString();
		String comment = ((EditText) findViewById(R.id.comment)).getText().toString();
		Event e = new Event();
		e.setNume(event);
    	e.setData(date);
    	e.setOra(time);
    	e.setLocatie(location);
    	e.setComentarii(comment);  
    	Gson gson = new Gson();
    	String json = gson.toJson(e);
		new PostRequest(this).execute(json);
	}
	

	public void done(String response) {
		Log.d("hi",response);
	}
}
