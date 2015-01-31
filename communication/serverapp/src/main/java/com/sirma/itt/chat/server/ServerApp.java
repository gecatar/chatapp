package com.sirma.itt.chat.server;

import org.apache.log4j.Logger;

public class ServerApp {

	private static final Logger LOGGER = Logger.getLogger(ServerApp.class
			.getName());
	private final ConectionPanel view = new ConectionPanel(this);
	private final ServerComunicator comunicator = new ServerComunicator(view);

	public void startConection(String ip, int port) {
		comunicator.startConection(ip, port);
	}

	public void stopConnection() {
		comunicator.stopConection();
	}

	public static void main(String[] args) {
		new ServerApp();
	}

}
