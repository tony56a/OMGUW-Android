package com.omg.omguw;

import android.text.Spanned;

/**
 * Stores data used to handle a post
 */

public class OMG {

	private String date;
	private Spanned content;
	private int ID;
	private String CommentURL;
	
	
	/**
	 *  Constructor
	 * 
	 * @param date : The posting date
	 * @param content : The content of the post
	 * @param id : The ID of the post
	 * @param commentURL : The URL to the post's comments
	 */
	public OMG(String date, Spanned content, int id, String commentURL) {
		super();
		this.date = date;
		this.content = content;
		ID = id;
		CommentURL = commentURL;
	}
	
	
	//Accessors and mutators for class variables
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public Spanned getContent() {
		return content;
	}
	
	public void setContent(Spanned content) {
		this.content = content;
	}
	
	public int getType() {
		return ID;
	}
	
	public void setType(int ID) {
		this.ID = ID;
	}
	
	public String getCommentURL() {
		return CommentURL;
	}
	public void setCommentURL(String commentURL) {
		CommentURL = commentURL;
	}

	
}
