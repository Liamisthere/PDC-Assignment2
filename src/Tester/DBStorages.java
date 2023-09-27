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

/**
 *
 * @author liana
 */
public class DBStorages {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public DBStorages() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public void Accounttable() {
        this.checkExistedTable("ACCOUNT", "Name VARCHAR(255), Surname VARCHAR(255), Age INT, Email VARCHAR(255), Password VARCHAR(255), Shopping_listEmail VARCHAR(255)");
    }

    public void Inventorytable() {
        this.checkExistedTable("INVENTORY", "Product_name VARCHAR(255), Company VARCHAR(255), Price FLOAT(24),  Rating FLOAT(24), Categories VARCHAR(255)");

    }

    private void checkExistedTable(String name, String values) {
        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);

            while (rs.next()) {

                String table_name = rs.getString("TABLE_NAME");
                System.out.print(table_name);

                if (table_name.equalsIgnoreCase(name)) {

                    System.out.println(name + " table exists");
                    break;
                } else {
                    this.statement.addBatch("CREATE TABLE " + name + " (" + values + ")");
                }

            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void closeConnection() {
        this.dbManager.closeConnections();
    }

}
