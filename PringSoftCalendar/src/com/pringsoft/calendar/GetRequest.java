package com.pringsoft.calendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class GetRequest extends AsyncTask<String, Void, String>{

	ListEvents ma = null;
	public GetRequest(ListEvents listEvent) {
		this.ma = listEvent;		
	}

	BackgroundService mc = null;
	public GetRequest(BackgroundService backgroundService) {
		this.mc = backgroundService;
	}

	@Override
	protected String doInBackground(String... params) {
		HttpClient httpclient = new DefaultHttpClient();
		Log.i("dataz",Arrays.toString(params));
		HttpGet httppost = new HttpGet("http://192.168.0.100:8080/Tommy/UserController?action=listEvent");
		String body = "";
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(0);
	        nameValuePairs.add(new BasicNameValuePair("action", "listEvent"));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        InputStream in = entity.getContent();
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String temp;
	        while ((temp = br.readLine())!=null)
	        	body += temp;
	        Log.i("hi",body);
	        
	        if (body.isEmpty())
	        	body = "Message empty with status code: " + String.valueOf(response.getStatusLine().getStatusCode());
	        return body;
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        body = "Can't connect to server";
	    }
		return body;
	}

	
	protected void onPostExecute(String result) {
		if(ma!=null)
			ma.done(result);
        if(mc!=null)
        	mc.done(result);
    }

}
