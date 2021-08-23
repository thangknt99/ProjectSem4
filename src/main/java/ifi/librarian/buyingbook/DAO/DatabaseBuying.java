package ifi.librarian.buyingbook.DAO;


import ifi.librarian.bookmanagement.model.Book;
import ifi.librarian.buyingbook.model.BuyBook;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DatabaseBuying{

	/**
	 * @Connection to mySQl server
	 */
	private static Connection CONN;

	/**
	 * Class and link(id and pass) to access Database
	 */
	private static String DBCLASS = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/baitap";
	private static String USER = "root";
	private static String PASSWORD = "0p9o8i7u6y5t4r";

	/**
	 * New access
	 */
	private static DatabaseBuying DATABASE = new DatabaseBuying();

	public DatabaseBuying() {
	}

	/**
	 * @return Book Manager Instance
	 */
	public static DatabaseBuying getInstance() {
		if (DATABASE == null) {
			DATABASE = new DatabaseBuying();
		}
		return DATABASE;
	}

	/**
	 * Connect to database
	 */
	public void dbConnection() throws ClassNotFoundException, SQLException{
		try{
			Class.forName(DBCLASS);
			CONN = DriverManager.getConnection(URL, USER, PASSWORD);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BuyBook> buyingList() throws SQLException, ClassNotFoundException{
		dbConnection();
		Statement stm = CONN.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM baitap.buying");
		List<BuyBook> bookList = new ArrayList<BuyBook>();
		while(rs.next()){
			BuyBook book1 = new BuyBook();
			book1.setTitle(rs.getString(1));
			book1.setAuthorName(rs.getString(2));
			bookList.add(book1);
		}
		return bookList;
		//conn.close();
	}
	
	/**
	 * Does book exist?
	 * 		True - Exist
	 */
	public boolean checkBook(String bookName) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		ResultSet rs = stm.executeQuery("SELECT baitap.book.Book_Name FROM baitap.book WHERE baitap.book.Book_Name Like '"+bookName+"'");
		return rs.next();
	}

	/**
	 * Does author exist?
	 * 		True - Exist
	 */
	public boolean checkAuthor(String authorName) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		ResultSet rs = stm.executeQuery("SELECT baitap.author.Author_Name FROM baitap.author WHERE baitap.author.Author_Name Like '"+authorName+"'");
		return rs.next();
	}
	
	public int getAuthorId(String authorName) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		int a = 0;
		ResultSet rs = stm.executeQuery("SELECT baitap.author.Author_Id FROM baitap.author WHERE baitap.author.Author_Name Like '"+authorName+"'");
		if(rs.next())
		{
			a = rs.getInt(1);
		}
		else {
			rs = stm.executeQuery("SELECT MAX(baitap.author.Author_ID) FROM baitap.author");
			while(rs.next()){
				a = rs.getInt(1) +1;
			}
			stm.executeUpdate("INSERT INTO baitap.author(baitap.author.author_id, baitap.author.author_name) VALUES ("+a+",'"+authorName+"')");	
		}
		return a;
	}
	
	public void addBook(String bookName, int authorID) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		UUID id = UUID.randomUUID();
		stm.executeUpdate("INSERT INTO baitap.book(book_id, author_id, book_name, status, create_date, update_date) \r\n" + 
				"VALUES ('"+id+"',"+authorID+",'"+bookName+"',4,SYSDATE(),SYSDATE())");			
	}
	
	public void deleteFromBuy(String bookName, String authorName) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		stm.executeUpdate("DELETE FROM baitap.buying WHERE baitap.buying.book_name = '"+bookName+"' AND baitap.buying.author_name = '"+authorName+"'");			
	}
	
	public void addBooktoBuy(String bookName, String authorName) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		stm.executeUpdate("INSERT INTO baitap.buying(book_name, author_name) \r\n" + 
				"VALUES ('"+bookName+"','"+authorName+"')");			
	}
}
