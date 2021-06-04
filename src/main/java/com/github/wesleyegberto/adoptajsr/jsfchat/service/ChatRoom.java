package com.github.wesleyegberto.adoptajsr.jsfchat.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;

import com.github.wesleyegberto.adoptajsr.jsfchat.model.User;

@ApplicationScoped
public class ChatRoom {
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Inject
	private Logger LOG;

	// creates a Endpoint on the fly
	@Inject
	@Push(channel = "chatChannel")
	private PushContext chatChannel;

	@Inject
	@Push(channel = "clock")
	private PushContext clockChannel;

	private String chatHistory = "";

	public ChatRoom() {
	}

	@PostConstruct
	public void postConstruct() {
		if (chatChannel != null)
			LOG.info("Channel: " + chatChannel);
		else
			LOG.info("Channel is null");
	}

	public void notifyNewUser(User user) {
		LOG.info("New user in the room: " + user.getNickname());
	}

	public String getChatHistory() {
		return chatHistory;
	}

	public void broadcastMessage(String user, String message) {
		String fullMessage = String.format("%s: %s", user, message);
		LOG.info("Broadcasting: " + fullMessage);
		chatChannel.send(fullMessage);
		chatHistory = chatHistory + "\n" + fullMessage;
	}

	public void broadcastClock() {
		clockChannel.send(LocalDateTime.now().format(DATE_FORMAT));
	}
}
