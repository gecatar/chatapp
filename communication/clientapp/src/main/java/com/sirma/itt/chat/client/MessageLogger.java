package com.sirma.itt.chat.client;

import java.util.ArrayList;

/**
 * Log messages.
 * 
 * @author root
 * 
 */
public class MessageLogger {

	private final ArrayList<String> messages = new ArrayList<String>();
	private int currentIndex = -1;

	/**
	 * Save messages.
	 */
	public void logMessage(String message) {
		if (!messages.contains(message)) {
			messages.add(message);
		}
		currentIndex = messages.size();
	}

	/**
	 * Return saved messages.
	 */
	public String getNextMessage() {
		if (containIndex(currentIndex - 1)) {
			currentIndex--;
			return messages.get(currentIndex);
		} else {
			currentIndex = -1;
			return "";
		}
	}

	/**
	 * Return saved messages.
	 */
	public String getPreviousMessage() {
		if (containIndex(currentIndex + 1)) {
			currentIndex++;
			return messages.get(currentIndex);
		} else {
			currentIndex = messages.size();
			return "";
		}
	}

	/**
	 * Return true if passed index is available.
	 */
	private boolean containIndex(int index) {
		return (index >= 0 && index < messages.size());
	}

	/**
	 * Print all messages.
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Messages:").append("\n");
		for (String message : messages) {
			stringBuilder.append(message).append("\n");
		}
		return stringBuilder.toString().trim();
	}
}