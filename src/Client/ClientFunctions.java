package Client;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static Client.Menu.*;

public class ClientFunctions {
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static Scanner reader;
    private static String encrypt(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                result.append((char) (string.charAt(i) - 10));
            } else result.append((char) (string.charAt(i) + 10));
        }
        return result.toString();
    }
    public static String decoding(String str){
        StringBuilder ans = new StringBuilder();
        for (int i=0; i<str.length(); i++) {
            if (Character.isDigit(str.charAt(i) + 10)) {
                ans.append((char)(str.charAt(i) + 10));
            }
            else ans.append((char)(str.charAt(i) - 10));
        }
        return ans.toString();
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
                    if ((!ans2.equals("")) && ((ans2.charAt(0) == 'Н') || (ans2.charAt(0) == 'N'))) {
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

    public static void deleteUser(){
        connector.connect();
        StringBuilder msg = new StringBuilder("deleteUser;");
        System.out.println("Введите логин, удаляемого пользователя:");
        String log = reader.nextLine();
        msg.append(encrypt(log) + ";");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine()+"!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showUsers(){
        connector.connect();
        StringBuilder msg = new StringBuilder("showUsers;");
        try {
            out.write(msg+System.lineSeparator());
            out.flush();
            String buf = in.readLine();
            while (!buf.equals(";;")){
                String part[] = buf.split(";",3);
                System.out.println(decoding(part[0]) + "  |  " + decoding(part[1]));
                buf = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addExpert(){
        String log,pass1, pass2, competencyLevel;

        System.out.println("Регистрация");
        System.out.println("Введите новый логин: ");
        log = reader.nextLine();

        System.out.println("Введите новый пароль: ");
        pass1 = reader.nextLine();

        System.out.println("Подтвердите пароль: ");
        pass2 = reader.nextLine();
        if (!pass1.equals(pass2)){
            System.out.println("Пароли не совпадают!");
            return;
        }
        System.out.println("Введите уровень компетентности эксперта(0..10): ");
        competencyLevel = reader.nextLine();
        try {
            if ((Integer.parseInt(competencyLevel)<0) || (Integer.parseInt(competencyLevel)>10)){
                System.out.println("Неверный уровень компетентности!");
                return;
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        connector.connect();
        try {
            StringBuilder msg = new StringBuilder("addExpert;");
            msg.append(encrypt(log)+";");
            msg.append(encrypt(pass1)+";");
            msg.append(competencyLevel+";");
            out.write(msg+System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showExpert(){
        connector.connect();
        StringBuilder msg = new StringBuilder("showExperts;");
        try {
            out.write(msg+System.lineSeparator());
            out.flush();
            String buf = in.readLine();
            while (!buf.equals(";;;;")){
                String part[] = buf.split(";", 20);
                System.out.print(decoding(part[0]) + "  |  " + decoding(part[1]) + "  |  " + part[2] + " | (");

                String ratings[] = part[3].split(" ", 20);
                for (int i=0; i<ratings.length-1; i++){
                    System.out.print(ratings[i] + ",");
                }
                System.out.println(ratings[ratings.length-1] + ")");
                buf = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
