package ifi.librarian.buyingbook.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ifi.librarian.bookmanagement.DAO.*;
import ifi.librarian.bookmanagement.model.*;
import ifi.librarian.buyingbook.DAO.DatabaseBuying;
import ifi.librarian.buyingbook.model.BuyBook;

public class BuyingService {
	
	@Autowired
	private DatabaseBuying buying;
	
	public List<BuyBook> buyingList() throws SQLException, ClassNotFoundException{
		return buying.buyingList();
	}
	
	public boolean addNewBook(String bookName,String authorName) throws SQLException, ClassNotFoundException{
		if(buying.checkAuthor(authorName)&&buying.checkBook(bookName))
			return false;
		else {
			buying.addBook(bookName, buying.getAuthorId(authorName));
			buying.deleteFromBuy(bookName,authorName);
			return true;
		}		
	}
	
	public void addBookBuying(String bookName,String authorName) throws SQLException, ClassNotFoundException{
		buying.addBooktoBuy(bookName,authorName);
	}
	
	public void deleteBookBuying(String bookName,String authorName) throws SQLException, ClassNotFoundException{
		buying.deleteFromBuy(bookName,authorName);
	}
}
