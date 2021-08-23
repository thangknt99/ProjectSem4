package ifi.librarian.usermanagement.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ifi.librarian.usermanagement.DAO.DatabaseUserList;
import ifi.librarian.usermanagement.model.User;

public class UserService {

	@Autowired
	private DatabaseUserList dbUserList;
	
	public List<User> userList() throws SQLException, ClassNotFoundException{
		return dbUserList.userList();
	}
}
