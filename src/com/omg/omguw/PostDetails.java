package com.omg.omguw;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.style.URLSpan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndContentImpl;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.FeedFetcher;
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;

/**
 * Activity to display a paticular post, and its comments
 *
 */
public class PostDetails extends Activity {

	private String date;
	private Spanned content;
	private int ID;
	private String commentURL;
	ArrayList<Comment> commentList = null;
	private Runnable viewList;
	private ProgressDialog m_ProgressDialog = null; 
	
	public static final int NAVIGATE_CODE = 1;
	public static final int HISTORY_CODE = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_details);
		
		//get the content from the intent that launched this activity
		Bundle extras = getIntent().getExtras();
		date = extras.getString("DATE");
		content = (Spanned) extras.get("CONTENT");
		ID = extras.getInt("TYPE");
		commentURL = extras.getString("URL");

		//get the views for the layout's post textviews,and set them
		//to the appropriate content
		TextView tv1 = (TextView) findViewById(R.id.textView1);
		TextView tv2 = (TextView) findViewById(R.id.textView2);
		TextView tv3 = (TextView) findViewById(R.id.textView3);
		tv1.setText("Date: " + date);
		tv2.setText("#: " + ID);
		tv3.setText(content);
		
		final URLSpan[] spans = content.getSpans(0, content.length(),
				URLSpan.class);
		
		if (spans.length > 0 && spans != null) {
			tv3.setOnClickListener(new OnClickListener() {

				//start on onclick listener, opening the first link in the post's content
				//using an intent
				public void onClick(View v) {
					String url = spans[0].getURL();
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				}

			});
		}

		//setup an arraylist of comment objects to populate the comment list
		commentList = new ArrayList<Comment>();

		viewList = new Runnable() {

			public void run() {
				getContent();
			}
		};

		// run the comment retrieval process in another thread,
		// and open a progressdialog to indicate that posts are being retrieved
		Thread thread = new Thread(null, viewList, "GetData");
		thread.start();
		m_ProgressDialog = ProgressDialog.show(PostDetails.this,    
	              "Please wait...", "Retrieving comments ...", true);
	}

	public void onDestroy() {
		super.onDestroy();
	}

	public void onPause() {
		super.onPause();
	}
	
	/**
	 * Method to post content online using the ACTION_SEND intent
	 */
	public void shareContent(View v) {
		
		//create a new intent 
		Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");

		//set the subject and text to appropriate content
		shareIntent.putExtra(Intent.EXTRA_SUBJECT,
				"Check out this cool post from OMGUW!");
		shareIntent.putExtra(Intent.EXTRA_TEXT,
				"\"" + content.toString() + "\"");

		//launch the intentchooser, allowing the user to select the
		//application used to share the post
		Intent intentChooser = Intent.createChooser(shareIntent,
				"Choose an app to share this post with:");
		this.startActivity(intentChooser);
	}

	public void switchMode(View v) {
		Intent naviIntent = new Intent(this, NavigateApp.class);
		startActivityForResult(naviIntent, NAVIGATE_CODE);
	}

	public void goHome(View v) {
		Intent goHomeIntent = new Intent(getApplicationContext(),
				MainActivity.class);
		startActivity(goHomeIntent);
		this.finish();
	}



	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_CANCELED) {
			return;
		}

		int value = data.getExtras().getInt("NAVICODE");
		System.out.println(value);

		Intent navigateIntent = new Intent(getApplicationContext(),
				PostList.class);
		switch (value) {
		case 1:

			navigateIntent.putExtra("URL",
					"http://www.omguw.com/feeds/posts/default");

			break;
		case 2:

			navigateIntent
					.putExtra("URL",
							"http://omguwmissedconnections.blogspot.com/feeds/posts/default");

			break;
		case 3:

			navigateIntent.putExtra("URL",
					"http://omguwilu.blogspot.com/feeds/posts/default");

			break;
		case 4:

			navigateIntent.putExtra("URL",
					"http://omguwoh.blogspot.com/feeds/posts/default");

			break;
		default:

			navigateIntent.putExtra("URL",
					"http://omguwask.blogspot.com/feeds/posts/default");

			break;
		}
		startActivity(navigateIntent);
		finish();
	}

	public void submitComment(View v)
	{
		Intent intent = new Intent(getApplicationContext(),
				PostComment.class);
		intent.putExtra("URL", commentURL);
		startActivity(intent);
	}
	
	private void getContent() {
		SyndFeed feed = null;
		FeedFetcher feedFetcher = new HttpURLFeedFetcher();

		try {
			feed = feedFetcher.retrieveFeed(new URL(commentURL));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		try
		{
			for (int i = 0; i < feed.getEntries().size(); i++) {
				String date = null, author = null;
				Spanned content = null;
				SyndEntry abc = (SyndEntry) feed.getEntries().get(i);
				SyndContentImpl bcd = (SyndContentImpl) abc.getContents().get(0);
				content = process(bcd.getValue());
				date = abc.getPublishedDate().toString();
				author = abc.getAuthor();
				commentList.add(new Comment(content, date, author));

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
		Collections.reverse(commentList);
		System.out.println(commentList.size());
		runOnUiThread(returnRes);

	}

	private Runnable returnRes = new Runnable() {

		public void run() {
			LinearLayout list = (LinearLayout) findViewById(R.id.linearLayout1);
			for (int i = 0; i < commentList.size(); i++) {
				Comment comment = commentList.get(i);
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View v = vi.inflate(R.layout.listitem, null);

				if (comment != null) {
					TextView a = (TextView) v.findViewById(R.id.textView1);
					TextView b = (TextView) v.findViewById(R.id.textView2);
					TextView c = (TextView) v.findViewById(R.id.TextView01);
					if (a != null) {
						a.setText("Date: " + comment.getDate());
					}
					if (b != null) {
						b.setText(comment.getText());
					}
					if (c != null) {
						c.setText("Author: " + comment.getAuthor());
					}

					final URLSpan[] urls = comment.getText().getSpans(0,
							comment.getText().length(), URLSpan.class);
					if (urls.length > 0 && urls != null) {
						b.setOnClickListener(new OnClickListener() {

							public void onClick(View v) {
								String url = urls[0].getURL();
								Intent i = new Intent(Intent.ACTION_VIEW);
								i.setData(Uri.parse(url));
								startActivity(i);
							}

						});
					}
				}
				list.addView(v);
			}
			
			m_ProgressDialog.dismiss();
		}
	};

	private Spanned process(String convert) {

		Spanned returnValue = Html.fromHtml(convert);
		return returnValue;
	}

}
