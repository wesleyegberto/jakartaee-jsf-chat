package com.github.wesleyegberto.adoptajsr.jsfchat.controller;

import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.github.wesleyegberto.adoptajsr.jsfchat.service.ChatRoom;

@Model
public class ChatController {
    @Inject
    private Logger LOG;
    
    @Inject
    private UserBean userBean;
    
    @Inject
    private ChatRoom chatRoom;
    
    private String message;
    
    public ChatController() {
    }

    public String getMessage() {
	return message;
    }
    
    public void setMessage(String message) {
	this.message = message;
    }
    
    public void sendMessage() {
	LOG.info(userBean.getNickname() + " is broadcasting: " + message);
	chatRoom.broadcastMessage(message);
    }
}
