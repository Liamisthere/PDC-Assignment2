/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import static Tester.Accounts.*;
import static Tester.User_interfaces.*;
import java.util.*;

/**
 *
 * @author Liam Naidoo
 */
//Onlineshopping class is main 
class Onlineshopping {

    private static Scanner scanner;

    //Menu at the beginning of the program
    public static void menu(Accountstore o) {
        Access ed = new Access(o);
        boolean terminate = false;

        System.out.println("Please select the following instructions");

        //While loop to ensure user inputs correct responses
        while (terminate != true) {
            //Print out options for user to respond
            //Asks user for response and stores it in response string
            System.out.println("A)Login with account\nB)Create new account \nC)quit");
            String response = scanner.next();

            //If response equals A (ignorecase) than send user to login method
            if (response.equalsIgnoreCase("A")) {
                ed.login();
                //End while loop as condition is met
                terminate = true;

            } //If response equals B (ignorecase) than send user to enroll method
            else if (response.equalsIgnoreCase("B")) {
                ed.enrollUser();
            } //If response equals C (ignorecase) than teminate program
            else if (response.equalsIgnoreCase("C")) {
                terminate = true;
                System.out.println("Turning off programme");
                //End while loop as condition is met and user want to end program
                
            } //Otherwise prompt user that input invalid and to try again
            else {
                System.out.println("Invalid input, please enter the following input");
            }

        }
    }

    //Main method of the program
    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        //Creates Accountstore as accounts
        Accountstore Accounts = new Accountstore();
        System.out.println("Hello World! Welcome to the Online Shopping!\n");

        //Send user to menu
        menu(Accounts);

    }

}
