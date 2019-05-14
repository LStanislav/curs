package Server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import static Server.Server.*;
import static Server.ServerFunctions.*;
import static Server.ServerFunctions.showExperts;

public class ServerThread extends Thread {

    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        try {
            incNumberUsers();
            showNumberUsers();
            while (true) {
                String message = in.readLine();
                String msg[];
                msg = message.split(";");
                switch (msg[0]) {
                    case "authorization":
                        out.write(authorization(msg[1], msg[2], msg[3]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "addUser":
                        out.write(addUser(msg[1], msg[2]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showUsers":
                        List<String> list = showUsers();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "deleteUser":
                        out.write(deleteUser(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "addExpert":
                        out.write(addExpert(msg[1], msg[2], msg[3]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showExperts":
                        list = showExperts();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "findExpert":
                        out.write(findExpert(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "deleteExpert":
                        out.write(deleteExpert(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "editExpert":
                        out.write(editExpert(msg[1], msg[2]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "addGoal":
                        out.write(addGoal(msg[1], msg[2]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "deleteGoal":
                        out.write(deleteGoal(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showGoals":
                        list = showGoals();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "estimateGoals":
                        list = showGoals();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        out.write(editExpertMark(msg[1], in.readLine()) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showResults":
                        out.write(calculateNumberExperts() + System.lineSeparator());
                        out.flush();
                        list = showGoals();
                        out.write((list.size() - 1) + System.lineSeparator());
                        out.flush();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        list = showExperts();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "makeDecision":
                        out.write(solveTask(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showDecisions":
                        list = showDecisions();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "exit":
                        decNumberUsers();
                        showNumberUsers();
                        clientSocket.close();
                        return;
                }
            }
        } catch (SocketException e) {
            System.out.println("Ошибка на клиенте!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
