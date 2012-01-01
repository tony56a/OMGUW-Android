package com.omg.omguw;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndContentImpl;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndLinkImpl;
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.FeedFetcher;
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.FetcherException;
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.FeedException;



public class AbcdActivity extends ListActivity {

	String url;
	int type;
	ArrayList<OMG> list = null;
	private OMGAdapter m_adapter;
	private Runnable viewList;
	private ProgressDialog m_ProgressDialog = null; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Bundle extras = getIntent().getExtras(); 
		url=extras.getString("URL");
		type = extras.getInt("TYPE");
		
		list = new ArrayList<OMG>();
		this.m_adapter =new OMGAdapter(this,R.layout.listitem,list);
		setListAdapter(this.m_adapter);
		ListView lv = getListView();
		
		
		viewList = new Runnable(){
			
			public void run() {
				getContent();
			}
		};
		
		
		Thread thread =  new Thread(null, viewList , "GetData");
		thread.start();
		m_ProgressDialog = ProgressDialog.show(AbcdActivity.this,    
	              "Please wait...", "Retrieving posts ...", true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent detailIntent = new Intent(getApplicationContext(),
						PostDetails.class);

				detailIntent.putExtra("CONTENT", list.get(position).getContent());
				detailIntent.putExtra("DATE", list.get(position).getDate());
				detailIntent.putExtra("TYPE", list.get(position).getType());
				detailIntent.putExtra("URL", list.get(position).getCommentURL());


				startActivity(detailIntent);
				
			}
		});

	}

	public void selectDate(View v)
	{
		Intent naviIntent = new Intent(this, DateSelector.class);
		startActivityForResult(naviIntent, PostDetails.HISTORY_CODE);
	}
	
	
	public void goHome(View v)
	{
		Intent goHomeIntent = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(goHomeIntent);
		this.finish();
	}
	
	public void switchMode(View v)
	{
		Intent naviIntent = new Intent(this, NavigateApp.class);
		startActivityForResult(naviIntent, PostDetails.NAVIGATE_CODE);
	}
	
	public void sendOMG(View v)
	{
    	final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND); 
    	emailIntent.setType("plain/text"); 
    	emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"OMGSUBMIT@gmail.com"}); 
    	switch(type)
    	{
    		case 1:
    			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "OMG"); 
    	    	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "OMG:");
    	    	break;
    		case 2:
    			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "MC"); 
    	    	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "MC:"); 
    	    	break;
    		case 3:
    			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "ILU"); 
    	    	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "ILU:"); 
    	    	break;
    		case 4:
    			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "OVERHEARD"); 
    	    	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "OVERHEARD: "); 
    	    	break;
    	    default:
    			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "ASK"); 
    	    	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "ASK:"); 
    	    	break;
    	}
    
    	startActivity(Intent.createChooser(emailIntent, "Submit OMG"));
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==Activity.RESULT_CANCELED)
		{
			return;
		}
		
		
		
		
		if(requestCode==PostDetails.NAVIGATE_CODE)
		{
			int value = data.getExtras().getInt("NAVICODE");
			System.out.println(value);
		Intent navigateIntent = new Intent(getApplicationContext(),
				AbcdActivity.class);
		switch (value) {
		case 1:
			
			navigateIntent.putExtra("URL", "http://www.omguw.com/feeds/posts/default");
			
			
			break;
		case 2:
	
			navigateIntent.putExtra("URL", "http://omguwmissedconnections.blogspot.com/feeds/posts/default");
		
			
			break;
		case 3:
			
			navigateIntent.putExtra("URL", "http://omguwilu.blogspot.com/feeds/posts/default");
		
			break;
		case 4:
		
			navigateIntent.putExtra("URL", "http://omguwoh.blogspot.com/feeds/posts/default");
			
			
			break;
		default:
	
			navigateIntent.putExtra("URL", "http://omguwask.blogspot.com/feeds/posts/default");
		
			
			break;
		}
		navigateIntent.putExtra("TYPE", value);
		startActivity(navigateIntent);
		finish();
		}
		else
		{
		
			Intent historyIntent = new Intent(getApplicationContext(),AbcdActivity.class);
			int beginDay = data.getExtras().getInt("BEGINDAY");
			int beginMonth = data.getExtras().getInt("BEGINMONTH");
			int beginYear = data.getExtras().getInt("BEGINYEAR");
			int endDay = data.getExtras().getInt("ENDDAY");
			int endMonth = data.getExtras().getInt("ENDMONTH");
			int endYear = data.getExtras().getInt("ENDYEAR");
			
			DecimalFormat formatter = new DecimalFormat("00");
			
			
			
			Intent navigateIntent = new Intent(getApplicationContext(),
					AbcdActivity.class);
			switch (type) {
			case 1:

				navigateIntent.putExtra(
						"URL",
						"http://www.omguw.com/feeds/posts/default?published-min="
								+ beginYear + "-"
								+ formatter.format(beginMonth) + "-"
								+ formatter.format(beginDay)
								+ "T00:00:00&published-max=" + endYear + "-"
								+ formatter.format(endMonth) + "-"
								+ formatter.format(endDay) + "T23:59:59");

				break;
			case 2:

				navigateIntent.putExtra(
						"URL",
						"http://omguwmissedconnections.blogspot.com/feeds/posts/default?published-min="
								+ beginYear + "-"
								+ formatter.format(beginMonth) + "-"
								+ formatter.format(beginDay)
								+ "T00:00:00&published-max=" + endYear + "-"
								+ formatter.format(endMonth) + "-"
								+ formatter.format(endDay) + "T23:59:59");

				break;
			case 3:

				navigateIntent.putExtra(
						"URL",
						"http://omguwilu.blogspot.com/feeds/posts/default?published-min="
								+ beginYear + "-"
								+ formatter.format(beginMonth) + "-"
								+ formatter.format(beginDay)
								+ "T00:00:00&published-max=" + endYear + "-"
								+ formatter.format(endMonth) + "-"
								+ formatter.format(endDay) + "T23:59:59");

				break;
			case 4:
				navigateIntent.putExtra(
						"URL",
						"http://omguwoh.blogspot.com/feeds/posts/default?published-min="
								+ beginYear + "-"
								+ formatter.format(beginMonth) + "-"
								+ formatter.format(beginDay)
								+ "T00:00:00&published-max=" + endYear + "-"
								+ formatter.format(endMonth) + "-"
								+ formatter.format(endDay) + "T23:59:59");

				break;
			default:
				navigateIntent.putExtra(
						"URL",
						"http://omguwask.blogspot.com/feeds/posts/default?published-min="
								+ beginYear + "-"
								+ formatter.format(beginMonth) + "-"
								+ formatter.format(beginDay)
								+ "T00:00:00&published-max=" + endYear + "-"
								+ formatter.format(endMonth) + "-"
								+ formatter.format(endDay) + "T23:59:59");

				break;
			}
			navigateIntent.putExtra("TYPE", type);
			startActivity(navigateIntent);
			finish();
		}
	}

	@SuppressWarnings("unchecked")
	private void getContent()
	{
		SyndFeed feed = null;
		FeedFetcher feedFetcher = new HttpURLFeedFetcher();
		try {
			feed = feedFetcher.retrieveFeed( new URL( url) );
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FetcherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			for(int i=0;i<feed.getEntries().size();i++)
			{
				String date = null,type = null;
				Spanned content=null;
				
				SyndLinkImpl postLink=null;
				SyndEntry abc = (SyndEntry)feed.getEntries().get(i);
				SyndContentImpl bcd = (SyndContentImpl) abc.getContents().get(0);
				content=process(bcd.getValue());
				date = abc.getPublishedDate().toString();
				type = abc.getTitle().substring(1);
				postLink = (SyndLinkImpl) abc.getLinks().get(1);
	
				System.out.println(postLink.getHref());
				list.add(new OMG(date,content,Integer.parseInt(type),postLink.getHref()));
	
	
			}
		}
		catch(NullPointerException e)
		{
			Runnable toast = new Runnable(){

				public void run() {
					Toast.makeText(getApplicationContext(), "Cannot Connect to Website",
							Toast.LENGTH_SHORT).show();
					
				}
			};
			runOnUiThread(toast);
			finish();
		}


		System.out.println(list.size());
		runOnUiThread(returnRes);
	}

	private Spanned process(String value) {

		int first = value.indexOf("/>");
		int last = value.indexOf("<div");

		String convert = value.substring(first+2, last);
		if(convert.indexOf(" ")==0)
		{
			convert = convert.substring(1);
		}
		Spanned returnValue = Html.fromHtml(convert);
		

		return returnValue;
	}

	private Runnable returnRes = new Runnable() {

		
		public void run() {
			m_adapter.notifyDataSetChanged();
			//            if(list != null && list.size() > 0){
			//                
			//                for(int i=0;i<1;i++)
			//                m_adapter.add(list.get(i));
			//            }
			//            
			m_adapter.notifyDataSetChanged();
			m_ProgressDialog.dismiss();
		}
	};

	private String getPage(String website)
	{
		try
		{
			HttpClient hc = new DefaultHttpClient();
			HttpPost post = new HttpPost(website);
			HttpResponse rp = hc.execute(post);

			if(rp.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
			{
				return EntityUtils.toString(rp.getEntity());
			}
			else
			{
				return " ";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return website;
	}

	private class OMGAdapter extends ArrayAdapter<OMG>
	{
		private ArrayList<OMG> items;

		public OMGAdapter(Context context,int textViewResource,ArrayList<OMG> items)
		{
			super(context,textViewResource,items);
			this.items=items;
		}

		public View getView(int pos,View view,ViewGroup parent)
		{
			View v = view;
			if(v== null)
			{
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v=vi.inflate(R.layout.listitem,null);
			}
			OMG entry = items.get(pos);
			if(entry!=null)
			{
				TextView a = (TextView) v.findViewById(R.id.textView1);
				TextView b = (TextView) v.findViewById(R.id.textView2);
				TextView c = (TextView) v.findViewById(R.id.TextView01);
				if(a!=null)
				{
					a.setText("Date: "+entry.getDate());
				}
				if(b!=null)
				{
					b.setText(entry.getContent());
				}
				if(c!=null)
				{
					c.setText("ID: "+Integer.toString(entry.getType()));
				}
			}
			return v;
		}

	}
}