package com.omg.omguw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class NavigateApp extends Activity {

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        // Setup the window
	        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	        setContentView(R.layout.navigation_layout);

	        // Set result CANCELED in case the user backs out
	        setResult(Activity.RESULT_CANCELED);
	 }
	 
	 public void returnResult(View v)
	 {
		  Intent intent = new Intent();
        
		 int id = v.getId ();
		 switch(id)
		 {
		 case R.id.omgbtn:
			 intent.putExtra("NAVICODE", 1);
			 break;
		 case R.id.mcbtn:
			 intent.putExtra("NAVICODE", 2);
			 break;
		 case R.id.ilubtn:
			 intent.putExtra("NAVICODE", 3);
			 break;
		 case R.id.ohbtn:
			 intent.putExtra("NAVICODE",4);
			 break;
	     default:
	    	 intent.putExtra("NAVICODE", 5);
	    	 break;
		 }
		 
		 setResult(Activity.RESULT_OK, intent);
         finish();
	 }
	
}
