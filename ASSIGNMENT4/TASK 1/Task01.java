import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FeeService {
    public String pay(String regNo, String amountText) {
        if (regNo == null || regNo.trim().isEmpty()) throw new IllegalArgumentException("Registration number is required.");
        double amount;
        try { amount = Double.parseDouble(amountText.trim()); }
        catch (Exception e) { throw new IllegalArgumentException("Enter a valid numeric amount."); }
        if (amount <= 0) throw new IllegalArgumentException("Amount must be greater than zero.");
        // Payment/database work belongs in this service. A real deployment would call a DAO here.
        return String.format("Payment successful for %s: Rs. %.2f", regNo.trim(), amount);
    }
}
public class Task01 extends Frame {
    private final TextField regNo = new TextField(18);
    private final TextField amount = new TextField(18);
    private final Label status = new Label("Enter details and select an action.");
    private final FeeService service = new FeeService();

    public Task01() {
        super("Student Fee Payment");
        setLayout(new BorderLayout(8,8));
        Panel form = new Panel(new GridLayout(2,2,6,6));
        form.add(new Label("Registration number:")); form.add(regNo);
        form.add(new Label("Amount (INR):")); form.add(amount);
        Panel actions = new Panel(new FlowLayout());
        Button pay = new Button("Pay"), clear = new Button("Clear"), receipt = new Button("Receipt");
        actions.add(pay); actions.add(clear); actions.add(receipt);
        add(form,BorderLayout.CENTER); add(actions,BorderLayout.NORTH); add(status,BorderLayout.SOUTH);
        pay.addActionListener(e -> {
            try { status.setText(service.pay(regNo.getText(), amount.getText())); }
            catch (IllegalArgumentException ex) { status.setText("Error: " + ex.getMessage()); }
        });
        clear.addActionListener(e -> { regNo.setText(""); amount.setText(""); status.setText("Fields cleared."); });
        receipt.addActionListener(e -> status.setText("Receipt preview: " + regNo.getText() + " / Rs. " + amount.getText()));
        addWindowListener(new WindowAdapter(){ public void windowClosing(WindowEvent e){ dispose(); }});
        setSize(480,180);
    }
    public static void main(String[] args){ EventQueue.invokeLater(() -> new Task01().setVisible(true)); }
}
