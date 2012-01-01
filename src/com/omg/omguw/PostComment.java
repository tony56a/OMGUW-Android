package com.omg.omguw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostComment extends Activity {

	EditText et1;
	Button btn1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_comment);
		et1 = (EditText) findViewById(R.id.editText2);
		btn1 = (Button) findViewById(R.id.button1);
		
	}
	
	public void submitComment(View v)
	{
		String text= et1.getText().toString();
		Bundle extras = getIntent().getExtras(); 
		String url = extras.getString("URL");
		
		try {
			URL link = new URL(url);
			URLConnection conn = link.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
	        ((HttpURLConnection)conn).setRequestMethod("POST");

	        conn.setRequestProperty("Content-Type", " application/atom+xml");

			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write("<entry xmlns='http://www.w3.org/2005/Atom'><title type=\"text\"></title> <content type=\"html\">"
					+ text + "</content></entry>");
			wr.flush();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line = rd.readLine()) !=null)
			{
				System.out.println(line);
			}
			wr.close();
			rd.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finish();
		
	}

}
