package com.labs.l4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        Utils.printInfo();
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = JDBCUtils.getConnection()){
            JDBCUtils.clearTable(connection);

            String line;
            ArrayList<String> commandInfo;

            while (true) {
                line = scanner.nextLine();
                commandInfo = Utils.parseCommand(line);

                if (!commandInfo.isEmpty()) {
                    if (!commandInfo.get(0).equals("exit")) {
                        Utils.executeCommand(connection, commandInfo);
                    }
                    else {
                        break;
                    }
                }
                else {
                    System.out.println("Wrong command");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}