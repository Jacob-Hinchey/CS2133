import java.util.*;
import javax.swing.*;
import java.awt.*;

public class TriPanel extends JPanel{

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    int width, height, triangleSides;
    height = getHeight();
    width = getWidth();
    if(height < width){
      triangleSides = height;
    }
    else{
      triangleSides = width;
    }
    triangleBySquares(g, 0, 0, triangleSides);
  }

  public void triangleBySquares(Graphics g, int horizontal, int vertical, int triangleSides){
    int topHorizontal, topVertical, leftHorizontal, leftVertical, rightHorizontal, rightVertical;
    if(triangleSides == 1){
            g.drawRect(horizontal, vertical, 1, 1);
    }
    else {
        topHorizontal = horizontal + (triangleSides / 4);
        topVertical = vertical;
        leftHorizontal = horizontal;
        leftVertical = vertical + (triangleSides / 2);
        rightHorizontal = horizontal + (triangleSides / 2);
        rightVertical = vertical + (triangleSides / 2);
        triangleSides /= 2;
        triangleBySquares(g, topHorizontal, topVertical, triangleSides);
        triangleBySquares(g, leftHorizontal, leftVertical, triangleSides);
        triangleBySquares(g, rightHorizontal, rightVertical, triangleSides );
    }
  }
}
