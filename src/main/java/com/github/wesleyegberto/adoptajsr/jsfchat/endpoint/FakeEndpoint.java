package com.github.wesleyegberto.adoptajsr.jsfchat.endpoint;

import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.Session;

public class FakeEndpoint extends Endpoint {
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		// https://java.net/jira/browse/WEBSOCKET_SPEC-240
		// https://github.com/eclipse-ee4j/websocket-api/issues/240, migrated issue ?
	}
}