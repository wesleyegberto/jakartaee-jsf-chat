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
	@Inject
	private UserBean userBean;

	private User user;
	private boolean rememberMe;
	private boolean userAlreadySignedUp;

	public User getUser() {
		if (user == null) {
			if (cookieHelper.containsCookie(COOKIE_REMEMBERME)) {
				user = startSessionFromCookie();
			}
			if (user == null) {
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

	public boolean isUserAlreadySignedUp() {
		return userAlreadySignedUp;
	}

	public void verifyNicknameUniqueness() {
		String displayAttribute = "";
		if (userDao.isNicknameUsed(user.getNickname())) {
			displayAttribute = "block";
		} else {
			displayAttribute = "none";
		}
		// just to show how to execute a JS
		facesContext.getPartialViewContext().getEvalScripts()
				.add("document.getElementById('nickname-error').style.display='" + displayAttribute + "'");
	}

	public String signIn() {
		try {
			userDao.createUser(user);
			if (this.rememberMe) {
				createCookieToUser();
			}
			userBean.startSessionFor(user);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "All set", "User registered successfully."));
			return goToChatRoom();
		} catch (UsernameNotUniqueException ex) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nickname is already in use",
					"Please, insert another nickname."));
		}
		return null;
	}

	public String goToChatRoom() {
		return "chat-room?faces-redirect=true";
	}

	private void createCookieToUser() {
		String textValue = String.format("%s;%s", user.getNickname(), user.getName());
		cookieHelper.setCookie(COOKIE_REMEMBERME, textValue);
	}

	private User startSessionFromCookie() {
		String cookieValue = cookieHelper.getCookieValue(COOKIE_REMEMBERME);
		if (cookieValue != null && cookieValue.contains(";")) {
			LOG.info("Cookie: " + cookieValue);
			User user = createUserFromCookie(cookieValue);
			// just a workaround to in-memory DB
			if (!userDao.isNicknameUsed(user.getNickname())) {
				userDao.createUser(user);
			}
			userBean.startSessionFor(user);
			userAlreadySignedUp = rememberMe = true;
			return user;
		}
		return null;
	}

	private User createUserFromCookie(String cookieValue) {
		String[] tokens = cookieValue.split(";");
		return new User(tokens[0], tokens[1]);
	}
}
