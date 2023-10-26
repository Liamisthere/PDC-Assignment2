/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import java.util.*;

/**
 *
 * @author Liam Naidoo
 */
//Accounts class is the structure of user in the program
public class Accounts {

    private String name;
    private String surname;
    private String email;
    private int age;
    private String password;
    private ArrayList<String> shopping_list;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    // Constructor consists of name, surname, email, age and password and assigns them in 
    // Shopping list is created
    public Accounts(String name, String surname, String email, int age, String Password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.password = Password;
        this.shopping_list = new ArrayList<String>();
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the shopping_list
     */
    public ArrayList<String> getShopping_list() {
        return shopping_list;
    }

    /**
     * @param shopping_list the shopping_list to set
     */
    public void setShopping_list(ArrayList<String> shopping_list) {
        this.shopping_list = shopping_list;
    }

    // Sends a message about the shopping list's size if empty or full
    public void ListEmpty(Accounts o) {
        if (o.getShopping_list().isEmpty()) {
            System.out.println("Your Shopping list is empty at the moment");
        } else {
            System.out.println("You have items in our shopping list");
        }
    }

    // Overrides toString method to print out details about the Account
    @Override
    public String toString() {

        return this.name + ", " + this.surname + ", " + this.email + ", " + this.password + ", " + this.age + ", /" + this.getShopping_list();
    }

    //Returns a string of the contents of the shopping list
    public String Shop_listed() {
        String list = "";
        int count = 0;
        for (String a : this.shopping_list) {
            count++;
            list += count + ". " + a + "\n";
        }

        return list;

    }

}
