/**
 * Java 2. Lesson 6. Homework
 *
 * @ author Sergey Zhurov
 * @ vertion dated Jan 14 2018
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;


public class SimpleClientExceptionAbuse implements Runnable {
    final static String SERVER_ADDR = "127.0.0.1"; // or "localhost"
    final static int SERVER_PORT = 2048;
    final static int ABUSE_LEVEL = 100;
    static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread[] thread = new Thread[ABUSE_LEVEL];                      // threads to abuse Exception
        try {
            new Socket(SERVER_ADDR, SERVER_PORT);                       // checking if SimpleServer is alive
            // skipping code below if Exception is caught (SimpleServer is not active)
            for (int i = 0; i < ABUSE_LEVEL; i++) {
                thread[i] = new Thread(new SimpleClientExceptionAbuse());
                thread[i].start();
            }
            for (Thread t : thread)
                t.join();
            System.out.println();
            System.out.println("ConnectException generated " + count + " times");
        } catch (IOException e) {
            System.out.println("SimpleServer doesnt work at: " + SERVER_ADDR + " port #: " + SERVER_PORT);
            System.out.println("Nothing else i can do.");
        }
    }

    @Override
    public void run() {
        try {
            new Socket(SERVER_ADDR, SERVER_PORT + 1);    // trying to connect to the wrong port
        } catch (ConnectException e) {
            System.out.println(e.getMessage());
            count++;                                          // counting number of caught exceptions
        } catch (IOException e) { }
    }
}
