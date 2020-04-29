package com.labs.l4;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JDBCUtils {
    public static final JDBCUtils INSTANCE = new JDBCUtils();
    private Connection _connection;
    private final ReadWriteLock _lock;

    private JDBCUtils() {
        try {
            _connection = setConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        _lock = new ReentrantReadWriteLock();
        checkDBExistence();
    }

    private Connection setConnection() throws SQLException {
        String dbUrl = "jdbc:sqlite://home//yaroslav//UNIVERSITY//COMPUTER-SCIENCE//" +
                "Java//IdeaProjects//Java-labs//src//com//labs//l4//products.db";
        Connection connection = DriverManager.getConnection(dbUrl);

        if (connection.isValid(1)) {
            System.out.println("Connection is successful");
        }

        return connection;
    }

    public int addProduct(final String name, final int price) throws SQLException {
        int id = getProductId(name);

        if (id == -1) {
            String insertQuery = "INSERT INTO Products (barcode, name, price) VALUES (?, ?, ?)";

            _lock.writeLock().lock();
            try (PreparedStatement statement = _connection.prepareStatement(insertQuery)) {
                statement.setInt(1, generateBarcode());
                statement.setString(2, name);
                statement.setInt(3, price);
                statement.executeUpdate();

                id = getProductId(name);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                _lock.writeLock().unlock();
            }
        }
        else {
            System.out.println("This product is exist in data base");
        }

        return id;
    }

    public boolean deleteProduct(final String name) throws SQLException{
        String deleteQuery = "DELETE FROM Products WHERE name == ?";

        _lock.writeLock().lock();
        boolean res = false;
        try (PreparedStatement statement = _connection.prepareStatement(deleteQuery)){
            statement.setString(1, name);
            res = statement.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            _lock.writeLock().unlock();
        }

        return res;
    }

    public ArrayList<String> getAllProducts() throws SQLException {
        ArrayList<String> res = new ArrayList<>();
        String getQuery = "SELECT * FROM Products";

        _lock.readLock().lock();
        try (PreparedStatement statement = _connection.prepareStatement(getQuery)){
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    String productInfo = resultSet.getString("name") + " " +
                            Integer.toString(resultSet.getInt("price")) + "$";
                    res.add(productInfo);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            _lock.readLock().unlock();
        }

        return res;
    }

    public ArrayList<String> filterByPrice(int price1, int price2) throws SQLException {
        if (price1 > price2) {
            price1 += price2;
            price2 = price1 - price2;
            price1 = price1 - price2;
        }

        ArrayList<String> res = new ArrayList<>();
        String getQuery = "SELECT * FROM Products WHERE price BETWEEN ? AND ?";

        _lock.readLock().lock();
        try (PreparedStatement statement = _connection.prepareStatement(getQuery)){
            statement.setInt(1, price1);
            statement.setInt(2, price2);

            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    String name = resultSet.getString("name");
                    String sPrice = Integer.toString(resultSet.getInt("price"));

                    res.add(name + " " + sPrice + "$");
                }

                _lock.readLock().unlock();
                return res;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            _lock.readLock().unlock();
        }

        return res;
    }

    public int getPrice(final String name) throws SQLException{
        String getQueary = "SELECT price FROM Products WHERE name = ?";
        int res = 0;

        _lock.readLock().lock();
        try (PreparedStatement statement = _connection.prepareStatement(getQueary)){
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            res = resultSet.next() ? resultSet.getInt("price") : 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            _lock.readLock().unlock();
        }

        return res;
    }

    public boolean changePrice(final String name, final int newPrice) throws SQLException{
        String updateQuery = "UPDATE Products SET price = ? WHERE name = ?";
        boolean res = false;

        _lock.writeLock().lock();
        try (PreparedStatement statement = _connection.prepareStatement(updateQuery)){
            statement.setInt(1, newPrice);
            statement.setString(2, name);

            res = statement.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            _lock.writeLock().unlock();
        }

        return res;
    }

    public Connection getConnection() {
        return _connection;
    }

    private void checkDBExistence() {
        final String checkQuery = "CREATE TABLE IF NOT EXISTS 'Products' (" +
                "id INTEGER DEFAULT 1 NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "barcode INTEGER NOT NULL,\n" +
                "name VARCHAR NOT NULL,\n" +
                "price INT NOT NULL CHECK (price >= 0))";
        _lock.writeLock().lock();
        try (PreparedStatement statement = _connection.prepareStatement(checkQuery)){
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            _lock.writeLock().unlock();
        }
    }

    public void clearTable() throws SQLException {
        String clearQuery = "DELETE FROM Products";
        _lock.writeLock().lock();
        try (PreparedStatement statement = _connection.prepareStatement(clearQuery)){
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            _lock.writeLock().unlock();
        }
    }

    private int getProductId(final String name) throws SQLException {
        String findQuery = "SELECT * FROM Products WHERE name = ?";
        int res = -1;

        _lock.readLock().lock();
        try (PreparedStatement statement = _connection.prepareStatement(findQuery)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    res =  resultSet.getInt("id");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            _lock.readLock().unlock();
        }

        return res;
    }

    private int generateBarcode() throws SQLException {
        String getQuery = "SELECT id FROM Products WHERE id = (SELECT MAX(id) FROM Products)";
        int res = 0;

        _lock.readLock().lock();
        try (PreparedStatement statement = _connection.prepareStatement(getQuery)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    res = Utils.generateBarcode(resultSet.getInt("id"));
                }
                else {
                    res = Utils.generateBarcode(0);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            _lock.readLock().unlock();
        }

        return res;
    }
}
