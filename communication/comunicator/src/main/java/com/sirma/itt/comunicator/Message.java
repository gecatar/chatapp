package com.sirma.itt.comunicator;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MessageCommand commandID;
	public String sender;
	public String receiver;
	public String text;

	public Message(String receiver, String text) {
		this.receiver = receiver;
		this.text = text;
		commandID = MessageCommand.TEXT_MESAGE;
	}

	public Message(MessageCommand commandID) {
		this.commandID = commandID;
	}

	public Message(MessageCommand commandID, String name) {
		this.commandID = commandID;
		receiver = name;
	}

	public Message(String name, MessageCommand commandID) {
		this.commandID = commandID;
		sender = name;
	}
}
