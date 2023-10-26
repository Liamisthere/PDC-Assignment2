/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author liana
 */
//This class ensures if tables are created and are existed in the database
public class DBStorages {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    //Constructor of DBStorages to ensure that there is a connection from the database
    public DBStorages() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Create an array list full of products for Inventory table
    private ArrayList<Product> Productlist() {
        ArrayList<Product> pd = new ArrayList<Product>();

        Product p1 = new Product("Blue ball point pen", "Warehouse Stationary", 2.0, 5.0, "Stationary");
        Product p2 = new Product("PSVR2", "Sony Entertainment", 1000.0, 5.0, "Gaming");
        Product p3 = new Product("Razer RGB Keyboard", "Razer.com", 50.0, 2.2, "Gaming");
        Product p4 = new Product("Fluoro Notebook", "Warehouse Stationary", 1.0, 2.324323, "Stationary");
        Product p5 = new Product("Magic 8 ball", "Novelty.com", 12.0, 5.0, "Novelty");
        Product p6 = new Product("Red ball point pen", "Warehouse Stationary", 3.0, 5.0, "Stationary");
        Product p7 = new Product("PS5", "Sony Entertainment", 1000.0, 5.0, "Gaming");
        Product p8 = new Product("Red Dead Redemption 2", "Rockstar Games", 50.0, 5.0, "Gaming");
        Product p9 = new Product("T800 endoskeleton", "Skynet", 100000.0, 5.0, "Other");

        pd.add(p1);
        pd.add(p2);
        pd.add(p3);
        pd.add(p4);
        pd.add(p5);
        pd.add(p6);
        pd.add(p7);
        pd.add(p8);
        pd.add(p9);

        return pd;
    }

    //Table for Accounts 
    public void Accounttable() {

        //Check if Account table doesn't exist, if it doesn't exist, follow commands 
        if (!this.checkExistedTable("ACCOUNT")) {
            //Try following statements, create account table and execute batch
            try {
                this.statement.addBatch("CREATE TABLE ACCOUNT (Name VARCHAR(255), Surname VARCHAR(255), Age INT, Email VARCHAR(255), Password VARCHAR(255), Shopping_list VARCHAR(255))");
                this.statement.executeBatch();

                System.out.println("ACCOUNT table has been created");
            } //Catch any SQLException errors
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    //Table for Products 
    public void Inventorytable() {

        //Check if Inventory table doesn't exist, if it doesn't exist, follow commands 
        if (!this.checkExistedTable("INVENTORY")) {
            //Try following statements, create inventory table and execute batch
            try {
                this.statement.addBatch("CREATE TABLE INVENTORY (Product_ID INT,Product_name VARCHAR(255), Company VARCHAR(255), Price FLOAT(24),  Rating DOUBLE, Categories VARCHAR(255))");

                ArrayList<Product> list = Productlist();

                //Loop through array list containing products and add them to inventory table
                int count = 0;
                for (Product p : list) {
                    String name = p.getName();
                    String company = p.getCompany();
                    double price = p.getPrice();
                    double rating = p.getRating();
                    String category = p.getCategory();
                    count++;
                    this.statement.addBatch("INSERT INTO INVENTORY VALUES (" + count + ",'" + name + " ', '" + company + "', " + price + ", " + rating + ", '" + category + " ')");
                }

                this.statement.executeBatch();

                System.out.println("INVENTORY table has been created");
            } //Catch any SQLException errors
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    //Check if given table name exists in data base
    public boolean checkExistedTable(String name) {

        //Try following command
        try {
            boolean exists = false;
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);

            while (rs.next()) {

                String table_name = rs.getString("TABLE_NAME");
                System.out.println(table_name);

                //If table name equals to given table name, set exist to true 
                if (table_name.equalsIgnoreCase(name)) {

                    exists = true;
                }
            }

            rs.close();

            return exists;

        } //Catch any SQLException
        catch (SQLException ex) {
            System.out.println(ex.getMessage());

            return false;
        }
    }

    //Add given Account to Accounts table
    public void addAccountDB(Accounts a) {

        //Try following statement and execute batch
        try {

            this.statement.addBatch("INSERT INTO ACCOUNT VALUES ('" + a.getName() + " ', '" + a.getSurname() + "', " + a.getAge() + ", '" + a.getEmail() + "', '" + a.getPassword() + "', 'NULL')");
            this.statement.executeBatch();
        } //Catch any SQLException
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //update given Account in Accounts table
    public void updateAccount(Accounts a) {
        //Try following statement and execute batch
        try {

            //If user's shooping list is empty, Update account and set user's account's Shopping_list column to null
            if (a.getShopping_list().isEmpty()) {
                this.statement.addBatch("UPDATE ACCOUNT SET Shopping_list = NULL WHERE Email = '" + a.getEmail() + "'");
                this.statement.executeBatch();
            } //Otherwise, update account with the shopping list
            else {
                String list = "";
                for (String t : a.getShopping_list()) {
                    list += t + ", ";
                }
                this.statement.addBatch("UPDATE ACCOUNT SET Shopping_list = '" + list + "' WHERE Email = '" + a.getEmail() + "'");
                this.statement.executeBatch();
            }
        } //Catch any SQLException
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //update given Product in Inventory table    
    public void updateProduct(Product p) {
        //Try following statement and execute batch
        try {

            this.statement.addBatch("UPDATE INVENTORY SET Rating = " + p.getRating() + " WHERE PRODUCT_ID = " + p.getProductID() + "");
            this.statement.executeBatch();
        } //Catch any SQLException
        catch (SQLException ex) {
            System.out.println(ex.getMessage() + "HERE");
        }

    }

    //Collect all acounts in Account table
    public ArrayList<Accounts> collectAccounts() {

        //Try following statement and execute batch
        try {
            ResultSet rs = this.statement.executeQuery("SELECT * FROM ACCOUNT");
            ArrayList<Accounts> collect = new ArrayList<Accounts>();

            // Go through rows and columns of Account table 
            while (rs.next()) {
                String name = rs.getString("NAME");
                String surname = rs.getString("SURNAME");
                int age = rs.getInt("AGE");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                String shopping_list = rs.getString("SHOPPING_LIST");

                ArrayList<String> shop_array = new ArrayList<String>();

                //If Shopping list isn't null, follow commands
                if (shopping_list != null) {
                    String[] split = shopping_list.split(", ");

                    //Loop through split list 
                    for (String item : split) {
                        //if item is not NULL then add it to shopping list
                        if (!item.equalsIgnoreCase("NULL")) {
                            shop_array.add(item);
                        }

                    }
                }

                //Set fields into account constructor
                Accounts ac = new Accounts(name, surname, email, age, password);

                //Set shopping list of account
                ac.setShopping_list(shop_array);

                //add Account class to array list
                collect.add(ac);
            }

            this.statement.executeBatch();
            rs.close();

            return collect;
        } //Catch any SQLException
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    //Collect all products in Inventory table
    public ArrayList<Product> collectProducts() {

        //Try following statement and execute batch
        try {
            ResultSet rs = this.statement.executeQuery("SELECT * FROM INVENTORY");
            ArrayList<Product> collect = new ArrayList<Product>();

            // Go through rows and columns of Inventory table 
            while (rs.next()) {
                int product_id = rs.getInt("Product_ID");
                String name = rs.getString("PRODUCT_NAME");
                String company = rs.getString("COMPANY");
                double price = rs.getDouble("PRICE");
                double rating = rs.getDouble("RATING");
                String category = rs.getString("CATEGORIES");

                //add all fields to product constructor
                Product pd = new Product(name, company, price, rating, category);
                pd.setProductID(product_id);

                //Add product to arraylist
                collect.add(pd);
            }

            this.statement.executeBatch();
            rs.close();

            return collect;
        } //Catch any SQLException
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    //Clost connection from the database
    public void closeConnection() {
        this.dbManager.closeConnections();
    }

}
