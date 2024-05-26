package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminProfile extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private compFile cfile;
	private JTextArea complaintTextArea;
    private JButton viewButton;
    // Add more components as needed

    public AdminProfile() {
    	String tmpPath = System.getProperty("java.io.tmpdir");
		cfile = new compFile(tmpPath + "comps.txt");

        initComponents();
    }

    private void initComponents() {
        setTitle("Admin Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        complaintTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(complaintTextArea);
        add(scrollPane, BorderLayout.CENTER);

        viewButton = new JButton("View Complaints");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to retrieve and display complaints
                displayComplaints();
            }
        });
        add(viewButton, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    private void displayComplaints() {
    	//new compReport(cfile);
    }
}
