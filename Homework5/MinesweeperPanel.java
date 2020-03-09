import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MinesweeperPanel extends JPanel{

    private MinesweeperFrame frame;
    private BoardOfPlay board;
    private MinesweepLabels[][] mineLabels;
    public MinesweeperPanel(MinesweeperFrame frame, int level) {
        this.frame = frame;
        board = new BoardOfPlay(this, level);
        setLayout(new GridLayout(board.getColumns(), board.getColumns()));
        mineLabels = new MinesweepLabels[board.getColumns()][board.getColumns()];
        for(int i=0; i<board.getColumns(); i++){
            for(int j=0; j<board.getColumns(); j++){
                MinesweepLabels newMine = new MinesweepLabels("?", i, j);
                newMine.addMouseListener(new MouseHandler(i, j, this));
                add(newMine);
                mineLabels[i][j] = newMine;
            }
        }
    }

    public MinesweeperPanel(MinesweeperFrame frame, BoardOfPlay board) {
        this.frame = frame;
        this.board = board;
        setLayout(new GridLayout(board.getColumns(), board.getColumns()));
        mineLabels = new MinesweepLabels[board.getColumns()][board.getColumns()];
        for(int i=0; i<board.getColumns(); i++) {
            for(int j=0; j<board.getColumns(); j++) {
                MinesweepLabels newMine = new MinesweepLabels("?", i, j);
                newMine.addMouseListener(new MouseHandler(i, j, this));
                add(newMine);
                mineLabels[i][j] = newMine;
            }
        }
        gameSetup();
    }

    private void gameWon(){
        int choice = JOptionPane.showConfirmDialog(null, "Won!", "Replay?", JOptionPane.YES_NO_OPTION);
        if(choice == 0){
            close();
            new MinesweeperFrame();
        }
        else{
            close();
        }
    }

    private void gameLost(){
        int choice = JOptionPane.showConfirmDialog(null, "Lost! Replay?", "Again?", JOptionPane.YES_NO_OPTION);
        if(choice == 0){
            close();
            new MinesweeperFrame();
        }
        else{
            close();
        }
    }

    public void close(){
        frame.setVisible(false);
        frame.dispose();
    }

    public MinesweeperFrame getFrame(){
        return frame;
    }

    public BoardOfPlay getBoard(){
        return board;
    }

    public void gameSetup(){
        if(board.getLoser()){
            for(int i=0; i<mineLabels.length; i++){
                for (int j = 0; j < mineLabels[0].length; j++){
                    board.getBoard()[i][j].setClicked(true);
                }
            }
        }
        for(int i=0; i<mineLabels.length; i++){
            for(int j=0; j<mineLabels[0].length; j++){
                if(board.getBoard()[i][j].getClicked()){
                    if(board.getBoard()[i][j].getIsBomb()){
                        mineLabels[i][j].setText("X");
                    }
                    else if(board.getBoard()[i][j].nearBombs(board.getBoard()) == 0){
                        mineLabels[i][j].setText(" ");
                    }
                    else{
                        mineLabels[i][j].setText(Integer.toString(board.getBoard()[i][j].nearBombs(board.getBoard())));
                    }
                }
                else{
                    if(board.getBoard()[i][j].getFlagged()){
                        mineLabels[i][j].setText("F");
                    }
                    else{
                        mineLabels[i][j].setText("?");
                    }
                }
            }
        }
        if(board.getLoser()){
            gameLost();
        }
        if(board.winner()){
            gameWon();
        }
    }
}

class JBar extends JMenuBar{
    private MinesweeperFrame frame;
    public JBar(MinesweeperFrame frame){
        super();
        this.frame = frame;
        MenuPop mm = new MenuPop("File");
        add(mm);
    }

    private class MenuPop extends JMenu{
        public MenuPop(String title){
            super(title);
            MenuItem menuItemOne = new MenuItem("New");
            menuItemOne.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPanel().getBoard().startGame();
                }
            });
            MenuItem menuItemTwo = new MenuItem("Save");
            menuItemTwo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int num = fileChooser.showSaveDialog(null);
                    if(num == JFileChooser.APPROVE_OPTION) {
                        File saved = fileChooser.getSelectedFile();
                        frame.getPanel().getBoard().saveGame(saved);
                    }
                }
            });
            MenuItem menuItemThree = new MenuItem("Load");
            menuItemThree.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int num = fileChooser.showOpenDialog(null);
                    if(num == JFileChooser.APPROVE_OPTION) {
                        File saved = fileChooser.getSelectedFile();
                        frame.getPanel().getBoard().loadGame(saved);
                    }
                }
            });
            MenuItem menuItemFour = new MenuItem("Quit");
            menuItemFour.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPanel().close();
                }
            });
            add(menuItemOne);
            add(menuItemTwo);
            add(menuItemThree);
            add(menuItemFour);
        }
    }

    private class MenuItem extends JMenuItem {
        public MenuItem(String title) {
            super(title);
        }
    }
}

class MinesweepLabels extends JLabel {

    public MinesweepLabels(String text ,int xPos, int yPos) {
        setText(text);
        setHorizontalAlignment(JLabel.CENTER);
    }
}

class MouseHandler extends MouseAdapter {

    private int x;
    private int y;
    private MinesweeperPanel panel;
    public MouseHandler(int x, int y, MinesweeperPanel panel) {
        this.x = x;
        this.y = y;
        this.panel = panel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            panel.getBoard().flaggedBomb(x, y);
        }
        else {
            panel.getBoard().pickBomb(x, y);
        }
        panel.gameSetup();
    }
}
