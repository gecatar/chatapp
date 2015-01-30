package com.sirma.itt.chat.server;

import org.apache.log4j.Logger;

import com.sirma.itt.comunicator.Communicator;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageTransferer;

public class ServerComunicator implements Communicator {

	private static final Logger LOGGER = Logger
			.getLogger(ServerComunicator.class.getName());

	public void startConection(String ip, int port) {

	}

	public void stopConection() {

	}

	public void addUserSession(MessageTransferer transferer) {

	}

	public void closeUserSession(MessageTransferer transferer) {

	}

	public void processMesage(Message message, MessageTransferer transferer) {

	}

}
