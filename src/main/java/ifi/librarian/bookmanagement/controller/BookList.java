package ifi.librarian.bookmanagement.controller;

import ifi.librarian.bookmanagement.service.BookService;

import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BookList {
	
	@Autowired
	private BookService bookService;
	/**
	 * Simply selects the home book list view.
	 */
	@RequestMapping(value = "/book")
	public ModelAndView bookHomepage() throws SQLException, ClassNotFoundException{
		ModelAndView modelViewObj = new ModelAndView("bookM","listBook", bookService.bookList() );
		return modelViewObj;
	}

	/**
	 * Change status of book(js - method onchange()).
	 */
	@RequestMapping(value = "/statusbook")
	public ModelAndView statusBook(@Validated String uuid,@Validated int status, Model model) throws SQLException, ClassNotFoundException {
		bookService.updateStatus(UUID.fromString(uuid),status);
		return bookHomepage();	
	}
	
	/**
	 * Add new book to database.
	 */
	@RequestMapping(value = "/addbook")
	public ModelAndView addBook(@Validated String bookName,@Validated String authorName, Model model) throws SQLException, ClassNotFoundException{
		if(bookService.addNewBook(bookName, authorName))
			model.addAttribute("message", 1);
		else {
			model.addAttribute("message", 0);
		}
		return bookHomepage();
	}
	
	/**
	 * Take Book Id and open format to change info a book.
	 */
	@RequestMapping(value = "/editbook", method = RequestMethod.POST)
	public ModelAndView editBook(@Validated String uuid,Model model) throws SQLException, ClassNotFoundException {
		model.addAttribute("bookEdit",bookService.infoBook(UUID.fromString(uuid)));
		return bookHomepage();
	}
	
	/**
	 * Update info of book
	 */
	@RequestMapping(value = "/editbook", method = RequestMethod.GET)
	public ModelAndView updateBook(@Validated String uuid, @Validated String bookName,@Validated String authorName,@Validated int stock, Model model) throws SQLException, ClassNotFoundException {
		bookService.updateBook(UUID.fromString(uuid),bookName,authorName,stock);
		return bookHomepage();
	}
}
