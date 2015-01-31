package com.sirma.itt.chat.client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sirma.itt.comunicator.ComponentID;

/**
 * Manage user connection request.
 * 
 * @author Evgeni Stefanov
 * 
 */
public class ConectionDialog extends JDialog implements ActionListener {

	private final JTextField ipAdress = new JTextField(15);
	private final JTextField port = new JTextField(4);
	private final JTextField nickname = new JTextField(10);
	private final JLabel conectionStatus = new JLabel();
	private final Dimension dialogDimension = new Dimension(400, 300);
	private final ClientApp listener;

	/**
	 * Create connection user interface.
	 */
	public ConectionDialog(ResourceBundle bundle, ClientApp listener) {
		this.listener = listener;
		setTitle(bundle.getString("conection_tab_name"));
		setLayout(new GridBagLayout());
		setSize(dialogDimension);
		conectionStatus.setText(bundle.getString("user_disconected_message"));
		ipAdress.setText("localhost");
		port.setText("1300");
		nickname.setText("User");
		JButton startConection = new JButton(
				bundle.getString("conect_button_name"));
		startConection.setName(ComponentID.CONECT_BUTTON_ID);
		JButton stopConection = new JButton(
				bundle.getString("disconect_button_name"));
		stopConection.setName(ComponentID.DISCONECT_BUTTON_ID);
		JLabel conectionStatusLabel = new JLabel(
				bundle.getString("conection_status_name"));
		JLabel ipLabel = new JLabel(bundle.getString("ip_label_name"));
		JLabel portLabel = new JLabel(bundle.getString("port_label_name"));
		JLabel nicknameLabel = new JLabel(
				bundle.getString("nickname_label_name"));
		nickname.setText("User");
		// -----------------------------------------------
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		add(conectionStatusLabel, gridBagConstraints);
		gridBagConstraints.gridx++;
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(conectionStatus, gridBagConstraints);
		gridBagConstraints.gridx--;
		gridBagConstraints.gridy++;
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		add(ipLabel, gridBagConstraints);
		gridBagConstraints.gridx++;
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ipAdress, gridBagConstraints);
		gridBagConstraints.gridx--;
		gridBagConstraints.gridy++;
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		add(portLabel, gridBagConstraints);
		gridBagConstraints.gridx++;
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(port, gridBagConstraints);
		gridBagConstraints.gridx--;
		gridBagConstraints.gridy++;
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		add(nicknameLabel, gridBagConstraints);
		gridBagConstraints.gridx++;
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(nickname, gridBagConstraints);
		gridBagConstraints.gridx--;
		gridBagConstraints.gridy++;
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		add(startConection, gridBagConstraints);
		gridBagConstraints.gridx++;
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(stopConection, gridBagConstraints);
	}

	@Override
	public String getName() {
		return nickname.getText();
	}

	public String getIP() {
		return ipAdress.getText();
	}

	public int getPort() {
		return Integer.parseInt(port.getText());
	}

	/**
	 * Display connection status.
	 */
	public void setConectionStatus(String conectionCondition) {
		conectionStatus.setText(conectionCondition);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof JButton) {
			JButton button = (JButton) event.getSource();
			if (button.getName().equals(ComponentID.CONECT_BUTTON_ID)) {
				listener.startConection(nickname.getText(), ipAdress.getText(),
						Integer.parseInt(port.getText()));
			}
			if (button.getName().equals(ComponentID.DISCONECT_BUTTON_ID)) {
				listener.stopConnection();
			}
		}
	}
}