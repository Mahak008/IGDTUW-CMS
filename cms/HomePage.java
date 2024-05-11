package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton studentButton;
    private JButton adminButton;
    private JLabel headingLabel;

    public HomePage() {
        setTitle("Home Page");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Heading Panel
        JPanel headingPanel = new JPanel();
        headingLabel = new JLabel("<html><center>Indira Gandhi Delhi Technical University For Women<br>Complaint Management Portal</center></html>");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headingPanel.add(headingLabel);
        container.add(headingPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30)); // Center-aligned with gaps
        studentButton = new JButton("Student");
        studentButton.addActionListener(this);
        formPanel.add(studentButton);

        adminButton = new JButton("Admin");
        adminButton.addActionListener(this);
        formPanel.add(adminButton);

        container.add(formPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == studentButton) {
            dispose(); // Close the current window
            new StudentRegistrationForm(); // Open the student login page
        } else if (e.getSource() == adminButton) {
            dispose(); // Close the current window
            new AdminLogin(); // Open the admin login page
        }
    }

}

