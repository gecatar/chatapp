package com.sirma.itt.chat.client.test;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.sirma.itt.chat.client.ClientComunicator;
import com.sirma.itt.chat.client.View;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageCommand;
import com.sirma.itt.comunicator.MessageTransferer;

@RunWith(MockitoJUnitRunner.class)
public class ClientComunicatorTest {

	@Mock
	View listener;
	ClientComunicator client;
	@Mock
	MessageTransferer transferer;

	@Before
	public void setUp() throws Exception {
		Mockito.when(transferer.readMessage()).thenThrow(new IOException());
	}

	/**
	 * Check behavior when adding user session.
	 */
	@Test
	public void addUserSessionTest() throws ClassNotFoundException, IOException {
		client = new ClientComunicator(listener);
		client.addUserSession(transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).setConectionStatus(
				Mockito.any(MessageCommand.class));
		Mockito.verify(transferer, Mockito.atLeast(1)).sendData(
				Mockito.any(Message.class));
	}

	/**
	 * Check behavior when removing user session.
	 */
	@Test
	public void closeUserSessionTest() {
		client = new ClientComunicator(listener);
		client.addUserSession(transferer);
		client.closeUserSession(transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).setConectionStatus(
				Mockito.any(MessageCommand.class));
		Mockito.verify(transferer, Mockito.atLeast(1)).closeSocket();
	}

	@Test
	public void proccesMessageTest() {
		client = new ClientComunicator(listener);
		client.processMesage(new Message(MessageCommand.INVALID_USER_NAME),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).setConectionStatus(
				Mockito.any(MessageCommand.class));
		client.processMesage(new Message(MessageCommand.TEXT_MESAGE),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).showMesage(
				Mockito.anyString(), Mockito.anyString());
		client.processMesage(new Message(MessageCommand.USER_CONECTED),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).addUser(
				Mockito.anyString());
		client.processMesage(new Message(MessageCommand.MESSAGE_SEEN),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1))
				.showMessageReadedNotification(Mockito.anyString());
		client.processMesage(new Message(MessageCommand.USER_DISCONECTED),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).removeUser(
				Mockito.anyString());
	}
}
