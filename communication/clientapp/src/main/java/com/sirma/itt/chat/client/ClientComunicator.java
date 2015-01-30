package com.sirma.itt.chat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sirma.itt.comunicator.AsynchConnectionRunner;
import com.sirma.itt.comunicator.Communicator;
import com.sirma.itt.comunicator.ComunicatorListener;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageTransferer;

public class ClientComunicator implements Communicator {

	private static final Logger LOGGER = Logger
			.getLogger(ClientComunicator.class.getName());
	private final ComunicatorListener listener;
	private Socket socket;
	private String name;
	private boolean conecting;

	public ClientComunicator(ComunicatorListener listener) {
		this.listener = listener;
	}

	public synchronized void startConection(String ip, int port) {
		if (!conecting) {
			conecting = true;
			new AsynchConnectionRunner(this, ip, port).start();
		}
	}

	/**
	 * Start connection whit remote server.
	 */
	public void connect(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			addUserSession(new MessageTransferer(this, socket,
					new ObjectOutputStream(socket.getOutputStream()),
					new ObjectInputStream(socket.getInputStream())));
		} catch (IOException e) {
			LOGGER.log(Level.ERROR, e);
			stopConection();
		}
	}

	/**
	 * Stop connection and close socket.
	 */
	public synchronized void stopConection() {
		if (conecting) {
			conecting = false;
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					LOGGER.log(Level.ERROR, e);
				}
			}
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
