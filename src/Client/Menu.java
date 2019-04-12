package Client;

import java.util.Scanner;

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
        while (true){
            int choice = showMenu(new String[]{"Вход как Админ","Вход как Пользователь","Вход как Эксперт"});

        }
    }
    public static void adminMenu(){
    }
}
