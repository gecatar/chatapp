package com.sirma.itt.chat.client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.sirma.itt.comunicator.ComponentID;

/**
 * List whit connected users.
 * 
 * @author GecaTM
 *
 */
public class UserList extends JList<String> implements MouseListener {

	private final DefaultListModel<String> listModel = new DefaultListModel<String>();
	private final View view;

	public void setLocalDetails(ResourceBundle bundle) {
		Border border = new TitledBorder(
				bundle.getString(ComponentID.USER_LIST_BORDER_ID));
		setBorder(border);
	}

	public UserList(View view) {
		setModel(listModel);
		this.view = view;
		addMouseListener(this);
	}

	public boolean contains(String name) {
		return listModel.contains(name);
	}

	/**
	 * Add use.
	 */
	public void addUser(String name) {
		if (!listModel.contains(name)) {
			listModel.addElement(name);
		}
	}

	/**
	 * Remove user.
	 */
	public void removeUser(String name) {
		if (listModel.contains(name)) {
			listModel.removeElement(name);
		}
	}

	public void clear() {
		listModel.clear();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("User List:");
		for (Object name : listModel.toArray()) {
			stringBuilder.append("->").append(name);
		}
		return stringBuilder.toString();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			String selectedName = getSelectedValue();
			if (selectedName != null) {
				view.showConversation(getSelectedValue());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}
