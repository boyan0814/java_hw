import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class NumClock extends JFrame {

    class ClockPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setFont(new Font("Time New Romans",Font.PLAIN, 60));
            String s = String.format("%02d:%02d:%02d",
                    new java.util.Date().getHours(),
                    new java.util.Date().getMinutes(),
                    new java.util.Date().getSeconds());
            g.drawString(s, 50, 50);
        }
    }

    public NumClock(String title) {
        super(title);
        setSize(400, 150);
        JPanel panel = new JPanel();
        setLayout(new BorderLayout(5, 5 ));

        add(panel, BorderLayout.NORTH);
        add(new ClockPanel(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Timer timer = new Timer(1000, new ActionListener() {
            int counter = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }
    public static void main(String args[]) {
        new NumClock("NumClock");
    }
}