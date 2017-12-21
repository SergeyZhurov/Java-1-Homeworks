/**
 * Java 1. Lesson 6. Homework task 8
 *
 * @ author Sergey Zhurov
 * @ vertion dated Dec 11 2017
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import java.util.Scanner;

public class Human extends Player{
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
