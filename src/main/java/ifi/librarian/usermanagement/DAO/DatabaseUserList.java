package ifi.librarian.usermanagement.DAO;

import ifi.librarian.usermanagement.model.User;

import java.sql.*;
import java.util.*;

public class DatabaseUserList {

	private static Connection CONN;

	private static String DBCLASS = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/baitap";
	private static String USER = "root";
	private static String PASSWORD = "0p9o8i7u6y5t4r";

	private static DatabaseUserList DATABASE = new DatabaseUserList();

	public DatabaseUserList() {
	}

	public static DatabaseUserList getInstance() {
		if (DATABASE == null) {
			DATABASE = new DatabaseUserList();
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
	public List<User> userList() throws SQLException, ClassNotFoundException{
		dbConnection();
		Statement stm = CONN.createStatement();
		ResultSet rs = stm.executeQuery("SELECT baitap.account.account_Id, baitap.account.level\r\n" + 
				"FROM baitap.account");
		List<User> userList = new ArrayList<User>();
		while(rs.next()){
			User us = new User();
			us.setuserId(rs.getString(1));
			us.setlevel(rs.getInt(2));
			userList.add(us);
		}
		return userList;
		//conn.close();
	}	
}
