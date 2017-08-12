package com.github.wesleyegberto.adoptajsr.jsfchat.service;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
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
	@Push(channel = "chatChannel")
	private PushContext chatChannel;

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
		// Set<Future<Void>> status = 
		chatChannel.send(fullMessage);
		/*
		if (status.isEmpty()) {
			throw new RuntimeException("You are alone my friend.");
		} else {
			status.forEach(t -> {
				try {
					t.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
					throw new RuntimeException("The message was interrupted, please, try again.");
				} catch (ExecutionException e) {
					e.printStackTrace();
					throw new RuntimeException("The message wasn't sent, please, try again.");
				}
			});
		}
		*/
		chatHistory = chatHistory + "\n" + fullMessage;
	}
}
