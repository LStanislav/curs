package Client;

import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

import static Client.Client.*;
import static Client.Client.reader;
import static Client.ClientFunctions.decoding;
import static Client.ClientFunctions.encrypt;
import static java.lang.Math.exp;
import static java.lang.Math.min;

public class Admin extends BaseUser {
    public Admin() {
    }

    public Admin(String login, String password) {
        super(login, password);
    }

    public static void showResults() {
        StringBuilder msg = new StringBuilder("showResults;");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            int numberExperts = Integer.parseInt(in.readLine());
            int numberGoals = Integer.parseInt(in.readLine());
            System.out.println("Количество экспертов: " + numberExperts);
            System.out.println("Количество целей: " + numberGoals);
            String goals[] = new String[numberGoals + 1];
            for (int i = 0; i < numberGoals + 1; i++) {
                goals[i] = in.readLine();
            }

            int marks[][] = new int[numberExperts][numberGoals];
            int competencies[] = new int[numberExperts];
            double results[] = new double[numberGoals];
            String expertName[] = new String[numberExperts];
            int sumCompetencies = 0;
            int max = -1;
            String buf = in.readLine();
            int i = 0;
            while (!buf.equals(";;")) {
                String part[] = buf.split(";");
                competencies[i] = Integer.parseInt(part[2]);
                expertName[i] = part[0];
                max = Math.max(max, expertName.length);
                sumCompetencies += competencies[i];
                String ratings[] = part[3].split(" ");
                for (int j = 0; j < min(ratings.length, numberGoals); j++) {
                    marks[i][j] = Integer.parseInt(ratings[j]);
                }
                i++;
                buf = in.readLine();
            }

            for (i = 0; i < numberExperts; i++) {
                System.out.printf("%" + max + "s(%2s) - ", decoding(expertName[i]), competencies[i]);
                for (int j = 0; j < numberGoals; j++){
                    System.out.printf("%3d", marks[i][j]);
                    double multiplier = (double) competencies[i] / (double) sumCompetencies;
                    results[j] += multiplier * (double) marks[i][j];
                }
                System.out.println();
            }
            System.out.println();
            max = -1;
            ArrayList<Pair<Double,String>> res = new ArrayList<>();
            System.out.println("Итоговые веса целей:");
            for (int j = 0; j < numberGoals; j++) {
                max = Math.max(max, results.length);
                String part[] = goals[j].split(";");
                res.add(new Pair(results[j],part[0]));
                System.out.printf("%3.3f %s\n", results[j], part[0]);
            }
            Comparator <Pair<Double,String>> comparator = new Comparator<Pair<Double, String>>() {
                @Override
                public int compare(Pair<Double, String> o1, Pair<Double, String> o2) {
                    if (o1.getKey()>o2.getKey())
                        return -1;
                    else return 1;
                }
            };
            res.sort(comparator);
            System.out.println();

            System.out.println("Отсортированные итоговые веса целей:");
            for (int j = 0; j < numberGoals; j++) {
                String part[] = res.get(j).getValue().split(";");
                Double mark = res.get(j).getKey();
                System.out.printf("%3.3f %s\n", mark, part[0]);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addUser() {
        String log, pass1, pass2;
        System.out.println("Регистрация");
        System.out.println("Введите новый логин:");
        log = reader.nextLine();

        System.out.println("Введите новый пароль:");
        pass1 = reader.nextLine();

        System.out.println("Подтвердите пароль:");
        pass2 = reader.nextLine();
        if (!pass1.equals(pass2)) {
            System.out.println("Пароли не совпадают!");
            return;
        }
        //connector.connect();
        try {
            StringBuilder msg = new StringBuilder("addUser;");
            msg.append(encrypt(log) + ";");
            msg.append(encrypt(pass1) + ";");
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser() {
        //connector.connect();
        StringBuilder msg = new StringBuilder("deleteUser;");
        System.out.println("Введите логин, удаляемого пользователя:");
        String log = reader.nextLine();
        msg.append(encrypt(log) + ";");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine() + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showUsers() {
        //connector.connect();
        StringBuilder msg = new StringBuilder("showUsers;");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            String buf = in.readLine();
            while (!buf.equals(";;")) {
                String part[] = buf.split(";", 3);
                System.out.println(decoding(part[0]) + "  |  " + decoding(part[1]));
                buf = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addExpert() {
        String log, pass1, pass2, competencyLevel;

        System.out.println("Регистрация");
        System.out.println("Введите новый логин: ");
        log = reader.nextLine();

        System.out.println("Введите новый пароль: ");
        pass1 = reader.nextLine();

        System.out.println("Подтвердите пароль: ");
        pass2 = reader.nextLine();
        if (!pass1.equals(pass2)) {
            System.out.println("Пароли не совпадают!");
            return;
        }
        System.out.println("Введите уровень компетентности эксперта(0..10): ");
        competencyLevel = reader.nextLine();
        try {
            if ((Integer.parseInt(competencyLevel) < 0) || (Integer.parseInt(competencyLevel) > 10)) {
                System.out.println("Неверный уровень компетентности!");
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //connector.connect();
        try {
            StringBuilder msg = new StringBuilder("addExpert;");
            msg.append(encrypt(log) + ";");
            msg.append(encrypt(pass1) + ";");
            msg.append(competencyLevel + ";");
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showExpert() {
        StringBuilder msg = new StringBuilder("showExperts;");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            String buf = in.readLine();
            while (!buf.equals(";;")) {
                String part[] = buf.split(";", 20);
                System.out.print(decoding(part[0]) + "  |  " + part[2] + " | (");

                String ratings[] = part[3].split(" ", 20);
                for (int i = 0; i < ratings.length - 1; i++) {
                    System.out.print(ratings[i] + ",");
                }
                System.out.println(ratings[ratings.length - 1] + ")");
                buf = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findExpert() {
        StringBuilder msg = new StringBuilder("findExpert;");
        System.out.println("Введите номер(логин) экперта: ");
        String log = reader.nextLine();
        msg.append(encrypt(log) + ";");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            String buf = in.readLine();
            if (!buf.equals("")) {
                String part[] = buf.split(";", 20);
                System.out.print(decoding(part[0])  + "  |  " + part[2] + " | (");
                String ratings[] = part[3].split(" ", 20);

                for (int i = 0; i < ratings.length - 1; i++) {
                    System.out.print(ratings[i] + ",");
                }
                System.out.println(ratings[ratings.length - 1] + ")");
            } else {
                System.out.println("Нет пользователя с таким номером!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteExpert() {
        //connector.connect();
        StringBuilder msg = new StringBuilder("deleteExpert;");
        System.out.println("Введите логин, удаляемого экперта:");
        String log = reader.nextLine();
        msg.append(encrypt(log) + ";");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine() + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void editExpert() {

        StringBuilder msg = new StringBuilder("editExpert;");
        System.out.println("Введите логин, редактируемого экперта:");
        String log = reader.nextLine();

        String competencyLevel;

        System.out.println("Введите уровень компетентности эксперта(0..10): ");
        competencyLevel = reader.nextLine();
        try {
            if ((Integer.parseInt(competencyLevel) < 0) || (Integer.parseInt(competencyLevel) > 10)) {
                System.out.println("Неверный уровень компетентности!");
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        msg.append(encrypt(log) + ";");
        msg.append(competencyLevel + ";");
        //connector.connect();
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addGoal() {
        //connector.connect();
        StringBuilder msg = new StringBuilder("addGoal;");
        String title;
        String description;
        System.out.println("Введите заголовок цели: ");
        title = reader.nextLine();
        System.out.println("Введите описание цели: ");
        description = reader.nextLine();
        msg.append(title + ";");
        msg.append(description + ";");

        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGoal() {
        //connector.connect();
        StringBuilder msg = new StringBuilder("deleteGoal;");
        System.out.println("Введите заголовок, удаляемой цели: ");
        String title = reader.nextLine();
        msg.append(title + ";");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine() + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showGoals() {
        //connector.connect();
        StringBuilder msg = new StringBuilder("showGoals;");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            String buf = in.readLine();
            while (!buf.equals(";;")) {
                String part[] = buf.split(";", 5);
                System.out.println(part[0] + "  |  " + part[1]);
                buf = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
