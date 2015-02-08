package com.sirma.itt.chat.client.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.sirma.itt.chat.client.MessageNotifyer;

/**
 * Check message notification functionality.
 * 
 * @author GecaTM
 *
 */
public class MessageNotifyerTest {

	private MessageNotifyer notifyer;

	@Before
	public void setUp() throws Exception {
		notifyer = new MessageNotifyer("", "");
	}

	/**
	 * Check correct adding on notifications.
	 */
	@Test
	public void addNotificationTest() {
		notifyer.addNotification("test");
		assertEquals(" 1 ->test", notifyer.getNotificationText());
	}

	/**
	 * Check removing notifications.
	 */
	@Test
	public void removeNotificationTest() {
		notifyer.addNotification("test");
		notifyer.removeNotification("test");
		assertEquals(" 0 ", notifyer.getNotificationText());
	}

	/**
	 * Check has notification.
	 */
	@Test
	public void hasNotificationTest() {
		notifyer.addNotification("test");
		assertTrue(notifyer.hasNotifications("test"));
		notifyer.removeNotification("test");
		assertFalse(notifyer.hasNotifications("test"));
	}
}
