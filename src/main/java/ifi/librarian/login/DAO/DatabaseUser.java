package ifi.librarian.login.DAO;

import java.sql.*;

import ifi.librarian.login.model.LoginedUser;

public class DatabaseUser {

	/**
	 * @Connection to mySQl server
	 */
	private static Connection CONN;

	/**
	 * Class and link(id and pass) to access Database
	 */
	private static final String DBCLASS = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/baitap";
	private static final String USER = "root";
	private static final String PASSWORD = "0p9o8i7u6y5t4r";

	/**
	 * New access
	 */
	private static DatabaseUser DATABASE = new DatabaseUser();

	public DatabaseUser() {
	}

	/**
	 * @return Book Manager Instance
	 */
	public static DatabaseUser getInstance() {
		if (DATABASE == null) {
			DATABASE = new DatabaseUser();
		}
		return DATABASE;
	}

	/**
	 * Connect to database
	 */
	public static void dbConnection() throws ClassNotFoundException, SQLException{
		try{
			Class.forName(DBCLASS);
			CONN = DriverManager.getConnection(URL, USER, PASSWORD);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkUser(String userName) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		ResultSet rs = stm.executeQuery("SELECT baitap.account.account_Id FROM baitap.account\r\n" + 
				"WHERE baitap.account.account_Id LIKE '"+userName+"'");
		return rs.next();
	}
	
	public LoginedUser loginedUser(String userName) throws SQLException, ClassNotFoundException {
		dbConnection();
		Statement stm = CONN.createStatement();
		ResultSet rs = stm.executeQuery("SELECT baitap.account.account_Id, baitap.account.password, baitap.account.level FROM baitap.account\r\n" + 
				"WHERE baitap.account.account_Id LIKE '"+userName+"'");
		LoginedUser user = new LoginedUser();
		while(rs.next()) {
			user.setidUser(rs.getString(1));
			user.setPassword(rs.getString(2));
			user.setLevel(rs.getInt(3));
		}
		return user;
	}
}
