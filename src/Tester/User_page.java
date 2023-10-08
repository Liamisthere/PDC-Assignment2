package Tester;

import Tester.Panel;
import java.util.*;
import java.io.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Tester.Productstore;

public class User_page extends JFrame implements ActionListener{
    
   
    private Productstore pgs = new Productstore();
    public JButton exitBtn;
    public JButton itemlistBtn;
    public JButton insertitemBtn;
    public JButton searchlistBtn;
    public JButton rateBtn;    
    public JButton removeitemBtn;
    public JButton viewcartBtn;
    public JButton payBtn;
    
    
    public JButton enterrateBtn;
    public JButton cancelBtn;
    
    
    public JPanel paypanel;
    public JPanel itemlistpanel;
    public JPanel insertitempanel;
    public JPanel searchlistpanel;
    public JPanel viewcartpanel;
    public JPanel removeitempanel;
    public JPanel ratepanel;
    
    public JPanel tablepanel;
    public JPanel fieldpanel;
        
    public JPanel buttonpanel;
    public JPanel copyrightpanel;
    
    public JTextField searchfield;
    
   
    
    public JComboBox<String> productfield;
    public JComboBox<Double> ratefield;
    
    
    public JTable item_list;
    public JTable search_list;
    
    Accountstore astore;
    Accounts a;
    
    public boolean quit;

    


    
    
    public User_page(Accounts a, Accountstore ac)
    {
     addRowToTable();
     this.a = a;
     this.astore = ac;  
     initComponents();
     intitPanels();
     initActionListener();
    }
    
    
    public void initComponents(){  

    
     productfield = new JComboBox();
     for (Product t : pgs.getInventory())
     {
            this.productfield.addItem(t.getName());
     }   
        
  
     ratefield = new JComboBox();
     
     for (double i=0; i <5 ; i+=0.25)
     {
        this.ratefield.addItem(i);
     }   
     
     
     
     searchfield = new JTextField(20);
     exitBtn = new JButton("Exit");
    
    insertitemBtn = new JButton("Insert item into cart");
    itemlistBtn = new JButton("See item list");
    searchlistBtn = new JButton("Search up item");
    rateBtn = new JButton("Rate item");
    removeitemBtn=  new JButton("Remove item from cart");
    viewcartBtn = new JButton("View cart");
    payBtn  = new JButton("Pay now");
    cancelBtn = new JButton("Cancel");
    
    enterrateBtn = new JButton("Enter");
    
     
     this.setSize(500, 500);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setLocationRelativeTo(null);
    }
     
   public void intitPanels()
   {  
       
    //North Panel
     JPanel northPanel = new JPanel();
     JLabel explainLabel = new JLabel("Welcome"+a.getName()+"Click on the following commands");
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
     
        
    
         
        System.out.println(pgs.getInventory());

         
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

    
    JPanel fieldpanel= new JPanel(); 
    productfield.setVisible(false);
    searchfield.setVisible(false);
    ratefield.setVisible(false);
    fieldpanel.add(productfield);
    fieldpanel.add(searchfield); 
    fieldpanel.add(ratefield);
    
    
     JPanel tablepanel= new JPanel();
     item_list.setVisible(false);
//     search_list.setVisible(false);
     tablepanel.add(item_list);
//     tablepanel.add(search_list);

     
    
    
     JPanel buttonpanel= new JPanel();
     cancelBtn.setVisible(false);
     enterrateBtn.setVisible(false);
     exitBtn.setVisible(true);
     
     buttonpanel.add(enterrateBtn);
     buttonpanel.add(cancelBtn);
     buttonpanel.add(exitBtn);

     
     JPanel copyrightpanel= new JPanel();
     JLabel prop = new JLabel("Created by Liam Naidoo");
     copyrightpanel.add(prop);
     
     southPanel.add(fieldpanel);
     southPanel.add(tablepanel);
     southPanel.add(buttonpanel);
     southPanel.add(copyrightpanel);
     
     this.add(southPanel, BorderLayout.SOUTH);
   }
   
   
   
   
   public void initActionListener(){ 
     
     this.exitBtn.addActionListener(this);
     this.itemlistBtn.addActionListener(this);
     this.cancelBtn.addActionListener(this);
     this.enterrateBtn.addActionListener(this);
     this.rateBtn.addActionListener(this);
     
   }        
   
   /*
   public void searchtable(ArrayList<Product> list)
   {
 
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("Name");
       model.addColumn("Company");
       model.addColumn("Rating");
       model.addColumn("Price");
       model.addColumn("Category");
       
       item_list = new JTable(model);
       
       item_list.setSize(1000, 1000);
       Object rowData[] = new Object[5];
       
       for(int i=0; i < list.size(); i++)
       {
          rowData[0] = list.get(i).getName();
          rowData[1] = list.get(i).getCompany();
          rowData[2] = list.get(i).getRating();
          rowData[3] = list.get(i).getPrice();
          rowData[4] = list.get(i).getCategory();
          model.addRow(rowData);
       }
   }
   */
   public void addRowToTable()
   {
 
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("Name");
       model.addColumn("Company");
       model.addColumn("Rating");
       model.addColumn("Price");
       model.addColumn("Category");
       
       item_list = new JTable(model);
       
       item_list.setSize(1000, 1000);
       ArrayList<Product> list = pgs.getInventory();
       Object rowData[] = new Object[5];
       
       for(int i=0; i < list.size(); i++)
       {
          rowData[0] = list.get(i).getName();
          rowData[1] = list.get(i).getCompany();
          rowData[2] = list.get(i).getRating();
          rowData[3] = list.get(i).getPrice();
          rowData[4] = list.get(i).getCategory();
          model.addRow(rowData);
          
       }
       
       
       
       
   
   }
   
   
   @Override
    public void actionPerformed(ActionEvent e) {
        
 
      
     if(e.getSource() == this.itemlistBtn)
     {
         System.out.println("Display item_list");
         
            insertitemBtn.setEnabled(false);
            itemlistBtn.setEnabled(false);
            searchlistBtn.setEnabled(false);
            rateBtn.setEnabled(false);
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            payBtn.setEnabled(false);

            item_list.setVisible(true);
            cancelBtn.setVisible(true);
         

     } 
     
     
     if(e.getSource() == this.rateBtn)
     {
         System.out.println("Rate an item");
         
            insertitemBtn.setEnabled(false);
            itemlistBtn.setEnabled(false);
            searchlistBtn.setEnabled(false);
            rateBtn.setEnabled(false);
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            payBtn.setEnabled(false);

            item_list.setVisible(true);
            enterrateBtn.setVisible(true);
            cancelBtn.setVisible(true);
         

            productfield.setVisible(true);
            ratefield.setVisible(true);
     } 
             if(e.getSource() == this.enterrateBtn)
            {
                insertitemBtn.setEnabled(true);
                itemlistBtn.setEnabled(true);
                searchlistBtn.setEnabled(true);
                rateBtn.setEnabled(true);
                removeitemBtn.setEnabled(true);
                viewcartBtn.setEnabled(true);
                payBtn.setEnabled(true);
             
            
                item_list.setVisible(false);
                enterrateBtn.setVisible(false);
                cancelBtn.setVisible(false);
                productfield.setVisible(false);
                ratefield.setVisible(false);
                
                
                
                System.out.println("Rating has been done");
                
                String product_name = (String) productfield.getSelectedItem();                
                double rate = (double) ratefield.getSelectedItem();

                pgs.ratedProduct(product_name, rate);
                
                
                System.out.println(pgs.getInventory());
                
            
            }
             
    
             
     
     
     /* if(e.getSource() == this.searchlistBtn)
     {
         searchfield.setVisible(true);
         if(searchfield.getText().trim().length() > 0)
         {
             ArrayList<Product> searched = pgs.findProduct(searchfield.getText(), pgs.getInventory());
             
             if(searched.size() > 0)
             {
                //this.searchtable(searched);
                //search_list.setVisible(true);
             }
         }
            
     
     }
     
     */
     

      
     if(e.getSource() == this.cancelBtn)
     {
            
            insertitemBtn.setEnabled(true);
            itemlistBtn.setEnabled(true);
            searchlistBtn.setEnabled(true);
            rateBtn.setEnabled(true);
            removeitemBtn.setEnabled(true);
            viewcartBtn.setEnabled(true);
            payBtn.setEnabled(true);
             
            
           item_list.setVisible(false);
           cancelBtn.setVisible(false);
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
