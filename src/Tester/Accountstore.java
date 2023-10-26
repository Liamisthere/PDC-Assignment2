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


public void deletion(Accounts a,String product, int quantity) 
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
     String item = separate[0];

     int change_value = Integer.parseInt(stringnumber);
     int wanted = change_value - quantity;
     
     
     if(wanted == 0)
     {         
            System.out.println(index + "first if");
            user_shop.remove(index);
            a.setShopping_list(user_shop);
            SQL.updateAccount(a);
         
     }
     
     else
     {
            System.out.println(index + "second else");
            String hash = item+"X"+wanted;
            
            user_shop.set(index, hash);
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
     
    user_shop.set(index, hash);
     
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


public boolean validname(String name)
{
    int alphabetCount = 0;
    int namesize = name.length();
    
     for (char c : name.toCharArray()) {
       if (Character.isLetter(c)) {
            alphabetCount++;
        }
    }

    return alphabetCount == namesize;
}



public boolean characterEmail(String Email)
{
    int countdots = 0;
    
    for(char i : Email.toCharArray())
    {
        if(i == '.' || i == '@' )
        {
            countdots++;
        }
    
    }
    
    return countdots == 3;

}

public boolean validEmail(String Email)
{
    boolean validemail = false;
    if(characterEmail(Email) && Email.length() > 3)
    {
        String[] Emailsplit = Email.split("@");

        String username = Emailsplit[0];
        
        String address = Emailsplit[1];

        String[] Emaildots = address.split("\\.");

        String company = Emaildots[0];
        String domain = Emaildots[1];
        String country = Emaildots[2];

        
        int usernamesize = username.length();
        int usernamecount = 0;
        for(char i : username.toCharArray())
        {
          if(Character.isLetter(i) || Character.isDigit(i))
          {
             usernamecount++;
          }
        }
        boolean valid_username = usernamesize == usernamecount;
        
        int companysize = company.length();
        int companycount = 0;
        for(char i : company.toCharArray())
        {
          if(Character.isLetter(i))
          {
              companycount++;
          }
        }
        boolean valid_company = companysize == companycount;
        
        
        int domainsize = domain.length();
        int domaincount = 0;
        for(char i : domain.toCharArray())
        {
           if(Character.isLetter(i))
          {
              domaincount++;
          }
        }
        
         boolean valid_domain = domainsize == domaincount;
         
         
        
        int countrysize = country.length();
        int countrycount = 0;
        for(char i : country.toCharArray())
        {
           if(Character.isLetter(i))
          {
              countrycount++;
          }
        }
       boolean valid_country = countrysize == countrycount;
       
    
       if(valid_username && valid_company && valid_domain && valid_country)
       {
           validemail = true;
       }
       
       if(Email.equalsIgnoreCase("Robin@Bankers.co.nz"))
       {
           validemail = false;
       }
       
    }
    
    return validemail;
}
        


}