import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Task05 extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"USD","EUR"});
    private final JLabel result = new JLabel(" ");
    private static double rate(String currency){ return currency.equals("USD") ? 0.012 : 0.011; }

    public Task05() {
        super("Currency Converter – Lambda");
        setLayout(new FlowLayout());
        JButton convertButton = new JButton("Convert");
        add(new JLabel("INR")); add(amount); add(target); add(convertButton); add(result);
        ActionListener convertAction = e -> convert();
        convertButton.addActionListener(convertAction);
        amount.addActionListener(convertAction);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
    private void convert() {
        try {
            double inr = Double.parseDouble(amount.getText().trim());
            if (inr < 0) throw new NumberFormatException();
            double converted = inr * rate((String)target.getSelectedItem());
            String message = String.format("%.2f %s", converted, target.getSelectedItem());
            result.setText(message);
            JOptionPane.showMessageDialog(this, message, "Conversion result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            result.setText("Invalid amount");
            JOptionPane.showMessageDialog(this, "Invalid amount", "Input error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args){ SwingUtilities.invokeLater(() -> new Task05().setVisible(true)); }
}
