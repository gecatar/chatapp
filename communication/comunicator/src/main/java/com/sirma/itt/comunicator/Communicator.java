package com.sirma.itt.comunicator;

/**
 * Allow connection between Client Server objects.
 * 
 * @author Evgeni Stefanov
 * 
 */
public interface Communicator {

	/**
	 * Start connection.
	 * 
	 * @param ip
	 * @param port
	 */
	public void startConection(String ip, int port);

	/**
	 * Stop connection.
	 */
	public void stopConection();

	/**
	 * Add user.
	 * 
	 * @param transferator
	 */
	public void addUserSession(MessageTransferer transferer);

	/**
	 * Remove user.
	 * 
	 * @param transferator
	 */
	public void closeUserSession(MessageTransferer transferer);

	/**
	 * Process message.
	 * 
	 * @param mesage
	 * @param transferator
	 */
	public void processMesage(Message message, MessageTransferer transferer);

	/**
	 * Start connection whit remote server.
	 * 
	 * @param ip
	 *            the address remote server.
	 * @param port
	 *            the port remote server.
	 */
	public void connect(String ip, int port);

}