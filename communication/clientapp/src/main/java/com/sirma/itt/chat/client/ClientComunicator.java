package com.sirma.itt.chat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sirma.itt.comunicator.AsynchConnectionRunner;
import com.sirma.itt.comunicator.Communicator;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageCommand;
import com.sirma.itt.comunicator.MessageTransferer;

/**
 * Send and receive messages from server.
 * 
 * @author GecaTM
 *
 */
public class ClientComunicator implements Communicator {

	private static final Logger LOGGER = Logger
			.getLogger(ClientComunicator.class.getName());
	private final View listener;
	private Socket socket;
	private MessageTransferer transferer;
	private String name;
	private boolean conecting;

	public ClientComunicator(View listener) {
		this.listener = listener;
	}

	/**
	 * Start connection whit remote server.
	 */
	@Override
	public synchronized void startConection(String ip, int port) {
		if (!conecting) {
			conecting = true;
			listener.setConectionStatus(MessageCommand.COMUNICATOR_CONECTING);
			new AsynchConnectionRunner(this, ip, port).start();
		}
	}

	/**
	 * Create connection whit remote server.
	 */
	@Override
	public boolean connect(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			addUserSession(new MessageTransferer(this, socket,
					new ObjectOutputStream(socket.getOutputStream()),
					new ObjectInputStream(socket.getInputStream())));
			return true;
		} catch (IOException e) {
			LOGGER.log(Level.ERROR, e);
			stopConection();
			return false;
		}
	}

	/**
	 * Stop connection and close socket.
	 */
	@Override
	public synchronized void stopConection() {
		if (conecting) {
			conecting = false;
			listener.setConectionStatus(MessageCommand.COMUNICATOR_DISCONECTED);
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					LOGGER.log(Level.ERROR, e);
				}
			}
		}
	}

	/**
	 * Start message transferrer and send user details to server.
	 */
	@Override
	public void addUserSession(MessageTransferer transferer) {
		this.transferer = transferer;
		transferer.start();
		transferer.sendData(Message.create()
				.setCommandID(MessageCommand.USER_LOG_IN).setSender(name));
		listener.setConectionStatus(MessageCommand.USER_CONECTED);
	}

	/**
	 * Stop connection whit remote server.
	 */
	@Override
	public void closeUserSession(MessageTransferer transferer) {
		transferer.closeSocket();
		stopConection();
	}

	/**
	 * Process messages.
	 */
	@Override
	public void processMesage(Message message, MessageTransferer transferer) {
		if (message.commandID == MessageCommand.TEXT_MESAGE) {
			listener.showMesage(message.sender, message.text);
			return;
		}
		if (message.commandID == MessageCommand.MESSAGE_SEEN) {
			listener.showMessageReadedNotification(message.sender);
			return;
		}
		if (message.commandID == MessageCommand.INVALID_USER_NAME) {
			listener.setConectionStatus(MessageCommand.INVALID_USER_NAME);
			return;
		}
		if (message.commandID == MessageCommand.USER_CONECTED) {
			listener.addUser(message.sender);
			return;
		}
		if (message.commandID == MessageCommand.USER_DISCONECTED) {
			listener.removeUser(message.sender);
			return;
		}
	}

	/**
	 * Send messages to remote server.
	 * 
	 * @param message
	 *            the message that will be send.
	 */
	public void sendMesage(Message message) {
		if (transferer != null) {
			message.sender = name;
			transferer.sendData(message);
		}
	}

	/**
	 * Set user name.
	 * 
	 * @param name
	 *            the name of user.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
