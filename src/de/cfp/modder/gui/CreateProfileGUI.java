package de.cfp.modder.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.cfp.modder.util.FileUtils;

public class CreateProfileGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	public CreateProfileGUI() {
		setTitle("Create Profile");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 177, 126);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 28, 140, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FileUtils.profileExists(textField.getText())) {
					System.out.println("Profile Exists.");
					MainGUI.lblLog.setText("Profile Exists");
					return;
				}
				FileUtils.createProfile(textField.getText());
				System.out.println("Created Profile");
				MainGUI.lblLog.setText("Created Profile");
				MainGUI.updateList();
				dispose();
			}
		});
		btnNewButton.setBounds(10, 59, 89, 23);
		contentPane.add(btnNewButton);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
	}

}
