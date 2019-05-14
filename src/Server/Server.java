package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static int numberUsers = 0;

    public static void incNumberUsers() {
        numberUsers++;
    }
    public static void decNumberUsers() {
        numberUsers--;
    }

    public static void showNumberUsers() {
        System.out.println("Количество пользователей на сервере: " + numberUsers);
    }

    public static void main(String[] args) throws IOException {
        try {
            server = new ServerSocket(4004);
            System.out.println("Server are started!");

            while (true) {
                clientSocket = server.accept();
                new ServerThread(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            server.close();
            clientSocket.close();
        }
    }
}
