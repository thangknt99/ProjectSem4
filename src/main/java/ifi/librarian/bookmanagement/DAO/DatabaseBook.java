package ifi.librarian.bookmanagement.DAO;


import ifi.librarian.bookmanagement.model.Book;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DatabaseBook {

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
	private static DatabaseBook DATABASE = new DatabaseBook();

	public DatabaseBook() {
	}

	/**
	 * @return Book Manager Instance
	 */
	public static DatabaseBook getInstance() {
		if (DATABASE == null) {
			DATABASE = new DatabaseBook();
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

	/**
	 * List book.
	 */
	public List<Book> getAllBookFromDb() throws SQLException, ClassNotFoundException{
		dbConnection();
		Statement stm = CONN.createStatement();
		ResultSet rs = stm.executeQuery("SELECT baitap.book.Book_ID,baitap.book.Book_Name,baitap.author.Author_Name,\r\n" + 
				"baitap.book.Status,baitap.book.stock,baitap.book.Create_Date,baitap.book.Update_Date\r\n" + 
				"FROM baitap.book INNER JOIN baitap.author ON baitap.book.Author_ID = baitap.author.Author_ID \r\n" + 
				"ORDER BY baitap.book.Book_Name ASC");
		List<Book> bookList = new ArrayList<Book>();
		while(rs.next()){
			Book book1 = new Book();
			book1.setUuid(UUID.fromString(rs.getString(1)));
			book1.setTitle(rs.getString(2));
			book1.setAuthorName(rs.getString(3));
			book1.setStatus(rs.getInt(4));
			book1.setStock(rs.getInt(5));
			book1.setdatemodifiles(LocalDate.parse(rs.getString(6)));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			book1.setdatetoupdate(LocalDateTime.parse(rs.getString(7), formatter));
			bookList.add(book1);
		}
		return bookList;
		//conn.close();
	}

	/**
	 * Update book's status.
	 * @throws ClassNotFoundException 
	 */
	public void changeStatus(UUID uuid, int status) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		stm.executeUpdate("UPDATE baitap.book \n" +
				"SET  baitap.book.Status = "+status+", baitap.book.Update_Date = SYSDATE()\n" +
				"WHERE baitap.book.Book_ID = '"+uuid+"'");			
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

	/**
	 * Does book_id exist?(2 books have same name but different UUID)
	 * 		True - Exist
	 */
	public boolean checkUUID(UUID uuid) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		ResultSet rs = stm.executeQuery("SELECT baitap.book.book_id FROM baitap.book WHERE baitap.book.book_id Like '"+uuid+"'");
		return rs.next();
	}

	/**
	 * Get author_id from another Table.
	 * 		Author exist - take ID
	 * 		Author not exist - make new ID
	 */
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

	/**
	 * New book to Database(after check)
	 */
	public void addBook(String bookName, int authorID) throws SQLException {
		Statement stm = CONN.createStatement();
		UUID id = UUID.randomUUID();
		stm.executeUpdate("INSERT INTO baitap.book(book_id, author_id, book_name, status, create_date, update_date) \r\n" + 
				"VALUES ('"+id+"',"+authorID+",'"+bookName+"',1,SYSDATE(),SYSDATE())");			
	}

	/**
	 * Take 1 book info from Database
	 */
	public List<Book> infoBook(UUID uuid) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		ResultSet rs = stm.executeQuery("SELECT baitap.book.Book_ID,baitap.book.Book_Name,baitap.author.Author_Name,\r\n" + 
				"baitap.book.Status,baitap.book.stock,baitap.book.Create_Date,baitap.book.Update_Date\r\n" + 
				"FROM baitap.book INNER JOIN baitap.author ON baitap.book.Author_ID = baitap.author.Author_ID\r\n" + 
				"WHERE baitap.book.Book_ID = '"+uuid+"'");
		List<Book> bookList = new ArrayList<Book>();
		while(rs.next()){
			Book book1 = new Book();
			book1.setUuid(UUID.fromString(rs.getString(1)));
			book1.setTitle(rs.getString(2));
			book1.setAuthorName(rs.getString(3));
			book1.setStatus(rs.getInt(4));
			book1.setStock(rs.getInt(5));
			book1.setdatemodifiles(LocalDate.parse(rs.getString(6)));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			book1.setdatetoupdate(LocalDateTime.parse(rs.getString(7), formatter));
			bookList.add(book1);
		}
		return bookList;

	}

	/**
	 * Update edited book to Database(after check)
	 * @throws ClassNotFoundException 
	 */
	public void updateBook(UUID uuid,String bookName, int authorID, int stock) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		stm.executeUpdate("UPDATE baitap.book \n" +
				"SET baitap.book.Author_ID = '"+authorID+"', baitap.book.Book_Name = '"+bookName+"', baitap.book.stock = "+stock+", baitap.book.Update_Date = SYSDATE()\n" +
				"WHERE baitap.book.Book_ID = '"+uuid+"'");			
	}

	public ArrayList<String> nameAuthor() throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		ResultSet rs = stm.executeQuery("SELECT baitap.author.Author_Name FROM baitap.author");
		ArrayList<String> authorList = new ArrayList<String>();
		while(rs.next()){
			authorList.add(rs.getString(1));
		}
		return authorList;

	}
	
	
}
