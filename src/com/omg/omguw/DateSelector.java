package com.omg.omguw;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DateSelector extends Activity {

	DatePicker begin,end=null;
	int beginDay,beginMonth,beginYear;
	int endDay,endMonth,endYear;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_selector);
		setResult(Activity.RESULT_CANCELED);
		
		Calendar today = Calendar.getInstance();
		
		beginDay=today.get(Calendar.DAY_OF_MONTH);
		endDay=today.get(Calendar.DAY_OF_MONTH);
		beginMonth=today.get(Calendar.MONTH);
		endMonth=today.get(Calendar.MONTH);
		beginYear=today.get(Calendar.YEAR);
		endYear=today.get(Calendar.YEAR);
		
		DatePicker dp1 = (DatePicker) findViewById(R.id.datePicker1);
		DatePicker dp2 = (DatePicker) findViewById(R.id.datePicker2);

		dp1.init(beginYear, beginMonth, beginDay,new OnDateChangedListener()
		{

			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				
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
				beginMonth=monthOfYear+1;
				beginDay=dayOfMonth;
			
			}
			
		});
		
		dp2.init(beginYear, beginMonth, beginDay,new OnDateChangedListener()
		{

			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				
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
				endMonth=monthOfYear+1;
				endDay=dayOfMonth;
			
			}
			
		});
	}
	
	public void returnDates(View v)
	{
		
		Intent intent = new Intent();
		intent.putExtra("BEGINDAY", beginDay);
		intent.putExtra("BEGINMONTH", beginMonth);
		intent.putExtra("BEGINYEAR", beginYear);
		intent.putExtra("ENDDAY", endDay);
		intent.putExtra("ENDMONTH", endMonth);
		intent.putExtra("ENDYEAR", endYear);
		setResult(Activity.RESULT_OK, intent);
        finish();
	}
	
}
