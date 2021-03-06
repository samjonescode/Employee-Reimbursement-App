package com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.dao.UserDAOImpl;
import com.models.User;

public class LoginController {
//	final static Logger log = Logger.getLogger("LoginController.class");
	
//	static Logger logger = Logger.getLogger(LoginController.class);


	public static String Login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDAOImpl udi = new UserDAOImpl();
		User user = new User();
		
		user = udi.selectUserByUsername(username);
		System.out.println("Username from html is: " + username);
		System.out.println("password in logincontroller, pulled from HTML is : " + password);
		if(user==null) {
			return "/html/Login.html";
		}
		if(user.getUserName().equals(username) && user.getPassWord().equals(password)) {
//			log.info("User " + user.getUserName() + " has logged in.");
//			logger.info(user.getUserName() + " has logged in successfully");
			request.getSession().setAttribute("User", user);
			int roleId = user.getRoleId();
			switch(roleId) {
			case 0:
				return "/html/employee-view-2.html";
			case 1: 
				return "/html/finance-manager.html";
			
			default:
				return "/html/Login.html";
			}
		}return "/html/Login.html";
		
	}
}
