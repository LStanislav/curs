package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static Client.Menu.*;

public class ClientFunctions {
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static Scanner reader;
    private static String encrypt(String password) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                result.append((char) (password.charAt(i) - 10));
            } else result.append((char) (password.charAt(i) + 10));
        }
        return result.toString();
    }
    private static Connector connector = ()->{
        try {
            clientSocket = new Socket("localhost", 4004);
            reader = new Scanner(System.in);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    };

    public static void authorization(String type) {
        try {
            String ans;
            while (true){
                connector.connect();
                StringBuilder msg = new StringBuilder("authorization;");
                msg.append(type);
                msg.append(";");
                System.out.print("Логин: ");
                msg.append(encrypt(reader.nextLine()));
                msg.append(";");
                System.out.print("Пароль: ");
                msg.append(encrypt(reader.nextLine()));
                msg.append(";");
                out.write(msg + System.lineSeparator());
                out.flush();
                ans = in.readLine();
                if (ans.equals("No")) {
                    System.out.println("Неверный логин или пароль!");
                    System.out.println("Попробовать еще раз?(Д/Н)");
                    String ans2 = reader.nextLine();
                    if ((ans2.charAt(0) == 'Н') || (ans2.charAt(0) == 'N')) {
                        break;
                    }
                } else break;
            }
            if (ans.equals("Yes")) {
                switch (type) {
                    case "admin":
                        adminMenu();
                        break;
                    case "user":
                        userMenu();
                        break;
                    case "expert":
                        expertMenu();
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void exit(){
        connector.connect();
        try {
            out.write("exit;"+System.lineSeparator());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(){
        String log,pass1, pass2;
        System.out.println("Регистрация");
        System.out.println("Введите новый логин:");
        log = reader.nextLine();

        System.out.println("Введите новый пароль:");
        pass1 = reader.nextLine();

        System.out.println("Подтвердите пароль:");
        pass2 = reader.nextLine();
        if (!pass1.equals(pass2)){
            System.out.println("Пароли не совпадают!");
            return;
        }
        connector.connect();
        try {
            StringBuilder msg = new StringBuilder("addUser;");
            msg.append(encrypt(log)+";");
            msg.append(encrypt(pass1)+";");
            out.write(msg+System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
