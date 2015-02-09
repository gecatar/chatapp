package com.sirma.itt.comunicator;

/**
 * Close communicator when application is closed.
 *
 * @author GecaTM
 *
 */
public class ComunicatorShutdownHook extends Thread {
	private Communicator comunicator;

	/**
	 * Set communicator.
	 */
	public ComunicatorShutdownHook(Communicator comunicator) {
		this.comunicator = comunicator;
	}

	/**
	 * Start closing.
	 */
	@Override
	public void run() {
		comunicator.stopConection();
	}
}
