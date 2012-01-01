package com.omg.omguw;

import android.text.Spanned;

public class Comment {

	private Spanned text;
	private String date;
	private String Author;
	
	
	public Comment(Spanned text, String date, String author) {
		super();
		this.text = text;
		this.date = date;
		Author = author;
	}
	
	public Spanned getText() {
		return text;
	}
	public void setText(Spanned text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	
	
	
}
