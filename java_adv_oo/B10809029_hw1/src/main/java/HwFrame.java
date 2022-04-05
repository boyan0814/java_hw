import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HwFrame extends JFrame {
    private JLabel startLabel, endLabel, ticketTypeLabel, tickNumLabel;
    private JComboBox startComboBox, endComboBox;
    private JPanel northPanel, centerPanel, southPanel;

    private ButtonGroup ticketGroup, numTickGroup;
    private JRadioButton normalRadioBtn, stuRadioBtn, onetickRadioBtn, twotickRadioBtn;
    private String[] name = {"南港", "台北", "板橋","桃園","新竹","苗栗","台中","彰化","雲林","嘉義","台南","左營"};
    private String startStation = "南港", endStation = "南港", ticketStr = "一般票", tickNumStr = "1";

    private JButton submitButton;

    public HwFrame(){
        setLayout(new BorderLayout()); //Layout 設定
        //Panel
        northPanel = new JPanel();
        centerPanel = new JPanel();
        southPanel = new JPanel();
        //Label
        startLabel = new JLabel("起始站 : ");
        endLabel = new JLabel("終點站 : ");
        ticketTypeLabel = new JLabel("票種 : ");
        tickNumLabel = new JLabel("張數 : ");
        //RadioBtn
        normalRadioBtn = new JRadioButton("一般票",true);
        stuRadioBtn = new JRadioButton("學生票");
        onetickRadioBtn = new JRadioButton("1",true);
        twotickRadioBtn = new JRadioButton("2");
        //RadioBtnGroup
        ticketGroup = new ButtonGroup();
        ticketGroup.add(normalRadioBtn);
        ticketGroup.add(stuRadioBtn);

        numTickGroup = new ButtonGroup();
        numTickGroup.add(onetickRadioBtn);
        numTickGroup.add(twotickRadioBtn);
        //Button
        submitButton = new JButton("確認送出");

        //Listener
        startComboBox = new JComboBox(name);
        startComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) startStation = e.getItem().toString();
            }
        });

        endComboBox = new JComboBox(name);
        endComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) endStation = e.getItem().toString();
            }
        });

        normalRadioBtn.addItemListener(new RadioButtonHandler(ticketGroup));
        stuRadioBtn.addItemListener(new RadioButtonHandler(ticketGroup));

        onetickRadioBtn.addItemListener(new RadioButtonHandler(numTickGroup));
        twotickRadioBtn.addItemListener(new RadioButtonHandler(numTickGroup));

        ButtonHandler handler = new ButtonHandler();
        submitButton.addActionListener(handler);
        //Panel add
        northPanel.add(startLabel);
        northPanel.add(startComboBox);
        northPanel.add(endLabel);
        northPanel.add(endComboBox);

        centerPanel.add(ticketTypeLabel);
        centerPanel.add(normalRadioBtn);
        centerPanel.add(stuRadioBtn);
        centerPanel.add(ticketTypeLabel);
        centerPanel.add(onetickRadioBtn,twotickRadioBtn);
        centerPanel.add(twotickRadioBtn);

        southPanel.add(submitButton);

        add(northPanel,BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private class RadioButtonHandler implements ItemListener
    {
        private ButtonGroup btnGroup;
        public RadioButtonHandler(ButtonGroup buttonGroup)
        {
            btnGroup = buttonGroup;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            JRadioButton button = (JRadioButton)e.getSource();
            if (button.getText().equals("一般票"))
                ticketStr = "一般票";
            else if (button.getText().equals("學生票"))
                ticketStr = "學生票";

            if (button.getText().equals("1"))
                tickNumStr = "1";
            else if (button.getText().equals("2"))
                tickNumStr = "2";
        }
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (startStation.equals(endStation)){
                JOptionPane.showMessageDialog(HwFrame.this, "不得選相同站點","失敗",JOptionPane.ERROR_MESSAGE);
            }else{
                String result = "成功購買從 " + startStation + " 到 " + endStation + " 的 " + ticketStr + " " + tickNumStr + "張";
                JOptionPane.showMessageDialog(HwFrame.this, result,"購票內容",JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }
}
