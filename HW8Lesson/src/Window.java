/**
 * Java 1. Lesson 6. Homework task 8
 *
 * @ author Sergey Zhurov
 * @ vertion dated Dec 11 2017
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {

    final char DOT_X = 'x';                                         // This is players dot
    final Image DOT_X_IMAGE = new ImageIcon("X.png").getImage();
    final Image DOT_Y_IMAGE = new ImageIcon("Y.png").getImage();
    final char DOT_Y = 'o';                                         // This is AI dot
    final char DOT_EMPTY = '.';                                     // Empty dot
    final int MAP_SIZE = 10;                                         // Sise of the map
    final int WIN_ROW = 4;                                          // Size of win line
    final int WINDOW_SIZE = MAP_SIZE * 50;
    final int WINDOW_DX = WINDOW_SIZE / 10;
    final int WINDOW_DY = WINDOW_SIZE / 2;
    final int CELL_SIZE = WINDOW_SIZE / MAP_SIZE;
    final String BTN_NEW_GAME = "New game";
    final String BTN_EXIT = "Exit";
    final String TITLE = "Tic Tac Toe game. Map size: " + MAP_SIZE;
    final String[] mapSizeItems = {"3", "4", "5", "6", "7", "8", "9", "10"};
    final String[] winRowItems = {"3", "4", "5", "6", "7", "8", "9", "10"};

    Panel panel = new Panel();
    Human human = new Human(DOT_X);
    Ai ai = new Ai(MAP_SIZE, WIN_ROW, DOT_Y);
    Map map = new Map(DOT_X, DOT_Y, MAP_SIZE, WIN_ROW, DOT_EMPTY, CELL_SIZE);
    ValueMap valueMap = new ValueMap(DOT_X, DOT_Y, DOT_EMPTY, MAP_SIZE, WIN_ROW, CELL_SIZE);

    public static void main(String args[]) {
        new Window();
    }

    Window() {
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);
        setLocationRelativeTo(null); // to the center
        setResizable(false);

        panel.setBackground(Color.white);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int[] x = {e.getX()/CELL_SIZE, e.getY()/CELL_SIZE};
                if (map.trySetCell(x, DOT_X)) {
                    panel.repaint();
                    if (map.checkWin(human.getDot())) {

                    }
                    if (map.checkFull()) {

                    }
                    valueMap.clear();
                    valueMap.fill(map.getMap());
                    map.trySetCell(ai.turn(valueMap.getValueMap()), ai.getDot());
                    panel.repaint();
                    if (map.checkWin(human.getDot())) {

                    }
                    if (map.checkFull()) {

                    }
                }
            }
        });
        JButton init = new JButton(BTN_NEW_GAME);
        init.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.clear();
                panel.repaint();
            }
        });
        JButton exit = new JButton(BTN_EXIT);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JComboBox comboBoxMapSize = new JComboBox(mapSizeItems);
        JComboBox comboBoxWinRow = new JComboBox(winRowItems);

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new GridLayout());
        panelBtn.add(comboBoxMapSize);
        panelBtn.add(comboBoxWinRow);
        panelBtn.add(init);
        panelBtn.add(exit);

        setLayout(new BorderLayout());
        add(panelBtn, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    class Panel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            map.paint(g);
        }
    }
}
