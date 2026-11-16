import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Task04 extends Frame {
    private final java.util.List<Point> points = new ArrayList<>();
    private final Label status = new Label("Clicks: 0");
    public Task04() {
        super("Draw Pad");
        setLayout(new BorderLayout());
        add(status,BorderLayout.SOUTH);
        addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                points.add(e.getPoint());
                status.setText("Clicks: " + points.size());
                repaint();
            }
        });
        addWindowListener(new WindowAdapter(){ @Override public void windowClosing(WindowEvent e){ dispose(); }});
        setSize(500,380);
    }
    @Override public void paint(Graphics g) {
        super.paint(g);
        for (Point p : points) g.fillOval(p.x-3,p.y-3,6,6);
    }
    public static void main(String[] args){ EventQueue.invokeLater(() -> new Task04().setVisible(true)); }
}
