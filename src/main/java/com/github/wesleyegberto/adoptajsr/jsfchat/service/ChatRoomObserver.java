package com.github.wesleyegberto.adoptajsr.jsfchat.service;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.event.WebsocketEvent;
import javax.faces.event.WebsocketEvent.Closed;
import javax.faces.event.WebsocketEvent.Opened;
import javax.inject.Inject;

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
