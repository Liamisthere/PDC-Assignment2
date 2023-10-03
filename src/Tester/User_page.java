package Tester;

import Tester.Panel;
import java.util.*;
import java.io.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class User_page extends JFrame implements ActionListener{
  
    
    public DefaultTableModel tableModel;
    
    
    public JButton exitBtn;
    public JButton itemlistBtn;
    public JButton searchlistBtn;
    public JButton rateBtn;    
    public JButton removeitemBtn;
    public JButton viewcartBtn;
    public JButton payBtn;
    
    public JTable item_list;
    public JTable search_list;
    
    Accountstore astore;
    Accounts a;
    Productstore ps;
    
    
    public boolean quit;

    


    
    
    public User_page(Productstore p, Accounts a, Accountstore ac)
    {
     initComponents();
     intitPanels();
     initActionListener();
     this.ps = p;
     this.a = a;
     this.astore = ac;
     
     tableModel = new DefaultTableModel();
     tableModel.addColumn("Name");
     tableModel.addColumn("Company");
     tableModel.addColumn("Price");
     tableModel.addColumn("Rating");
     tableModel.addColumn("Category");
     item_list = new JTable(tableModel);
    }
    
    
    public void initComponents(){  

    
    
     exitBtn = new JButton("Exit");
    
     this.setSize(400, 400);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setLocationRelativeTo(null);
    }
     
   public void intitPanels()
   {  
       
    //North Panel
     JPanel northPanel = new JPanel();
     JLabel explainLabel = new JLabel("Click on the following commands");
     northPanel.add(explainLabel);    
     this.add(northPanel, BorderLayout.NORTH);
     
     
    // Center Panel 
     Panel centerPanel =  new Panel();
     
     JLabel displayLabel = new JLabel("Display inventory");
     
     
  //   itemlistBtn.setVisible(false);
     
   
     
     
     JLabel addLabel = new JLabel("Add product to shopping list");
     JLabel cartLabel = new JLabel("See what's in your cart");
     JLabel rateLabel = new JLabel("Rate product");
     JLabel searchLabel = new JLabel("Search product");
     JLabel removeitemLabel = new JLabel("Remove item from cart");
     JLabel paynowLable = new JLabel("Pay now");
     
     
     
  /*  for(Product r : ps.getInventory())
    {
            //tableModel.addRow(new Object[]{r.getName(),r.getCompany(),r.getPrice(), r.getRating(), r.getCategory()});
        System.out.println(r);
    }
     
    //item_list.setVisible(false);
*/

       
       //System.out.println(ps.getInventory().size());

  //   centerPanel.add(item_list);
//     centerPanel.add(itemlistBtn);     
     centerPanel.add(displayLabel);
     centerPanel.add(addLabel);
     centerPanel.add(cartLabel);
     centerPanel.add(rateLabel);
     centerPanel.add(searchLabel);
     centerPanel.add(removeitemLabel);
     centerPanel.add(paynowLable);
     
     this.add(centerPanel, BorderLayout.CENTER);
     
     //South panel
     JPanel southPanel = new JPanel();

     southPanel.add(exitBtn);
     JLabel prop = new JLabel("Created by Liam Naidoo");
     southPanel.add(prop);
     this.add(southPanel, BorderLayout.SOUTH);
   }
   
   
   
   
   public void initActionListener(){ 
     
     this.exitBtn.addActionListener(this);
   }        
   
   
   @Override
    public void actionPerformed(ActionEvent e) {
        
 
      
     if(e.getSource() == this.itemlistBtn)
     {
         System.out.println("EXIT");

        
     }
     
     if(e.getSource() == this.exitBtn)
     {
         System.out.println("EXIT");
         this.setVisible(false);
         
         Menu cf = new Menu(astore);
        cf.setVisible(true);
     }
     
     
     
     
    }
    
    
}
