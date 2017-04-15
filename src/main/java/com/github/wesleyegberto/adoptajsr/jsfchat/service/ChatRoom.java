package com.github.wesleyegberto.adoptajsr.jsfchat.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
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
    @Inject @Push(channel = "chatChannel")
    private PushContext pushContext;

    private final Map<String, PushContext> connections = new ConcurrentHashMap<>();
    
    public ChatRoom() {
    }
    
    @PostConstruct
    public void postConstruct() {
	System.out.println("PushContext: " + pushContext);
    }

    public void connectUser(User user, PushContext context) {
	connections.put(user.getNickname(), context);
    }
    
    public void notifyNewUser(User user) {
	System.out.println("PushContext: " + pushContext);
	LOG.info("New user in the room: " + user.getNickname());
    }
    
    public void onOpen(@Observes @Opened WebsocketEvent event) {
	LOG.info("Opened connection " + event.getChannel() + " from " + event.getUser());
    }

    public void onClose(@Observes @Closed WebsocketEvent event) {
	LOG.info("Closed connection " + event.getChannel() + " from " + event.getUser()
		+ " with code " + event.getCloseCode());
    }
    
    public void broadcastMessage(String message) {
	LOG.info("Broadcasting: " + message);
	pushContext.send(message);
    }
}
