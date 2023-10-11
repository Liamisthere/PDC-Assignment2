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



    public void collect()
    {
    
        
    
    }
        


}