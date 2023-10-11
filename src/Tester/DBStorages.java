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
public class DBStorages {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public DBStorages() {        
        dbManager = new DBManager();
        conn = dbManager.getConnection();
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private ArrayList<Product> Productlist()
    {
           ArrayList<Product> pd = new  ArrayList<Product>();
           
           
     Product p1 =   new Product("Blue ball point pen", "Warehouse Stationary", 2.0, 5.0, "Stationary"); 
     Product p2 =   new Product("PSVR2", "Sony Entertainment", 1000.0, 5.0, "Gaming"); 
     Product p3 =   new Product("Razer RGB Keyboard", "Razer.com", 50.0, 2.2, "Gaming"); 
     Product p4 =   new Product("Fluoro Notebook", "Warehouse Stationary", 1.0, 2.324323, "Stationary"); 
     Product p5 =   new Product("Magic 8 ball", "Novelty.com", 12.0, 5.0, "Novelty"); 
     Product p6 =   new Product("Red ball point pen", "Warehouse Stationary", 3.0, 5.0, "Stationary"); 
     Product p7 =   new Product("PS5", "Sony Entertainment", 1000.0, 5.0, "Gaming"); 
     Product p8 =   new Product("Red Dead Redemption 2", "Rockstar Games", 50.0, 5.0, "Gaming"); 
     Product p9 =   new Product("T800 endoskeleton", "Skynet", 100000.0, 5.0, "Other"); 
    
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

    public void Accounttable() {

        if(!this.checkExistedTable("ACCOUNT"))
        {
            try{
                
                this.statement.addBatch("CREATE TABLE ACCOUNT (Name VARCHAR(255), Surname VARCHAR(255), Age INT, Email VARCHAR(255), Password VARCHAR(255), Shopping_list VARCHAR(255))");
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
               
            
            ArrayList<Product> list =  Productlist();
            
            for(Product p : list)
            {
                String name = p.getName();
                String company = p.getCompany();
                double price = p.getPrice();
                double rating = p.getRating();
                String category = p.getCategory();
                
                this.statement.addBatch("INSERT INTO INVENTORY VALUES ('" + name +" ', '"+ company +"', "+ price+", "+rating+", '"+category+" ')");
            }
                
                
                
                
                this.statement.executeBatch();
                
                
                System.out.println("INVENTORY table has been created");
            }
            
       catch(SQLException ex)
       {
           System.out.println(ex.getMessage());
       }
      }
        
    }

    public boolean checkExistedTable(String name) {
        try {
            boolean exists = false;
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);

            while (rs.next()) {

                String table_name = rs.getString("TABLE_NAME");
                System.out.println(table_name);

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
    
    
    public void addAccountDB(Accounts a)
    {
        try{
            
            this.statement.addBatch("INSERT INTO ACCOUNT VALUES ('"+a.getName()+" ', '"+a.getSurname()+"', "+a.getAge()+", '"+a.getEmail()+"', '"+a.getPassword()+"', '"+""+a.getShopping_list()+" ')");
            this.statement.executeBatch();    
        }
        
       catch(SQLException ex)
       {
           System.out.println(ex.getMessage());
       }
    
    }
    
    public void updateAccount(Accounts a)
    {
        try{
            
            this.statement.addBatch("UPDATE ACCOUNT SET Shopping_list = '"+a.getShopping_list()+"' WHERE Email = '"+a.getEmail()+"'");
            this.statement.executeBatch();    
        }
        
       catch(SQLException ex)
       {
           System.out.println(ex.getMessage());
       }
    
    }
    
   
    
    
    
    
     public ArrayList<Accounts> collectAccounts() {
      try {
        ResultSet rs = this.statement.executeQuery("SELECT * FROM ACCOUNT");
        ArrayList<Accounts> collect = new  ArrayList<Accounts>();
          
       while(rs.next())
       {
           String name = rs.getString("NAME");
           String surname = rs.getString("NAME");
           int age = rs.getInt("AGE");
           String email  = rs.getString("EMAIL");
           String password  = rs.getString("PASSWORD");
           String shopping_list =  rs.getString("SHOPPING_LIST");
           
           String[] split = shopping_list.split(",");
           ArrayList<String> shop_array = new ArrayList<String>() ;
           
           for(String item : split)
           {
               shop_array.add(item);
           }
           
           
           Accounts ac = new Accounts(name, surname, email, age,  password);
           ac.setShopping_list(shop_array);
 
           collect.add(ac);
      }
     
      this.statement.executeBatch();
        rs.close();
        
        
        return collect;
      }
        
       catch(SQLException ex)
       {
           System.out.println(ex.getMessage());
       }
      
      return null;
    }
    
    
    
    public void closeConnection() {
        this.dbManager.closeConnections();
    }

}
