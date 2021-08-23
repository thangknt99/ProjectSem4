package ifi.librarian.login.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import ifi.librarian.login.service.AppUtils;
import ifi.librarian.login.service.LoginService;

@Controller
public class Login {

	@Autowired
	private LoginService login;
	@Autowired
	private AppUtils apputils;
	/**
	 * Simply selects the home book list view.
	 */

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@Validated String userName, HttpSession session) throws SQLException, ClassNotFoundException, ServletException{
		if(login.checkUser(userName)) {
			apputils.storeLoginedUser(session, login.loginedUser(userName));
			ModelAndView modelViewObj = new ModelAndView("menubar");
			return modelViewObj;
		}
		else {
			ModelAndView modelViewObj = new ModelAndView("index");
			return  modelViewObj;
		}
	}

}
