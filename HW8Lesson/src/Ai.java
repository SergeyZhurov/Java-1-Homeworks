/**
 * Java 1. Lesson 6. Homework task 8
 *
 * @ author Sergey Zhurov
 * @ vertion dated Dec 11 2017
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import java.util.Random;

public class Ai extends Player{
    private int mapSize, winRow;
    private Random rand = new Random();

    Ai(final int mapSize, final int winRow, final char DOT) {
        super(DOT);
        this.mapSize = mapSize;
        this.winRow = winRow;
    }

    int[] turn(int[][] valueMap) {                                         // Returns coordinates in [2] array
        int maxPrice;
        int[] move = new int[2];
        outerloop:
        for (maxPrice = winRow - 1; maxPrice > 0; maxPrice--)            // Determining value of best possible move
            for (int i = 0; i < mapSize; i++)
                for (int j = 0; j < mapSize; j++)
                    if (valueMap[i][j] == maxPrice) break outerloop;
        int tempCounter = 0;
        for (int i = 0; i < mapSize; i++)                                // Counting number of moves with best value
            for (int j = 0; j < mapSize; j++)
                if (valueMap[i][j] == maxPrice) tempCounter++;
        int aiMove = rand.nextInt(tempCounter) + 1;                      // Picking random move with best value
        tempCounter = 0;
        outerloop:
        for (int i = 0; i < mapSize; i++)                               // Looking for picked move
            for (int j = 0; j < mapSize; j++) {
                if (valueMap[i][j] == maxPrice) tempCounter++;
                if (tempCounter == aiMove) {
                    move[0] = i;                                        // Actual AI move
                    move[1] = j;
                    break outerloop;
                }
            }
        return move;
    }
    void setSize(int x) {                                               //Setting size for new game
        mapSize = x;
    }

    void setWinRow(int x) {                                             //Setting size for new game
        winRow = x;
    }
}
