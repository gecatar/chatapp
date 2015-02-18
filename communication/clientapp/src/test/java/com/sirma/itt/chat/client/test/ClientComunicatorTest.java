package com.sirma.itt.chat.client.test;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.sirma.itt.chat.client.ClientComunicator;
import com.sirma.itt.chat.client.View;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageCommand;
import com.sirma.itt.comunicator.MessageTransferer;

@RunWith(MockitoJUnitRunner.class)
public class ClientComunicatorTest {

	View listener;
	ClientComunicator client;
	MessageTransferer transferer;

	@Before
	public void setUp() throws Exception {
		listener = Mockito.mock(View.class);
		client = Mockito.spy(new ClientComunicator(listener));
		transferer = Mockito.mock(MessageTransferer.class);

	}

	@Test
	public void startConnectionTest() {
		Mockito.when(client.connect("test", 0)).thenReturn(true);
		client.startConection("test", 0);
		client.startConection("test", 0);
		client.stopConection();
		client.startConection("test", 0);
		Mockito.verify(listener, Mockito.times(2)).setConectionStatus(
				MessageCommand.COMUNICATOR_CONECTING);
	}

	@Test
	public void stopConnectionTest() {
		Mockito.when(client.connect("test", 0)).thenReturn(true);
		client.stopConection();
		client.startConection("test", 0);
		client.stopConection();
		Mockito.verify(listener, Mockito.times(1)).setConectionStatus(
				MessageCommand.COMUNICATOR_DISCONECTED);
	}

	/**
	 * Test send message method.
	 */
	@Test
	public void sendMessageTest() {
		client.sendMesage(Message.create());
		client.addUserSession(transferer);
		client.sendMesage(Message.create());
		Mockito.verify(transferer, Mockito.atLeast(1)).sendData(
				Mockito.any(Message.class));
	}

	/**
	 * Check behavior when adding user session.
	 */
	@Test
	public void addUserSessionTest() throws ClassNotFoundException, IOException {
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
		client.addUserSession(transferer);
		client.closeUserSession(transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).setConectionStatus(
				Mockito.any(MessageCommand.class));
		Mockito.verify(transferer, Mockito.atLeast(1)).closeSocket();
	}

	@Test
	public void proccesMessageTest() {
		client.processMesage(
				Message.create().setCommandID(MessageCommand.INVALID_USER_NAME),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).setConectionStatus(
				Mockito.any(MessageCommand.class));
		client.processMesage(
				Message.create().setCommandID(MessageCommand.TEXT_MESAGE),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).showMesage(
				Mockito.anyString(), Mockito.anyString());
		client.processMesage(
				Message.create().setCommandID(MessageCommand.USER_CONECTED),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).addUser(
				Mockito.anyString());
		client.processMesage(
				Message.create().setCommandID(MessageCommand.MESSAGE_SEEN),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1))
				.showMessageReadedNotification(Mockito.anyString());
		client.processMesage(
				Message.create().setCommandID(MessageCommand.USER_DISCONECTED),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).removeUser(
				Mockito.anyString());
	}
}
