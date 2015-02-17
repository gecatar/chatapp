package com.sirma.itt.comunicator;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	public MessageCommand commandID;
	public String sender;
	public String receiver;
	public String text;

	private Message() {
	}

	public static Message create() {
		return new Message();
	}

	public Message setCommandID(MessageCommand commandId) {
		this.commandID = commandId;
		return this;
	}

	public Message setSender(String sender) {
		this.sender = sender;
		return this;
	}

	public Message setReceiver(String receiver) {
		this.receiver = receiver;
		return this;
	}

	public Message setText(String text) {
		this.text = text;
		return this;
	}
}
