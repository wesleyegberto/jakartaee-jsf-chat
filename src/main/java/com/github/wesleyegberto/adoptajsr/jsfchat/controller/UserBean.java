package com.github.wesleyegberto.adoptajsr.jsfchat.controller;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

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
		if (user == null)
			return "Anonymous";
		return user.getName();
	}

	public String getNickname() {
		if (user == null)
			return "Anonymous";
		return user.getNickname();
	}

	public boolean isLogged() {
		return user != null;
	}

}
