package de.cfp.modder.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import de.cfp.modder.util.Constants;
import de.cfp.modder.util.FileUtils;

@SuppressWarnings("serial")
public class MainGUI extends JFrame {

	private JPanel contentPane;
	public static JLabel lblLog;
	private static JList list;
	
	public MainGUI() {
		setTitle("Modder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreate = new JButton("Create Profile");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateProfileGUI();
			}
		});
		btnCreate.setBounds(159, 44, 125, 23);
		contentPane.add(btnCreate);
		
		JButton btnApply = new JButton("Apply Mods");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!FileUtils.profileExists(list.getSelectedValue().toString())) {
					System.out.println("Profile doesn't exist");
					lblLog.setText("Profile doesn't exist");
					return;
				}
				FileUtils.applyMods(list.getSelectedValue().toString());
				lblLog.setText("Applied Mods");
			}
		});
		btnApply.setBounds(317, 44, 133, 23);
		contentPane.add(btnApply);
		
		JButton btnDelete = new JButton("Delete Profile");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!FileUtils.profileExists(list.getSelectedValue().toString())) {
					System.out.println("Profile doesn't exist");
					lblLog.setText("Profile doesn't exist");
					return;
				}
				FileUtils.deleteProfile(list.getSelectedValue().toString());
				System.out.println("Deleted Profile");
				lblLog.setText("Deleted Profile");
				updateList();
			}
		});
		btnDelete.setBounds(159, 78, 125, 23);
		contentPane.add(btnDelete);
		
		JButton btnOpen = new JButton("Open Profile Folder");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!FileUtils.profileExists(list.getSelectedValue().toString())) {
					System.out.println("Profile doesn't exist");
					lblLog.setText("Profile doesn't exist");
					return;
				}
				FileUtils.openProfileFolder(list.getSelectedValue().toString());
			}
		});
		btnOpen.setBounds(317, 78, 133, 23);
		contentPane.add(btnOpen);
		
		lblLog = new JLabel("");
		lblLog.setBounds(159, 19, 195, 14);
		contentPane.add(lblLog);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBounds(10, 11, 133, 137);
		list = new JList();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		panel.add(scrollPane);
		
		contentPane.add(panel);
		updateList();


		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		
	}
	
	public static void updateList() {
		File f = new File(Constants.MODDER);

        String[] pathnames = f.list();
        DefaultListModel<String> l1 = new DefaultListModel<>();  
		for (String pathname : pathnames) {
			l1.addElement(pathname);
        }
		list.setModel(l1);
	}
}
