package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentRegistrationForm extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField enrolmentNumberField, emailField, nameField;
    JComboBox<String> departmentComboBox;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField;

    public StudentRegistrationForm() {
        setTitle("Student Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        JLabel headingLabel = new JLabel("Student Registration Form", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel enrolmentNumberLabel = new JLabel("Enrolment Number:");
        enrolmentNumberField = new JTextField();
        
        JLabel emailLabel = new JLabel("Official Email:");
        emailField = new JTextField();

        JLabel departmentLabel = new JLabel("Department:");
        String[] departmentOptions = {"MCA", "B.Tech", "M.Tech", "PhD", "MBA"};
        departmentComboBox = new JComboBox<>(departmentOptions);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField();
        
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(enrolmentNumberLabel);
        formPanel.add(enrolmentNumberField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(departmentLabel);
        formPanel.add(departmentComboBox);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(confirmPasswordLabel);
        formPanel.add(confirmPasswordField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
        	String name=nameField.getText();
            String enrolmentNumber = enrolmentNumberField.getText();
            String email=emailField.getText();
            String department = (String) departmentComboBox.getSelectedItem();
            char[] passwordChars = passwordField.getPassword();
            char[] confirmPasswordChars = confirmPasswordField.getPassword();

            String password = new String(passwordChars);
            String confirmPassword = new String(confirmPasswordChars);
            
            if(name.isEmpty() || enrolmentNumber.isEmpty() || email.isEmpty() || department.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
            {
            	JOptionPane.showMessageDialog(this, "Please fill out all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            	return;
            }
            
            if (!email.endsWith("@igdtuw.ac.in")) {
                JOptionPane.showMessageDialog(this, "Only college official email required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(password.equals(confirmPassword)) {
            DataBaseManager.insertStudent(name,enrolmentNumber, email,department, password);
            JOptionPane.showMessageDialog(this, "Details saved successfully.");
            }
            else {
            	JOptionPane.showMessageDialog(this, "Password should be matched with confirm password.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            System.out.println("Name: " + name);
            System.out.println("Enrolment Number: " + enrolmentNumber);
            System.out.println("Official Email: " + email);
            System.out.println("Department: " + department);
            System.out.println("Password: " + password);
            System.out.println("Confirm Password: " + confirmPassword);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);

        JLabel alreadyHaveAccountLabel = new JLabel("Already have an account?");
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentLogin();
            }
        });
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginPanel.add(alreadyHaveAccountLabel);
        loginPanel.add(loginButton);

        JPanel submitAndLoginPanel = new JPanel(new GridLayout(2, 1));
        submitAndLoginPanel.add(buttonPanel);
        submitAndLoginPanel.add(loginPanel);

        add(headingLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(submitAndLoginPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

}