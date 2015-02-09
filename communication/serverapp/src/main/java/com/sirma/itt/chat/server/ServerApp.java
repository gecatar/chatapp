package com.sirma.itt.chat.server;

import org.apache.log4j.Logger;

import com.sirma.itt.comunicator.ComunicatorShutdownHook;

/**
 * Start view and server.
 * 
 * @author GecaTM
 *
 */
public class ServerApp {

	private static final Logger LOGGER = Logger.getLogger(ServerApp.class
			.getName());
	private final ConectionPanel view = new ConectionPanel(this);
	private final ServerComunicator comunicator = new ServerComunicator(view);

	public ServerApp() {
		Runtime.getRuntime().addShutdownHook(
				new ComunicatorShutdownHook(comunicator));
	}

	/**
	 * When user press connect button.
	 * 
	 * @param ip
	 * @param port
	 */
	public void startConection(String ip, int port) {
		comunicator.startConection(ip, port);
	}

	/**
	 * When user press stop connection button.
	 */
	public void stopConnection() {
		comunicator.stopConection();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ServerApp();
	}

}
