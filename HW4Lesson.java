/**
 * Java 1. Lesson 3. Homework
 * 
 * @ author Sergey Zhurov
 * @ vertion dated Dec 5 2017
 * 
 */
 
 import java.util.*;
 
 class HW4Lesson {

    final char DOT_X = 'x';
    final char DOT_Y = 'y';
    final char DOT_EMPTY = '.';
    final int MAP_SIZE = 10;                                             // You can change size of the map
    final int WIN_ROW = 4;                                              // and the number of x/y in a row to win the game
    char[][] map = new char[MAP_SIZE][MAP_SIZE];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    int[][] valueMap = new int[MAP_SIZE][MAP_SIZE];                     // Array for evaluating every possible AI move to block player

public static void main(String[] args) {
        new HW4Lesson();
    }

    HW4Lesson () {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("YOU WON!!!");
                break;
            }
            if (checkFull()) {
                System.out.println("DRAW!!!");
                break;
            }
            fillValueMap();                                            // Evaluating every possible move by AI
            aiTurn();
            printMap();
            if (checkWin(DOT_Y)) {
                System.out.println("AI WON!!!");
                break;
            }
            if (checkFull()) {
                System.out.println("DRAW!!!");
                break;
            }
        }
    }

    void initMap() {
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                map[i][j] = '.';
    }

    void printMap() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++)
                System.out.print(map[j][i] + " ");
            System.out.println();
        }
        System.out.println();
    }

    void humanTurn() {
        int x, y;
        do {
            System.out.format("Enter X and Y (1..%d): %n", MAP_SIZE);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x,y));
        map[x][y] = DOT_X;
    }

    void aiTurn() {
        int maxPrice;
        outerloop:
        for (maxPrice = WIN_ROW - 1; maxPrice > 0; maxPrice--)          // Determining value of best possible move
            for (int i = 0; i < MAP_SIZE; i++)
                for (int j = 0; j < MAP_SIZE; j++)
                    if (valueMap[i][j] == maxPrice) break outerloop;
        int tempCounter = 0;
        for (int i = 0; i < MAP_SIZE; i++)                              // Counting number of moves with best value
            for (int j = 0; j < MAP_SIZE; j++)
                if (valueMap[i][j] == maxPrice) tempCounter++;
        int aiMove = rand.nextInt(tempCounter) + 1;                     // Picking random move with best value
        tempCounter = 0;
        outerloop:
        for (int i = 0; i < MAP_SIZE; i++)                              // Looking for picked move
            for (int j = 0; j < MAP_SIZE; j++) {
                if (valueMap[i][j] == maxPrice) tempCounter++;
                if (tempCounter == aiMove) {
                    map[i][j] = DOT_Y;                                  // Actual AI move
                    break outerloop;
                }
            }
    }

/* Method divides gamemap into small square maps with the size of WIN_ROW. After that it checks if there is 
a win row in any of these minimaps
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

    boolean checkFull() {
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                if (map[i][j] == DOT_EMPTY) return false;
        return true;
    }

    boolean isCellValid(int x, int y) {
        if ((x >= 0) && (y >= 0) && (x < MAP_SIZE) && (y < MAP_SIZE) && (map[x][y] == DOT_EMPTY)) return true;
        else return false;
    }

    void fillValueMap() {
        for (int i = 0; i < MAP_SIZE; i++)                              // Clearing valueMap
            for (int j = 0; j < MAP_SIZE; j++)
                valueMap[i][j] = 0;
        for (int i = 0; i <= MAP_SIZE - WIN_ROW; i++)                   // Going through all possible miniMaps filling valueMap
            for (int j = 0; j <= MAP_SIZE - WIN_ROW; j++)
                countMiniValueMap(i, j);
// Uncomment the next section to print valueMap after every human turn
/*
            for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++)
                System.out.print(valueMap[j][i] + " ");
            System.out.println();
        }
        System.out.println();
*/
    }

/* Method is counting value of every cell in minimap (left upper courner at x/y, size of minimap is WIN_ROW)
and assings it to the same cell on the valueMap if the value on minimap is greater than on valueMap
*/

    void countMiniValueMap(int x, int y) {
        // count horizontal
        int[] rowValue = new int[MAP_SIZE];
        for (int j = y; j < y + WIN_ROW; j++)
            for(int i = x; i < x + WIN_ROW; i++) {
                if (map[i][j] == DOT_X) rowValue[j]++;                   // The more DOT_X in the row the greater the value of AI move
                if (map[i][j] == DOT_Y) {                                // If there is already DOT_Y in the row, move in this row costs nothing
                    rowValue[j] = 0;
                    break;
                }
            }
        for (int j = y; j < y + WIN_ROW; j++)                           // If the cell is free and counted value is more than the one that is already
            for (int i = x; i < x + WIN_ROW; i++)                       // in valueMap - assing the value to valueMap
                if ((map[i][j] == DOT_EMPTY) && (rowValue[j] > valueMap[i][j])) valueMap[i][j] = rowValue[j];
        // count vertical
        Arrays.fill(rowValue, 0);
        for (int i = x; i < x + WIN_ROW; i++)
            for (int j = y; j < y + WIN_ROW; j++) {
                if (map[i][j] == DOT_X) rowValue[i]++;
                if (map[i][j] == DOT_Y) {
                    rowValue[i] = 0;
                    break;
                }
            }
        for (int i = x; i < x + WIN_ROW; i++)
            for (int j = y; j < y + WIN_ROW; j++)
                if ((map[i][j] == DOT_EMPTY) && (rowValue[i] > valueMap[i][j])) valueMap[i][j] = rowValue[i];
        // count diagonal
        rowValue[0] = 0;
        outerloop:
        for (int i = x; i < x + WIN_ROW; i++) {
            for (int j = 0; j < y + WIN_ROW; j++) {
                if ((i - x == j - y) && (map[i][j] == DOT_X)) rowValue[0]++;
                if ((i - x == j - y) && (map[i][j] == DOT_Y)) {
                    rowValue[0] = 0;
                    break outerloop;
                }
            }
        }
        for (int i = x; i < x + WIN_ROW; i++)
            for (int j = y; j < y + WIN_ROW; j++)
                if ((i - x == j - y) && (map[i][j] == DOT_EMPTY) && (rowValue[0] > valueMap[i][j])) valueMap[i][j] = rowValue[0];
        // count other diagonal
        rowValue[0] = 0;
        outerloop:
        for (int i = x; i < x + WIN_ROW; i++) {
            for (int j = y; j < y + WIN_ROW; j++) {
                if ((i - x + j - y == WIN_ROW - 1) && (map[i][j] == DOT_X)) rowValue[0]++;
                if ((i - x + j - y == WIN_ROW - 1) && (map[i][j] == DOT_Y)) {
                    rowValue[0] = 0;
                    break outerloop;
                }
            }
        }
        for (int i = x; i < x + WIN_ROW; i++)
            for (int j = y; j < y + WIN_ROW; j++)
                if ((i -x + j - y == WIN_ROW - 1) && (map[i][j] == DOT_EMPTY) && (rowValue[0] > valueMap[i][j])) valueMap[i][j] = rowValue[0];
    }
}