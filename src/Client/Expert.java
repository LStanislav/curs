package Client;

import java.io.IOException;

import static Client.Client.*;
import static Client.ClientFunctions.encrypt;

public class Expert extends BaseUser {
    private int marks[];
    private int competency;
    public Expert() {
        competency = 0;
    }

    public Expert(String login, String password) {
        super(login, password);
        competency = 0;
    }

    public int[] getMarks() {
        return marks;
    }

    public int getCompetency() {
        return competency;
    }

    public void setCompetency(int competency) {
        this.competency = competency;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public void setMarks(String str) {
        String[] marks = str.split(" ");
        this.marks = new int[marks.length];
        for (int i=0; i<marks.length; i++){
            this.marks[i] = Integer.parseInt(marks[i]);
        }
    }

    public static void estimateGoals(String login) {
        //connector.connect();
        StringBuilder msg = new StringBuilder("estimateGoals;");

        try {
            msg.append(encrypt(login) + ";");
            out.write(msg + System.lineSeparator());
            out.flush();
            msg = new StringBuilder();
            String buf = in.readLine();
            while (!buf.equals(";;")) {
                String part[] = buf.split(";", 5);
                System.out.println(part[0] + "  |  " + part[1]);
                while (true) {
                    System.out.println("Введите вашу оценку: ");
                    String mark = reader.nextLine();
                    try {
                        if ((Integer.parseInt(mark) < 0) || (Integer.parseInt(mark) > 10)) {
                            System.out.println("Неверая оценка!");
                        } else {
                            msg.append(mark + " ");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                buf = in.readLine();
            }
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
