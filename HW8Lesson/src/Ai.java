/**
 * Java 1. Lesson 6. Homework task 8
 *
 * @ author Sergey Zhurov
 * @ vertion dated Dec 11 2017
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import java.util.Random;

public class Ai extends Player{
    private final int MAP_SIZE, WIN_ROW;
    private Random rand = new Random();

    Ai(final int MAP_SIZE, final int WIN_ROW, final char DOT) {
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
