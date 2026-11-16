import java.awt.*;
import javax.swing.*;

public class Task03 extends JFrame {
    public Task03() {
        super("Campus Library Search");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8,8));

        JLabel title = new JLabel("Campus Library Search", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(20f));
        add(title, BorderLayout.NORTH);

        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField query = new JTextField(22);
        JComboBox<String> category = new JComboBox<>(new String[]{"All","Book","Journal","Thesis"});
        JButton search = new JButton("Search");
        searchRow.add(new JLabel("Search:")); searchRow.add(query);
        searchRow.add(category); searchRow.add(search);

        JTextArea results = new JTextArea("Search results will appear here.");
        results.setEditable(false);
        JScrollPane resultScroll = new JScrollPane(results);
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prev=new JButton("Prev"), next=new JButton("Next"), reserve=new JButton("Reserve");
        bottom.add(prev); bottom.add(next); bottom.add(reserve);
        JLabel status = new JLabel("Ready");
        JPanel south = new JPanel(new BorderLayout());
        south.add(bottom,BorderLayout.CENTER); south.add(status,BorderLayout.SOUTH);

        JPanel center = new JPanel(new BorderLayout());
        center.add(searchRow,BorderLayout.NORTH);
        center.add(resultScroll,BorderLayout.CENTER);
        add(center,BorderLayout.CENTER);
        add(south,BorderLayout.SOUTH);

        search.addActionListener(e -> { results.setText("Searching for: "+query.getText()+"\\nCategory: "+category.getSelectedItem()); status.setText("Search completed."); });
        prev.addActionListener(e -> status.setText("Previous page"));
        next.addActionListener(e -> status.setText("Next page"));
        reserve.addActionListener(e -> status.setText("Select a result to reserve."));
        setSize(700,480);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args){ SwingUtilities.invokeLater(() -> new Task03().setVisible(true)); }
}
