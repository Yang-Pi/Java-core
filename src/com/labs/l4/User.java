package com.labs.l4;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class User implements Runnable {
    private String _fileName;
    private Connection _connection;

    User(final String fileName, Connection connection) {
        _fileName = fileName;
        _connection = connection;
    }

    @Override
    public void run() {
        SecureRandom random = new SecureRandom();

        ArrayList<String> commands = JSONUtils.parseJson(_fileName);
        for (String sCommand : commands) {
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


