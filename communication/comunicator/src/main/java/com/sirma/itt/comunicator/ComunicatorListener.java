package com.sirma.itt.comunicator;

/**
 * Receive messages from communicator object.
 * 
 * @author GecaTM
 * 
 */
public interface ComunicatorListener {

	/**
	 * When connection status is changed.
	 */
	public void setConectionStatus(MessageCommand status);

	/**
	 * When new message arrive.
	 */
	public void showMesage(String name, String text);

	/**
	 * When new user is connected.
	 */
	public void addUser(String name);

	/**
	 * When user leave.
	 */
	public void removeUser(String name);
}
