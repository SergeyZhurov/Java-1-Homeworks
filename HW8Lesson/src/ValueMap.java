/**
 * Java 1. Lesson 6. Homework task 8
 *
 * @ author Sergey Zhurov
 * @ vertion dated Dec 11 2017
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import java.util.Arrays;

public class ValueMap extends Map{
    private int[][] valueMap;   // Array for evaluating every possible AI move to block player

    ValueMap (final char DOT_X, final char DOT_Y, final char DOT_EMPTY, final int MAP_SIZE,
              final int WIN_ROW, final int CELL_SIZE) {
        super(DOT_X, DOT_Y, MAP_SIZE, WIN_ROW, DOT_EMPTY, CELL_SIZE);
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

    void setSize(int x) {
        MAP_SIZE = x;
    }

    void setWinRow(int x) {
        WIN_ROW = x;
    }
}
