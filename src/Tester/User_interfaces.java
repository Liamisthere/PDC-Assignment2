/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import static Tester.Onlineshopping.*;
import static Tester.Receipt.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Liam Naidoo
 */

//User_interfaces class is the user page of the program, it is responsible of user inputs
public class User_interfaces {
    Accounts a;
    Accountstore o;
    Scanner scanner = new Scanner(System.in);
    
    
   //Constructor consists of Accountstore
   public User_interfaces(Accountstore o)
   {
          this.o =  o;
   }
   
 // User_Page 
public void user_Page(Accounts a) {
    this.a = a; 
        boolean quit = false;
        
        
        // While loop is used to continue with the following functions
        while (quit != true) {
            
            //Get number of items
            int cart_num = o.number_cart(a);
            
            //Print out options and number of items in the cart
            System.out.println("You have "+cart_num+" items in your cart");
            System.out.println("A)Display inventory\nB)Add product to shopping list\nC)See what's in your cart\nD)Rate product\nE)Search product\nF}Remove item from cart\nG)Pay now\nH)Log off");
           
            
            String options = scanner.next();
            Productstore Products = new Productstore();
            Product_response pr = new Product_response(Products, a, o);
             
            //If response equal A (ignore case) then print inventory
            if (options.equalsIgnoreCase("A")) {
                System.out.println(Products);  
            } 
            
            //If response equal B (ignore case) then run print inventory and selectItem() method
            else if (options.equalsIgnoreCase("B")) {                
                System.out.println(Products);
                pr.selectItem();
            }

            //If response equal C (ignore case) then print User's shopping list
            else if (options.equalsIgnoreCase("C")) {
                System.out.println(a.getShopping_list());
            }                   
             
            
            //If response equal D (ignore case) then run rateProduct() method
            else if (options.equalsIgnoreCase("D")) {
                pr.rateProduct();
            }
             
             
            //If response equal E (ignore case) then run searchItem()  method
            else if (options.equalsIgnoreCase("E")) {
              pr.searchItem();

            } 
              
            //If response equal F (ignore case) then run the following condition
            else if (options.equalsIgnoreCase("F")) {
                                
                //If shopping list size doesn't equal to 0 then run removeItem()
                 if(a.getShopping_list().size() != 0)
                {
                    pr.removeItem(a);
                }
                 
                //Otherwise prompt user with a message that list is empty
                else
                {
                    System.out.println("\nSorry, cart is empty, add items first");
                }
             }  
            
            //If response equal G (ignore case) then run the following condition
            else if (options.equalsIgnoreCase("G")) {
                
                //If shopping list size doesn't equal to 0 then run payment() method
                if(a.getShopping_list().size() != 0)
                {
                    payproduct pay = new  payproduct(a, o, Products);
                    pay.payment();
                }
                
                //Otherwise prompt user with a message that list is empty
                else
                {
                    System.out.println("\nSorry, cart is empty, add items first");
                }
              
                
            }
            
            //If response equal H (ignore case) then end while loop and return to menu
            else if (options.equalsIgnoreCase("H")) {
                quit = true;
            } 
            
            //Otherwise prompt user that invalid input and to try again
            else {
                System.out.println("Invalid input, please enter the following input");
            }
        }
    }
        
}

