/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *
 * @author Liam Naidoo
 */
//Accountstore is responsible in handling Account's functions 
//Accountstore implements AccountIO
public class Accountstore implements AccountIO {

    private ArrayList<Accounts> storage;

    //Default Constructor that assign this.storage as a new ArrayList for Accounts
    Accountstore() {
        this.storage = new ArrayList<Accounts>();

        //Inputs data in this.storage
        collect_Data(this.storage);
    }

    //Inspect needs email and password and it ensures that account exists
    public boolean inspect(String email, String Password) {
        //assign coutn to 0
        int count = 0;

        //assign response to false
        boolean response = false;

        String get_name = "";

        //Uses for loop in storage to go through accounts
        for (Accounts a : storage) {
            //If for loop email equals given email and for loop password equals given password than count 1 up and get name
            if (email.equals(a.getEmail()) && Password.equals(a.getPassword())) {
                count++;
                get_name += a.getName();
            }
        }

        //If count doesn't equal to zero set response to true
        if (count != 0) {
            System.out.println("\nWelcome back " + get_name + "!");
            response = true;
        } //Otherwise set response to false
        else {
            response = false;
        }

        //Return boolean value response
        return response;
    }

    //Selects an account given name and password
    public Accounts select_accounts(String email, String Password) {
        //Uses for loop in storage to go through accounts
        for (Accounts a : storage) {
            //If for loop email equals given email and for loop password equals given password than return a
            if (email.equals(a.getEmail()) && Password.equals(a.getPassword())) {
                return a;
            }
        }
        //Returns null otherwise
        return null;
    }

    // Add users into into storage
    public void addUsers(Accounts o, Accountstore b) {
        int count = 0;

        //If account storage is empty then use store_data to store accounts

        if (b.storage.isEmpty()) {
            store_data(o);
            b.storage.add(o);
            store_data(o);
        } //Otherwise Use for loop to go through storage
        else {
            for (Accounts a : b.storage) {
                //if for loop email equals to given email then account already has the same email and add 1 
                if (o.getEmail().equals(a.getEmail())) {
                    //Prompts user with message saying email is in use and can't be used
                    System.out.println("This Account has the same Email!");

                    //add one more to count
                    count += 1;
                }
            }
            //If count equals to 0 then add account to storage and use store_data to store accounts
            if (count == 0) {
                b.storage.add(o);
                store_data(o);
            }

        }
    }

    //Overrides the store_data method from AccountIO
    @Override
    public void store_data(Accounts o) {
        try {
            //Use for loop to print out storage onto AccountDatabase.txt
            PrintWriter pw = new PrintWriter("./resources/AccountDatabase.txt");
            for (Accounts a : storage) {
                pw.println(a);
            }
            pw.close();//Close file
        } //Catch FileNotFoundException and prompt user with message
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } //Catch IOException and prompt user with message
        catch (IOException e) {
            System.out.println("Error reading from file ");
        }
    }

    //Overrides update_accounts method from AccountIO and used Accounts object
    @Override
    public void update_accounts(Accounts user) {
        try {
            //Reads file from AccountDatabase.txt
            FileReader s = new FileReader("./resources/AccountDatabase.txt");
            BufferedReader reader = new BufferedReader(s);
            LineNumberReader ln = new LineNumberReader(s);
            String line = null;

            //Assign position to 0
            int position = 0;

            //Create collect
            String collect = "";

            //While loop to go through each line in the text file
            while ((line = reader.readLine()) != null) {
                //Splits line into an array by splitting ", "
                Object[] array_list = line.split(", ");

                //If array_list's email equals to user's email then add 1 to position
                if (array_list[2].equals(user.getEmail())) {
                    position += 1;
                } //Otherwie add line into collect with new line
                else {
                    collect += line + "\n";
                }

            }

            //Prints collect and user into AccountDatabase.txt file 
            PrintWriter pw = new PrintWriter(new FileOutputStream("./resources/AccountDatabase.txt"));
            pw.print(collect);
            pw.print(user);
            pw.close(); //Close file

        } //Catch FileNotFoundException and prompt user with message        
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } //Catch IOException and prompt user with message    
        catch (IOException e) {
            System.out.println("Error reading from file ");
        }

    }

    //Collects data of accounts from AccountDatabase.txt
    private static void collect_Data(ArrayList<Accounts> o) {
        try {
            //Reads file from AccountDatabase.txt
            FileReader s = new FileReader("./resources/AccountDatabase.txt");
            BufferedReader reader = new BufferedReader(s);
            String line = null;

            //Create content ArrayList
            ArrayList<String> content = new <String>ArrayList();

            //While loop to go through each line in the text file
            while ((line = reader.readLine()) != null) {
                //Splits line into an array by "/" and assign it into shop_list
                String[] shop_list = line.split("/");

                //Assign shop_list[1] into content_list 
                String content_list = shop_list[1];

                //Splits line into an array by ", " and assign it into account_store
                Object[] account_store = line.split(", ");

                //Splits content_list into an array by ", " and assign it into array    
                String[] array = content_list.split(", ");
                int length = array.length;

                //If content_list equal to the String "[]"
                if (content_list.equals("[]")) {
                    //Do nothing
                } //Otherwise follow the commands
                else {

                    //If length of list equals 1 value then strip off "[" and "]" on 1 value and replace array position with it
                    if (length == 1) {
                        String first = array[0].replace('[', '!');
                        first = first.replace(']', '!');
                        String first_space = first.replaceAll("!", "");
                        array[length - 1] = first_space;

                    } //Otherwise strip off "[" and "]" on start and end values of array
                    else {
                        String first = array[0].replace('[', '!');
                        String first_space = first.replaceAll("!", "");

                        String last = array[length - 1].replace(']', '!');
                        String last_space = last.replaceAll("!", "");

                        array[0] = first_space;
                        array[length - 1] = last_space;

                    }
                    // use for loop in array and add it to content list
                    for (String e : array) {
                        content.add(e);
                    }
                }

                //Assign the other values of Accout from account_store array
                String name = (String) account_store[0];
                String surname = (String) account_store[1];
                String email = (String) account_store[2];
                String password = (String) account_store[3];

                //Change String number to an integer number
                String number = (String) account_store[4];
                int age = Integer.parseInt(number);

                //Insert all values into Accounts constructorm insert Shopping list into accountss and add it into ArrayList
                Accounts p1 = new Accounts(name, surname, email, age, password);
                p1.setShopping_list(content);
                o.add(p1);

            }

        } //Catch FileNotFoundException and prompt user with message        
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } //Catch IOException and prompt user with message        
        catch (IOException e) {
            System.out.println("Error reading from file ");
        }

    }

//Get number of items in cart    
    public int number_cart(Accounts a) {
        int count = 0;

        //Create an ArrayList and assign it to account's shopping list
        ArrayList<String> list = a.getShopping_list();

        //Use for loop to see how many item there are by splitting value and adding number of items ot counts
        for (String s : list) {
            String[] splitted = s.split("X");
            int number = Integer.parseInt(splitted[1]);
            count += number;

        }

        //return count
        return count;
    }

    // Overrides toString method to print out list from storage
    @Override
    public String toString() {
        String list = "";
        for (Accounts a : storage) {
            list += a + "\n";
        }

        return list;
    }

}
