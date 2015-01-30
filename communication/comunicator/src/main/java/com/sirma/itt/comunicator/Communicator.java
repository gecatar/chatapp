package com.sirma.itt.comunicator;

/**
 * Allow connection between Client Server objects.
 * 
 * @author Evgeni Stefanov
 * 
 */
public interface Communicator {

	/**
	 * Start conection.
	 * 
	 * @param ip
	 * @param port
	 */
	public void startConection(String ip, int port);

	/**
	 * Stop conection.
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
	 * Stop connection attempts.
	 */
	public void closeConectorSession();

	/**
	 * Set name on comunicator.
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * Process message.
	 * 
	 * @param mesage
	 * @param transferator
	 */
	public void processMesage(Message message, MessageTransferer transferer);

	/**
	 * Send message.
	 * 
	 * @param name
	 * @param text
	 */
	public void sendMesage(Message message);

}