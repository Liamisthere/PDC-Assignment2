/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;


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
    
    // Gets user into program if they have existing account in the program
     public boolean Login(String email, String password) {

        boolean validate = o.inspect(email, password);
  
        
        return validate;
    }
}
