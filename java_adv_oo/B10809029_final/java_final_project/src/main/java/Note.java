import javax.swing.*;

public class Note extends JLabel {

    static ImageIcon noteIcon = new ImageIcon("src/image/note.png");

    public int x;

    public int y;

    public Note() {
        this.setIcon(noteIcon);
        this.setSize(104, 13);
        this.setVisible(false);
    }

}