package com.sirma.itt.chat.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sirma.itt.comunicator.AsynchConnectionRunner;
import com.sirma.itt.comunicator.Communicator;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageCommand;
import com.sirma.itt.comunicator.MessageTransferer;

/**
 * Receive connections from clients and send messages.
 * 
 * @author GecaTM
 *
 */
public class ServerComunicator implements Communicator {

	private static final Logger LOGGER = Logger
			.getLogger(ServerComunicator.class.getName());
	private final ConectionPanel listener;
	private final UserManager userManager = new UserManager();
	private ServerSocket serverSocket;
	private String name;
	private boolean conecting;

	public ServerComunicator(ConectionPanel listener) {
		this.listener = listener;
	}

	/**
	 * Start receiving connections from clients.
	 */
	public synchronized void startConection(String ip, int port) {
		if (!conecting) {
			conecting = true;
			listener.setConectionStatus(MessageCommand.COMUNICATOR_CONECTING);
			new AsynchConnectionRunner(this, ip, port).start();
		}
	}

	/**
	 * Open port and wait for connection.
	 */
	public boolean connect(String ip, int port) {
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				LOGGER.log(Level.INFO, "New User connected");
				addUserSession(new MessageTransferer(this, socket,
						new ObjectOutputStream(socket.getOutputStream()),
						new ObjectInputStream(socket.getInputStream())));
			}
		} catch (IOException e) {
			LOGGER.log(Level.INFO, e);
		} finally {
			stopConection();
		}
		return false;
	}

	/**
	 * Stop receiving connections and close all connections.
	 */
	public synchronized void stopConection() {
		if (conecting) {
			conecting = false;
			listener.setConectionStatus(MessageCommand.COMUNICATOR_DISCONECTED);
			userManager.removeUsersSessions();
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					LOGGER.log(Level.INFO, e);
				}
			}
		}
	}

	/**
	 * Start DataTransferator. Add user to unregistered user list.
	 */
	public void addUserSession(MessageTransferer transferer) {
		transferer.start();
	}

	/**
	 * Close User DataTransferator, remove user from list and notify all users.
	 */
	public void closeUserSession(MessageTransferer transferer) {
		transferer.closeSocket();
		listener.removeUser(userManager.getUserName(transferer));
		userManager.removeUser(transferer);
	}

	/**
	 * Redirect message.
	 */
	public void processMesage(Message message, MessageTransferer transferer) {
		if (message.commandID == MessageCommand.USER_LOG_IN) {
			userManager.registerUser(message.sender, transferer);
			listener.addUser(userManager.getUserName(transferer));
			return;
		}
		userManager.sendMesageToUser(message);
	}
}
