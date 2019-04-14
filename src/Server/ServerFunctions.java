package Server;

import java.io.*;

public class ServerFunctions {
    public static String authorization(String type, String login, String password) {
        //File file = new File("D://JavaProject/curs/src/Server/" + "type");
        //FileReader reader = new FileReader("D://JavaProject/curs/src/Server/" + "type"+".txt");
//        File file = new File("D://JavaProject/enter/" + type + ".txt");
//        System.out.println(file.exists() + " " + file.isFile() + file.getAbsolutePath());
        BufferedReader buffer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("D://JavaProject/curs/enter/" + type + ".txt");
            buffer = new BufferedReader(new InputStreamReader(fileInputStream));
            String log;
            String pass;
            while ((log = buffer.readLine()) != null) {
                pass = buffer.readLine();
                if ((pass.equals(password)) && (log.equals(login))) {
                    return "Yes";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(new String(buf));
        return "No";
    }

    public static String addUser(String login, String password) {
        BufferedReader buffer = null;
        String ans = "";
        try {
            FileInputStream fileInputStream = new FileInputStream("D://JavaProject/curs/enter/user.txt");
            buffer = new BufferedReader(new InputStreamReader(fileInputStream));
            String log;
            String pass;
            while ((log = buffer.readLine()) != null) {
                pass = buffer.readLine();
                if (log.equals(login)) {
                    ans = "Есть пользователь с таким логином!";
                    break;
                }
            }
            if (ans.equals("")){
                ans = "Пользователь успешно добавлен!";
                File file = new File("D://JavaProject/curs/enter/user.txt");
                System.out.println(file.exists()+" " + file.getAbsolutePath());

                FileWriter fileWriter = new FileWriter("D://JavaProject/curs/enter/user.txt",true);
                System.out.println(login + " " + password);
                fileWriter.write(login+"\n");
                fileWriter.write(password+"\n");
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
