/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import static Tester.Onlineshopping.menu;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Liam Naidoo
 */

// Access class ensures that the user is in the user program
public class Access {
    
    
    Scanner scanner = new Scanner(System.in);
    Accountstore o;
    
    //Constructor consists of Accountstore and sets it as value "o"
    public Access(Accountstore o)
    {
        this.o =  o;
    }
    
    // Enrolls user into the program.
    public void enrollUser() {
        try {
            
            //Asks for first name and stores it in name
            System.out.println("Please enter your name:");
            String name = scanner.next();

            //Asks for last name and stores it in surname
            System.out.println("Please enter your surname (your last name):");
            String surname = scanner.next();

            //Open up the getAge() method and store it in age
            int age = getAge();

            //Asks for email and stores it in email
            System.out.println("Please enter your email:");
            String email = scanner.next();

            //Asks for password and stores it in password
            System.out.println("Please enter your Password:");
            String password = scanner.next();

            
            //Fill in Accounts object with values and sends into addusers() method.
            Accounts A1 = new Accounts(name, surname, email, age, password);
            o.addUsers(A1, o);

            //Catches any InputMismatchException and sends a message
        } catch (InputMismatchException InputMismatchException) {

            System.out.println("Invalid input. Not an integer, try again");
            scanner.next();//flush the scanner.
        }

    }

    // Gets the age of user
    private int getAge() {
        int age = 0;
        boolean receive = false;
        
        //While the receive boolean isn't true, carry on with this method
        while (receive != true) {
            try {
                
                //Asks for age and stores it in agewhile loop condition is satasifed
                System.out.println("Please enter your age (numbers only):");
                age = scanner.nextInt();
                
                receive = true;
                
               //Catches any InputMismatchException and sends a message
            } catch (InputMismatchException InputMismatchException) {

                System.out.println("Invalid input. Not an integer, try again");
                scanner.next();//flush the scanner.

            }
        }
        // return the age
        return age;
    }  
    
    
    // Gets user into program if they have existing account in the program
     public void login() {
         
        //Asks for email and stores it in email string
        System.out.println("Please enter your email:");
        String email = scanner.next();

        //Asks for password and stores it in email password
        System.out.println("Please enter your Password:");
        String password = scanner.next();

        //Sends email and password to inspect method to see if account exists
        boolean validate = o.inspect(email, password);

        //If Account exists than Select user's account and take them into the program 
        if (validate == true) {
            Accounts current_user = o.select_accounts(email, password);
            User_interfaces ui = new User_interfaces(o);
            ui.user_Page(current_user);
           
        //Otherwise prompt user that account isn't correct and send it to menu method
        } else {
            System.out.println("Sorry, email or password is invalid.");
            menu(o);
        }    
    }
}
