package com.github.wesleyegberto.adoptajsr.jsfchat.service;

import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.faces.event.WebsocketEvent;
import jakarta.faces.event.WebsocketEvent.Closed;
import jakarta.faces.event.WebsocketEvent.Opened;
import jakarta.inject.Inject;

@ApplicationScoped
public class ChatRoomObserver {
	@Inject
	private Logger LOG;


	public void onOpen(@Observes @Opened WebsocketEvent event) {
		LOG.info("Opened connection " + event.getChannel() + " from " + event.getUser());
	}

	public void onClose(@Observes @Closed WebsocketEvent event) {
        String channel = event.getChannel();
        LOG.info("Channel " + channel + " was successfully closed!");
		LOG.info("Closed connection " + event.getChannel() + " from " + event.getUser() + " with code "
				+ event.getCloseCode());
	}
}
