package com.labs.l4;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Utils {
    public static void executeCommand(Connection connection, ArrayList<String> commandInfo, boolean isPrinting) throws SQLException {
        switch (commandInfo.get(0)) {
            case "add": {
                if (commandInfo.size() == 3) {
                    String name = commandInfo.get(1);
                    try {
                        int price = Integer.parseInt(commandInfo.get(2));
                        int currentId = JDBCUtils.addProduct(connection, name, price);
                        if (isPrinting) {
                            System.out.println("Product has added");
                        }
                    } catch (Exception e) {
                        if (isPrinting) {
                            System.out.println("Wrong command");
                        }
                    }
                }
                else {
                    if (isPrinting) {
                        System.out.println("Not enough arguments");
                    }
                }
                break;
            }

            case "delete": {
                String name = commandInfo.get(1);
                boolean wasDeleted = JDBCUtils.deleteProduct(connection, name);
                if (wasDeleted) {
                    if (isPrinting) {
                        System.out.println("Product " + name + " has deleted");
                    }
                }
                else {
                    if (isPrinting) {
                        System.out.println("Can't delete");
                    }
                }
                break;
            }

            case "show_all": {
                ArrayList<String> products = JDBCUtils.getAllProducts(connection);
                if (!products.isEmpty()) {
                    if (isPrinting) {
                        for (String product : products) {
                            System.out.println(product);
                        }
                    }
                }
                else {
                    if (isPrinting) {
                        System.out.println("No products yet");
                    }
                }

                break;
            }

            case "price": {
                String name = commandInfo.get(1);
                int price = JDBCUtils.getPrice(connection, name);
                if (isPrinting) {
                    if (price != 0) {
                        System.out.println(name + " " + price + "$");
                    } else {
                        System.out.println("No such product");
                    }
                }
                break;
            }

            case "change_price" : {
                if (commandInfo.size() == 3) {
                    String name = commandInfo.get(1);
                    int price = Integer.parseInt(commandInfo.get(2));

                    boolean wasUpdated = JDBCUtils.changePrice(connection, name, price);
                    if (isPrinting) {
                        if (wasUpdated) {
                            System.out.println("Price of product " + name + " was updated");
                        }
                        else {
                            System.out.println("Can't update");
                        }
                    }
                }
                else {
                    if (isPrinting) {
                        System.out.println("Not enough arguments");
                    }
                }
                break;
            }

            case "filter_by_price" : {
                if (commandInfo.size() == 3) {
                    int price1 = Integer.parseInt(commandInfo.get(1));
                    int price2 = Integer.parseInt(commandInfo.get(2));
                    ArrayList<String> filteredProducts = JDBCUtils.filterByPrice(connection, price1, price2);

                    if (isPrinting) {
                        if (!filteredProducts.isEmpty()) {
                            for (String product : filteredProducts) {
                                System.out.println(product);
                            }
                        }
                        else {
                            System.out.println("No products by this filter");
                        }
                    }

                }
                else {
                    if (isPrinting) {
                        System.out.println("Not enough arguments");
                    }
                }
            }
        }
    }

    public static ArrayList<String> parseCommand(String line) {
        ArrayList<String> res = new ArrayList<String>();
        String command = line.substring(0, 1);

        if (command.equals("/")) {
            int indexLastParsedChar = line.indexOf(" ");
            command = line.substring(1, indexLastParsedChar != -1 ? indexLastParsedChar : line.length());

            switch (command) {
                case "add" :
                case "change_price" : {
                    res.add(command);
                    line = line.substring(line.indexOf(" ") + 1);
                    ArrayList<String> parsedTwoWords = parseTwoWords(line);
                    if (parsedTwoWords.size() == 2) {
                        res.add(parsedTwoWords.get(0));
                        String price = checkPrice(parsedTwoWords.get(1));
                        res.add(price);
                    }
                    break;
                }

                case "filter_by_price" : {
                    res.add(command);
                    line = line.substring(line.indexOf(" ") + 1);
                    ArrayList<String> parsedTwoWords = parseTwoWords(line);
                    if (parsedTwoWords.size() == 2) {
                        String price1 = checkPrice(parsedTwoWords.get(0));
                        res.add(price1);
                        String price2 = checkPrice(parsedTwoWords.get(1));
                        res.add(price2);
                    }
                    break;
                }

                case "delete" :
                case "price" : {
                    res.add(command);//get command
                    res.add(line.substring(++indexLastParsedChar)); //get name of product
                    break;
                }

                case "show_all" :
                case "exit" : {
                    res.add(command);
                    break;
                }

            }
        }
        return res;
    }

    private static ArrayList<String> parseTwoWords(String line) {
        ArrayList<String> res = new ArrayList<String>();
        int indexLastParsedChar = line.indexOf(" ");

        if (indexLastParsedChar != -1) {
            res.add(line.substring(0, indexLastParsedChar)); //get first word
            line = line.substring(++indexLastParsedChar);
            res.add(line.substring(0)); //get second word
        }

        return res;
    }

    private static String checkPrice(String sPrice) {
        if (!sPrice.equals("")) {
            if (sPrice.length() != 1) {
                try {
                    int iPrice = Integer.parseInt(sPrice.substring(0, sPrice.length() - 1));
                } catch (Exception e) {
                    return "";
                }
            }

            try {
                int lastChar = Integer.parseInt(sPrice.substring(sPrice.length() - 1));
            } catch (Exception e) {
                return sPrice.substring(0, sPrice.length() - 1);
            }
        }

        return sPrice;
    }

    public static int generateBarcode(final int id) {
        SecureRandom random = new SecureRandom();
        final int coefficient = 12345;

        String sBarCode = "469"; //country code
        /*Integer num = (1000 + random.nextInt(9000));
        sBarCode += num.toString(); //producer code*/
        int num = id != 0 ? (coefficient * id) % 100000 : (coefficient - 1);
        sBarCode += Integer.toString(num); //product code
        num = random.nextInt(10);
        sBarCode += Integer.toString(num);

        return Integer.parseInt(sBarCode);
    }

    public static void printInfo() {
        System.out.println("DATABASE OF OUR SHOP");
        System.out.println("Available commands:");
        System.out.println("    /add product_name price -- add new product to db");
        System.out.println("    /delete product_name -- delete product from db");
        System.out.println("    /show_all -- print all products");
        System.out.println("    /price product_name -- get price of the product");
        System.out.println("    /change_price product_name price -- change price of the product");
        System.out.println("    /filter_by_price price_1 price_2 -- get products between prices");
        System.out.println("    /exit -- stop editing session\n");
    }
}
