/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import static Tester.Receipt.printReceipt;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Liam Naidoo
 */
//This class pays is responsible for paying the products and showing the price
public class payproduct {

    Accounts a;
    Accountstore o;
    Productstore e;
    Scanner scanner = new Scanner(System.in);

    //Constructor consists of 3 objects: Accounts, Accountstore, Productstore 
    public payproduct(Accounts a, Accountstore o, Productstore e) {
        this.a = a;
        this.o = o;
        this.e = e;
    }

    // Payment method helps out in price 
    public void payment() {

        double price = e.totalPrice(a, e);
        System.out.println("That'll be $" + price + "\n");
        boolean terminate = false;

        System.out.println("Would you like to pay?\ny for yes or n  for no");

        // While loop ensures the user's response is either a in 'y' or 'n'
        while (terminate != true) {

            String response = scanner.next();

            // If user's response it 'y' (ignoring case) then carry on with the payment 
            if (response.equalsIgnoreCase("y")) {

                //Map has String as key and product as value and is stored as list
                Map<String, Product> list = e.hashlisted(e, a);

                System.out.println("Payment is successful!");
                int length = a.getShopping_list().size();
                int count = 0;

                System.out.println(list);

                printReceipt(list, a);

                //Remove items from user's Shopping list using while loop
                while (length != 0) {
                    a.getShopping_list().remove(count);
                    length--;
                }

                //update the current account and end while loop as it's done
                
                terminate = true;
            } // If user's response it 'n' (ignoring case) then take user to user page
            else if (response.equalsIgnoreCase("n")) {
                System.out.println("Payment has been cancelled!");
          
            } //Otherwise prompt user with message and carry on with asking for response
            else {
                System.out.println("Invalid response!\nEnter y for yes or n for no");

            }

        }
    }

}
