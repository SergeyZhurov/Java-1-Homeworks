package Client;
/**
 * Java 2. Lesson 8. Homework
 *
 * @ author Sergey Zhurov
 * @ vertion dated Jan 29 2018
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

public interface ClientConstants {
    char DOT_X = 'x';                                                  // This is players dot
    char DOT_Y = 'o';                                                  // This is AI dot
    char DOT_EMPTY = '.';                                              // Empty dot
    int WINDOW_SIZE_X = 516;
    int WINDOW_SIZE_Y = 565;
    int CELL_SIZE = 50;
    String BTN_NEW_GAME = "New game";
    String BTN_EXIT = "Exit";
    String[] mapSizeItems = {"3", "4", "5", "6", "7", "8", "9", "10"}; // Array for comboBoxMapSize
    String[] winRowItems = {"3", "4", "5", "6", "7", "8", "9", "10"};  // Array for comboBoxWinRow
    String SERVER_ADDRESS = "localhost";
    int PORT = 123;
}
