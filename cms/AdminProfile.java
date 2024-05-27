package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminProfile extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel complaintPanel;
    private JButton refreshButton,viewComplaintButton;

    public AdminProfile() {
        initComponents();
        loadComplaints();
    }

    private void initComponents() {
        setTitle("Admin Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a top panel with profile picture and welcome message
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(173, 216, 230)); // Light blue background

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome, Admin!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Center panel to display complaints
        complaintPanel = new JPanel();
        complaintPanel.setLayout(new BoxLayout(complaintPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(complaintPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with refresh button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(173, 216, 230));
        
        viewComplaintButton=new JButton("View All Complaints");
        viewComplaintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new compCheck();
            }
        });
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadComplaints();
            }
        });
        bottomPanel.add(viewComplaintButton);
        bottomPanel.add(refreshButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Menu bar for additional options
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to handle logout
                dispose(); // Close the admin profile window
                new AdminLogin(); // Redirect to login screen
            }
        });
        optionsMenu.add(logoutMenuItem);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);

        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    private void loadComplaints() {
        complaintPanel.removeAll();
        complaintPanel.revalidate();
        complaintPanel.repaint();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Bharti@304");
            String query = "SELECT * FROM complaints WHERE status = 'Pending'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                String complaintType = rs.getString("complaint_type");
                String complaintText = rs.getString("complaint");
                int complaintId = rs.getInt("cno");

                
                JPanel complaintEntry = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout to align components left
                complaintEntry.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                complaintEntry.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));


                JLabel nameLabel = new JLabel("Name: " + name);
                JLabel typeLabel = new JLabel("Type: " + complaintType);
                JTextArea complaintArea = new JTextArea(complaintText);
                complaintArea.setEditable(false);
                JButton approveButton = new JButton("Approve");
                approveButton.addActionListener(new ApproveButtonListener(complaintId));
                
                JButton rejectButton = new JButton("Reject");
                rejectButton.addActionListener(new RejectButtonListener(complaintId));

             // Add components to the complaint entry panel
                complaintEntry.add(nameLabel);
                complaintEntry.add(typeLabel);
                complaintEntry.add(complaintArea);
                complaintEntry.add(rejectButton);
                complaintEntry.add(approveButton);

                complaintPanel.add(complaintEntry);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private class ApproveButtonListener implements ActionListener {
        private int complaintId;

        public ApproveButtonListener(int complaintId) {
            this.complaintId = complaintId;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Bharti@304");
                String query = "UPDATE complaints SET status = 'Approved' WHERE cno = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, complaintId);
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
                loadComplaints(); // Reload complaints after approval
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    private class RejectButtonListener implements ActionListener {
        private int complaintId;

        public RejectButtonListener(int complaintId) {
            this.complaintId = complaintId;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Bharti@304");
                String query = "UPDATE complaints SET status = 'Rejected' WHERE cno = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, complaintId);
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
                loadComplaints(); // Reload complaints after rejection
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
