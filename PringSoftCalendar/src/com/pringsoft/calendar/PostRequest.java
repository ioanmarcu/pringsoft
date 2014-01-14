package com.pringsoft.calendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.os.AsyncTask;
import android.util.Log;

public class PostRequest extends AsyncTask<String, Void, String> {
	
	NewEvent ma = null;
	public PostRequest(NewEvent newEvent) {
		this.ma = newEvent;
	}
	
	@Override
	protected String doInBackground(String... params) {
		HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://192.168.0.102:8080/Tommy/UserController");

	    String body = "";
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	        nameValuePairs.add(new BasicNameValuePair("json", params[0]));
	        nameValuePairs.add(new BasicNameValuePair("action", "addEvent"));
	        
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        InputStream in = entity.getContent();
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        
	        String temp;
	        while ((temp = br.readLine())!=null)
	        	body += temp;
	        
	        if (body.isEmpty())
	        	body = "Message empty with status code: " + String.valueOf(response.getStatusLine().getStatusCode());
	        Log.i("dataz",body);
	        return body;
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        body = "Could not connect to server!";
	    }
		return body;
	}
	
	protected void onPostExecute(String result) {
        ma.done(result);
    }

}
