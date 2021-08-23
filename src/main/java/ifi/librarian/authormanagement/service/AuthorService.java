package ifi.librarian.authormanagement.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ifi.librarian.authormanagement.DAO.DatabaseAuthor;
import ifi.librarian.authormanagement.model.Author;

public class AuthorService {

	@Autowired
	private DatabaseAuthor authorData;

	public List<Author> authorList()throws SQLException, ClassNotFoundException{	
		return authorData.authorList();
	}
	
	public boolean checkAuthor(String authorName)throws SQLException, ClassNotFoundException{	
		return authorData.addNewAuthor(authorName);
	}

	public void changeDescription(String description,int authorId)throws SQLException, ClassNotFoundException {
		authorData.changeDescription(description, authorId);
	}
}
