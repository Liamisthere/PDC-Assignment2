/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import java.util.ArrayList;

/**
 *
 * @author Liam Naidoo
 */

//ProductIO is an interface that hold some methods to handle FileIO inin Productstore
public interface ProductIO {
    
    public void update(Product p);
    
    public void insert_Products(ArrayList<Product> o);
    
}
