/**
 * Java 1. Lesson 6. Homework task 5
 * 
 * @ author Sergey Zhurov
 * @ vertion dated Dec 11 2017
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import java.util.*;

class TicTacToe {

    public static void main(String[] args) {
        final char DOT_X = 'x';                                        // This is players dot
        final char DOT_Y = 'o';                                        // This is AI dot
        final char DOT_EMPTY = '.';                                    // Empty dot
        Scanner sc = new Scanner(System.in);
        System.out.format("%nWelcome to the TicTacToe game!%n%n");
        System.out.print("Please choose the map size : ");
        final int MAP_SIZE = sc.nextInt();                             // Sise of the map
        System.out.print("Please choose the size of win line : ");
        final int WIN_ROW = sc.nextInt();                              // Size of win line
        Human human = new Human(DOT_X);
        Ai ai = new Ai(MAP_SIZE, WIN_ROW, DOT_Y);
        Map map = new Map(MAP_SIZE, WIN_ROW, DOT_EMPTY);               // Game map
        ValueMap valueMap = new ValueMap(DOT_X, DOT_Y, DOT_EMPTY, MAP_SIZE, WIN_ROW); // Map for evaluating possible AI moves
        map.print();
        while (true) {
            do {
                System.out.format("Enter X and Y (1..%d): %n", MAP_SIZE);
            } while (!map.trySetCell(human.turn(), human.getDot()));    // Human trying to make a move
            map.print();
            if (map.checkWin(human.getDot())) {
                System.out.println("YOU WON!!!");
                break;
            }
            if (map.checkFull()) {
                System.out.println("DRAW!!!");
                break;
            }
            valueMap.clear();
            valueMap.fill(map.getMap());
            while (!map.trySetCell(ai.turn(valueMap.getValueMap()), ai.getDot())); // Ai trying to make a move
            map.print();
            if (map.checkWin(ai.getDot())) {
                System.out.println("AI WON!!!");
                break;
            }
            if (map.checkFull()) {
                System.out.println("DRAW!!!");
                break;
            }
        }
    }
}

class Map {                                                          // Class for the game map
    protected final char DOT_EMPTY;
    protected final int MAP_SIZE, WIN_ROW;
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

class ValueMap extends Map {                                       // Class for the valueMap needed for AI to make a choise
    private final char DOT_X, DOT_Y;
    private int[][] valueMap;                                      // Array for evaluating every possible AI move to block player

    ValueMap (final char DOT_X, final char DOT_Y, final char DOT_EMPTY, final int MAP_SIZE, final int WIN_ROW) {
        super(MAP_SIZE, WIN_ROW, DOT_EMPTY);
        this.DOT_X = DOT_X;
        this.DOT_Y = DOT_Y;
        this.valueMap = new int[MAP_SIZE][MAP_SIZE];
    }

    void clear() {                                                 // Clears valueMap
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                valueMap[i][j] = 0;
    }

    int[][] getValueMap() {
        return valueMap;
    }

    void fill(char[][] map) {
        for (int i = 0; i <= MAP_SIZE - WIN_ROW; i++)              // Going through all possible miniMaps filling valueMap
            for (int j = 0; j <= MAP_SIZE - WIN_ROW; j++)
                countMiniValueMap(i, j, map);
// Uncomment next section to print valueMap after every fill
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

    private void countMiniValueMap(int x, int y, char[][] map) {                        
        // count horizontal
        int[] rowValue = new int[MAP_SIZE];
        for (int j = y; j < y + WIN_ROW; j++)
            for(int i = x; i < x + WIN_ROW; i++) {
                if (map[i][j] == DOT_X) rowValue[j]++;        // The more DOT_X in the row the greater the value of AI move
                if (map[i][j] == DOT_Y) {                     // If there is already DOT_Y in the row, move in this row costs nothing
                    rowValue[j] = 0;
                    break;
                }
            }
        for (int j = y; j < y + WIN_ROW; j++)                // If the cell is free and counted value is more than the one that is already
            for (int i = x; i < x + WIN_ROW; i++)            // in valueMap - assing the value to valueMap
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
            for (int j = y; j < y + WIN_ROW; j++) {
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

interface IPlayer {
    abstract char getDot();
}

abstract class Player implements IPlayer {
    final char DOT;

    Player(char DOT){
        this.DOT = DOT;
    }

    public char getDot() {
        return DOT;
    }
}

class Human extends Player {                                                 // Class for the player
    private Scanner sc = new Scanner(System.in);

    Human(final char DOT){
        super(DOT);
    }

    int[] turn() {
        int[] move = new int[2];
        move[0] = sc.nextInt() - 1;
        move[1] = sc.nextInt() - 1;
        return move;
    }
}

class Ai extends Player {                                                   // Class for AI
    private final int MAP_SIZE, WIN_ROW;
    private Random rand = new Random();

    Ai(int MAP_SIZE, int WIN_ROW, char DOT) {
        super(DOT);
        this.MAP_SIZE = MAP_SIZE;
        this.WIN_ROW = WIN_ROW;
    }

    int[] turn(int[][] valueMap) {                                         // Returns coordinates in [2] array
        int maxPrice;
        int[] move = new int[2];
        outerloop:
        for (maxPrice = WIN_ROW - 1; maxPrice > 0; maxPrice--)            // Determining value of best possible move
            for (int i = 0; i < MAP_SIZE; i++)
                for (int j = 0; j < MAP_SIZE; j++)
                    if (valueMap[i][j] == maxPrice) break outerloop;
        int tempCounter = 0;
        for (int i = 0; i < MAP_SIZE; i++)                                // Counting number of moves with best value
            for (int j = 0; j < MAP_SIZE; j++)
                if (valueMap[i][j] == maxPrice) tempCounter++;
        int aiMove = rand.nextInt(tempCounter) + 1;                      // Picking random move with best value
        tempCounter = 0;
        outerloop:
        for (int i = 0; i < MAP_SIZE; i++)                               // Looking for picked move
            for (int j = 0; j < MAP_SIZE; j++) {
                if (valueMap[i][j] == maxPrice) tempCounter++;
                if (tempCounter == aiMove) {
                    move[0] = i;                                        // Actual AI move
                    move[1] = j;
                    break outerloop;
                }
            }
        return move;
    }
}