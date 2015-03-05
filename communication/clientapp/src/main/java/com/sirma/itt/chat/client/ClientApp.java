package com.sirma.itt.chat.client;

import com.sirma.itt.comunicator.ComunicatorShutdownHook;
import com.sirma.itt.comunicator.Message;

/**
 * Create client communication and view.
 * 
 * @author GecaTM
 *
 */
public class ClientApp {

	private final View view = new View(this);
	private final ClientComunicator comunicator = new ClientComunicator(view);

	public ClientApp() {
		Runtime.getRuntime().addShutdownHook(
				new ComunicatorShutdownHook(comunicator));
	}

	/**
	 * When user press connect button.
	 * 
	 * @param name
	 *            the name of user
	 * @param ip
	 *            target IP address.
	 * @param port
	 *            target port.
	 */
	public void startConection(String name, String ip, int port) {
		comunicator.setName(name);
		comunicator.startConection(ip, port);
	}

	/**
	 * When user press stop connection button.
	 */
	public void stopConnection() {
		comunicator.stopConection();
	}

	/**
	 * When user send message.
	 * 
	 * @param message
	 *            the message for sending.
	 */
	public void sendMessage(Message message) {
		comunicator.sendMesage(message);
	}

	public static void main(String[] args) {
		new ClientApp();
	}

}
