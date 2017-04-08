package com.github.wesleyegberto.adoptajsr.jsfchat.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.SessionMap;
import javax.inject.Inject;

import com.github.wesleyegberto.adoptajsr.jsfchat.db.UserDAO;
import com.github.wesleyegberto.adoptajsr.jsfchat.model.User;

@Model
public class IndexController {

    // throwing WELD-001408: Unsatisfied dependencies
    // @Inject
    @SessionMap
    private Map<String, Object> map;
    
    private User user;
    
    @Inject
    private UserDAO userDao;
    
    @PostConstruct
    public void init() {
	System.out.println("[IndexController] postConstruct");
	System.out.println("[IndexController] UserDAO injected: " + userDao);
    }
    
    public User getUser() {
	if (user == null)
	    user = new User();
	return user;
    }

    public String getMessage() {
	return "Hello JSF 2.3!";
    }
    
    public String signIn() {
	System.out.println(user);
	return null;
    }
}
