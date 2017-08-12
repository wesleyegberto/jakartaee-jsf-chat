package com.github.wesleyegberto.adoptajsr.jsfchat.controller;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	
	@Inject
	@Push(channel = "clock")
	private PushContext clockChannel;

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
		clockChannel.send(LocalDateTime.now());
	}

	public void sendMessage() {
		chatRoom.broadcastMessage(userBean.getNickname(), message);
		this.message = null;
	}
}
