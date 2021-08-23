package ifi.librarian.login.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import ifi.librarian.login.DAO.DatabaseUser;
import ifi.librarian.login.model.LoginedUser;

public class LoginService {
	
	@Autowired
	private DatabaseUser dbUser;

	public boolean checkUser(String userName) throws SQLException, ClassNotFoundException{
		if(dbUser.checkUser(userName))
			return true;
		else
			return false;
	}
	
	public LoginedUser loginedUser(String userName) throws SQLException, ClassNotFoundException{
			return dbUser.loginedUser(userName);
	}
	
}
