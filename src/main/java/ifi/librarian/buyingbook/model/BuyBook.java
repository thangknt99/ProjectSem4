package ifi.librarian.buyingbook.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;



public class BuyBook {
	
	private String title;
	private String authorName;
	
	public BuyBook(String title,String authorName){
		this.title = title;
		this.authorName = authorName;
	}

	public BuyBook() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
