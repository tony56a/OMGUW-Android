package com.omg.omguw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
	}
	
	public void onDestroy()
	{
		super.onDestroy();
	}
	
	public void onPause()
	{
		super.onPause();
	}
	
	public void onResume()
	{
		super.onResume();
	}
	
	public void onClickOMG(View v)
	{
		Intent omgIntent = new Intent(getApplicationContext(),
				AbcdActivity.class);
		omgIntent.putExtra("URL", "http://www.omguw.com/feeds/posts/default");
		omgIntent.putExtra("TYPE", 1);
		startActivity(omgIntent);
	}
	
	public void onClickMC(View v)
	{
		Intent omgIntent = new Intent(getApplicationContext(),
				AbcdActivity.class);
		omgIntent.putExtra("URL", "http://omguwmissedconnections.blogspot.com/feeds/posts/default");
		omgIntent.putExtra("TYPE", 2);
		startActivity(omgIntent);
	}
	
	public void onClickILU(View v)
	{
		Intent omgIntent = new Intent(getApplicationContext(),
				AbcdActivity.class);
		omgIntent.putExtra("URL", "http://omguwilu.blogspot.com/feeds/posts/default");
		omgIntent.putExtra("TYPE", 3);
		startActivity(omgIntent);
	}
	
	public void onClickOH(View v)
	{
		Intent omgIntent = new Intent(getApplicationContext(),
				AbcdActivity.class);
		omgIntent.putExtra("URL", "http://omguwoh.blogspot.com/feeds/posts/default");
		omgIntent.putExtra("TYPE", 4);
		startActivity(omgIntent);
	}
	
	public void onClickAsk(View v)
	{
		Intent omgIntent = new Intent(getApplicationContext(),
				AbcdActivity.class);
		omgIntent.putExtra("URL", "http://omguwask.blogspot.com/feeds/posts/default");
		omgIntent.putExtra("TYPE", 5);
		startActivity(omgIntent);
	}
	
}
