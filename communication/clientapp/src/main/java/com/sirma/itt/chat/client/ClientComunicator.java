package com.sirma.itt.chat.client;

import java.net.Socket;

import org.apache.log4j.Logger;

import com.sirma.itt.comunicator.Communicator;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageTransferer;

public class ClientComunicator implements Communicator {

	private static final Logger LOGGER = Logger
			.getLogger(ClientComunicator.class.getName());
	private Socket socket;
	private boolean conecting;

	public void startConection(String ip, int port) {
		if (!conecting) {
			conecting = true;
		}
	}

	public void stopConection() {
		if (conecting) {
			conecting = false;
		}
	}

	public void addUserSession(MessageTransferer transferer) {

	}

	public void closeUserSession(MessageTransferer transferer) {

	}

	public void processMesage(Message message, MessageTransferer transferer) {

	}

	public void sendMesage(Message message) {

	}

	public void setName(String name) {

	}

}
