import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PageController {

    public static JFrame window = new JFrame();

    //按鍵事件
    public static int keyCodeF = KeyEvent.VK_F;
    public static int keyCodeG = KeyEvent.VK_G;
    public static int keyCodeH = KeyEvent.VK_H;
    public static int keyCodeJ = KeyEvent.VK_J;

    GameStart  gameStart = new GameStart();

    //頁面配置初始化
    public PageController(){
        window.setSize(1280, 835);
        window.setResizable(false);
        window.setVisible(true);
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void run(){
        //start圖片設定
        ImageIcon start_img = new ImageIcon("src/image/start.png");
        JLabel label_start = new JLabel();
        label_start.setSize(485,185);
        label_start.setLocation(398,308);
        label_start.setIcon(start_img);
        label_start.setVisible(false);
        window.add(label_start);
        label_start.setVisible(true);

        //背景圖片設置
        ImageIcon menu_bg = new ImageIcon("src/image/menu_bg.jpg");
        JLabel label_bg = new JLabel();
        label_bg.setSize(1280,800);
        label_bg.setLocation(0,0);
        label_bg.setIcon(menu_bg);
        label_bg.setVisible(false);
        window.add(label_bg);
        label_bg.setVisible(true);

        //開始圖片按下後事件 (開始遊戲)
        label_start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                window.getContentPane().removeAll();
                window.getContentPane().repaint();
                gameStart.GameStartinit("src/mp3/Inferno.mp3","src/txt/Inferno.txt");
                gameStart.start();
            }
        });
    }
}
