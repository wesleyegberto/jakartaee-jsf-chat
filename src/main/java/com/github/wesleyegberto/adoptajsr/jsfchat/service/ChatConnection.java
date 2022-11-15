package com.github.wesleyegberto.adoptajsr.jsfchat.service;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

// @ServerEndpoint(value = "/websocket")
public class ChatConnection {
	private static final Logger LOG = Logger.getLogger("ChatConnection");
	private static final Set<ChatConnection> connections = new CopyOnWriteArraySet<>();
	private String nickname;
	private Session session;

	public ChatConnection() {
	}

	@OnOpen
	public void start(Session session) {
		LOG.info("Incoming connection");
		this.session = session;
		connections.add(this);
		String message = String.format("%s %s", nickname, "has joined.");
		broadcast(message);
	}

	@OnClose
	public void end() {
		LOG.info("Ended connection");
		connections.remove(this);
		String message = String.format("%s %s", nickname, "has disconnected.");
		broadcast(message);
	}

	@OnMessage
	public void incoming(String message) {
		LOG.info("Incoming message");
		// Never trust the client
		String filteredMessage = String.format("%s: %s", nickname, message.toString());
		broadcast(filteredMessage);
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		LOG.info("Chat error: " + t.getMessage());
	}

	private static void broadcast(String msg) {
		for (ChatConnection client : connections) {
			try {
				synchronized (client) {
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				LOG.info("Failed to send message to client: " + e.getMessage());
				connections.remove(client);
				try {
					client.session.close();
				} catch (IOException e1) {
				}
				String message = String.format("%s %s", client.nickname, "has been disconnected.");
				broadcast(message);
			}
		}
	}
}
