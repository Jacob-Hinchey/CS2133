import java.util.*;
import java.io.*;
import java.util.concurrent.*;

public class BoardOfPlay implements Serializable{

    private static final long serialVersionUID = 32L;
    private Bomb[][] board;
    private int rows;
    private int columns;
    private int bombAmount = 0;
    private int flagsAmount = 0;
    private boolean loser = false;
    private MinesweeperPanel minePanel;
    public BoardOfPlay(MinesweeperPanel minePanel, int level) {
        this.minePanel = minePanel;
        if(level == 0){
            columns = 10;
            rows = 10;
            bombAmount = 10;
        }
        else if(level == 1){
            columns = 15;
            rows = 15;
            bombAmount = 33;
        }
        else if(level == 2){
            columns = 20;
            rows = 20;
            bombAmount = 80;
        }
        else{
            System.out.println("Not Playable");
            minePanel.close();
        }
        board = new Bomb[columns][rows];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                board[i][j] = new Bomb(i, j);
            }
        }
        List<int[]> minesPresent = new ArrayList<>();
        for(int i=0; i<bombAmount; i++){
            int xRand = ThreadLocalRandom.current().nextInt(0, columns);
            int yRand = ThreadLocalRandom.current().nextInt(0, rows);
            int[] randBomb = new int[] {yRand, xRand};
            while(minesPresent.contains(randBomb)){
                xRand = ThreadLocalRandom.current().nextInt(0, columns);
                yRand = ThreadLocalRandom.current().nextInt(0, rows);
                randBomb = new int[] {yRand, xRand};
            }
            minesPresent.add(randBomb);
        }
        for(int i=0; i<minesPresent.size(); i++){
            int[] bombPlace = minesPresent.get(i);
            int rowX = bombPlace[0];
            int columnY = bombPlace[1];
            board[rowX][columnY].setMine(true);
        }
    }

    public void pickBomb(int horz, int vert){

        if(!board[horz][vert].getFlagged()){
            board[horz][vert].setClicked(true);
            if(board[horz][vert].nearBombs(board) == 0){
                if (board[horz][vert].getHorz() > 0){
                    if(!board[horz-1][vert].getClicked()){
                        pickBomb(horz-1, vert);
                    }
                    if (board[horz][vert].getVert() > 0){
                        if(!board[horz-1][vert-1].getClicked()){
                            pickBomb(horz-1, vert-1);
                        }
                    }
                }
                if (board[horz][vert].getVert() > 0){
                    if(!board[horz][vert-1].getClicked()){
                        pickBomb(horz, vert-1);
                    }
                    if (board[horz][vert].getHorz() < board.length - 1){
                        if(!board[horz+1][vert-1].getClicked()){
                            pickBomb(horz+1, vert-1);
                        }
                    }
                }
                if (board[horz][vert].getHorz() < board.length - 1){
                    if(!board[horz+1][vert].getClicked()){
                        pickBomb(horz+1, vert);
                    }
                    if (board[horz][vert].getVert() < board[0].length - 1){
                        if(!board[horz+1][vert+1].getClicked()){
                            pickBomb(horz+1, vert+1);
                        }
                    }
                }
                if (board[horz][vert].getVert() < board[0].length - 1) {
                    if(!board[horz][vert+1].getClicked()){
                        pickBomb(horz, vert+1);
                    }
                    if (board[horz][vert].getHorz() > 0){
                        if(!board[horz-1][vert+1].getClicked()){
                            pickBomb(horz-1, vert+1);
                        }
                    }
                }
            }
            if(board[horz][vert].getIsBomb()){
                loser = true;
            }
        }
    }

    public void flaggedBomb(int horz, int vert){

        if(!board[horz][vert].getClicked()){
            if (board[horz][vert].getFlagged()){
                flagsAmount--;
                board[horz][vert].setFlagged(false);
            }
            else{
                flagsAmount++;
                board[horz][vert].setFlagged(true);
            }
        }
    }

    public boolean winner(){
        int count = 0;
        if(flagsAmount == bombAmount){
            for(int i=0; i<board.length; i++){
                for(int j=0; j<board[0].length; j++){
                    if(board[i][j].getIsBomb()){
                        if(board[i][j].getFlagged()){
                            count++;
                        }
                    }
                }
            }
            if(count == bombAmount){
                return true;
            }
        }
        return false;
    }

    public int getColumns(){
        return columns;
    }

    public int getRows(){
        return rows;
    }

    public boolean getLoser(){
        return loser;
    }

    public Bomb[][] getBoard(){
        return board;
    }

    public void startGame(){
        minePanel.close();
        new MinesweeperFrame();
    }

    public void startGame(BoardOfPlay board){
        minePanel.close();
        new MinesweeperFrame(board);
    }

    public void saveGame(File file){
        FileOutputStream out = null;
        ObjectOutputStream oos = null;
        try{
            out = new FileOutputStream(file);
            oos = new ObjectOutputStream(out);
            oos.writeObject(this);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            if(out != null){
                try{
                    out.close();
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            if(oos != null){
                try{
                    oos.close();
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    public void loadGame(File file){
        FileInputStream in = null;
        ObjectInputStream ois = null;
        try{
            in = new FileInputStream(file);
            ois = new ObjectInputStream(in);
            BoardOfPlay loadedField = (BoardOfPlay) ois.readObject();
            startGame(loadedField);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            if(in != null){
                try{
                    in.close();
                }
                catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
            if(ois != null){
                try{
                    ois.close();
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}

class Bomb implements Serializable{

    private static final long serialVersionUID = 32L;
    private int horz = 0;
    private int vert = 0;
    private boolean isBomb = false;
    private boolean clicked = false;
    private boolean flagged = false;

    public int getHorz(){
        return horz;
    }

    public int getVert(){
        return vert;
    }

    public boolean getIsBomb(){
        return isBomb;
    }

    public Bomb(int horz, int vert){
        this.horz = horz;
        this.vert = vert;
    }

    public void setMine(boolean isBomb){
        this.isBomb = isBomb;
    }

    public void setClicked(boolean clicked){
        this.clicked = clicked;
    }

    public void setFlagged(boolean flagged){
        this.flagged = flagged;
    }

    public boolean getClicked(){
        return clicked;
    }

    public boolean getFlagged(){
        return flagged;
    }

    public int nearBombs(Bomb[][] bom){
        int count = 0;
        if(horz > 0){
            if(bom[horz-1][vert].getIsBomb()){
                count++;
            }
            if(vert < bom[0].length-1){
                if(bom[horz-1][vert+1].getIsBomb()){
                    count++;
                }
            }
        }
        if(vert < bom[0].length-1){
            if(bom[horz][vert+1].getIsBomb()){
                count++;
            }
            if(horz < bom.length-1){
                if(bom[horz+1][vert+1].getIsBomb()){
                    count++;
                }
            }
        }
        if(horz < bom.length-1){
            if(bom[horz+1][vert].getIsBomb()){
                count++;
            }
            if(vert > 0){
                if(bom[horz+1][vert-1].getIsBomb()){
                    count++;
                }
            }
        }
        if(vert > 0){
            if(bom[horz][vert-1].getIsBomb()){
                count++;
            }
            if(horz > 0){
                if(bom[horz-1][vert-1].getIsBomb()){
                    count++;
                }
            }
        }
        return count;
    }
}
