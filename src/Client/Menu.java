package Client;

import java.util.Scanner;

import static Client.ClientFunctions.*;

public class Menu {
    public static void clearScreen() {
        for (int i=0; i<50; i++){
            System.out.println();
        }
    }
    public static int showMenu(String punkts[]){
        for(int i=0; i<punkts.length; i++) {
            System.out.println((i+1)+". " + punkts[i]);
        }
        System.out.println("0. Выход");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        int x;
        try {
            x = Integer.parseInt(choice);
        }
        catch (NumberFormatException e){
            x=-1;
        }
        if ((x>=0) && (x<=punkts.length)){
            return x;
        }
        else return -1;
    }
    public static void firstMrenu(){
        boolean contin = true;
        while (contin){
            int choice = showMenu(new String[]{"Вход как Админ","Вход как Пользователь","Вход как Эксперт"});
            switch (choice){
                case 1:
                    System.out.println(1);
                    authorization("admin");
                    break;
                case 2:
                    authorization("user");
                    System.out.println(2);
                    break;
                case 3:
                    authorization("expert");
                    System.out.println(3);
                    break;
                case 0:
                    exit();
                    contin = false;
                    break;
                default:
                    System.out.println("Неверная команда!!!");
                break;
            }
        }
    }
    public static void adminMenu(){
        boolean contin = true;
        while (contin){
            int choice = showMenu(new String[]{"пункт1","пункт2","Управление пользователями", "пункт4"});
            switch (choice){
                case 1:
                    System.out.println(1);
                    break;
                case 2:
                    System.out.println(2);
                    break;
                case 3:
                    System.out.println(3);
                    addUser();
                    break;
                case 0:
                    contin = false;
                    break;
                default:
                    System.out.println("Неверная команда!!!");
                    break;
            }
        }
    }
    public static void userMenu(){
        boolean contin = true;
        while (contin){
            int choice = showMenu(new String[]{"пункт1","пункт2","Управление пользователями", "пункт4"});
            switch (choice){
                case 1:
                    System.out.println(1);
                    break;
                case 2:
                    System.out.println(2);
                    break;
                case 3:
                    System.out.println(3);
                    break;
                case 0:
                    contin = false;
                    break;
                default:
                    System.out.println("Неверная команда!!!");
                    break;
            }
        }
    }
    public static void expertMenu(){
        boolean contin = true;
        while (contin){
            int choice = showMenu(new String[]{"пункт1","пункт2","Управление пользователями", "пункт4"});
            switch (choice){
                case 1:
                    System.out.println(1);
                    break;
                case 2:
                    System.out.println(2);
                    break;
                case 3:
                    System.out.println(3);
                    break;
                case 0:
                    contin = false;
                    break;
                default:
                    System.out.println("Неверная команда!!!");
                    break;
            }
        }
    }
}

