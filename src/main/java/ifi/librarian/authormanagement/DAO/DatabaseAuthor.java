package ifi.librarian.authormanagement.DAO;

import ifi.librarian.authormanagement.model.Author;

import java.sql.*;
import java.util.*;

public class DatabaseAuthor {

	private static Connection CONN;

	private static String DBCLASS = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/baitap";
	private static String USER = "root";
	private static String PASSWORD = "0p9o8i7u6y5t4r";

	private static DatabaseAuthor DATABASE = new DatabaseAuthor();

	public DatabaseAuthor() {
	}

	public static DatabaseAuthor getInstance() {
		if (DATABASE == null) {
			DATABASE = new DatabaseAuthor();
		}
		return DATABASE;
	}

	public void dbConnection() throws ClassNotFoundException, SQLException{
		try{
			Class.forName(DBCLASS);
			CONN = DriverManager.getConnection(URL, USER, PASSWORD);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Author> authorList() throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM baitap.author");
		List<Author> authorList = new ArrayList<Author>();
		while(rs.next()){
			Author au1 = new Author();
			au1.setauthorId(rs.getInt(1));
			au1.setnameAuthor(rs.getString(2));
			au1.setdescription(rs.getString(3));
			authorList.add(au1);
		}
		return authorList;
	}
	
	public boolean addNewAuthor(String authorName) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		int a = 0;
		ResultSet rs = stm.executeQuery("SELECT baitap.author.Author_Id FROM baitap.author WHERE baitap.author.Author_Name Like '"+authorName+"'");
		if(rs.next())
		{
			return false;
		}
		else {
			rs = stm.executeQuery("SELECT MAX(baitap.author.Author_ID) FROM baitap.author");
			while(rs.next()){
				a = rs.getInt(1) +1;
			}
			stm.executeUpdate("INSERT INTO baitap.author(baitap.author.author_id, baitap.author.author_name) VALUES ("+a+",'"+authorName+"')");	
			return true;
		}
	}
	
	public void changeDescription(String description,int authorId)throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		stm.executeUpdate("UPDATE baitap.author \n" +
				"SET  baitap.author.Author_Infor = '"+description+"'\n" +
				"WHERE baitap.author.Author_ID = "+authorId+"");	
		
	}
	
	
}
