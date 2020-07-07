package spbstuWorks.work4;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        Utils.printInfo();

        try (Connection connection = JDBCUtils.INSTANCE.getConnection()){
            JDBCUtils.INSTANCE.clearTable();
            //Other user
            Thread userThread = new Thread(new User("userImitation.json", connection));
            userThread.setDaemon(true);
            userThread.start();
            //We
            startMainUser(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void startMainUser(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String line;
        ArrayList<String> commandInfo;

        while (true) {
            line = scanner.nextLine();
            commandInfo = Utils.parseCommand(line);

            if (!commandInfo.isEmpty()) {
                if (!commandInfo.get(0).equals("exit")) {
                    Utils.executeCommand(connection, commandInfo, true);
                }
                else {
                    break;
                }
            }
            else {
                System.out.println("Wrong command");
            }
        }
    }
}