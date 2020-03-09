import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweepFrame extends JFrame {
    public MineSweepFrame() {
        setTitle("Minesweeper");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MineSweepPanel panel = new MineSweepPanel();
        add(panel);
        setVisible(true);
    }
    public void closeFrame() {
        setVisible(false);
        closeFrame();
        dispose();
    }
}
