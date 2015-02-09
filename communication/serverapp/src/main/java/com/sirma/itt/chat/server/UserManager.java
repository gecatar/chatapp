package com.sirma.itt.chat.server;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageCommand;
import com.sirma.itt.comunicator.MessageTransferer;

public class UserManager {

	private final Map<String, MessageTransferer> users = new ConcurrentHashMap<String, MessageTransferer>();

	/**
	 * Save user in list of registered users.
	 */
	public void registerUser(String name, MessageTransferer transferer) {
		if (!users.containsKey(name)) {
			users.put(name, transferer);
			sendUsersList(transferer);
			notifyForNewUser(name);
		} else {
			notifyForBadName(transferer);
			transferer.closeSocket();
		}
	}

	/**
	 * Remove user from map. Close connection and notify other users.
	 */
	public void removeUser(MessageTransferer transferer) {
		if (isUserInMap(transferer)) {
			String name = getUserName(transferer);
			users.remove(name);
			notifyForUserLeaving(name);
		}
	}

	/**
	 * Send message to specified user.
	 */
	public void sendMesageToUser(Message message) {
		if (users.containsKey(message.receiver)) {
			users.get(message.receiver).sendData(message);
		}
	}

	/**
	 * Close all user sessions.
	 */
	public void removeUsersSessions() {
		for (Entry<String, MessageTransferer> user : users.entrySet()) {
			user.getValue().closeSocket();
		}
		users.clear();
	}

	/**
	 * Notify registered users for new users registered.
	 */
	private void notifyForNewUser(String name) {
		Message message = new Message(name, MessageCommand.USER_CONECTED);
		sendMessageToAll(message);
	}

	/**
	 * Notify registered users for leaving users.
	 */
	private void notifyForUserLeaving(String name) {
		Message message = new Message(name, MessageCommand.USER_DISCONECTED);
		sendMessageToAll(message);
	}

	/**
	 * Send all registered users at moment of registering on user.
	 */
	private void sendUsersList(MessageTransferer transferer) {
		Set<String> keys = users.keySet();
		for (String key : keys) {
			transferer.sendData(new Message(key, MessageCommand.USER_CONECTED));
		}
	}

	/**
	 * Check is user in conversation list.
	 */
	private boolean isUserInMap(MessageTransferer transferer) {
		return (users.containsValue(transferer));
	}

	/**
	 * Return name of user.
	 */
	public String getUserName(MessageTransferer transferer) {
		for (Entry<String, MessageTransferer> user : users.entrySet()) {
			if (user.getValue() == transferer) {
				return user.getKey();
			}
		}
		return "";
	}

	/**
	 * Notify user for invalid user name.
	 */
	private void notifyForBadName(MessageTransferer transferer) {
		transferer.sendData(new Message(MessageCommand.INVALID_USER_NAME));
	}

	/**
	 * Send message to all users.
	 */
	private void sendMessageToAll(Message message) {
		for (Entry<String, MessageTransferer> user : users.entrySet()) {
			user.getValue().sendData(message);
		}
	}

	/**
	 * Print all user registered on server.
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Users:").append("\n");
		for (String key : users.keySet()) {
			stringBuilder.append(key).append("\n");
		}
		return stringBuilder.toString().trim();
	}
}
