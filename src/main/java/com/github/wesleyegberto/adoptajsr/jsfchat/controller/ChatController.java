package com.github.wesleyegberto.adoptajsr.jsfchat.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.github.wesleyegberto.adoptajsr.jsfchat.service.ChatRoom;

@Model
public class ChatController {
	@Inject
	private UserBean userBean;

	@Inject
	private ChatRoom chatRoom;

	private String message;

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

	public void sendMessage() {
		chatRoom.broadcastMessage(userBean.getNickname(), message);
		this.message = null;
	}
}
