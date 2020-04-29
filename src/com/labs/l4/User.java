package com.labs.l4;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class User implements Runnable {
    private final Connection _connection;
    private ArrayList<String> _commands;

    User(final String fileName, Connection connection) {
        _connection = connection;
        _commands = JSONUtils.parseJson(fileName);
    }

    @Override
    public void run() {
        SecureRandom random = new SecureRandom();

        for (String sCommand : _commands) {
            ArrayList<String> commandInfo = Utils.parseCommand(sCommand);

            if (!commandInfo.isEmpty()) {
                if (!commandInfo.get(0).equals("exit")) {
                    try {
                        Utils.executeCommand(_connection, commandInfo, false);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    break;
                }
            }

            pause(2000 + random.nextInt(2000));
        }
    }

    private void pause(final int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


