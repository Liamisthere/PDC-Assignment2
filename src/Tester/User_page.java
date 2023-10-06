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
    
    
    public JButton exitBtn;
    public JButton itemlistBtn;
    public JButton insertitemBtn;
    public JButton searchlistBtn;
    public JButton rateBtn;    
    public JButton removeitemBtn;
    public JButton viewcartBtn;
    public JButton payBtn;
    
    public JPanel paypanel;
    public JPanel itemlistpanel;
    public JPanel insertitempanel;
    public JPanel searchlistpanel;
    public JPanel viewcartpanel;
    public JPanel removeitempanel;
    public JPanel ratepanel;
    
    
    public JTextField searchfield; 
    
    
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

    }
    
    
    public void initComponents(){  

    
        
     searchfield = new JTextField(20);
     exitBtn = new JButton("Exit");
    
    insertitemBtn = new JButton("Insert item into cart");
    itemlistBtn = new JButton("See item list");
    searchlistBtn = new JButton("Search up item");
    rateBtn = new JButton("Rate item");
    removeitemBtn=  new JButton("Remove item from cart");
    viewcartBtn = new JButton("View cart");
    payBtn  = new JButton("Pay now");
     
     
     this.setSize(500, 500);
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
     
     
     JPanel itemlistpanel= new JPanel();
     JLabel displayLabel = new JLabel("Display inventory");
     displayLabel.setVisible(true);
     itemlistBtn.setVisible(true);
     
     itemlistpanel.add(displayLabel);
     itemlistpanel.add(itemlistBtn);
     
     JPanel insertitempanel= new JPanel();
     JLabel addLabel = new JLabel("Add product to shopping list");
     displayLabel.setVisible(true);
     itemlistBtn.setVisible(true);
     itemlistpanel.add(displayLabel);
     insertitempanel.add(itemlistBtn);
     
        
    

     JPanel viewcartpanel= new JPanel();
     JLabel cartLabel = new JLabel("See what's in your cart");
     cartLabel.setVisible(true);
     viewcartBtn.setVisible(true);
     viewcartpanel.add(cartLabel);       
     viewcartpanel.add(viewcartBtn);    
    
      
     JPanel ratepanel= new JPanel();
     JLabel rateLabel = new JLabel("Rate product");
     rateLabel.setVisible(true);
     rateBtn.setVisible(true);
     ratepanel.add(rateLabel);  
     ratepanel.add(rateBtn);
     
     JPanel searchlistpanel= new JPanel();
     JLabel searchLabel = new JLabel("Search product");
     searchLabel.setVisible(true);
     searchlistBtn.setVisible(true);     
     searchlistpanel.add(searchLabel);
     searchlistpanel.add(searchlistBtn);

            
            
     JPanel removeitempanel= new JPanel();
     JLabel removeitemLabel = new JLabel("Remove item from cart");        
     removeitemLabel.setVisible(true);
     removeitemBtn.setVisible(true);     
     
     removeitempanel.add(removeitemLabel);
     removeitempanel.add(removeitemBtn);
     
     
   
     JPanel paypanel= new JPanel();   
     JLabel paynowLable = new JLabel("Pay now");
     paynowLable.setVisible(true);
     payBtn.setVisible(true);     
 
     paypanel.add(paynowLable);
     paypanel.add(payBtn);
     
     
     
     centerPanel.add(itemlistpanel);
     centerPanel.add(insertitempanel);
     centerPanel.add(viewcartpanel);
     centerPanel.add(ratepanel);
     centerPanel.add(searchlistpanel);
     centerPanel.add(removeitempanel);
     centerPanel.add(paypanel);
     
     this.add(centerPanel, BorderLayout.CENTER);
     
     //South panel
     
     

     
    
    

     
     JPanel southPanel = new JPanel();
     
/*     
    String[] names = {"name","company","rating","price","category"};     
    String[][] inventory_data = new String[ps.getInventory().size()][1]; // Assuming one column for the product names
    int count = 0;
    for (Product p : ps.getInventory()) {
        inventory_data[count][0] = p.getName();
        count++;
    }
     
    
     item_list = new JTable(inventory_data, names);
     item_list.setVisible(true);
     
     
     
    
    ArrayList<Product> searched =  ps.findProduct(searchfield.getText(), ps.getInventory());
             
    String[][] search_data = new String[searched.size()][1]; // Assuming one column for the product names
    int scount = 0;
    for (Product p : ps.getInventory()) {
        inventory_data[count][0] = p.getName();
        scount++;
    }
     
     search_list = new JTable(search_data, names);
     search_list.setVisible(false);
     
     
    southPanel.add(item_list);
*/

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
