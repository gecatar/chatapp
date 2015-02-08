package com.sirma.itt.chat.client;

import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import com.sirma.itt.comunicator.ComponentID;

/**
 * Create and select users conversations.
 * 
 * @author Evgeni Stefanov
 * 
 */
public class ConversationPanel extends JTabbedPane {

	private static final Icon userOnlineIcon = new ImageIcon(
			ConversationPanel.class.getResource("/user-green-icon.png"));
	private static final Icon userOflineIcon = new ImageIcon(
			ConversationPanel.class.getResource("/user-red-icon.png"));
	private static final Icon checkedMessageIcon = new ImageIcon(
			ConversationPanel.class.getResource("/mail-check-icon.png"));
	private static final Icon newMessageIcon = new ImageIcon(
			ConversationPanel.class.getResource("/mail-icon.png"));

	public void showConectionStatusIcon(Conversation conversation) {
		if (conversation.isConected()) {
			showOnlineIcon(conversation);
		} else {
			showOfflineIcon(conversation);
		}
	}

	/**
	 * Show online icon.
	 * 
	 * @param conversation
	 */
	public void showOnlineIcon(Conversation conversation) {
		setIconAt(indexOfComponent(conversation), userOnlineIcon);
	}

	/**
	 * Show offline icon.
	 * 
	 * @param conversation
	 */
	public void showOfflineIcon(Conversation conversation) {
		setIconAt(indexOfComponent(conversation), userOflineIcon);
	}

	/**
	 * Show checked message icon.
	 * 
	 * @param conversation
	 */
	public void showCheckedMessageIcon(Conversation conversation) {
		setIconAt(indexOfComponent(conversation), checkedMessageIcon);
	}

	/**
	 * Show message icon.
	 * 
	 * @param conversation
	 */
	public void showNewMessageIcon(Conversation conversation) {
		setIconAt(indexOfComponent(conversation), newMessageIcon);
	}

	/**
	 * Set local details.
	 * 
	 * @param bundle
	 * @param statusTab
	 */
	public void setLocalDetails(ResourceBundle bundle, StatusTab statusTab) {
		addTab(bundle.getString(ComponentID.STATUS_TAB_ID), statusTab);
	}
}