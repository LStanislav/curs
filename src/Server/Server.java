package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static Server.ServerFunctions.*;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(4004);
            System.out.println("Server are started!");

            while (true) {
                clientSocket = server.accept();
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String message = in.readLine();

                String msg[];
                msg = message.split(";", 10);
                switch (msg[0]) {
                    case "authorization":
                        out.write(authorization(msg[1], msg[2], msg[3]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "addUser":
                        out.write(addUser(msg[1], msg[2]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showUsers":
                        List<String> list = showUsers();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "deleteUser":
                        out.write(deleteUser(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "addExpert":
                        out.write(addExpert(msg[1], msg[2], msg[3]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showExperts":
                        list = showExperts();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "findExpert":
                        out.write(findExpert(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "deleteExpert":
                        out.write(deleteExpert(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "editExpert":
                        out.write(editExpert(msg[1],msg[2]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "exit":
                        return;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
