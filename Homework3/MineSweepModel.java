import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MineSweepModel{
    //add different level & jMenuBar to hold levels:Benginer, Intermediate, and Advanced
    private MineProps[][] sweepZone;
    private int yAmount;
    private int xAmount;
    private int numberOfMines = 0;
    private int flagsPlaced = 0;
    private boolean loser = false;
    private boolean winner = false;
    public MineSweepModel(int yAmount, int xAmount, int numberOfMines) {

        sweepZone = new MineProps[xAmount][yAmount];
        this.xAmount = xAmount;
        this.yAmount = yAmount;
        this.numberOfMines = numberOfMines;
        for(int i=0; i<sweepZone.length; i++) {
            for(int j=0; j<sweepZone[0].length; j++) {
                sweepZone[i][j] = new MineProps(i, j);
            }
        }
        ArrayList<Integer> minesPlaced = new ArrayList<>();
        for(int i=0; i<numberOfMines; i++) {
            double randomMinePlacement = Math.random() * 100;
            while(minesPlaced.contains((int)randomMinePlacement)) {
                randomMinePlacement = Math.random() * 100;
            }
            minesPlaced.add((int)randomMinePlacement);
        }
        for(int i=0; i<minesPlaced.size(); i++) {
            int rowNum = minesPlaced.get(i) / 10;
            int colNum = minesPlaced.get(i) % 10;
            sweepZone[rowNum][colNum].setIsMine(true);
        }
    }

    public void stepMine(MineSweepLabel label, MineSweepLabel[][] labels) {
        int x = label.getx();
        int y = label.gety();
        if(!sweepZone[x][y].getFlagged()) {
            sweepZone[x][y].setClicked(true);
            if(sweepZone[x][y].minesAround(sweepZone) == 0) {
                if (sweepZone[x][y].getX() > 0) {
                    if(!sweepZone[x-1][y].getClicked()) {
                        MineSweepLabel nextMine = labels[x-1][y];
                        stepMine(nextMine, labels);
                    }
                    if (sweepZone[x][y].getY() > 0) {
                        if(!sweepZone[x-1][y-1].getClicked()) {
                            MineSweepLabel nextMine = labels[x-1][y-1];
                            stepMine(nextMine, labels);
                        }
                    }
                }
                if (sweepZone[x][y].getY() > 0) {
                    if(!sweepZone[x][y-1].getClicked()) {
                        MineSweepLabel nextMine = labels[x][y-1];
                        stepMine(nextMine, labels);
                    }
                    if (sweepZone[x][y].getX() < sweepZone.length - 1) {
                        if(!sweepZone[x+1][y-1].getClicked()) {
                            MineSweepLabel nextMine = labels[x+1][y-1];
                            stepMine(nextMine, labels);
                        }
                    }
                }
                if (sweepZone[x][y].getX() < sweepZone.length - 1) {
                    if(!sweepZone[x+1][y].getClicked()) {
                        MineSweepLabel nextMine = labels[x+1][y];
                        stepMine(nextMine, labels);
                    }
                    if (sweepZone[x][y].getY() < sweepZone[0].length - 1) {
                        if(!sweepZone[x+1][y+1].getClicked()) {
                            MineSweepLabel nextMine = labels[x+1][y+1];
                            stepMine(nextMine, labels);
                        }
                    }
                }
                if (sweepZone[x][y].getY() < sweepZone[0].length - 1) {
                    if(!sweepZone[x][y+1].getClicked()) {
                        MineSweepLabel nextMine = labels[x][y+1];
                        stepMine(nextMine, labels);
                    }
                    if (sweepZone[x][y].getX() > 0) {
                        if(!sweepZone[x-1][y+1].getClicked()) {
                            MineSweepLabel nextMine = labels[x-1][y+1];
                            stepMine(nextMine, labels);
                        }
                    }
                }
            }
            if(sweepZone[x][y].getIsMine()) {
                loser = true;
            }
        }
    }

    public void mineBs(MineSweepLabel[][] labels) {
        if(loser) {
            for(int i=0; i<labels.length; i++) {
                for (int j = 0; j < labels[0].length; j++) {
                    sweepZone[i][j].setClicked(true);
                }
            }
        }
        for(int i=0; i<labels.length; i++) {
            for(int j=0; j<labels[0].length; j++) {
                if(sweepZone[i][j].getClicked()) {
                    if(sweepZone[i][j].getIsMine()) {
                        labels[i][j].setText("X");
                    }
                    else if(sweepZone[i][j].minesAround(sweepZone) == 0) {
                        labels[i][j].setText(" ");
                    }
                    else {
                        labels[i][j].setText(Integer.toString(sweepZone[i][j].minesAround(sweepZone)));
                    }
                }
            }
        }
        if(loser) {
            System.out.println("You lost!");
        }
        if(winCondition()) {
            System.out.println("You won!");
        }
    }

    private boolean winCondition() {
        int count = 0;
        if(flagsPlaced == numberOfMines) {
                    count++;
            }
            if(count == 10) {
                winner = true;
                return true;
            }
        return false;
    }

    public int getxAmount() {
        return xAmount;
    }
    public int getyAmount() {
        return yAmount;
    }
    public boolean getWinner() {
        return winner;
    }
    public boolean getLoser() {
        return loser;
    }

    private class MineProps {
        private int x = 0;
        private int y = 0;
        private boolean isMine = false;
        private boolean clicked = false;
        private boolean flagged = false;
        private MineProps(int x, int y) {
            this.x = x;
            this.y = y;
        }
        private void setIsMine(boolean isMine) {
            this.isMine = isMine;
        }
        private void setClicked(boolean clicked) {
            this.clicked = clicked;
        }
        private void setFlagged(boolean flagged) {
            this.flagged = flagged;
        }
        private int getX() {
            return x;
        }
        private int getY() {
            return y;
        }
        private boolean getIsMine() {
            return isMine;
        }
        private boolean getClicked() {
            return clicked;
        }
        private boolean getFlagged() {
            return flagged;
        }
        private int minesAround(MineProps[][] mf) {
            int count = 0;
            if(x > 0) {
                if(mf[x-1][y].getIsMine()) {
                    count++;
                }
                if(y < mf[0].length-1) {
                    if(mf[x-1][y+1].getIsMine()) {
                        count++;
                    }
                }
            }
            if(y < mf[0].length-1) {
                if(mf[x][y+1].getIsMine()) {
                    count++;
                }
                if(x < mf.length-1) {
                    if(mf[x+1][y+1].getIsMine()) {
                        count++;
                    }
                }
            }
            if(x < mf.length-1) {
                if(mf[x+1][y].getIsMine()) {
                    count++;
                }
                if(y > 0) {
                    if(mf[x+1][y-1].getIsMine()) {
                        count++;
                    }
                }
            }
            if(y > 0) {
                if(mf[x][y-1].getIsMine()) {
                    count++;
                }
                if(x > 0) {
                    if(mf[x-1][y-1].getIsMine()) {
                        count++;
                    }
                }
            }
            return count;
        }
    }
    public void flagMine(MineSweepLabel label) {
        int x = label.getx();
        int y = label.gety();
        if(!sweepZone[x][y].getClicked()) {
            if (sweepZone[x][y].getFlagged()) {
                flagsPlaced--;
                sweepZone[x][y].setFlagged(false);
                label.setText("?");
            }
            else if(flagsPlaced < 10){
                flagsPlaced++;
                sweepZone[x][y].setFlagged(true);
                label.setText("F");
            }
        }
    }
}

class MineSweepLabel extends JLabel {
  private int x;
  private int y;
  public MineSweepLabel(String letter ,int x, int y) {
      setText(letter);
      setHorizontalAlignment(JLabel.CENTER);
      this.x = x;
      this.y = y;
  }

  public int getx() {
      return x;
  }

  public int gety() {
      return y;
  }
