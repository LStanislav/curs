package Server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerFunctions {

    public static String authorization(String type, String login, String password) {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/" + type + ".txt")));
            String log;
            String pass;
            System.out.println(login + " " + password);
            while ((log = buffer.readLine()) != null) {
                pass = buffer.readLine();
                System.out.println(log + " " + pass);
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
        String ans = "";
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/user.txt")));
            String log;
            String pass;
            while ((log = buffer.readLine()) != null) {
                pass = buffer.readLine();
                if (log.equals(login)) {
                    ans = "Есть пользователь с таким логином!";
                    break;
                }
            }
            if (ans.equals("")) {
                ans = "Пользователь успешно добавлен!";
                File file = new File("D://JavaProject/curs/enter/user.txt");
                System.out.println(file.exists() + " " + file.getAbsolutePath());

                FileWriter fileWriter = new FileWriter("D://JavaProject/curs/enter/user.txt", true);
                System.out.println(login + " " + password);
                fileWriter.write(login + "\n");
                fileWriter.write(password + "\n");
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static List<String> showUsers() {
        File file = new File("D://JavaProject/curs/enter/user.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());
        ArrayList<String> res = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/user.txt")));
            String log;
            String pass;
            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                res.add(log + ";" + pass + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.add(";;");
        return res;
    }

    public static String deleteUser(String login) {
        String ans = "";
        try {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/user.txt")));
            String log;
            String pass;
            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                if (log.equals(login)) {
                    ans = "Пользователь успешно удален!";
                }
            }

            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/user.txt")));
                fileWriter = new FileWriter("D://JavaProject/curs/enter/buffer.txt", true);

                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    if (!log.equals(login)) {
                        fileWriter.write(log + "\n");
                        fileWriter.write(pass + "\n");
                    }
                }
                fileWriter.close();
                fileWriter = new FileWriter("D://JavaProject/curs/enter/user.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/curs/enter/user.txt", true);
                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/curs/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет пользователя с таким логином!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static String addExpert(String login, String password, String competencyLevel) {
        String ans = "";
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/expert.txt")));
            String log;
            String pass;
            String competLevel;
            while ((log = buffer.readLine()) != null) {
                pass = buffer.readLine();
                competLevel = buffer.readLine();
                if (log.equals(login)) {
                    ans = "Есть эксперт с таким логином!";
                    break;
                }
            }
            if (ans.equals("")) {
                ans = "Эксперт успешно добавлен!";
                File file = new File("D://JavaProject/curs/enter/expert.txt");
                System.out.println(file.exists() + " " + file.getAbsolutePath());

                FileWriter fileWriter = new FileWriter("D://JavaProject/curs/enter/expert.txt", true);
                System.out.println(login + " " + password + " " + competencyLevel);
                fileWriter.write(login + "\n");
                fileWriter.write(password + "\n");
                fileWriter.write(competencyLevel + "\n");
                fileWriter.write("0 0 0" + "\n");
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static List<String> showExperts() {
        File file = new File("D://JavaProject/curs/enter/expert.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());

        ArrayList<String> res = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/expert.txt")));
            String log;
            String pass;
            String competencyLevel;
            String ratings;

            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                competencyLevel = bufferedReader.readLine();
                ratings = bufferedReader.readLine();
                res.add(log + ";" + pass + ";" + competencyLevel + ";" + ratings + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.add(";;;;");
        return res;
    }

    public static String findExpert(String login) {
        File file = new File("D://JavaProject/curs/enter/expert.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());
        StringBuilder res = new StringBuilder("");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/expert.txt")));
            String log;
            String pass;
            String competencyLevel;
            String ratings;

            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                competencyLevel = bufferedReader.readLine();
                ratings = bufferedReader.readLine();
                if (log.equals(login)) {
                    res.append(log + ";" + pass + ";" + competencyLevel + ";" + ratings + ";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    public static String deleteExpert(String login) {
        String ans = "";
        try {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/expert.txt")));
            String log;
            String pass;
            String competencyLevel;
            String ratings;
            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                competencyLevel = bufferedReader.readLine();
                ratings = bufferedReader.readLine();
                if (log.equals(login)) {
                    ans = "Эксперт успешно удален!";
                }
            }

            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/expert.txt")));
                fileWriter = new FileWriter("D://JavaProject/curs/enter/buffer.txt", true);

                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    competencyLevel = bufferedReader.readLine();
                    ratings = bufferedReader.readLine();
                    if (!log.equals(login)) {
                        fileWriter.write(log + "\n");
                        fileWriter.write(pass + "\n");
                        fileWriter.write(competencyLevel + "\n");
                        fileWriter.write(ratings + "\n");
                    }
                }
                fileWriter.close();
                fileWriter = new FileWriter("D://JavaProject/curs/enter/expert.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/curs/enter/expert.txt", true);
                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    competencyLevel = bufferedReader.readLine();
                    ratings = bufferedReader.readLine();

                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    fileWriter.write(competencyLevel + "\n");
                    fileWriter.write(ratings + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/curs/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет эксперта с таким логином!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static String editExpert(String login, String competencyLevel) {
        String ans = "";
        try {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/expert.txt")));
            String log;
            String pass;
            String competLevel;
            String ratings;
            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                competLevel = bufferedReader.readLine();
                ratings = bufferedReader.readLine();
                if (log.equals(login)) {
                    ans = "Информация об эксперте успешно отредактирована!";
                }
            }

            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/expert.txt")));
                fileWriter = new FileWriter("D://JavaProject/curs/enter/buffer.txt", true);

                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    competLevel = bufferedReader.readLine();
                    ratings = bufferedReader.readLine();

                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    if (!log.equals(login)) {
                        fileWriter.write(competLevel + "\n");
                    } else {
                        fileWriter.write(competencyLevel + "\n");
                    }
                    fileWriter.write(ratings + "\n");

                }
                fileWriter.close();

                fileWriter = new FileWriter("D://JavaProject/curs/enter/expert.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/curs/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/curs/enter/expert.txt", true);
                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    competLevel = bufferedReader.readLine();
                    ratings = bufferedReader.readLine();

                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    fileWriter.write(competLevel + "\n");
                    fileWriter.write(ratings + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/curs/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет эксперта с таким логином!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
