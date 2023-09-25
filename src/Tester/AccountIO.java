/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Tester;



/**
 *
 * @author Liam Naidoo
 */
//AccountIO is an interface that hold some methods to handle FileIO in Accountstore
public interface AccountIO {
    
    public void store_data(Accounts o);
    
    public void update_accounts(Accounts user);
}
