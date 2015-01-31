package com.sirma.itt.chat.client;

import org.apache.log4j.Logger;

import com.sirma.itt.comunicator.Message;

public class ClientApp {

	private static final Logger LOGGER = Logger.getLogger(ClientApp.class
			.getName());
	private final View view = new View(this);
	private final ClientComunicator comunicator = new ClientComunicator(view);

	public void startConection(String name, String ip, int port) {
		comunicator.setName(name);
		comunicator.startConection(ip, port);
	}

	public void stopConnection() {
		comunicator.stopConection();
	}

	public void sendMessage(Message message) {
		comunicator.sendMesage(message);
	}

	public static void main(String[] args) {
		new ClientApp();
	}

}
