package cms;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.*;

class compCheck extends JFrame {
    private static final long serialVersionUID = 1L;
    JTable table;
    JScrollPane scrollPane;

    compCheck() {
        setTitle("View All Complaints");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // Table headers
        String[] columnNames = {"Complaint Number", "Complaint Type", "Complaint", "Status", "Course", "Department"};

        // Fetch data from database
        String[][] data = fetchData();

        // Create table with data
        table = new JTable(data, columnNames);

        // Customize table header
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        table.getTableHeader().setDefaultRenderer(new HeaderRenderer(table));

        scrollPane = new JScrollPane(table);
        c.add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private String[][] fetchData() {
        String[][] data = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Bharti@304");
            String query = "SELECT cno, complaint_type, complaint, status, course, dept FROM complaints";
            PreparedStatement pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstmt.executeQuery();

            // Determine number of rows
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            // Initialize data array
            data = new String[rowCount][6];
            int row = 0;

            while (rs.next()) {
                data[row][0] = rs.getString("cno");
                data[row][1] = rs.getString("complaint_type");
                data[row][2] = rs.getString("complaint");
                data[row][3] = rs.getString("status");
                data[row][4] = rs.getString("course");
                data[row][5] = rs.getString("dept");
                row++;
            }

            // Close resources
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    // Custom header renderer
    class HeaderRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        public HeaderRenderer(JTable table) {
            setHorizontalAlignment(JLabel.CENTER);
            setForeground(table.getTableHeader().getForeground());
            setBackground(table.getTableHeader().getBackground());
            setFont(table.getTableHeader().getFont());
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            c.setFont(c.getFont().deriveFont(Font.BOLD));
            return c;
        }
    }
}
