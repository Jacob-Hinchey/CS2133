import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrowserPanel extends JPanel {
    BrowserFrame browserFrame;
    BrowserPage browserPage;
    JTextField url;
    JScrollPane mainPage;
    JTextArea textSpace;
    public void setFrame(BrowserFrame browserFrame){
        this.browserFrame = browserFrame;
    }

    public BrowserPanel(){
        super();
        setLayout(new BorderLayout());
        url = new JTextField();
        textSpace = new JTextArea();
        textSpace.setLineWrap(true);
        mainPage = new JScrollPane(textSpace);
        url.addActionListener(new UrlTextFieldHandler());
        add(url, BorderLayout.PAGE_START);
        add(mainPage, BorderLayout.CENTER);
    }

    private class UrlTextFieldHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String enteredText = url.getText();
            UrlRequest urlRequest = new UrlRequest();
            String pageText = urlRequest.recieveHTMLtoDisplay(enteredText);
            browserPage = new BrowserPage(pageText);
            textSpace.setText(browserPage.pageBody);
            browserFrame.setTitle(browserPage.pageHeader);
        }
    }
}
