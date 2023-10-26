/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/**
 * @author liana Receipts: Prints out the layout of the receipt in
 * Printed_receipt.txt,
 *
 */
// Receipt class is resposible in printing out the receipt
public class Receipt {

    //Uses Map function and Accounts object to print out receipt in Printed_receipt.txt
    public static void printReceipt(Map<String, Product> o, Accounts a) {

        // Date program from imported library ensures the current date and time
        Date date = new Date();
        try {

            // prints "<><>" 30 times
            String line = "";
            for (int i = 0; i < 30; i++) {
                line += "<><>";
            }

            double balance = 0;

            // Prints out data in Printed_receipt.txt
            PrintWriter pw = new PrintWriter(new FileOutputStream("./resources/Printed_receipt.txt"));

            pw.println("Shopping App");
            pw.println(line);

            pw.println("Purchased by:" + a.getName() + " " + a.getSurname());
            pw.println("Email Address: " + a.getEmail() + "\n");

            //Prints out all the keys and values in Maps
            for (Map.Entry<String, Product> entry : o.entrySet()) {

                pw.println(entry.getKey() + " - " + entry.getValue() + "\n");
                balance += entry.getValue().getPrice();
            }

            pw.println("Balance due: $" + balance);

            pw.println(line);
            pw.println("Date of transaction: " + date);
            pw.println("\nCreated by Liam Naidoo");
            pw.close();
        } //catches FileNotFoundException error and sents a message about it insted
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
