import javax.swing.*;
import java.awt.*;

public class MinesweeperFrame extends JFrame{

    private MinesweeperPanel panel;
    public MinesweeperFrame(){
        int level = start();
        setTitle("Minesweeper");
        setSize(1000, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        panel = new MinesweeperPanel(this, level);
        JBar menu = new JBar(this);
        setJMenuBar(menu);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public MinesweeperFrame(BoardOfPlay field){
        setTitle("Minesweeper");
        setSize(1000, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        panel = new MinesweeperPanel(this, field);
        JBar menu = new JBar(this);
        setJMenuBar(menu);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static int start() {
        String[] levelSelect = new String[] {"Beginner", "Intermediate", "Advanced"};
        int playerOption = JOptionPane.showOptionDialog(null, "What level?", "Minesweeper", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, levelSelect, levelSelect[0]);
        return playerOption;
    }

    public MinesweeperPanel getPanel() {
        return panel;
    }
}
