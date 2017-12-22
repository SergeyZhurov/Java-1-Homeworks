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
import java.util.Collection;

public class Window extends JFrame {

    final char DOT_X = 'x';                                         // This is players dot
    final Image DOT_X_IMAGE = new ImageIcon("X.png").getImage();
    final Image DOT_Y_IMAGE = new ImageIcon("Y.png").getImage();
    final char DOT_Y = 'o';                                         // This is AI dot
    final char DOT_EMPTY = '.';                                     // Empty dot
    static private int MAP_SIZE = 10;                                         // Sise of the map
    static private int WIN_ROW = 4;                                          // Size of win line
    int WINDOW_SIZE = MAP_SIZE * 50;
    final int WINDOW_DX = WINDOW_SIZE / 10;
    final int WINDOW_DY = WINDOW_SIZE / 2;
    final int CELL_SIZE = WINDOW_SIZE / MAP_SIZE;
    final String BTN_NEW_GAME = "New game";
    final String BTN_EXIT = "Exit";
    private String TITLE = "Welcome to the Tic Tac Toe game.";
    final String[] mapSizeItems = {"3", "4", "5", "6", "7", "8", "9", "10"};
    final String[] winRowItems = {"3", "4", "5", "6", "7", "8", "9", "10"};
    JComboBox comboBoxMapSize = new JComboBox(mapSizeItems);
    JComboBox comboBoxWinRow = new JComboBox(winRowItems);
    static private Window gameWindow;
    boolean gameActive = false;

    Panel panel = new Panel();
    Human human = new Human(DOT_X);
    Ai ai = new Ai(MAP_SIZE, WIN_ROW, DOT_Y);
    Map map = new Map(DOT_X, DOT_Y, MAP_SIZE, WIN_ROW, DOT_EMPTY, CELL_SIZE);
    ValueMap valueMap = new ValueMap(DOT_X, DOT_Y, DOT_EMPTY, MAP_SIZE, WIN_ROW, CELL_SIZE);

    public static void main(String args[]) {
        gameWindow = new Window();
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
                if (gameActive) {
                    int[] x = {e.getX()/CELL_SIZE, e.getY()/CELL_SIZE};
                    if (map.trySetCell(x, DOT_X)) {
                        panel.repaint();
                        if (map.checkWin(human.getDot())) {
                            setTitle("You WON!!!");
                            gameActive = false;
                        }
                        if ((map.checkFull()) && (gameActive)) {
                            setTitle("DRAW.");
                            gameActive = false;
                        }
                        valueMap.clear();
                        valueMap.fill(map.getMap());
                        if (gameActive) map.trySetCell(ai.turn(valueMap.getValueMap()), ai.getDot());
                        panel.repaint();
                        if ((map.checkWin(ai.getDot())) && (gameActive)) {
                            setTitle("AI WON!!!");
                            gameActive = false;
                        }
                        if ((map.checkFull()) && (gameActive)) {
                            setTitle("DRAW.");
                            gameActive = false;
                        }
                    }
                }
            }
        });
        JButton btnNewGame = new JButton(BTN_NEW_GAME);
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.clear();
                String s1 = (String)comboBoxMapSize.getSelectedItem();
                String s2 = (String)comboBoxWinRow.getSelectedItem();
                int a = Integer.parseInt(s1);
                int b = Integer.parseInt(s2);
                if (a >= b) {
                    map.setSize(a);
                    map.setWinRow(b);
                    valueMap.setSize(a);
                    valueMap.setWinRow(b);
                    ai.setSize(a);
                    ai.setWinRow(b);
                    panel.repaint();
                    setTitle("TicTacToe game. Map size: " + a + ". Win row: " + b);
                    gameActive = true;
                } else setTitle("Please select map size greater or equal to win row.");
            }
        });
        JButton btnExit = new JButton(BTN_EXIT);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JLabel labelMapSize = new JLabel("Map size: ");
        JLabel labelWinRow = new JLabel("Win row: ");



        JPanel panelNorthEast = new JPanel();
        panelNorthEast.setLayout(new GridLayout());
        panelNorthEast.add(labelMapSize);
        panelNorthEast.add(comboBoxMapSize);
        panelNorthEast.add(labelWinRow);
        panelNorthEast.add(comboBoxWinRow);

        JPanel panelNorthWest = new JPanel();
        panelNorthWest.setLayout(new BorderLayout());
        panelNorthWest.add(btnNewGame);
        panelNorthWest.add(btnExit);


        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new GridLayout());

        panelNorth.add(panelNorthEast);
        panelNorth.add(panelNorthWest);

        add(panelNorth, BorderLayout.NORTH);
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
