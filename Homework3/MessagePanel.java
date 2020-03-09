import java.util.*;
import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel{

  public void paintComponent(Graphics g){

    super.paintComponent(g);
    g.drawString("Homework 3 in CSII!", 235, 300);
    g.drawLine(200, 450, 400, 450);
    g.drawLine(200, 450, 200, 200);
    g.drawLine(400, 450, 400, 200);
    g.drawLine(200, 200, 240, 200);
    g.drawLine(400, 200, 360, 200);
    g.drawLine(240, 200, 260, 150);
    g.drawLine(360, 200, 340, 150);
    g.drawLine(260, 150, 260, 100);
    g.drawLine(340, 150, 340, 100);
    g.drawOval(260, 85, 80, 30);
  }
}
