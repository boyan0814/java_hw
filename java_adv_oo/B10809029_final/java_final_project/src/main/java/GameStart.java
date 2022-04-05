import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javazoom.jl.player.Player;

public class GameStart extends JFrame {
    //設定 F、G、H、J 的判定 x 座標
    int x1 = 409;
    int x2 = 530;
    int x3 = 651;
    int x4 = 772;

    //設定觸發成功區域
    int missUp = 574;
    int goodUp = 614;
    int perfectUp = 644;
    int perfectDown = 724;
    int goodDown = 754;

    //計算結果
    int perfectAmount = 0;
    int goodAmount = 0;
    int missAmount = 0;

    //音效存放點
    public static AudioClip perfectSound[] = new AudioClip[3];
    public static AudioClip goodSound[]=new AudioClip[3];
    Player gamePlayer;//MP3撥放器

    Timer timer;//計數器
    
    int speedLimit = 0; int speed = 0;
    String noteRecord;int next;

    Note[] notes = new Note[20];//掉落按鍵實體陣列
    int noteIndex = 0;

    HitListener hitListener = new HitListener();//觸發監聽器
    static JLabel background = new JLabel(new ImageIcon("src/image/game_bg.png"));//背景設定

    Result result = new Result();

    public void GameStartinit(String audioFilePath, String txtFilePath){
        perfectAmount = 0;goodAmount = 0;missAmount = 0;//計算結果重製

        //樂譜、音樂檔讀取
        try {
            FileReader in = new FileReader(txtFilePath);
            BufferedReader reader = new BufferedReader(in);
            speedLimit = Integer.valueOf(reader.readLine()).intValue();
            noteRecord = reader.readLine();//樂譜讀取
            reader.close();
            in.close();

            URL pecfectUrl = new File("src/sound/perfecthit.wav").toURI().toURL();
            URL goodUrl = new File("src/sound/goodhit.wav").toURI().toURL();
            gamePlayer = new Player(new FileInputStream(new File(audioFilePath)));

            for (int i=0;i<3;i++){
                perfectSound[i] = Applet.newAudioClip(pecfectUrl);
                goodSound[i] = Applet.newAudioClip(goodUrl);
            }

        }catch (Exception e){

        }
        //將 notes(掉落鍵) 實體化
        for (int i = 0; i < 20; i++) {
            notes[i] = new Note();
            PageController.window.getContentPane().add(notes[i]);
        }
        background.setVisible(false);
        PageController.window.add(background);
        background.setBounds(0, 0, 1280, 800);
        background.setVisible(true);
    }

    //開始遊戲
    public void start(){
        PageController.window.addKeyListener(hitListener);
        Thread music = new Thread() {
            public void run() {
                try {
                    gamePlayer.play();
                } catch (Exception e) {
                }
            }
        };music.start();

        timer = new Timer(true);//記數
        timer.scheduleAtFixedRate(new Drop(), 0, 10);//10毫秒執行
    }

    class Drop extends TimerTask {
        @Override
        public void run() {
            if (speed == 0) {
                switch (noteRecord.charAt(next)) {
                    case '0': {
                        next++;
                        break;
                    }
                    case '1': {
                        notes[noteIndex].x = x1;
                        notes[noteIndex].y = 0;
                        notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                        notes[noteIndex].setVisible(true);
                        noteIndex++;
                        if (noteIndex == 20) {
                            noteIndex = 0;
                        }
                        next++;
                        break;
                    }
                    case '2': {
                        notes[noteIndex].x = x2;
                        notes[noteIndex].y = 0;
                        notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                        notes[noteIndex].setVisible(true);
                        noteIndex++;
                        if (noteIndex == 20) {
                            noteIndex = 0;
                        }
                        next++;
                        break;
                    }
                    case '3': {
                        notes[noteIndex].x = x3;
                        notes[noteIndex].y = 0;
                        notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                        notes[noteIndex].setVisible(true);
                        noteIndex++;
                        if (noteIndex == 20) {
                            noteIndex = 0;
                        }
                        next++;
                        break;
                    }
                    case '4': {
                        notes[noteIndex].x = x4;
                        notes[noteIndex].y = 0;
                        notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                        notes[noteIndex].setVisible(true);
                        noteIndex++;
                        if (noteIndex == 20) {
                            noteIndex = 0;
                        }
                        next++;
                        break;
                    }
                    //結束偵測
                    case '5': {
                        timer.cancel();
                        gamePlayer.close();
                        PageController.window.removeKeyListener(hitListener);
                        result.resultPage(perfectAmount, goodAmount, missAmount);
                        return;
                    }
                }
            }

            speed++;
            //每 10*speedLimit 毫秒刷新一次
            if (speed == speedLimit) {
                speed = 0;
            }

            for (int i = 0; i < 20; i++) {
                //若還未被消除則 y 座標 逐漸下移
                if (notes[i].isVisible()) {
                    notes[i].y += 4;
                    notes[i].setLocation(notes[i].x, notes[i].y);

                    //超過觸發點移除並記為 miss
                    if (notes[i].y == 800) {
                        notes[i].setVisible(false);
                        missAmount++;
                    }
                }
            }
        }
    }
    int perfectCount = 0;
    int goodCount = 0;
    //觸發監聽器
    private class HitListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyPressed(KeyEvent e) {
            //預設歸零
            int noteIndex = -1;
            int noteY = -1;
            int noteX = 0;
            //按下不同按鍵取不同相對 x 值
            int keyCode = e.getKeyCode();
            if (keyCode == PageController.keyCodeF) {
                noteX = x1;
            } else if (keyCode == PageController.keyCodeG) {
                noteX = x2;
            } else if (keyCode == PageController.keyCodeH) {
                noteX = x3;
            } else if (keyCode == PageController.keyCodeJ) {
                noteX = x4;
            }
            //抓取按鍵x相同 && 還在顯示中 && y最大(最下面)
            for (int i = 0; i < 20; i++) {
                if (notes[i].x == noteX && notes[i].isVisible() && notes[i].y > noteY) {
                    noteY = notes[i].y;
                    noteIndex = i;
                }
            }
            if (noteIndex == -1) {
                return;
            }

            //依不同處發位置判定結果
            if (noteY >= perfectUp && noteY <= perfectDown) {
                notes[noteIndex].setVisible(false);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        perfectSound[perfectCount].play();
                    }
                });thread.run();
                perfectAmount++;
                perfectCount++;
                if (perfectCount==3)perfectCount=0;
                System.out.println("perfect");
            } else if ((noteY < perfectUp && noteY >= goodUp) || (noteY > perfectDown && noteY <= goodDown)) {
                notes[noteIndex].setVisible(false);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        goodSound[goodCount].play();
                    }
                });thread.run();
                goodAmount++;
                goodCount++;
                if (goodCount==3)goodCount=0;
                System.out.println("good");
            } else if ((noteY < goodUp && noteY >= missUp) || (noteY > goodDown)) {
                notes[noteIndex].setVisible(false);
                missAmount++;
                System.out.println("miss");
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}
