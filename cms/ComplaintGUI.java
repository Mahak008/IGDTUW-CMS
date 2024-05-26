package cms;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ComplaintGUI implements ActionListener, WindowListener {
	private JFrame win;
	private compFile cfile;
	private JButton menuBtns[];
	private String loggedInEmail;

	public ComplaintGUI(String loggedInEmail) {
		this.loggedInEmail=loggedInEmail;
		win = new JFrame();
		String tmpPath = System.getProperty("java.io.tmpdir");
		cfile = new compFile(tmpPath + "comps.txt");
		
		// Heading Panel
        JPanel headingPanel = new JPanel();
        JLabel headingLabel = new JLabel("<html><center>Student Dashboard</center></html>");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingPanel.add(headingLabel);
        win.add(headingPanel, BorderLayout.NORTH);

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
			new compStatus(cfile);
		} else if (menuBtns[2] == e.getSource()) {
				new compCheck(cfile);
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
		cfile.exit();
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
