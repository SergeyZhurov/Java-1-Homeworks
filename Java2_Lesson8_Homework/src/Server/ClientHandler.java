package Server;
/* Java 2. Lesson 8. Homework
*
* @ author Sergey Zhurov
* @ vertion dated Jan 29 2018
* @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
*/

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private int mapSize, winRow;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String clientMessage;
        String[] initClientData;
        int[] turn;
        char[][] map = new char[0][0];                       // must be initialized with "new game" command from client
        int currentRead;

        System.out.println("Client " + Thread.currentThread().getName() + " connected.");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream())){
            do {
                clientMessage = reader.readLine();
                if (!clientMessage.equals(null)) {
                    switch (clientMessage) {
                        case "new game":
                            initClientData = reader.readLine().split(" ");
                            this.mapSize = Integer.parseInt(initClientData[0]);
                            this.winRow = Integer.parseInt(initClientData[1]);
                            map = new char[mapSize][mapSize];
                            Server.valueMap.put(Thread.currentThread().getName(), new ValueMap(mapSize, winRow));
                            break;
                        case "turn":
                            Server.valueMap.get(Thread.currentThread().getName()).clear();
                            clientMessage = reader.readLine();
                            currentRead = 0;
                            try {
                                for (int i = 0; i < mapSize; i++)
                                    for (int j = 0; j < mapSize; j++) {
                                        map[i][j] = clientMessage.charAt(currentRead);
                                        currentRead++;
                                    }
                            } catch (StringIndexOutOfBoundsException e) { continue; }
                            Server.valueMap.get(Thread.currentThread().getName()).fill(map);
                            turn = Ai.turn(Server.valueMap.get(Thread.currentThread().getName()).getValueMap(),
                                    mapSize, winRow);
                            writer.println(turn[0] + " " + turn[1]);
                            writer.flush();
                    }
                }
            } while (!"exit".equals(clientMessage));
            socket.close();
            System.out.println("Client " + Thread.currentThread().getName() + " disconnected.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
