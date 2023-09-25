/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import java.util.*;
import java.io.*;

/**
 *
 * @author Liam Naidoo
 */

//Productstore is responsible in handling product's functions 
//Productstore implements ProductIO
public class Productstore implements ProductIO{

    private ArrayList<Product> inventory;
    
    
    
    //Sets Inventory
    public void setInventory(ArrayList<Product> inventory) {
        this.inventory = inventory;
    }

    
    //Get Inventory
    public ArrayList<Product> getInventory() {
        return inventory;
    }

    //Constructor that create ArrayList<>() in inventory and uses inserts_products to insert Products to list
    Productstore() {
        this.inventory = new ArrayList<>();
        insert_Products(this.inventory);
    }

    //Search for product which uses string and an ArrayList<Product> 
    public ArrayList<Product> findProduct(String value, ArrayList<Product> list) {
        
        //Create new ArrayList list and assign it to search 
        ArrayList<Product> search = new ArrayList<>();
        
        //Lowercase value
        value = value.toLowerCase();
        
        //For loop to go through ArrayList
        for(Product a : list)
        {     
            
           //If name or category or company equals to value than add it to search
           if (a.getName().toLowerCase().contains(value)||a.getCategory().toLowerCase().contains(value)||a.getCompany().toLowerCase().contains(value))
           {
            search.add(a);
           }
        }
        
        //If search is empty then return null
        if (search.isEmpty())
        {
            return null;
        }
        
        //return search 
        return search;
    }
    
    
    //Display method prints the array_list with number at the front as item number
    public String display(ArrayList<Product> search_list)
    { 
        String list = "";
        int count = 0;
        for (Product a : search_list) {
            count++;
            list += count + "." + a + "\n";       
        }
        return list;
    }
    
    //select method returns the product given index and ArrayList<Product>
    public Product select(ArrayList<Product> o, int index)
    {
        
        index = index-1;
        Product find = o.get(index);
        return find;
    }
    
    //hashProduct method hashes the product by putting in quantity with the use of Product object and  Accounts object
    public void hashProduct(Product o, Accounts a)
    {       
       int count = 0;
       
       String item_name = "";
       int get_number = 0;
       int further_number = 0;
       boolean exist = false;

       //for loop is used to go through the inventory
       for(Product s : inventory)
       {
           //if given name equals to for loop name than add 1 to count
           if(s.getName().equals(o.getName()))
           {
               count ++;
           }

           //if count equals to 1 then exist is set to true
           if(count == 1)
           {
               exist = true;
           }
           
       }
       
          //If shopping list is empty then add item with quantity 1 and add it to shopping list
          if (a.getShopping_list().isEmpty())
          {
            item_name = o.getName()+"X1";
            a.getShopping_list().add(item_name);
          } 
          
          //Otherwise runn the following commands 
          else{
          
          int index = 0;
          int counters = 0;
          int enlisted = 0;
          
          //For loop is used to go through shopping list
          for(String e : a.getShopping_list())
          {              
               //Split value into an array by X and convert String number to integer
               String[] seperate = e.split("X");
               String bare_name = seperate[0];
               int number = Integer.parseInt(seperate[1]);
               
               
               //If bare_name equals to for loop's name, then run the following functions
               if(bare_name.equals(o.getName()))
               {
                   get_number = number;
                   further_number = number +=1;
                   
                   //Add name with X and new number
                   item_name = o.getName()+"X"+further_number;
                   
                   //Add index of shopping list value to index
                   index += a.getShopping_list().indexOf(e);
                   
                   //Set exist to true and add 1 to counters and enlisted
                   exist = true;
                   counters += 1;
                   enlisted += 1;
               }
          }
          
               //If enlisted doesn't equals to 1, then add name with "X1" to shopping list and print out item_name
               if(enlisted != 1) 
               {
                   item_name = o.getName()+"X1";
                   System.out.println(item_name);
                   a.getShopping_list().add(item_name);
               }
               
               //If exist equals to true and counters equals to 1 then remove old value from shopping list and add new value  
               else if(exist == true && counters == 1)
               {
                    a.getShopping_list().remove(index);
                     a.getShopping_list().add(item_name);
               }
          
          
          }
    }
    
    
    
    //Overrides insert_Products from ProductIO
    @Override
    public void insert_Products(ArrayList<Product> o) {
        try {
            
            
            //Read text file ProductDatabase.txt
            FileReader s = new FileReader("./resources/ProductDatabase.txt");
                       
            BufferedReader reader = new BufferedReader(s);
            String line = null;
     
            //Uses while loop to go through each line in the file
            while((line = reader.readLine()) != null) {
             
                //Splits line into an array by ", "
                Object[] account_store = line.split(", ");

                String name = (String) account_store[0];
                String company = (String) account_store[1];

                String number_price = (String) account_store[2];
                double price = Double.parseDouble(number_price);

                String number_rate = (String) account_store[3];
                double rate = Double.parseDouble(number_rate);

                String category = (String) account_store[4];
                
                
                //Insert all values to Product constructor and add it to Arraylist
                Product p1 = new Product(name, company, price, rate, category);
                o.add(p1);   
            }

        } 
        //Catch FileNotFoundException and prompt user with message   
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } 
        //Catch IOException and prompt user with message   
        catch (IOException e) {
            System.out.println("Error reading from file ");
        }

    }

     //Overrides update from ProductIO
    @Override
    public void update(Product p)
    {        
        try{
            
            //Read text file ProductDatabase.txt
            FileReader s = new FileReader("./resources/ProductDatabase.txt");
            BufferedReader reader = new BufferedReader(s);
            LineNumberReader ln = new LineNumberReader(s);
            String line = null;
             
            int position = 0;
            String collect = "";
            
            //Uses while loop to go through each line in the file
            while((line = reader.readLine())!=null)
            {
                //Splits line into an array by ", "
                Object[] array_list =  line.split(", ");
                
                //If array_list[0] equals to given name then add 1 to position
                if(array_list[0].equals(p.getName()))
                {
                    position +=1;
                }
                
                //Otherwise add line with newline to collect
                else
                {
                    collect += line+"\n";
                }
            }
            
            //Print collect and p to ProductDatabase.txt
            PrintWriter pw = new PrintWriter(new FileOutputStream("./resources/ProductDatabase.txt"));
            pw.print(collect);
            pw.print(p);
            pw.close();
           
            
        }
        
        //Catch FileNotFoundException and prompt user with message   
        catch(FileNotFoundException e)
        {
             System.out.println("File not found");
        }
        //Catch IOException and prompt user with message        
        catch(IOException e)
        {
            System.out.println("Error reading from file ");
        }
    
    }
    
    // Overrides toString method to print out details about the product
    @Override
    public String toString() {
        String list = "";
        int count = 0;
        for (Product a : inventory) {
            count++;
            list += count + "." + a + "\n";
            
        }
        //returns list
        return list;
    }
    
    
    //size will return number of values in inventory
    public int size()
    {
       int count = 0;
       for (Product a : inventory) {
           count++;          
       }
       return count;
    }

    //hash returns HashMap of String and Product
    public HashMap hash(Productstore o){
        
        //HashMap is created and is named myMap
        HashMap myMap = new HashMap();
        
        
        //For loop to go through inventory
        for(Product a : o.inventory)
        {
            //Splits Product into an array by ", " and puts it into myMap 
            String sentence = a.toString();
            Object[] array = sentence.split(", ");
            myMap.put(array[0], a);
        }
        
        return myMap;
    }
   
    //hashlisted returns Map containing String as key, Product Object as value
    public Map<String, Product> hashlisted(Productstore o, Accounts a)
    {
        //Map is created and is assigned by checkout 
        Map<String, Product> checkout = new HashMap();
        
        //For loop to go through inventory
        for(Product t : o.inventory)
        {
            
        //Another for loop to go through inventory  
        for(String e : a.getShopping_list())
        {
            //splits
            String[] array = e.split("X");
            String product_name = array[0]; 
            int number = Integer.parseInt(array[1]);
            
            if(product_name.equals(t.getName()))
            {
                //Get price, multiply by quantity of product and set price and put it in checkout
                double price = t.getPrice();
                double multiply  = number * price;
                 t.setPrice(multiply);
                 checkout.put(e, t);
                
            
            }
            
        }
        }
        
        //return checkout
        return checkout;    
    }
    
    

    
    //Returns total Price of the shopping list using Accounts and Productstore
    public double totalPrice(Accounts a, Productstore o)
   {
       
        double total = 0;
       
        //Uses for loop to go through inventory
        for(Product t : o.inventory)
        {
            
            //Uses another for loop to go through shopping list
            for(String e : a.getShopping_list())
            {
                //Splits string into an array by 'X' and array is assigned by strip
                String[] strip = e.split("X");
                
                //assigns bare by strip[0]
                String bare = strip[0];
                
                //Convert String number to an integer 
                int quantity = Integer.parseInt(strip[1]);
                
                //If bare equals to for loop inventory's name, add calcutalion 
                if(bare.equals(t.getName()))
                {
                    double calulate = quantity * t.getPrice();
                    
                    total += calulate;
                }
                
            }
        
        }
        
        //returns the total
        return total;
   }
    
    
  
    
   
}
