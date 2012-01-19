
package com.omg.omguw;

import android.text.Spanned;

/**
 * Stores data used to handle a post's comments
 */
public class Comment {

	private Spanned text;
	private String date;
	private String author;
	
	
	/**
	 * Constructor
	 * 
	 * @param text : Content of a comment
	 * @param date : Date of posting
	 * @param author : Author of post
	 */
	public Comment(Spanned text, String date, String author) {
		super();
		this.text = text;
		this.date = date;
		this.author = author;
	}
	
	/**
	 * Accessor for text variable
	 * @return the text variable for the object 
	 */
	public Spanned getText() {
		return text;
	}
	
	/**
	 * Mutator for text variable
	 * @param text : New text object
	 */
	public void setText(Spanned text) {
		this.text = text;
	}
	
	
	/**
	 * Accessor for date variable
	 * @return the date variable for the object
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Mutator for the date variable
	 * @param date : New date value
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Accessor for author variable
	 * @return the author variable for the object
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Mutator for the date variable
	 * @param author : New author value
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
}
