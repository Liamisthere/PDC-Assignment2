/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Liam Naidoo
 */
// Product_response class is responsible for the actions the user interacts with the product
public class Product_response {

    Productstore Products;
    Accounts a;
    Accountstore o;

    //Constructor consists of Productstore, Accounts and Accountstore and assigns them
    Product_response(Productstore Products, Accounts a, Accountstore o) {
        this.Products = Products;
        this.a = a;
        this.o = o;
    }

    // Select an item from inventory
    public void selectItem() {
        Scanner scanner = new Scanner(System.in);
        boolean selected = false;

        // Uses a While loop to ensure the condition is met
        while (selected != true) {

            try {
                int list_size = Products.size();

                //Asks for item number and stores it in index integer
                System.out.println("Please enter item number that you want to add:");
                int index = scanner.nextInt();

                // If index is in range than select product in inventory, hash product and update account
                if (index > 0 && index <= list_size) {
                    Product item = Products.select(Products.getInventory(), index);
                    Products.hashProduct(item, a);
                    o.update_accounts(a);

                    //end while loop as it's done
                    selected = true;

                } //Otherwise promt user that item number is out of range
                else {
                    System.out.println("Number is out of range");
                }

            } //Catches any InputMismatchException and sends a message
            catch (InputMismatchException InputMismatchException) {

                System.out.println("Invalid input. Not an integer, try again");
                scanner.next();//flush the scanner.
            }
        }
    }

    //Search item from 
    public void searchItem() {
        Scanner scanner = new Scanner(System.in);

        //Create an arraylist called search_list to store any items that have been found
        ArrayList<Product> search_list = new ArrayList<Product>();

        //Asks for name of product and stores it in search string
        System.out.println("Enter the product you're looking for.");
        String search = scanner.next();

        //Assign search_list to the findProduct method which has mathes of searched items
        search_list = Products.findProduct(search, Products.getInventory());

        // If search_list is empty then prompt user that item isn't founded
        if (search_list == null) {
            System.out.println("Product not found");
        } // Otherwise carry on with method
        else {

            //Show search_list
            String show = Products.display(search_list);
            System.out.println(show);

            //Set the inventory to search_list
            Products.setInventory(search_list);

            //Open up the select item method with the inventory list as search_list 
            selectItem();
        }
    }

    //Rate the product
    public static double rate() {
        Scanner scanner = new Scanner(System.in);
        boolean satasfied = false;
        double rate = 0;

        // While loop ensures the user's response is correct or satasfied
        while (satasfied != true) {
            try {

                //Asks for rating of product and stores it in response double
                System.out.println("Please enter your rating of the product:");
                double response = scanner.nextDouble();

                // If response greater or equal to 0 and less then or equal to 5, assign response as rate
                if (response >= 0.0 && response <= 5.0) {
                    rate = response;

                    //End while loop as condition is satasified
                    satasfied = true;
                } //Otherwise prompt user with meessage that rating is out of range 
                else {
                    System.out.println("Invalid input. number out of range, rating must be between 0 and 5");
                }

            } //Catches any InputMismatchException and sends a message
            catch (InputMismatchException InputMismatchException) {

                System.out.println("Invalid input. Not an integer or a double, try again");
                scanner.next();//flush the scanner.
            }
        }
        //return the rate of the product
        return rate;
    }

    // Select product for rating 
    public void rateProduct() {
        Scanner scanner = new Scanner(System.in);
        boolean selected = false;

        // While loop ensures the user's response is correct or satasfied
        while (selected != true) {

            try {
                //Assigns size of products as list_size 
                int list_size = Products.size();

                System.out.println(Products.display(Products.getInventory()));

                //Asks for item number and stores it in index integer
                System.out.println("Please enter item number that you want to rate:");
                int index = scanner.nextInt();

                // If response greater than 0 and less then or equal to , assign response as rate 
                if (index > 0 && index <= list_size) {

                    //Assign selected product as p and prompt name of selected product
                    Product p = Products.getInventory().get(index - 1);
                    System.out.println("You have selected:" + p.getName());

                    //Assign rate method as rated 
                    double rated = rate();

                    //Set the rating of seleced product and update product
                    p.setRating(rated);
                    Products.update(p);

                    //End while loop as condition is met
                    selected = true;

                    //Otherwise prompt user that number is out of range
                } else {
                    System.out.println("Number is out of range");
                }

            } //Catches any InputMismatchException and sends a message
            catch (InputMismatchException InputMismatchException) {

                System.out.println("Invalid input. Not an integer, try again");
                scanner.next();//flush the scanner.
            }
        }

    }

    //Removes item in user's shopping list given index and account
    public void removeItem(Accounts a) {
        Scanner scanner = new Scanner(System.in);
        boolean removed = false;
        System.out.println(a.Shop_listed());

        // While loop ensures the user's response is correct or satasfied
        while (removed != true) {

            try {
                ArrayList<String> shop_list = a.getShopping_list();
                int list_size = shop_list.size();

                //Asks for item number and stores it in index integer
                System.out.println("Please enter item number from the list:");
                int index = scanner.nextInt();
                index--;

                //index is less than or equal to 0 and index greater
                if (index >= 0 && index <= list_size) {

                    String receive = shop_list.get(index);

                    //Splits into an array item from 'X' to get number of items and the name of item
                    String[] split_x = receive.split("X");

                    //Gets item from array
                    String get_item = split_x[0];

                    //Gets number from array and converts in to integer 
                    int get_number = Integer.parseInt(split_x[1]);

                    //If get number equals 1 than remove item from shopping list and update accounts
                    if (get_number == 1) {
                        shop_list.remove(index);
                        o.update_accounts(a);

                        //End while loop as condition is met
                        removed = true;
                    } //Otherwise take away 1 from get_number
                    else {
                        int subtract = get_number - 1;

                        //Remove old item with old number from shopping list
                        shop_list.remove(index);

                        //Insert item name and new number into shopping list and update accounts
                        String layout = get_item + "X" + subtract;
                        shop_list.add(layout);
                        o.update_accounts(a);

                        //End while loop as condition is met
                        removed = true;
                    }

                } //Otherwise prompt user with message
                else {
                    System.out.println("Number is out of bounds");
                }

            } //Catch InputMismatchException and prompt user with message
            catch (InputMismatchException InputMismatchException) {

                System.out.println("Invalid input. Not an integer, try again");
                scanner.next();//flush the scanner.
            } //Catch IndexOutOfBoundsException and prompt user with message
            catch (IndexOutOfBoundsException IndexOutOfBoundsException) {

                System.out.println("Number is out of range");

            }

        }
    }

}
