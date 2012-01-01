package com.omg.omguw;

import android.text.Spanned;

public class OMG {

	private String date;
	private Spanned content;
	private int ID;
	private String CommentURL;
	
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

	public OMG(String date, Spanned content, int iD, String commentURL) {
		super();
		this.date = date;
		this.content = content;
		ID = iD;
		CommentURL = commentURL;
	}
	
	
	
	
}
