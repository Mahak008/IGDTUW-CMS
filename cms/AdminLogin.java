package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLogin extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "Bharti@304";
	// Components
    private JLabel usernameLabel, passwordLabel, headingLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public AdminLogin() {
        setTitle("Student Login Form");
        setLocationRelativeTo(null);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Heading Panel
        JPanel headingPanel = new JPanel();
        headingLabel = new JLabel("<html><center>Admin Login</center></html>");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingPanel.add(headingLabel);
        add(headingPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*'); // Set password field to hide characters
        loginButton = new JButton("Login");

        // Add components to form panel
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(new JLabel()); // Empty label for alignment
        formPanel.add(loginButton);

        loginButton.addActionListener(this);

        // Add form panel to content pane
        add(formPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        
    	String username=usernameField.getText();
    	char[] pswd=passwordField.getPassword();
    	String password=new String(pswd);
        
    	try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
            	SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new AdminProfile().setVisible(true);
                    }
                });

               
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while processing your request. Please try again later.");
        }
    	finally {
            // Clear password field
            passwordField.setText("");
        }
    }
}