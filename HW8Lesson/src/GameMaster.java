/**
 * Java 1. Lesson 6. Homework task 8
 *
 * @ author Sergey Zhurov
 * @ vertion dated Dec 11 2017
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import java.util.Scanner;

public class GameMaster {
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
