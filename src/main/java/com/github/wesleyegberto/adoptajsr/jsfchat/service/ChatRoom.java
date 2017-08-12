package com.github.wesleyegberto.adoptajsr.jsfchat.service;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.event.WebsocketEvent;
import javax.faces.event.WebsocketEvent.Closed;
import javax.faces.event.WebsocketEvent.Opened;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;

import com.github.wesleyegberto.adoptajsr.jsfchat.model.User;

@ApplicationScoped
public class ChatRoom {

	@Inject
	private Logger LOG;

	// creates a Endpoint on the fly
	@Inject
	@Push
	private PushContext chatChannel;

	private String chatHistory = "";

	public ChatRoom() {
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("PushContext: " + chatChannel);
	}

	public void onOpen(@Observes @Opened WebsocketEvent event) {
		LOG.info("Opened connection " + event.getChannel() + " from " + event.getUser());
	}

	public void onClose(@Observes @Closed WebsocketEvent event) {
		LOG.info("Closed connection " + event.getChannel() + " from " + event.getUser() + " with code "
				+ event.getCloseCode());
	}

	public void notifyNewUser(User user) {
		System.out.println("PushContext: " + chatChannel);
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
}
