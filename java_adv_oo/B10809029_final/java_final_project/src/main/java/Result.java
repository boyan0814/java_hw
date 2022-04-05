import javax.swing.*;
import java.awt.*;

public class Result {
    static JLabel result = new JLabel(new ImageIcon("src/image/result_bg.jpg"));

    public void resultPage(int perfect, int good, int miss) {
        PageController.window.getContentPane().removeAll();
        PageController.window.getContentPane().repaint();

        JLabel perfectLabel = new JLabel("Perfect :"+perfect);
        JLabel goodLabel = new JLabel("Good :"+good);
        JLabel missLabel = new JLabel("Miss :"+miss);
        JLabel thanktxt = new JLabel("--- Thank's for playing ---");

        perfectLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        goodLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        missLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        thanktxt.setFont(new Font("Calibri", Font.BOLD, 30));


        perfectLabel.setVisible(false);
        goodLabel.setVisible(false);
        missLabel.setVisible(false);
        thanktxt.setVisible(false);

        PageController.window.getContentPane().add(perfectLabel);
        PageController.window.getContentPane().add(goodLabel);
        PageController.window.getContentPane().add(missLabel);
        PageController.window.getContentPane().add(thanktxt);

        result.setVisible(false);
        PageController.window.getContentPane().add(result);
        result.setBounds(0, 0, 1280, 800);
        result.setVisible(true);

        perfectLabel.setBounds(500, 210, 200, 100);
        goodLabel.setBounds(500, 314, 200, 100);
        missLabel.setBounds(500, 418, 200, 100);
        thanktxt.setBounds(500, 502, 500, 100);

        perfectLabel.setVisible(true);
        goodLabel.setVisible(true);
        missLabel.setVisible(true);
        thanktxt.setVisible(true);
    }

}
