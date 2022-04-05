import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoseDetailFrame extends JFrame {
    public JLabel statusBar = new JLabel("Click the mouse");

    private String wStr[]={"Do","Re","Mi","Fa","So","La","Si","Do"};
    private String bStr[]={"#DO", "#Re", "#Fa", "#So", "#La"};

    private PianoPanel panel = new PianoPanel();
    private Rectangle w[] = {
            new Rectangle(0,0,90,400),
            new Rectangle(100,0,90,400),
            new Rectangle(200,0,90,400),
            new Rectangle(300,0,90,400),
            new Rectangle(400,0,90,400),
            new Rectangle(500,0,90,400),
            new Rectangle(600,0,90,400),
            new Rectangle(700,0,90,400),
    };
    private Rectangle b[] = {
            new Rectangle(75,0,40,200),
            new Rectangle(175,0,40,200),
            new Rectangle(375,0,40,200),
            new Rectangle(475,0,40,200),
            new Rectangle(575,0,40,200),
    };
    public MoseDetailFrame(){

        statusBar.setFont(new Font(statusBar.getFont().getName(), statusBar.getFont().getStyle(), 30));
        this.add(this.statusBar, "South");
        panel.setLayout(new FlowLayout());
        this.add(panel);
        panel.addMouseListener( new MouseClickHandler());
    }

    public class PianoPanel extends JPanel {

        public PianoPanel(){


        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(new Color(255,255,255));
            for (int i=0 ;i<=7; i++){
                g2.fill(w[i]);
            }
            g2.setColor(new Color(0,0,0));
            for (int i=0;i<=4;i++){
                g2.fill(b[i]);
            }
        }
    }

    private class MouseClickHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i=0;i<=7;i++){
                if (w[i].getBounds().contains(e.getPoint()))
                    statusBar.setText(wStr[i]);
            }
            for (int i=0;i<=4;i++){
                if (b[i].getBounds().contains(e.getPoint()))
                    statusBar.setText(bStr[i]);
            }
            super.mouseClicked(e);
        }
    }

}
