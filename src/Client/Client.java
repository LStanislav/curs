package Client;

import java.io.*;
import java.net.Socket;

import static Client.Menu.firstMrenu;
import static Client.Menu.showMenu;

public class Client {
    private static Socket socket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        firstMrenu();
        //System.out.println(showMenu(new String[]{"123", "123"}));
    }
}
