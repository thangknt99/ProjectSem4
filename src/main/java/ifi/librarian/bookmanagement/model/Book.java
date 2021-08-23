package ifi.librarian.bookmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 *  Book class ( Simple POJO)
 */
public class Book {
	
	/**
	 * uuid
	 */
	private UUID uuid;
	/**
	 * title of book
	 */
	private String title;
	/**
	 * author name
	 */
	private String authorName;
	/**
	 * status
	 */
	private Integer status;
	/**
	 * stock
	 */
	private Integer stock;
	/**
	 * datemodifiles
	 */
	private LocalDate datemodifiles;

	/**
	 * datetoupdate
	 */
	private LocalDateTime datetoupdate;

	/**
	 * Constructor of all arguments
	 * @param uuid
	 * @param title
	 * @param authorName
	 * @param status
	 * @param stock
	 * @param datemodifiles
	 * @param datetoupdate
	 */
	public Book(UUID uuid,String title,String authorName,Integer status, LocalDate datemodifiles, LocalDateTime datetoupdate,int stock){
		this.uuid = uuid;
		this.title = title;
		this.authorName = authorName;
		this.status = status;
		this.datemodifiles = datemodifiles;
		this.datetoupdate = datetoupdate;
		this.stock = stock;
	}

	public Book() {
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public LocalDate getdatemodifiles() {
		return datemodifiles;
	}

	public void setdatemodifiles(LocalDate localTime) {
		this.datemodifiles = localTime;
	}


	public LocalDateTime getdatetoupdate() {
		return datetoupdate;
	}

	public void setdatetoupdate(LocalDateTime datetoupdate) {
		this.datetoupdate = datetoupdate;
	}

}
