package com.omg.omguw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author applepie
 *
 */
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
	
	
	/**
	 * Launches the PostList activity to display the 25 most recent OMGs
	 */
	public void onClickOMG(View v)
	{
		Intent omgIntent = new Intent(getApplicationContext(),
				PostList.class);
		omgIntent.putExtra("URL", "http://www.omguw.com/feeds/posts/default");
		omgIntent.putExtra("TYPE", 1);
		startActivity(omgIntent);
	}
	
	/**
	 * Launches the PostList activity to display the 25 most recent MCs
	 */
	public void onClickMC(View v)
	{
		Intent omgIntent = new Intent(getApplicationContext(),
				PostList.class);
		omgIntent.putExtra("URL", "http://omguwmissedconnections.blogspot.com/feeds/posts/default");
		omgIntent.putExtra("TYPE", 2);
		startActivity(omgIntent);
	}
	
	/**
	 * Launches the PostList activity to display the 25 most recent ILUs
	 */
	public void onClickILU(View v)
	{
		Intent omgIntent = new Intent(getApplicationContext(),
				PostList.class);
		omgIntent.putExtra("URL", "http://omguwilu.blogspot.com/feeds/posts/default");
		omgIntent.putExtra("TYPE", 3);
		startActivity(omgIntent);
	}
	
	/**
	 * Launches the PostList activity to display the 25 most recent Overheards
	 */
	public void onClickOH(View v)
	{
		Intent omgIntent = new Intent(getApplicationContext(),
				PostList.class);
		omgIntent.putExtra("URL", "http://omguwoh.blogspot.com/feeds/posts/default");
		omgIntent.putExtra("TYPE", 4);
		startActivity(omgIntent);
	}
	
	/**
	 * Launches the PostList activity to display the 25 most recent Questions
	 */
	public void onClickAsk(View v)
	{
		Intent omgIntent = new Intent(getApplicationContext(),
				PostList.class);
		omgIntent.putExtra("URL", "http://omguwask.blogspot.com/feeds/posts/default");
		omgIntent.putExtra("TYPE", 5);
		startActivity(omgIntent);
	}
	
}
