package com.sirma.itt.comunicator;

/**
 * Open connection asynchronous.
 * 
 * @author GecaTM
 * 
 */
public class AsynchConnectionRunner extends Thread {

	private final Communicator comunicator;
	private final String ip;
	private final int port;

	/**
	 * Create thread that will start connection.
	 */
	public AsynchConnectionRunner(Communicator comunicator, String ip, int port) {
		this.comunicator = comunicator;
		this.ip = ip;
		this.port = port;
	}

	/**
	 * Start connection.
	 */
	@Override
	public void run() {
		comunicator.connect(ip, port);
	}
}