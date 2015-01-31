package com.sirma.itt.chat.client;

import org.apache.log4j.Logger;

public class ClientApp {

	private static final Logger LOGGER = Logger.getLogger(ClientApp.class
			.getName());
	private final View view = new View(this);
	private final ClientComunicator comunicator = new ClientComunicator(view);

	public void startConection(String name, String ip, int port) {
		comunicator.startConection(ip, port);
	}

	public void stopConnection() {
		comunicator.stopConection();
	}

	public static void main(String[] args) {
		new ClientApp();
	}

}
