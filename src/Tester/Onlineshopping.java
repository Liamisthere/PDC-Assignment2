/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import static Tester.Accounts.*;
import java.util.*;

/**
 *
 * @author Liam Naidoo
 */
//Onlineshopping class is main 
class Onlineshopping {

  
  
           
    

    //Main method of the program      
   public static void main(String args[])
   {
       DBManager dbManager = new DBManager();
       System.out.println(dbManager.getConnection());
       
       DBStorages store = new DBStorages();
       store.Accounttable();
       store.Inventorytable();
       
       Accountstore Accounts = new Accountstore();
       
       Menu cf = new Menu(Accounts);
       cf.setVisible(true);
   }
        
    

}
