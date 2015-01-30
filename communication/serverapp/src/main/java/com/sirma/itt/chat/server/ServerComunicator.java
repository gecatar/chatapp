package com.sirma.itt.chat.server;

import java.net.ServerSocket;

import org.apache.log4j.Logger;

import com.sirma.itt.comunicator.AsynchConnectionRunner;
import com.sirma.itt.comunicator.Communicator;
import com.sirma.itt.comunicator.ComunicatorListener;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageCommand;
import com.sirma.itt.comunicator.MessageTransferer;

public class ServerComunicator implements Communicator {

	private static final Logger LOGGER = Logger
			.getLogger(ServerComunicator.class.getName());
	private final ComunicatorListener listener;
	private ServerSocket serverSocket;
	private String name;
	private boolean conecting;

	public ServerComunicator(ComunicatorListener listener) {
		this.listener = listener;
	}

	public synchronized void startConection(String ip, int port) {
		if (!conecting) {
			conecting = true;
			listener.setConectionStatus(MessageCommand.COMUNICATOR_CONECTING);
			new AsynchConnectionRunner(this, ip, port).start();
		}
	}

	public void connect(String ip, int port) {

	}

	public synchronized void stopConection() {
		if (conecting) {
			conecting = false;
			listener.setConectionStatus(MessageCommand.COMUNICATOR_DISCONECTED);
		}
	}

	public void addUserSession(MessageTransferer transferer) {

	}

	public void closeUserSession(MessageTransferer transferer) {

	}

	public void processMesage(Message message, MessageTransferer transferer) {

	}
}
