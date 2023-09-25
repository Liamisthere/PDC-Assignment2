/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

/**
 *
 * @author Liam Naidoo
 */
//Product class is the structure of items in the program
public class Product {

    private String name;
    private String company;
    private double price;
    private double rating;
    private String category;

    //Constructor consists of name, company, price, rating and category
    Product(String name, String company, double price, double rating, String category) {
        this.name = name;
        this.company = company;
        this.price = price;
        this.rating = rating;
        this.category = category;

    }

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
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    // Overrides toString method to print out details about the product
    @Override
    public String toString() {
        return this.name + ", " + this.company + ", " + this.price + ", " + this.rating + ", " + this.category;
    }

}
