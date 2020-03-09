import java.util.*;
import javax.swing.*;
import java.awt.*;

public class TriFrame extends JFrame{

  public TriFrame(){
    setTitle("Sierpinski");
    setSize(600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container triangle = getContentPane();
    TriPanel panel = new TriPanel();
    triangle.add(panel);
    setVisible(true);
  }
}
