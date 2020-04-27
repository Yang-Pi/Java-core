package com.labs.l4;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;

public class JDBCUtils {
    public static Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:sqlite://home//yaroslav//UNIVERSITY//COMPUTER-SCIENCE//" +
                "Java//IdeaProjects//Java-labs//src//com//labs//l4//products.db";
        Connection connection = DriverManager.getConnection(dbUrl);

        if (connection.isValid(1)) {
            System.out.println("Connection is successful");
        }

        return connection;
    }

    public static void clearTable(Connection connection) throws SQLException {
        String clearQuery = "DELETE FROM Products";
        try (PreparedStatement statement = connection.prepareStatement(clearQuery)){
            statement.executeUpdate();
        }
    }

    public static int addProduct(Connection connection, final String name, final int price) throws SQLException {
        int id = getProductId(connection, name);

        if (id == -1) {
            String insertQuery = "INSERT INTO Products (barcode, name, price) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setInt(1, generateBarcode(connection));
                statement.setString(2, name);
                statement.setInt(3, price);
                statement.executeUpdate();

                id = getProductId(connection, name);
            }
        }
        else {
            System.out.println("This product is exist in data base");
        }

        return id;
    }

    public static boolean deleteProduct(Connection connection, final String name) throws SQLException{
        String deleteQuery = "DELETE FROM Products WHERE name == ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)){
            statement.setString(1, name);

            return statement.executeUpdate() != 0;
        }
    }

    public static ArrayList<String> getAllProducts(Connection connection) throws SQLException {
        ArrayList<String> res = new ArrayList<>();
        String getQuery = "SELECT * FROM Products";

        try (PreparedStatement statement = connection.prepareStatement(getQuery)){
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    String productInfo = resultSet.getString("name") + " " +
                            Integer.toString(resultSet.getInt("price")) + "$";
                    res.add(productInfo);
                }
            }
        }

        return res;
    }

    public static ArrayList<String> filterByPrice(Connection connection, int price1, int price2) throws SQLException {
        if (price1 > price2) {
            price1 += price2;
            price2 = price1 - price2;
            price1 = price1 - price2;
        }

        ArrayList<String> res = new ArrayList<>();
        String getQuery = "SELECT * FROM Products WHERE price BETWEEN ? AND ?";

        try (PreparedStatement statement = connection.prepareStatement(getQuery)){
            statement.setInt(1, price1);
            statement.setInt(2, price2);

            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    String name = resultSet.getString("name");
                    String sPrice = Integer.toString(resultSet.getInt("price"));

                    res.add(name + " " + sPrice + "$");
                }
            }
        }

        return res;
    }

    public static int getPrice(Connection connection, final String name) throws SQLException{
        String getQueary = "SELECT price FROM Products WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(getQueary)){
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getInt("price") : 0;
        }
    }

    public static boolean changePrice(Connection connection, final String name, final int newPrice) throws SQLException{
        String updateQuery = "UPDATE Products SET price = ? WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)){
            statement.setInt(1, newPrice);
            statement.setString(2, name);

            return statement.executeUpdate() != 0;
        }
    }

    private static int getProductId(Connection connection, final String name) throws SQLException {
        String findQuery = "SELECT * FROM Products WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(findQuery)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
                else {
                    return -1;
                }
            }
        }
    }

    private static int generateBarcode(Connection connection) throws SQLException {
        String getQuery = "SELECT id FROM Products WHERE id = (SELECT MAX(id) FROM Products)";
        try (PreparedStatement statement = connection.prepareStatement(getQuery)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Utils.generateBarcode(resultSet.getInt("id"));
                }
                else {
                    return Utils.generateBarcode(0);
                }
            }
        }
    }
}
