/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
 * @author Liam Naidoo
 */
//Accountstore is responsible in handling Account's functions 
//Accountstore implements AccountIO
public class Accountstore {

    private ArrayList<Accounts> storage;

    private Statement statement;
    
    private DBStorages SQL = new DBStorages();

    //Default Constructor that assign this.storage as a new ArrayList for Accounts
    Accountstore() {
        
        if(this.SQL.collectAccounts() != null){
            this.storage = this.SQL.collectAccounts();
        }
        
        else
        {
            this.storage = new ArrayList<Accounts>();
        }
        
    }

    //Inspect needs email and password and it ensures that account exists
    public boolean inspect(String email, String Password) {
        //assign coutn to 0
        int count = 0;

        //assign response to false
        boolean response = false;

        String get_name = "";

        //Uses for loop in storage to go through accounts
        for (Accounts a : storage) {
            //If for loop email equals given email and for loop password equals given password than count 1 up and get name
            if (email.equals(a.getEmail()) && Password.equals(a.getPassword())) {
                count++;
                get_name += a.getName();
            }
        }

        //If count doesn't equal to zero set response to true
        if (count != 0) {
            System.out.println("\nWelcome back " + get_name + "!");
            response = true;
        } //Otherwise set response to false
        else {
            response = false;
        }

        //Return boolean value response
        return response;
    }

    //Selects an account given name and password
    public Accounts select_accounts(String email, String Password) {
        //Uses for loop in storage to go through accounts
        for (Accounts a : storage) {
            //If for loop email equals given email and for loop password equals given password than return a
            if (email.equals(a.getEmail()) && Password.equals(a.getPassword())) {
                return a;
            }
        }
        //Returns null otherwise
        return null;
    }

    // Add users into into storage
    public void addUsers(Accounts o) {
        this.storage.add(o);

    }
    
    
    public boolean Existed(Accounts o, Accountstore b)
    {
        boolean exist = false;
        
        int count = 0;
        for (Accounts a : b.storage) {
                //if for loop email equals to given email then account already has the same email and add 1 
                if (o.getEmail().equals(a.getEmail())) {
              
                    //add one more to count
                    count += 1;
                }
            }
        
        //If count equals to 0 then add account to storage and use store_data to store accounts
         if (count == 0) {
                exist = false;
        }
    
         else if (count > 0) {
             //Prompts user with message saying email is in use and can't be used
                System.out.println("Email in already in use!");
                exist = true;
        }
        
        return exist;
    } 

//Get number of items in cart    
    public int number_cart(Accounts a) {
        int count = 0;

        //Create an ArrayList and assign it to account's shopping list
        ArrayList<String> list = a.getShopping_list();

        //Use for loop to see how many item there are by splitting value and adding number of items ot counts
        for (String s : list) {
            String[] splitted = s.split("X");
            int number = Integer.parseInt(splitted[1]);
            count += number;

        }

        //return count
        return count;
    }

    // Overrides toString method to print out list from storage
    @Override
    public String toString() {
        String list = "";
        for (Accounts a : storage) {
            list += a + "\n";
        }

        return list;
    }


public void deletion(Accounts a,String product) 
{
    ArrayList<String> user_shop = a.getShopping_list(); 
    boolean exist = false;
   int index = 0;
    
    for(String items :user_shop)
   {
       String[] separate = items.split("X");
       String clear = separate[0];
       System.out.println(clear+":"+product);
       if(clear.equals(product))
       {
            exist =true;
            index = user_shop.indexOf(items);
            System.out.println(index);
       }
   }
    
    if(exist ==true)
   {
     System.out.println("HERE");
     String wanted_item = user_shop.get(index);
     
     String[] separate = wanted_item.split("X");
     String stringnumber = separate[1];
     

     int change_value = Integer.parseInt(stringnumber);
     if(change_value == 1)
     {
         if(a.getShopping_list().isEmpty())
         {
            System.out.println("list size: "+a.getShopping_list().size());
            ArrayList<String> new_shoplist = new ArrayList<String>();
            a.setShopping_list(new_shoplist);
            a.setShopping_list(new_shoplist);
            SQL.updateAccount(a);
         
         }
         else{
            System.out.println(index);
            user_shop.remove(index);
            a.setShopping_list(user_shop);
            SQL.updateAccount(a);
         
         }
     }
     
     
     if(a.getShopping_list().isEmpty())
     {
         System.out.println("Cart is empty");
         ArrayList<String> new_shoplist = new ArrayList<String>();
         a.setShopping_list(new_shoplist);
         
         a.setShopping_list(new_shoplist);
         SQL.updateAccount(a);
         
     }
     
     
     else if(change_value == 0)
     {
         user_shop.remove(index);
     }
     
     
     else
     {
        
        //user_shop.remove(index);
        //System.out.println(user_shop.get(index));
         
         String convert = String.valueOf(change_value -1);
        String hash = product;
        hash +="X".trim();
        hash += convert;     
        user_shop.add(hash);
        a.setShopping_list(user_shop);
        SQL.updateAccount(a);
     
     }

   }
    
    
}    

    
    
public void insertion(Accounts a,String product, int quantity) 
{   
   ArrayList<String> user_shop = a.getShopping_list(); 
   boolean exist = false;
   int index = 0;
   for(String items :user_shop)
   {
       String[] separate = items.split("X");
       String clear = separate[0];
       System.out.println(clear+":"+product);
       if(clear.equals(product))
       {
            exist =true;
            index = user_shop.indexOf(items);
            System.out.println(index);
       }
   }
   
   if(exist ==true)
   {
     System.out.println("HERE");
     String wanted_item = user_shop.get(index);
     
     String[] separate = wanted_item.split("X");
     
     String stringnumber = separate[1];
     
     int change_value = Integer.parseInt(stringnumber);
     
     String convert = String.valueOf(change_value + quantity);
     String hash = product;
     hash +="X".trim();
     hash += convert;
     
     user_shop.remove(index);
     user_shop.add(hash);
     
    System.out.println(hash);
    a.setShopping_list(user_shop);
   }
   
   else{
    System.out.println("ALSO HERE");
    String convert = String.valueOf(quantity);
    String hash = product;
    hash +="X".trim();
    hash += convert;
   
    System.out.println(hash);
    user_shop.add(hash);
    a.setShopping_list(user_shop);
   
   
   }
   
     
      
      
      SQL.updateAccount(a);
      

}    
    
    


public boolean validpassword(String password) {
    int numberCount = 0;
    int alphabetCount = 0;

    for (char c : password.toCharArray()) {
        if (Character.isDigit(c)) {
            numberCount++;
        } else if (Character.isLetter(c)) {
            alphabetCount++;
        }
    }

    return numberCount >= 3 && alphabetCount >= 3;
}




        


}