package com.omg.omguw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * Navigation activity, changes the post list to the selected type
 * (OMG,MC,ILU,OH,ASK)
 */
public class NavigateApp extends Activity {

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

//	        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	        setContentView(R.layout.navigation_layout);

	        // Set result CANCELED in case the user backs out
	        setResult(Activity.RESULT_CANCELED);
	 }
	 
	 
	 //onClick handler for the buttons in the layout
	 public void returnResult(View v)
	 {
		 //create a new intent to send information back to 
		 //the starting activity
		 Intent intent = new Intent();
        
		 //get the id of the button pressed,
		 //and set the desired post type accordingly
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
		 
		 //set the result to OK, and return to the starting activity
		 setResult(Activity.RESULT_OK, intent);
         finish();
	 }
	
}
