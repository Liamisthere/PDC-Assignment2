package Tester;

import Tester.Panel;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;


public class User_page extends JFrame implements ActionListener{
  
    
    
    public JButton exitBtn;
    public JButton itemlistBtn;
    public JButton searchlistBtn;
    public JButton rateBtn;    
    public JButton removeitemBtn;
    public JButton viewcartBtn;
    public JButton payBtn;
    
    public JTable item_list;
    public JTable search_list;
    
    
    public boolean quit;



    
    
    public User_page()
    {
     initComponents();
     intitPanels();
     initActionListener();
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
     JLabel addLabel = new JLabel("Add product to shopping list");
     JLabel cartLabel = new JLabel("See what's in your cart");
     JLabel rateLabel = new JLabel("Rate product");
     JLabel searchLabel = new JLabel("Search product");
     JLabel removeitemLabel = new JLabel("Remove item from cart");
     JLabel paynowLable = new JLabel("Pay now");
     
     
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
        
        
 
      
     
     if(e.getSource() == this.exitBtn)
     {
         System.out.println("EXIT");
         this.setVisible(false);
        
     }
     
     
     
     
    }
    
}
