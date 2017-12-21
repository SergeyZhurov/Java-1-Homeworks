/**
 * Java 1. Lesson 6. Homework task 8
 *
 * @ author Sergey Zhurov
 * @ vertion dated Dec 11 2017
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

public class Map {
    private final char DOT_EMPTY;
    private final int MAP_SIZE, WIN_ROW;
    private char[][] map;

    Map(int MAP_SIZE, int WIN_ROW, char DOT_EMPTY) {                // Fills gamemap with empty dots
        this.MAP_SIZE = MAP_SIZE;
        this.WIN_ROW = WIN_ROW;
        this.DOT_EMPTY = DOT_EMPTY;
        this.map = new char[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    void print() {                                                 // Prints gamemap
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++)
                System.out.print(map[j][i] + " ");
            System.out.println();
        }
        System.out.println();
    }

    char[][] getMap() {                                           // Returns the gamemap
        return map;
    }

    boolean trySetCell(int[] a, char dot) {                      // int a[2] is the coordinates for the try
        int x = a[0];                                            // Tries to put the "dot" in the selected cell
        int y = a[1];
        if (((x >= 0) && (y >= 0) && (x < MAP_SIZE) && (y < MAP_SIZE) && (map[x][y]) == DOT_EMPTY)) {
            map[x][y] = dot;
            return true;
        } else return false;
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
