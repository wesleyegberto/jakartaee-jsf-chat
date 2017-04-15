package com.github.wesleyegberto.adoptajsr.jsfchat.controller;

import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.github.wesleyegberto.adoptajsr.jsfchat.CookieHelper;
import com.github.wesleyegberto.adoptajsr.jsfchat.UsernameNotUniqueException;
import com.github.wesleyegberto.adoptajsr.jsfchat.db.UserDAO;
import com.github.wesleyegberto.adoptajsr.jsfchat.model.User;

@Model
public class IndexController {

    @Inject
    private Logger LOG;
    
    private static final String COOKIE_REMEMBERME = "jsf-chat-rme";
    
    @Inject
    private FacesContext facesContext;
    
    @Inject
    private CookieHelper cookieHelper;
    
    @Inject
    private UserDAO userDao;
    private boolean rememberMe;
    
    private User user;
    
    public User getUser() {
	if (user == null) {
	    if(cookieHelper.containsCookie(COOKIE_REMEMBERME)) {
		user = getUserFromCookie();
	    } else {
		user = new User();
	    }
	}
	return user;
    }

    public String getMessage() {
	return "Hello JSF 2.3!";
    }
    
    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void verifyNicknameUniqueness() {
	LOG.info("Validating nickname uniqueness");
	String displayAttribute = "";
	if(userDao.isNicknameUsed(user.getNickname())) {
	    displayAttribute = "block";
	} else {
	    displayAttribute = "none";
	}
	// just to show how to execute a JS
	facesContext.getPartialViewContext()
    		.getEvalScripts()
        	.add("document.getElementById('nickname-error').style.display='" + displayAttribute + "'");
    }
    
    public String signIn() {
	try {
	    userDao.createUser(user);
	    if(this.rememberMe) {
		createCookieToUser();
	    }
	    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "All set", "User registered successfully."));
	} catch(UsernameNotUniqueException ex) {
	    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nickname is already in use", "Please, insert another nickname."));
	}
	return "chat-room";
    }

    private void createCookieToUser() {
	String textValue = String.format("%s;%s", user.getNickname(), user.getName()); // too weak
	cookieHelper.setCookie(COOKIE_REMEMBERME, textValue);
    }
    
    private User getUserFromCookie() {
	String cookieValue = cookieHelper.getCookieValue(COOKIE_REMEMBERME);
	if(cookieValue != null) {
	    LOG.info("Cookie: " + cookieValue);
	    if (cookieValue.contains(";")) {
		String[] tokens = cookieValue.split(";");
		return new User(tokens[0], tokens[1]);
	    }
	}
	return null;
    }
}
