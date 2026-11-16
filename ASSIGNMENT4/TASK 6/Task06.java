import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Task06 extends JFrame {
    // Configure these values for your local MySQL installation.
    private static final String URL = "jdbc:mysql://localhost:3306/campus_events";
    private static final String USER = "root";
    private static final String PASSWORD = "YOUR_MYSQL_PASSWORD";

    private final JTextField studentId = new JTextField(12);
    private final JTextField studentName = new JTextField(12);
    private final JComboBox<String> event = new JComboBox<>(new String[]{"Hackathon","Cultural Fest","Tech Talk"});
    private final JTextField filter = new JTextField(12);
    private final DefaultTableModel model = new DefaultTableModel(new String[]{"ID","Student","Event"},0);
    private final JTable table = new JTable(model);
    private final JLabel status = new JLabel("Ready");

    public Task06() {
        super("Campus Event Registration");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8,8));
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem refresh = new JMenuItem("Refresh");
        JMenuItem exit = new JMenuItem("Exit");
        refresh.addActionListener(e -> loadRegistrations(""));
        exit.addActionListener(e -> dispose());
        file.add(refresh); file.add(exit); bar.add(file); setJMenuBar(bar);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Student ID")); form.add(studentId);
        form.add(new JLabel("Name")); form.add(studentName);
        form.add(new JLabel("Event")); form.add(event);
        JButton register = new JButton("Register");
        form.add(register);
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton applyFilter = new JButton("Filter");
        filterPanel.add(new JLabel("Event filter")); filterPanel.add(filter); filterPanel.add(applyFilter);
        JPanel north = new JPanel(new BorderLayout());
        north.add(form,BorderLayout.NORTH); north.add(filterPanel,BorderLayout.SOUTH);
        add(north,BorderLayout.NORTH);
        add(new JScrollPane(table),BorderLayout.CENTER);
        add(status,BorderLayout.SOUTH);

        register.addActionListener(e -> registerAsync());
        applyFilter.addActionListener(e -> loadRegistrationsAsync(filter.getText().trim()));
        setSize(850,500); setLocationRelativeTo(null);
    }
    private Connection connect() throws SQLException { return DriverManager.getConnection(URL,USER,PASSWORD); }

    private void registerAsync() {
        String id=studentId.getText().trim(), name=studentName.getText().trim(), ev=(String)event.getSelectedItem();
        if(id.isEmpty() || name.isEmpty()) { status.setText("Enter student ID and name."); return; }
        status.setText("Registering...");
        new SwingWorker<String,Void>() {
            protected String doInBackground() throws Exception {
                try(Connection c=connect();
                    PreparedStatement ps=c.prepareStatement("INSERT INTO registrations(student_id, student_name, event_name) VALUES(?,?,?)")) {
                    ps.setString(1,id); ps.setString(2,name); ps.setString(3,ev); ps.executeUpdate();
                }
                return "Registration successful.";
            }
            protected void done() {
                try { status.setText(get()); loadRegistrationsAsync(""); }
                catch(Exception ex){ status.setText("Registration failed (duplicate or database error): "+ex.getCause().getMessage()); }
            }
        }.execute();
    }
    private void loadRegistrations(String eventFilter) {
        model.setRowCount(0);
        String sql="SELECT student_id, student_name, event_name FROM registrations"+(eventFilter.isEmpty()?"":" WHERE event_name=?")+" ORDER BY id DESC";
        try(Connection c=connect(); PreparedStatement ps=c.prepareStatement(sql)) {
            if(!eventFilter.isEmpty()) ps.setString(1,eventFilter);
            try(ResultSet rs=ps.executeQuery()) {
                while(rs.next()) model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3)});
            }
            status.setText("Loaded registrations.");
        } catch(SQLException ex){ status.setText("Database error: "+ex.getMessage()); }
    }
    private void loadRegistrationsAsync(String eventFilter) {
        status.setText("Loading...");
        new SwingWorker<java.util.List<Object[]>,Void>() {
            protected java.util.List<Object[]> doInBackground() throws Exception {
                java.util.List<Object[]> rows=new java.util.ArrayList<>();
                String sql="SELECT student_id, student_name, event_name FROM registrations"+(eventFilter.isEmpty()?"":" WHERE event_name=?")+" ORDER BY id DESC LIMIT 100 OFFSET 0";
                try(Connection c=connect(); PreparedStatement ps=c.prepareStatement(sql)) {
                    if(!eventFilter.isEmpty()) ps.setString(1,eventFilter);
                    try(ResultSet rs=ps.executeQuery()) { while(rs.next()) rows.add(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3)}); }
                }
                return rows;
            }
            protected void done() {
                try { model.setRowCount(0); for(Object[] row:get()) model.addRow(row); status.setText("Loaded page (up to 100 rows)."); }
                catch(Exception ex){ status.setText("Could not load data: "+ex.getCause().getMessage()); }
            }
        }.execute();
    }
    public static void main(String[] args){ SwingUtilities.invokeLater(() -> new Task06().setVisible(true)); }
}
