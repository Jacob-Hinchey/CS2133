import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweepPanel extends JPanel{
        //jMenuBar to hold levels:Benginer, Intermediate, and Advanced
        public MineSweepModel game;
        public MineSweepLabel[][] tiles;
        public MineSweepPanel() {
            setLayout(new GridLayout(10, 10));
            game = new MineSweepModel(10, 10, 10);
            tiles = new MineSweepLabel[10][10];
            JMenuBar jMenuBar = new JMenuBar();
            setVisible(true);
            for(int i=0; i<game.getxAmount(); i++) {
                for(int j=0; j<game.getyAmount(); j++) {
                    MineSweepLabel minePlacement = new MineSweepLabel("?", i, j);
                    minePlacement.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent click) {
                            if(click.getButton() == MouseEvent.BUTTON3) {
                                game.flagMine(minePlacement);
                                if(game.getWinner()) {
                                    playerWon();
                                }
                            }
                            else {
                                game.stepMine(minePlacement, tiles);
                                if(game.getLoser()) {
                                    playerLost();
                                }
                            }
                            game.mineBs(tiles);
                        }
                    });
                    add(minePlacement);
                    tiles[i][j] = minePlacement;
                }
            }
        }

        public void playerLost() {
            int playQuestion = JOptionPane.showConfirmDialog(null, "You Lost! Play again?", "Play again?", JOptionPane.YES_NO_OPTION);
            if(playQuestion == 0) {
                setVisible(false);
                closeFrame();
                new MineSweepFrame();
            }
            else {
                setVisible(false);
                closeFrame();
            }

        }
        public void closeFrame() {
            setVisible(false);
        }
        public void playerWon() {
            int playQuestion = JOptionPane.showConfirmDialog(null, "You Won!", "Play again?", JOptionPane.YES_NO_OPTION);
            if(playQuestion == 0) {
                setVisible(false);
                closeFrame();
                new MineSweepFrame();
            }
            else {
                setVisible(false);
                closeFrame();
            }
        }
}
