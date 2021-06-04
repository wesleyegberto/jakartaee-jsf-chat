package com.github.wesleyegberto.adoptajsr.jsfchat.controller;

import java.io.Serializable;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.github.wesleyegberto.adoptajsr.jsfchat.service.ChatRoom;

@Named
@ViewScoped
public class ChatController implements Serializable {
	private static final long serialVersionUID = -2592023720528506766L;
	private String message;

	@Inject
	private UserBean userBean;
	@Inject
	private ChatRoom chatRoom;

	public ChatController() {
	}

	public String getChatHistory() {
		return chatRoom.getChatHistory();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void updateClock() {
		this.chatRoom.broadcastClock();
	}

	public void sendMessage() {
		chatRoom.broadcastMessage(userBean.getNickname(), message);
		this.message = null;
	}
}
