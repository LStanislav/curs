package Client;

import java.util.Scanner;

import static Client.ClientFunctions.*;

public class Menu {
    private Menu(){}
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static int showMenu(String punkts[]) {
        for (int i = 0; i < punkts.length; i++) {
            System.out.println((i + 1) + ". " + punkts[i]);
        }
        System.out.println("0. Выход");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        int x;
        try {
            x = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            x = -1;
        }
        if ((x >= 0) && (x <= punkts.length)) {
            return x;
        } else return -1;
    }

    public static void firstMenu() {
        boolean contin = true;
        while (contin) {
            int choice = showMenu(new String[]{"Вход как Админ", "Вход как Пользователь", "Вход как Эксперт"});
            switch (choice) {
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

    public static void adminMenu() {
        boolean contin = true;
        while (contin) {
            int choice = showMenu(new String[]{"Управление пользователями", "Управление экспертами", "Управление целями"});
            switch (choice) {
                case 1:
                    System.out.println(1);
                    usersManagementMenu();
                    break;
                case 2:
                    System.out.println(2);
                    expertsManagementMenu();
                    break;
                case 3:
                    System.out.println(3);
                    goalsManagementMenu();
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

    public static void usersManagementMenu() {
        boolean contin = true;
        while (contin) {
            int choice = showMenu(new String[]{"Добавление пользователя", "Удаление пользователя", "Просмотр всех пользователей"});
            switch (choice) {
                case 1:
                    System.out.println(1);
                    addUser();
                    break;
                case 2:
                    System.out.println(2);
                    deleteUser();
                    break;
                case 3:
                    System.out.println(3);
                    showUsers();
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

    public static void expertsManagementMenu() {
        boolean contin = true;
        while (contin) {
            int choice = showMenu(new String[]{"Добавление экперта", "Удаление эксперта", "Просмотр оценок одного эксперта",
                    "Просмотр всех экспертов", "Изменение уровня компетентности эксперта", "Таблица приоритетов"});
            switch (choice) {
                case 1:
                    System.out.println(1);
                    addExpert();
                    break;
                case 2:
                    deleteExpert();
                    System.out.println(2);
                    break;
                case 3:
                    System.out.println(3);
                    findExpert();
                    break;
                case 4:
                    System.out.println(4);
                    showExpert();
                    break;
                case 5:
                    System.out.println(5);
                    editExpert();
                    break;
                case 6:
                    System.out.println(6);
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

    public static void goalsManagementMenu() {
        boolean contin = true;
        while (contin) {
            int choice = showMenu(new String[]{"Добавление цели", "Удаление цели", "Просмотр всех целей с описанием",
                    "Просмотр итоговых весов целей"});
            switch (choice) {
                case 1:
                    System.out.println(1);
                    addGoal();
                    break;
                case 2:
                    System.out.println(2);
                    deleteGoal();
                    break;
                case 3:
                    System.out.println(3);
                    showGoals();
                    break;
                case 4:
                    System.out.println(4);
                    showResults();
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

    public static void userMenu() {
        boolean contin = true;
        while (contin) {
            int choice = showMenu(new String[]{"Просмотр всех целей с описанием", "Просмотр итоговых весов целей"});
            switch (choice) {
                case 1:
                    System.out.println(1);
                    showGoals();
                    break;
                case 2:
                    System.out.println(2);
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

    public static void expertMenu(String login){
        boolean contin = true;
        while (contin) {
            int choice = showMenu(new String[]{"Просмотр всех целей с описанием", "Вынесение оценок по всем целям"});
            switch (choice) {
                case 1:
                    System.out.println(1);
                    showGoals();
                    break;
                case 2:
                    System.out.println(2);
                    estimateGoals(login);
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

