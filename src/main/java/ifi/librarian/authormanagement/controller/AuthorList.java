package ifi.librarian.authormanagement.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifi.librarian.authormanagement.service.AuthorService;


@Controller
public class AuthorList {
	
	@Autowired
	private AuthorService authorService;
	/**
	 * Simply selects the home user list view.
	 */
	@RequestMapping(value = "/author")
	public ModelAndView authorHomepage() throws SQLException, ClassNotFoundException{
		ModelAndView modelViewObj = new ModelAndView("authorM", "authorList", authorService.authorList());
		return modelViewObj;
	}

	@RequestMapping(value = "/addAuthor")
	public ModelAndView addAuthor(@Validated String authorName, Model model) throws SQLException, ClassNotFoundException{
		if(authorService.checkAuthor(authorName))
			model.addAttribute("message", 1);
		else {
			model.addAttribute("message", 0);
		}
		return authorHomepage();
	}
	
	@RequestMapping(value = "/changeDescription")
	public ModelAndView changeDescription(@Validated String description, @Validated int authorId,Model model) throws SQLException, ClassNotFoundException{
		authorService.changeDescription(description, authorId);
		return authorHomepage();
	}
	
}
