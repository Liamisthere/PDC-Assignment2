/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import static Tester.Accounts.*;
import java.util.*;
import java.lang.NullPointerException;

/**
 *
 * @author Liam Naidoo
 */
//Onlineshopping class is main 
class Onlineshopping {

    // Catches any nullpointers exceptions when program is running
    public static void onlinerunner() {
        
        //try starting up the database and other programs
        try {

            DBManager dbManager = new DBManager();
            System.out.println(dbManager.getConnection());

            DBStorages store = new DBStorages();
            store.Accounttable();
            store.Inventorytable();

            Accountstore Accounts = new Accountstore();

            Menu cf = new Menu(Accounts);
            cf.setVisible(true);

        } //sends a message if NullPointerException has been caught
        catch (NullPointerException ex) {
            System.out.println("ERROR: " + ex + " has been detected");
            System.out.println("Program is already running.\n");
            System.out.println("Solution 1: Try to shutdown the running Program in order to run\n");
            System.out.println("Solution 2: Stop the Java DB server in order to run");
        }

    }

    //Main method of the program      
    public static void main(String args[]) {
        onlinerunner();
    }

}
