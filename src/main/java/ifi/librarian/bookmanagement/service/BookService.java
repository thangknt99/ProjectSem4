package ifi.librarian.bookmanagement.service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ifi.librarian.bookmanagement.DAO.*;
import ifi.librarian.bookmanagement.model.*;

public class BookService {

	/**
	 * Access to Database
	 * Change to @Bean to easier access
	 */
	@Autowired
	private DatabaseBook bookData;

	/**
	 * Book list import to Model.
	 */
	public List<Book> bookList()throws SQLException, ClassNotFoundException{	
		return bookData.getAllBookFromDb();
	}

	/**
	 * Change status of book.
	 */
	public void updateStatus(UUID uuid, int status)throws SQLException, ClassNotFoundException{		
		bookData.changeStatus(uuid, status);
	}

	/**
	 * Add new book.
	 */
	public boolean addNewBook(String bookName,String authorName) throws SQLException, ClassNotFoundException{
		if(bookData.checkAuthor(authorName)&&bookData.checkBook(bookName))
			return false;
		else {
			bookData.addBook(bookName, bookData.getAuthorId(authorName));
			return true;
		}		
	}

	/**
	 * Take a book info with UUID.
	 */
	public List<Book> infoBook(UUID uuid)throws SQLException, ClassNotFoundException{		
		return bookData.infoBook(uuid);
	}

	/**
	 * Update info to the book.
	 */
	public boolean updateBook(UUID uuid,String bookName,String authorName, int stock) throws SQLException, ClassNotFoundException{
		if(bookData.checkAuthor(authorName)&&bookData.checkBook(bookName)&&!bookData.checkUUID(uuid))
			return false;
		else {
			bookData.updateBook(uuid, bookName, bookData.getAuthorId(authorName), stock);
			return true;
		}		
	}
}
