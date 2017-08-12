package com.github.wesleyegberto.adoptajsr.jsfchat.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.github.wesleyegberto.adoptajsr.jsfchat.model.User;

@Named
@SessionScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private User user;

	public void startSessionFor(User user) {
		this.user = user;
	}

	public void finishSession() {
		this.user = null;
	}

	public User getUser() {
		return user;
	}

	public String getName() {
		if (user != null)
			return user.getName();
		return "Anonymous";
	}

	public String getNickname() {
		return user.getNickname();
	}

	public boolean isLogged() {
		return user != null;
	}

}
