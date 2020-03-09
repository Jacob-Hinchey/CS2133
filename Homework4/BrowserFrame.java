import javax.swing.*;
import java.awt.*;
public class BrowserFrame extends JFrame {
    BrowserFrame(){
        setTitle("HTML Homework 4");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BrowserPanel browserPanel = new BrowserPanel();
        browserPanel.setFrame(this);
        Container container;
        container = getContentPane();
        container.add(browserPanel);
        setVisible(true);
    }
}
