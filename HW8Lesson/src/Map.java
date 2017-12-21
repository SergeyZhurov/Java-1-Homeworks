/**
 * Java 1. Lesson 6. Homework task 8
 *
 * @ author Sergey Zhurov
 * @ vertion dated Dec 11 2017
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Map {
    protected final char DOT_EMPTY, DOT_X, DOT_Y;
    protected final int MAP_SIZE, WIN_ROW, CELL_SIZE;
    protected char[][] map;
    private final Image IMAGE_X, IMAGE_Y, IMAGE_EMPTY;

    Map(char DOT_X, char DOT_Y, int MAP_SIZE, int WIN_ROW, char DOT_EMPTY, int CELL_SIZE) {                // Fills gamemap with empty dots
        this.DOT_X = DOT_X;
        this.DOT_Y = DOT_Y;
        this.MAP_SIZE = MAP_SIZE;
        this.WIN_ROW = WIN_ROW;
        this.DOT_EMPTY = DOT_EMPTY;
        this.CELL_SIZE = CELL_SIZE;
        this.map = new char[MAP_SIZE][MAP_SIZE];
        this.IMAGE_X = new ImageIcon("src\\X.png").getImage();
        this.IMAGE_Y = new ImageIcon("src\\O.png").getImage();
        this.IMAGE_EMPTY = new ImageIcon("src\\EMPTY.png").getImage();
    }

    void clear() {
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    void paint(Graphics g) {                                        // Paints gamemap
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == DOT_X) {
                    g.drawImage(IMAGE_X, i * CELL_SIZE, j * CELL_SIZE, null);
                }
                if (map[i][j] == DOT_Y) {
                    g.drawImage(IMAGE_Y, i * CELL_SIZE, j * CELL_SIZE, null);
                }
                if (map[i][j] == DOT_EMPTY) {
                    g.drawImage(IMAGE_EMPTY, i * CELL_SIZE, j * CELL_SIZE, null);
                }
            }
        }
    }

    char[][] getMap() {                                           // Returns the gamemap
        return map;
    }

    boolean trySetCell(int[] a, char dot) {                      // int a[2] is the coordinates for the try
        int x = a[0];                                            // Tries to put the "dot" in the selected cell
        int y = a[1];
        if ((map[x][y]) == DOT_EMPTY) {
            map[x][y] = dot;
            return true;
        }
        return false;
    }
    /*
    Next method divides gamemap into small square maps with the size of WIN_ROW.
    After that it checks if there is a win row in any of these minimaps
    */
    boolean checkWin(char dot) {
        int winCounter;
        for (int x = 0; x <= MAP_SIZE - WIN_ROW; x++)
            for (int y = 0; y <= MAP_SIZE - WIN_ROW; y++) {
                // check horisontal
                for (int j = y; j < y + WIN_ROW; j++){
                    winCounter = 0;
                    for (int i = x; i < x + WIN_ROW; i++)
                        if (map[i][j] == dot) winCounter++;
                    if (winCounter == WIN_ROW) return true;
                }
                // check vertical
                for (int i = x; i < x + WIN_ROW; i++){
                    winCounter = 0;
                    for (int j = y; j < y + WIN_ROW; j++)
                        if (map[i][j] == dot) winCounter++;
                    if (winCounter == WIN_ROW) return true;
                }
                // check diagonal
                winCounter = 0;
                for (int i = x; i < x + WIN_ROW; i++)
                    for (int j = y; j < y + WIN_ROW; j++)
                        if ((i - x == j - y) && (map[i][j] == dot)) winCounter++;
                if (winCounter == WIN_ROW) return true;
                // other diagonal
                winCounter = 0;
                for (int i = x; i < x + WIN_ROW; i++)
                    for(int j = y; j < y + WIN_ROW; j++)
                        if ((i - x + j - y == WIN_ROW - 1) && (map[i][j] == dot)) winCounter++;
                if (winCounter == WIN_ROW) return true;
            }
        return false;
    }

    boolean checkFull() {                                           // True if there are no DOT_EMPTY
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                if (map[i][j] == DOT_EMPTY) return false;
        return true;
    }
}
