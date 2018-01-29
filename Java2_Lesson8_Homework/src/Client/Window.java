package Client;
/**
 * Java 2. Lesson 8. Homework
 *
 * @ author Sergey Zhurov
 * @ vertion dated Jan 29 2018
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class Window extends JFrame implements ClientConstants {

    private String title = "Welcome to the Tic Tac Toe game.";
    private boolean gameActive = false;                                      // for stopping the game at the end

    private JComboBox comboBoxMapSize = new JComboBox(mapSizeItems);
    private JComboBox comboBoxWinRow = new JComboBox(winRowItems);
    private Panel panel = new Panel();                                               // Game panel
    private Map map = new Map(DOT_X, DOT_Y, 3, 3, DOT_EMPTY, CELL_SIZE);

    static BufferedReader reader;
    static PrintWriter writer;
    private static Socket socket;

    public static void main(String args[]) {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            new Window();
        } catch (IOException e) { System.exit(44); }
    }

    private Window() {
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
        setLocationRelativeTo(null);
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
                        if (map.checkWin(DOT_X)) {
                            setTitle("You WON!!!");
                            gameActive = false;
                        }
                        if ((map.checkFull()) && (gameActive)) {
                            setTitle("DRAW.");
                            gameActive = false;
                        }
                        try {
                            writer.println("turn");// signaling thea human turn is over. Server will wait for map
                            writer.flush();
                        if (gameActive) map.trySetCell(map.getAiTurn(), DOT_Y); // sending map and w8ing for AI move
                        } catch (IOException ex) { System.exit(45); }
                        panel.repaint();
                        if ((map.checkWin(DOT_Y)) && (gameActive)) {
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
                String s1 = (String)comboBoxMapSize.getSelectedItem();
                String s2 = (String)comboBoxWinRow.getSelectedItem();
                int a = Integer.parseInt(s1);
                int b = Integer.parseInt(s2);
                if(tryInitNewMap(a, b)) {
                    writer.println("new game");                                 // initializing new game on server side
                    writer.println(map.getMapSize() + " " + map.getWinRow());
                    writer.flush();
                }
            }
        });
        JButton btnExit = new JButton(BTN_EXIT);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.println("exit");
                    writer.flush();
                    reader.close();
                    writer.close();
                    socket.close();
                } catch (IOException ex) { System.exit(46); }
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
        panelNorthWest.setLayout(new GridLayout());
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

    private boolean tryInitNewMap(int a, int b) {               // Initializes new game with new parameters if possible
        if (a >= b) {
            map.setSize(a);
            map.setWinRow(b);
            map.newMap();
            panel.repaint();
            setTitle("TicTacToe game. Map size: " + a + ". Win row: " + b);
            gameActive = true;
            return true;
        } else {
            setTitle("Please select map size greater or equal to win row.");
            return false;
        }
    }
}
