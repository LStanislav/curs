package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(4004);
            System.out.println("Server are started!");
            clientSocket = server.accept();

            while (true) {

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String msg = in.readLine();
                System.out.println("!" + msg + "!");
                out.write("Server get: " + msg + System.lineSeparator());
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
