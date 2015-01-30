package com.sirma.itt.comunicator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Transfer messages between two DataTranferators.
 * 
 * @author Evgeni Stefanov
 * 
 */
public class MessageTranferer extends Thread {

	private static final Logger LOGGER = Logger
			.getLogger(MessageTranferer.class.getName());
	private final Communicator communicator;
	private final Socket socket;
	private final ObjectOutputStream ost;
	private final ObjectInputStream ist;

	public MessageTranferer(Communicator communicator, Socket socket,
			ObjectOutputStream ost, ObjectInputStream ist) {
		this.communicator = communicator;
		this.socket = socket;
		this.ost = ost;
		this.ist = ist;
	}

	/**
	 * Send message.
	 */
	public boolean sendData(Message message) {
		try {
			ost.writeObject(message);
			ost.flush();
			return true;
		} catch (IOException e) {
			// communicator.closeUserSession(this);
			LOGGER.log(Level.INFO, "User lost conection.", e);
			return false;
		}
	}

	/**
	 * Read messages from input stream.dfdr.
	 */
	public Message readMessage() throws ClassNotFoundException, IOException {
		return (Message) ist.readObject();
	}

	/**
	 * Entry point for receiving messages.
	 */
	@Override
	public void run() {
		try {
			while (true) {
				// communicator.processMesage(readMessage(), this);
			}
		} catch (IOException | ClassNotFoundException e) {
			LOGGER.log(Level.INFO, "Error when reading.", e);
		} finally {
			// communicator.closeUserSession(this);
		}
	}

	/**
	 * Close socket.
	 */
	public void closeSocket() {
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			LOGGER.log(Level.INFO, "Closing socket error.", e);
		}
	}
}
