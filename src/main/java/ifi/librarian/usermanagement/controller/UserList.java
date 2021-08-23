package ifi.librarian.usermanagement.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifi.librarian.usermanagement.service.UserService;



@Controller
public class UserList {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user")
	public ModelAndView userHomepage() throws SQLException, ClassNotFoundException{
		ModelAndView modelViewObj = new ModelAndView("userM","userList", userService.userList());
		return modelViewObj;
	}

}
