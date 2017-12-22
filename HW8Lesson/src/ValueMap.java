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

    ValueMap (final char DOT_X, final char DOT_Y, final char DOT_EMPTY, final int mapSize,
              final int winRow, final int CELL_SIZE) {
        super(DOT_X, DOT_Y, mapSize, winRow, DOT_EMPTY, CELL_SIZE);
        this.valueMap = new int[mapSize][mapSize];
    }

    void clear() {                                                 // Clears valueMap
        for (int i = 0; i < mapSize; i++)
            for (int j = 0; j < mapSize; j++)
                valueMap[i][j] = 0;
    }

    int[][] getValueMap() {
        return valueMap;
    }

    void fill(char[][] map) {
        for (int i = 0; i <= mapSize - winRow; i++)              // Going through all possible miniMaps filling valueMap
            for (int j = 0; j <= mapSize - winRow; j++)
                countMiniValueMap(i, j, map);
// Uncomment next section to print valueMap after every fill
/*
            for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++)
                System.out.print(valueMap[j][i] + " ");
            System.out.println();
        }
        System.out.println();
*/
    }

/* Method is counting value of every cell in minimap (left upper courner at x/y, size of minimap is winRow)
and assings it to the same cell on the valueMap if the value on minimap is greater than on valueMap
*/

    private void countMiniValueMap(int x, int y, char[][] map) {
        // count horizontal
        int[] rowValue = new int[mapSize];
        for (int j = y; j < y + winRow; j++)
            for(int i = x; i < x + winRow; i++) {
                if (map[i][j] == DOT_X) rowValue[j]++;        // The more DOT_X in the row the greater the value of AI move
                if (map[i][j] == DOT_Y) {                     // If there is already DOT_Y in the row, move in this row costs nothing
                    rowValue[j] = 0;
                    break;
                }
            }
        for (int j = y; j < y + winRow; j++)                // If the cell is free and counted value is more than the one that is already
            for (int i = x; i < x + winRow; i++)            // in valueMap - assing the value to valueMap
                if ((map[i][j] == DOT_EMPTY) && (rowValue[j] > valueMap[i][j])) valueMap[i][j] = rowValue[j];
        // count vertical
        Arrays.fill(rowValue, 0);
        for (int i = x; i < x + winRow; i++)
            for (int j = y; j < y + winRow; j++) {
                if (map[i][j] == DOT_X) rowValue[i]++;
                if (map[i][j] == DOT_Y) {
                    rowValue[i] = 0;
                    break;
                }
            }
        for (int i = x; i < x + winRow; i++)
            for (int j = y; j < y + winRow; j++)
                if ((map[i][j] == DOT_EMPTY) && (rowValue[i] > valueMap[i][j])) valueMap[i][j] = rowValue[i];
        // count diagonal
        rowValue[0] = 0;
        outerloop:
        for (int i = x; i < x + winRow; i++) {
            for (int j = y; j < y + winRow; j++) {
                if ((i - x == j - y) && (map[i][j] == DOT_X)) rowValue[0]++;
                if ((i - x == j - y) && (map[i][j] == DOT_Y)) {
                    rowValue[0] = 0;
                    break outerloop;
                }
            }
        }
        for (int i = x; i < x + winRow; i++)
            for (int j = y; j < y + winRow; j++)
                if ((i - x == j - y) && (map[i][j] == DOT_EMPTY) && (rowValue[0] > valueMap[i][j])) valueMap[i][j] = rowValue[0];
        // count other diagonal
        rowValue[0] = 0;
        outerloop:
        for (int i = x; i < x + winRow; i++) {
            for (int j = y; j < y + winRow; j++) {
                if ((i - x + j - y == winRow - 1) && (map[i][j] == DOT_X)) rowValue[0]++;
                if ((i - x + j - y == winRow - 1) && (map[i][j] == DOT_Y)) {
                    rowValue[0] = 0;
                    break outerloop;
                }
            }
        }
        for (int i = x; i < x + winRow; i++)
            for (int j = y; j < y + winRow; j++)
                if ((i -x + j - y == winRow - 1) && (map[i][j] == DOT_EMPTY) && (rowValue[0] > valueMap[i][j])) valueMap[i][j] = rowValue[0];
    }

    void setSize(int x) {               //Setting size for new game
        mapSize = x;
    }

    void setWinRow(int x) {             //Setting size for new game
        winRow = x;
    }
}
