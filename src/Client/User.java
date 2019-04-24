package Client;

import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static Client.Client.in;
import static Client.Client.out;
import static Client.ClientFunctions.decoding;
import static java.lang.Math.min;

public class User extends BaseUser {
    public User(String login, String password) {
        super(login, password);
    }
    public User() {
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
            Comparator<Pair<Double,String>> comparator = new Comparator<Pair<Double, String>>() {
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
}
