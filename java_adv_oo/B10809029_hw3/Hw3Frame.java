import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class Hw3Frame extends JFrame {

    public JButton submitBtn = new JButton("下載");
    public JTextField urlTextField = new JTextField();
    public JEditorPane urlGetField = new JEditorPane();
    private JScrollPane outputScrollPane = new JScrollPane(urlGetField);;
    private JPanel northPanel, centerPanel;

    public Hw3Frame(){

        urlTextField.setPreferredSize(new java.awt.Dimension(300, 20));
        urlGetField.setPreferredSize(new java.awt.Dimension(800, 400));
        outputScrollPane.setPreferredSize(new java.awt.Dimension(800, 400));
        urlGetField.setEditable(false);
		urlGetField.setContentType("text/html;charset=UTF-8");
        northPanel = new JPanel();
        centerPanel = new JPanel();

        northPanel.add(urlTextField);
        northPanel.add(submitBtn);

        centerPanel.add(outputScrollPane);

        ButtonHandler handler = new ButtonHandler();
        submitBtn.addActionListener(handler);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 將 test.txt 檔清除
                        FileOutputStream file = new FileOutputStream("test.txt");
                        FileOutputStream file2 = new FileOutputStream("clean.txt");
                        urlGetField.setText(null);
                        submitBtn.setEnabled(false);
                        loadURL(urlTextField.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            thread.run();

            try {
                thread.join();
            }catch (Exception exception){
                System.out.println("Exception:thread.join");
            }

            // 要創建 clean.txt 文本讓 urlGetField 重新導向 (導向同地址會無效)
            String cleanStr="file://clean.txt";
            String str="file://test.txt";

            try {
                urlGetField.setPage(Thread.currentThread().getContextClassLoader().getResource("clean.txt"));
                urlGetField.setPage(Thread.currentThread().getContextClassLoader().getResource("test.txt"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (urlGetField.getText().isEmpty()){
                urlGetField.setText("請更換網址(URL須包含https)");
            }

            submitBtn.setEnabled(true);
            System.out.println("done");
        }
    }


    public static void loadURL(String temp) throws IOException, URISyntaxException {

        System.out.println("request=" + temp);
        URL url = new URL(temp);

        URLConnection connection = url.openConnection();

        // 伺服器的安全設定不接受 Java程式 作為客戶端訪問
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        InputStream stream = connection.getInputStream();
        saveAsFile(stream, "test.txt", "UTF-8");

    }

    public static void saveAsFile(InputStream stream, String outputfile, String encoding) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(stream, encoding));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfile), encoding));

        String line = "";
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            output.write(line + "\n");
            output.write(line, 0, line.length());
        }

        output.flush();
        in.close();
        output.close();
    }
}
