package ifi.librarian.buyingbook.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifi.librarian.authormanagement.service.AuthorService;
import ifi.librarian.buyingbook.service.BuyingService;


@Controller
public class BuyingList {
	
	@Autowired
	private BuyingService buyingService;
	
	@RequestMapping(value = "/buying")
	public ModelAndView buyingHomepage() throws SQLException, ClassNotFoundException{
		ModelAndView modelViewObj = new ModelAndView("buying","buyingList", buyingService.buyingList());
		return modelViewObj;
	}
	
	@RequestMapping(value = "/addtodb")
	public ModelAndView addBooktoDB(@Validated String bookName,@Validated String authorName, Model model) throws SQLException, ClassNotFoundException{
		if(buyingService.addNewBook(bookName, authorName))
			model.addAttribute("sms", "Cuốn "+bookName+" đã được thêm!");
		else {
			model.addAttribute("sms", "Cuốn "+bookName+" tồn tại trong thư viện!");
		}
		return buyingHomepage();
	}
	
	@RequestMapping(value = "/addtobuy")
	public ModelAndView addBook(@Validated String bookName,@Validated String authorName, Model model) throws SQLException, ClassNotFoundException{
		buyingService.addBookBuying(bookName, authorName);
		return buyingHomepage();
	}
	
	@RequestMapping(value = "/deletebuying")
	public ModelAndView deleteBuyingBook(@Validated String bookName,@Validated String authorName, Model model) throws SQLException, ClassNotFoundException{
		buyingService.deleteBookBuying(bookName, authorName);
		return buyingHomepage();
	}
}
