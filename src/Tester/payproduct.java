/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import static Tester.Receipt.printReceipt;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Liam Naidoo
 */
//This class pays is responsible for paying the products and showing the price
public class payproduct {

    private Accounts a;
    private Accountstore o;
    private Productstore e;
    
    private DBStorages SQL = new DBStorages();
    
    //Constructor consists of 3 objects: Accounts, Accountstore, Productstore 
    public payproduct(Accounts a, Accountstore o, Productstore e) {
        this.a = a;
        this.o = o;
        this.e = e; 
    }

    // Payment method helps out in price 
    public void payment() {

        double price = e.totalPrice(a, e);
        
                //Map has String as key and product as value and is stored as list
                Map<String, Product> list = e.hashlisted(e, a);

                System.out.println("Payment is successful!");
                int length = a.getShopping_list().size();
                int count = 0;

                System.out.println(list);

                printReceipt(list, a);
                
                 ArrayList<String> user_shop = new ArrayList<String>();
                 a.setShopping_list(user_shop);
                 
                 SQL.updateAccount(a);

        }
}
