package cms;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

import javax.swing.*;

public class StudentProfile {
    private JFrame profileFrame;
    private JLabel nameLabel, emailLabel, enrollLabel, deptLabel, profilePicLabel;
    private JButton uploadButton;

    public StudentProfile(String loggedInEmail) {
        profileFrame = new JFrame("Student Profile");
        profileFrame.setSize(600, 400);
        profileFrame.setLayout(new BorderLayout());

        // Profile Heading
        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(new Color(70, 130, 180)); // Steel blue color
        JLabel headingLabel = new JLabel("<html><center>Student Profile</center></html>");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setForeground(Color.WHITE);
        headingPanel.add(headingLabel);
        profileFrame.add(headingPanel, BorderLayout.NORTH);

        // Profile Details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(new Color(240, 248, 255)); // Alice blue color

        nameLabel = new JLabel("Name: ");
        enrollLabel = new JLabel("Enrollment Number: ");
        emailLabel = new JLabel("Email: ");
        deptLabel = new JLabel("Department: ");

        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        enrollLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        deptLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        detailsPanel.add(nameLabel);
        detailsPanel.add(enrollLabel);
        detailsPanel.add(emailLabel);
        detailsPanel.add(deptLabel);

        // Profile Picture Panel
        JPanel picturePanel = new JPanel();
        picturePanel.setLayout(new BorderLayout());
        picturePanel.setBackground(new Color(224, 255, 255)); // Light cyan color

        profilePicLabel = new JLabel();
        profilePicLabel.setHorizontalAlignment(JLabel.CENTER);
        uploadButton = new JButton("Upload Profile Picture");

        picturePanel.add(profilePicLabel, BorderLayout.CENTER);
        picturePanel.add(uploadButton, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(255, 248, 220)); // Cornsilk color
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        mainPanel.add(picturePanel, BorderLayout.EAST);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Bharti@304");
            String query = "SELECT sname, enrollment_number, email, department, profile_picture FROM student_info WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, loggedInEmail);
            ResultSet rs = pstmt.executeQuery();

            // If user information is found, populate the fields
            if (rs.next()) {
                nameLabel.setText("Name: " + rs.getString("sname"));
                enrollLabel.setText("Enrollment Number: " + rs.getString("enrollment_number"));
                emailLabel.setText("Email: " + rs.getString("email"));
                deptLabel.setText("Department: " + rs.getString("department"));

                // Load profile picture
                String profilePicPath = rs.getString("profile_picture");
                if (profilePicPath != null && !profilePicPath.isEmpty()) {
                    ImageIcon profilePic = new ImageIcon(profilePicPath);
                    profilePicLabel.setIcon(new ImageIcon(profilePic.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                }
            }

            // Close resources
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        // Update the profile picture label
                        ImageIcon profilePic = new ImageIcon(selectedFile.getAbsolutePath());
                        profilePicLabel.setIcon(new ImageIcon(profilePic.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

                        // Save the new profile picture path to the database
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Bharti@304");
                        String updateQuery = "UPDATE student_info SET profile_picture = ? WHERE email = ?";
                        PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                        pstmt.setString(1, selectedFile.getAbsolutePath());
                        pstmt.setString(2, loggedInEmail);
                        pstmt.executeUpdate();

                        // Close resources
                        pstmt.close();
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        profileFrame.add(mainPanel, BorderLayout.CENTER);
        profileFrame.setVisible(true);
    }

//    public static void main(String[] args) {
//        // For testing purposes
//        new StudentProfile("bharti017mca23@igdtuw.ac.in");
//    }
}
