package Server;
/**
 * Java 2. Lesson 8. Homework
 *
 * @ author Sergey Zhurov
 * @ vertion dated Jan 29 2018
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import java.util.Random;

class Ai {
    static private Random rand = new Random();

    static synchronized int[] turn(int[][] valueMap, int mapSize, int winRow) { // Returns coordinates in [2] array
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
}
