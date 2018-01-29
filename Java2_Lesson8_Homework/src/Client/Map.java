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
import java.io.IOException;

class Map {
    private final char DOT_EMPTY, DOT_X, DOT_Y;
    private int mapSize, winRow, CELL_SIZE;

    int getMapSize() {
        return mapSize;
    }

    int getWinRow() {
        return winRow;
    }

    private char[][] map;
    private final Image IMAGE_X, IMAGE_Y, IMAGE_EMPTY;

    Map(char DOT_X, char DOT_Y, int mapSize, int winRow, char DOT_EMPTY, int CELL_SIZE) {//Fills gamemap with empty dots
        this.DOT_X = DOT_X;
        this.DOT_Y = DOT_Y;
        this.mapSize = mapSize;
        this.winRow = winRow;
        this.DOT_EMPTY = DOT_EMPTY;
        this.CELL_SIZE = CELL_SIZE;
        this.map = new char[mapSize][mapSize];
        this.IMAGE_X = new ImageIcon("src\\Client\\X.png").getImage();
        this.IMAGE_Y = new ImageIcon("src\\Client\\O.png").getImage();
        this.IMAGE_EMPTY = new ImageIcon("src\\Client\\EMPTY.png").getImage();
    }

    void newMap() {
        map = new char[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++)
            for (int j = 0; j < mapSize; j++)
                map[i][j] = DOT_EMPTY;
    }

    void setSize(int x) {                                           //Setting size for new game
        mapSize = x;
    }

    void setWinRow(int x) {                                         //Setting size for new game
        winRow = x;
    }

    void paint(Graphics g) {                                        // Paints gamemap
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
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

    int[] getAiTurn() throws IOException {                                           // send map to Server
        String[] s;
        int[] result = new int[2];

        for (int i = 0; i < mapSize; i++)
            for (int j = 0; j < mapSize; j++)
                Window.writer.print(map[i][j]);
        Window.writer.println();
        Window.writer.flush();
        s = Window.reader.readLine().split(" ");
        result[0] = Integer.parseInt(s[0]);
        result[1] = Integer.parseInt(s[1]);
        return result;
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
    Next method divides gamemap into small square maps with the size of winRow.
    After that it checks if there is a win row in any of these minimaps
    */
    boolean checkWin(char dot) {
        int winCounter;
        for (int x = 0; x <= mapSize - winRow; x++)
            for (int y = 0; y <= mapSize - winRow; y++) {
                // check horisontal
                for (int j = y; j < y + winRow; j++){
                    winCounter = 0;
                    for (int i = x; i < x + winRow; i++)
                        if (map[i][j] == dot) winCounter++;
                    if (winCounter == winRow) return true;
                }
                // check vertical
                for (int i = x; i < x + winRow; i++){
                    winCounter = 0;
                    for (int j = y; j < y + winRow; j++)
                        if (map[i][j] == dot) winCounter++;
                    if (winCounter == winRow) return true;
                }
                // check diagonal
                winCounter = 0;
                for (int i = x; i < x + winRow; i++)
                    for (int j = y; j < y + winRow; j++)
                        if ((i - x == j - y) && (map[i][j] == dot)) winCounter++;
                if (winCounter == winRow) return true;
                // other diagonal
                winCounter = 0;
                for (int i = x; i < x + winRow; i++)
                    for(int j = y; j < y + winRow; j++)
                        if ((i - x + j - y == winRow - 1) && (map[i][j] == dot)) winCounter++;
                if (winCounter == winRow) return true;
            }
        return false;
    }

    boolean checkFull() {                                           // True if there are no DOT_EMPTY
        for (int i = 0; i < mapSize; i++)
            for (int j = 0; j < mapSize; j++)
                if (map[i][j] == DOT_EMPTY) return false;
        return true;
    }
}
