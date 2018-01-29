package Server;
/* Java 2. Lesson 8. Homework
*
* @ author Sergey Zhurov
* @ vertion dated Jan 29 2018
* @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
*/

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server implements ServerConstants {
    static volatile Map<String, ValueMap> valueMap = new HashMap<>();

    public static void main(String[] args) {
        new Server();
    }

    private Server() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("TicTacToe Server started!");
            while (true) {
                Socket socket = server.accept();
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException ex) {
            System.out.println("Failed to open port 123");
        }
    }
}
