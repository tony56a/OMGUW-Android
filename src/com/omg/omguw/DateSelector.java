package com.omg.omguw;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

/**
 * Date Selector for browsing by history in the PostList activity
 *
 */

public class DateSelector extends Activity {

	/*
	 * variables to store the selected begin/end dates,
	 * and the datepicker elements
	 */
	DatePicker begin,end=null;
	int beginDay,beginMonth,beginYear;
	int endDay,endMonth,endYear;
	
	
	/** 
	 * onCreate method for activity, sets dates for both daypicker elements,
	 * and sets a listener to modify the begin/end dates 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_selector);
		
		//set the result to cancelled on creation
		setResult(Activity.RESULT_CANCELED);
		
		//get the current time
		Calendar today = Calendar.getInstance();
		
		//set the begin/end time to the current date/month/year
		beginDay=today.get(Calendar.DAY_OF_MONTH);
		endDay=today.get(Calendar.DAY_OF_MONTH);
		beginMonth=today.get(Calendar.MONTH);
		endMonth=today.get(Calendar.MONTH);
		beginYear=today.get(Calendar.YEAR);
		endYear=today.get(Calendar.YEAR);
		
		//find the DatePicker views in the history_selector layout
		DatePicker dp1 = (DatePicker) findViewById(R.id.datePicker1);
		DatePicker dp2 = (DatePicker) findViewById(R.id.datePicker2);

		//initialize the begin date Datepicker
		dp1.init(beginYear, beginMonth, beginDay,new OnDateChangedListener()
		{
			//set the begin date variables to the selected date
			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				
				//if the year is invalid, then don't record it
				if(year>beginYear)
				{
					beginYear=beginYear;
				}
				else if(year<2009)
				{
					beginYear=2009;
				}
				else
				{
					beginYear=year;
				}
				
				//add one to the month, since blogger is 1 indexed for history
				beginMonth=monthOfYear+1;
				
				beginDay=dayOfMonth;
			
			}
			
		});
		
		
		//initalize the end date Datepicker
		dp2.init(beginYear, beginMonth, beginDay,new OnDateChangedListener()
		{

			//set the end date variables to the selected date
			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				
				
				//ensure the year is valid (between 2009 and now)
				if(year>endYear)
				{
					endYear=endYear;
				}
				else if(year<2009)
				{
					endYear=2009;
				}
				else
				{
					endYear=year;
				}
				
				//set the month to be 1 indexed
				endMonth=monthOfYear+1;
				
				endDay=dayOfMonth;
			
			}
			
		});
	}
	
	
	
	/**
	 * onClick handler for the return button in the list
	 * @param v
	 */
	public void returnDates(View v)
	{
		//create a new intent to send back to the activity that created this activity
		Intent intent = new Intent();
		
		//attach the begin/end dates to the intent
		intent.putExtra("BEGINDAY", beginDay);
		intent.putExtra("BEGINMONTH", beginMonth);
		intent.putExtra("BEGINYEAR", beginYear);
		intent.putExtra("ENDDAY", endDay);
		intent.putExtra("ENDMONTH", endMonth);
		intent.putExtra("ENDYEAR", endYear);
		
		//return to the starting activity, with the result set to OK
		setResult(Activity.RESULT_OK, intent);
        finish();
	}
	
}
