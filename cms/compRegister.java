package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class compRegister extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel name, mobile, email, dept,course, complaint,complaintArea;
    JTextField nameField,mobileField, emailField, deptField;
    JFrame f1;
    JTextArea textArea;
    JCheckBox terms;
    JButton submit;
    JLabel msg;
    JComboBox<?> ComplaintType;
    JComboBox<?> courseType;
    JTextArea screen;

    compRegister(String loggedInEmail){
        setTitle("Complaint Registration From");
        setSize(700,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);

        name = new JLabel("Name");
        name.setBounds(20,50,100,20);
        c.add(name);

        nameField=new JTextField();
        nameField.setBounds(130,50,100,20);
        c.add(nameField);

        mobile = new JLabel("Mobile Number");
        mobile.setBounds(20,100,100,20);
        c.add(mobile);

        mobileField=new JTextField(10);
        mobileField.setBounds(130,100,100,20);
        c.add(mobileField);

        email = new JLabel("Email Id");
        email.setBounds(20,150,100,20);
        c.add(email);

        emailField=new JTextField();
        emailField.setBounds(130,150,100,20);
        c.add(emailField);

        course =new JLabel("Course");
        course.setBounds(20,200,100,20); 
        c.add(course);
        
        String[] Course ={"B.Tech", "MCA", "M.Tech", "MBA", "PhD"};

        courseType =new JComboBox<Object>(Course);
        courseType.setBounds(130,200,100,20);
        c.add(courseType);
        
        dept = new JLabel("Department");
        dept.setBounds(20,250,100,20);
        c.add(dept);

        deptField=new JTextField();
        deptField.setBounds(130,250,100,20);
        c.add(deptField);
        

        complaint = new JLabel("Complaint Type");
        complaint.setBounds(20,300,100,20);
        c.add(complaint);

        String[] Complaint ={"Cleaning", "Maintenance", "Stationary", "Infrastructure", "Technical"};

        ComplaintType =new JComboBox<Object>(Complaint);
        ComplaintType.setBounds(130,300,100,20);
        c.add(ComplaintType);
        
        complaintArea = new JLabel("Complaint");
        complaintArea.setBounds(20,350,100,20);
        c.add(complaintArea);
        

        textArea=new JTextArea();
        textArea.setBounds(130, 350, 200 ,50);
        c.add(textArea);


        terms =new JCheckBox("Please Check all the Details");
        terms.setBounds(50,430, 250 ,20);
        c.add(terms);

        submit=new JButton("SUBMIT");
        submit.setBounds(150, 480, 80, 20);
        c.add(submit);

        submit.addActionListener(this);

        screen = new JTextArea();
        screen.setBounds(350,50,300,300);
        c.add(screen);

        msg=new JLabel("");
        msg.setBounds(20, 500, 250,20);
        c.add(msg);
        c.setBackground(new Color(173, 216, 230));
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Bharti@304");
            String query = "SELECT sname, email FROM student_info WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, loggedInEmail);
            ResultSet rs = pstmt.executeQuery();

            // If user information is found, populate the name and email fields
            if(rs.next()) {
                nameField.setText(rs.getString("sname"));
                emailField.setText(rs.getString("email"));
                
             // Disable editing of name and email fields
                nameField.setEditable(false);
                emailField.setEditable(false);
            }

            // Close resources
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(terms.isSelected()){
            msg.setText("Submission Completed !!");
            
            if (nameField.getText().isEmpty() || mobileField.getText().isEmpty() || emailField.getText().isEmpty() || deptField.getText().isEmpty() || textArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop further processing if required fields are empty
            }

            String name = nameField.getText();
            String Mobile  = mobileField.getText();
            String Email = email.getText();
            String Department = dept.getText();
            String Complaint_Type= (String)ComplaintType.getSelectedItem();
            String Course=(String)courseType.getSelectedItem();
            String Complaint = textArea.getText();
            
            
         // Insert into database
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Bharti@304");
                String query = "INSERT INTO complaints (name, mobile, email, course, dept, complaint_type, complaint) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setString(2, Mobile);
                pstmt.setString(3, Email);
                pstmt.setString(4, Course);
                pstmt.setString(5, Department);
                pstmt.setString(6, Complaint_Type);
                pstmt.setString(7, Complaint);
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            screen.setText("Name: " + name + "\n" +
               "Mobile Number: " + Mobile + "\n" +
               "Email Id: " + Email + "\n" +
               "Department: " + Department + "\n" +
               "Course: " + Course + "\n" +
               "Complaint Type: " + Complaint_Type + "\n" +
               "Complaint: " + Complaint + "\n");

        }else{
        	JOptionPane.showMessageDialog(this, "Please check the box to accept the submission.", "Error", JOptionPane.ERROR_MESSAGE);
            screen.setText("");
        }
    }
}
