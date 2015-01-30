package com.sirma.itt.comunicator;

import java.io.Serializable;

public class Message implements Serializable {

	public MessageCommand commandID;
	public String sender;
	public String receiver;
	public String text;

	public Message(String sender, String receiver, String text) {
		this.sender = sender;
		this.receiver = receiver;
		this.text = text;
		commandID = MessageCommand.TEXT_MESAGE;
	}

	public Message(MessageCommand commandID) {
		this.commandID = commandID;
	}

	public Message(String name, MessageCommand commandID) {
		this.commandID = commandID;
		sender = name;
	}
}
