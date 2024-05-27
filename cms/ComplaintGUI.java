package cms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComplaintGUI implements ActionListener, WindowListener {
	private JFrame win;
	private JButton menuBtns[];
	private String loggedInEmail;
	private JLabel profileLabel;

	public ComplaintGUI(String loggedInEmail) {
		this.loggedInEmail=loggedInEmail;
		win = new JFrame();
		
		// Heading Panel
        JPanel headingPanel = new JPanel();
        JLabel headingLabel = new JLabel("<html><center>Student Dashboard</center></html>");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setBackground(new Color(173, 216, 230));
        headingLabel.setOpaque(true);
        headingPanel.add(headingLabel);
        win.add(headingPanel, BorderLayout.CENTER);
        
        
     // Menu bar 
        JMenuBar menuBar = new JMenuBar();

        // Options menu
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to handle logout
                win.dispose(); // Close the student dashboard window
                new StudentLogin(); // Redirect to login screen
            }
        });
        optionsMenu.add(logoutMenuItem);
        menuBar.add(optionsMenu);

        // Profile menu
        JMenu profileMenu = new JMenu("Logged in as: ");
        profileMenu.setEnabled(true); // To make it non-clickable
        profileMenu.add(loggedInEmail);
        menuBar.add(Box.createHorizontalGlue()); // To push the profile menu to the right
        menuBar.add(profileMenu);

        win.setJMenuBar(menuBar);

		win.setTitle("Complaint Box");
		win.setSize(500, 600);
		win.addWindowListener(this);
		win.setLayout(new GridLayout(5, 1));

		menuBtns = new JButton[4];
		for (int i = 0; i < menuBtns.length; ++i) {
			menuBtns[i] = new JButton();
			win.add(menuBtns[i]);
			menuBtns[i].addActionListener(this);
		}
		menuBtns[0].setText("1. Register a Complaint");
		menuBtns[1].setText("2. Status of Complaint");
		menuBtns[2].setText("3. View all complaints");
		menuBtns[3].setText("4. View Profile");
		win.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (menuBtns[0] == e.getSource()) {
			new compRegister(loggedInEmail);
		} else if (menuBtns[1] == e.getSource()) {
			new compStatus(loggedInEmail);
		} else if (menuBtns[2] == e.getSource()) {
				new compCheck();
		} else if (menuBtns[3] == e.getSource()) {
			new StudentProfile(loggedInEmail);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		win.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		new ComplaintGUI("bharti017mca23@igdtuw.ac.in");
	}
}
