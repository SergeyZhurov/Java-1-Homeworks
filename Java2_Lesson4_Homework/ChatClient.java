/**
 * Java 2. Lesson 4. Homework
 *
 * @ author Sergey Zhurov
 * @ vertion dated Jan 8 2018
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ChatClient extends JFrame implements ActionListener {

    final String TITLE = "ChatClient";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 550;
    final int WINDOW_HEIGHT = 750;
    final String BTN_ENTER = "Enter";
    final String LOG_FILE_NAME = "log.txt";

    Log log;

    JTextArea textArea;
    JPanel southPanel;
    JTextField message;
    JButton enter;

    public static void main(String[] args) {
        new ChatClient();
    }

    ChatClient() {
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        textArea = new JTextArea("Welcome!");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        message = new JTextField();
        message.addActionListener(this);
        enter = new JButton(BTN_ENTER);
        enter.addActionListener(this);
        southPanel.add(message);
        southPanel.add(enter);
        add(BorderLayout.CENTER, textArea);
        add(BorderLayout.SOUTH, southPanel);
        setVisible(true);
        message.requestFocusInWindow();
        try {
            log = new Log(LOG_FILE_NAME);
        } catch (IOException e) {
            textArea.append("\n" + "Can't open log file at: " + LOG_FILE_NAME);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) {
            try {
                textArea.append("\n" + message.getText());
                if(log != null) log.write(message.getText());
            } catch(Exception e) { }
        }
        message.setText("");
        message.requestFocusInWindow();
    }

}