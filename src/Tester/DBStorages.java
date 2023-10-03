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

        if(!this.checkExistedTable("ACCOUNT"))
        {
            try{
                
                this.statement.addBatch("CREATE TABLE ACCOUNT (Name VARCHAR(255), Surname VARCHAR(255), Age INT, Email VARCHAR(255), Password VARCHAR(255), Shopping_listEmail VARCHAR(255))");
                this.statement.executeBatch();

                System.out.println("ACCOUNT table has been created");
            }
            
       catch(SQLException ex)
       {
           System.out.println(ex.getMessage());
       }
      }
        
        
    }

    public void Inventorytable() {    
        if(!this.checkExistedTable("INVENTORY"))
        {
            try{
                
                this.statement.addBatch("CREATE TABLE INVENTORY (Product_name VARCHAR(255), Company VARCHAR(255), Price FLOAT(24),  Rating FLOAT(24), Categories VARCHAR(255))");
                this.statement.executeBatch();
                System.out.println("INVENTORY table has been created");
            }
            
       catch(SQLException ex)
       {
           System.out.println(ex.getMessage());
       }
      }
        
    }

    private boolean checkExistedTable(String name) {
        try {
            boolean exists = false;
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);

            while (rs.next()) {

                String table_name = rs.getString("TABLE_NAME");
                System.out.print(table_name);

                if (table_name.equalsIgnoreCase(name)) {

                    exists = true;
                }
            }

            rs.close();
            
        return exists;           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            return false;
        }
    }

    public void closeConnection() {
        this.dbManager.closeConnections();
    }

}
