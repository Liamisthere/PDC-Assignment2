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
import java.awt.Dimension;

public class User_page extends JFrame implements ActionListener {

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
    public JButton removeBtn;
    
    
    public JButton entersearchBtn;
    public JButton enteritemBtn;
    
    public JButton removalnum;
    public JButton cancelremoval;
    
    public JPanel paypanel;
    public JPanel itemlistpanel;
    public JPanel insertitempanel;
    public JPanel searchlistpanel;
    public JPanel viewcartpanel;
    public JPanel removeitempanel;
    public JPanel ratepanel;

    public JPanel messagepanel;
    public JPanel sizepanel;
    
    public JPanel itemtablepanel;
    public JPanel usertablepanel;
    public JPanel searchtablepanel;
    
    public JPanel fieldpanel;

    public JPanel buttonpanel;
    public JPanel copyrightpanel;
    public JPanel insertpanel;

    public JTextField searchfield;

    public JComboBox<String> productfield;
    public JComboBox<Double> ratefield;
    
    public JComboBox<Integer> quantityfield;
    public JComboBox<String> usershoplist;

    public JComboBox<Integer> removefield;
    
    public JTable item_list;
    public JTable search_list;
    public JTable user_table;
    
    public JScrollPane user_scroll;
    public JScrollPane scroll;
    public JScrollPane search_scroll;

    public JLabel searchtext;
    public JLabel emptylist;
    public JLabel cartsize;
    
    Accountstore astore;
    Accounts a;

    DefaultTableModel search_model = new DefaultTableModel();
    DefaultTableModel item_model;
    
    DefaultTableModel user_model = new DefaultTableModel();
    
    
    public boolean quit;

    public User_page(Accounts a, Accountstore ac) {
        this.a = a;
        this.astore = ac;
        item_table();
        UserItemTable();
        searchtable();
        initComponents();
        intitPanels();
        initActionListener();
    }

    public void initComponents() {

        productfield = new JComboBox();
        for (Product t : pgs.getInventory()) {
            this.productfield.addItem(t.getName());
        }

        ratefield = new JComboBox();

        for (double i = 0; i < 5; i += 0.25) {
            this.ratefield.addItem(i);
        }

        quantityfield = new JComboBox();
        
        for(int i = 1; i<101; i++)
        {
            this.quantityfield.addItem(i); 
        }
        
        usershoplist = new JComboBox();
        
        for(String item : a.getShopping_list())
        {
            String[] splitted = item.split("X");
            String value = splitted[0];
            this.usershoplist.addItem(value); 
        }
        
        
        removefield = new JComboBox();
        
        searchtext = new JLabel("Enter keywords:");
        emptylist = new JLabel("Functions are restricted due to list being empty");
        
        searchfield = new JTextField(20);
        exitBtn = new JButton("Exit");
        
        itemlistBtn = new JButton("See item list");
        insertitemBtn = new JButton("Insert item into cart");
        searchlistBtn = new JButton("Search up item");
        rateBtn = new JButton("Rate item");
        removeitemBtn = new JButton("Remove item from cart");
        viewcartBtn = new JButton("View cart");
        payBtn = new JButton("Pay now");
        cancelBtn = new JButton("Cancel");
        removalnum = new JButton("Selct item");
        enterrateBtn = new JButton("Enter");
        entersearchBtn = new JButton("Enter"); 
        enteritemBtn = new JButton("Enter");
        removeBtn = new JButton("Remove");
        cancelremoval = new JButton("Change item");
        
        
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        
        

        
        
        if(a.getShopping_list().isEmpty())
        {
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            emptylist.setVisible(true);
            cartsize = new JLabel("You currently have "+astore.number_cart(a)+" item in your cart");
        }
        
        else if(a.getShopping_list().size() == 1)
        {
            emptylist.setVisible(false);
            cartsize = new JLabel("You currently have "+astore.number_cart(a)+" item in your cart");
        }
                
                
        else
        {
            removeitemBtn.setEnabled(true);
            viewcartBtn.setEnabled(true);
            emptylist.setVisible(false);
            cartsize = new JLabel("You have currently "+astore.number_cart(a)+" items in your cart");
        }
        
        
    }

    public void intitPanels() {

        //North Panel
        JPanel northPanel = new JPanel();
        JLabel explainLabel = new JLabel("Welcome " + a.getName() + " Click on the following commands");
        northPanel.add(explainLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Center Panel 
        Panel centerPanel = new Panel();

        itemlistpanel = new JPanel();
        itemlistBtn.setVisible(true);
        itemlistpanel.add(itemlistBtn);
        
        JPanel insertpanel = new JPanel();
        insertitemBtn.setVisible(true);
        insertpanel.add(insertitemBtn);

        insertitempanel = new JPanel();
        itemlistBtn.setVisible(true);
        insertitempanel.add(itemlistBtn);

        System.out.println(pgs.getInventory());

        viewcartpanel = new JPanel();
        viewcartBtn.setVisible(true);
        viewcartpanel.add(viewcartBtn);

        ratepanel = new JPanel();
        rateBtn.setVisible(true);
        ratepanel.add(rateBtn);

        searchlistpanel = new JPanel();
        searchlistBtn.setVisible(true);
        searchlistpanel.add(searchlistBtn);

        removeitempanel = new JPanel();
        removeitemBtn.setVisible(true);
        removeitempanel.add(removeitemBtn);

        paypanel = new JPanel();
        payBtn.setVisible(true);
        paypanel.add(payBtn);
        
        messagepanel = new JPanel();
        
        messagepanel.add(emptylist);

        sizepanel = new JPanel(); 
        sizepanel.add(cartsize);
        
        usertablepanel = new JPanel();
        itemtablepanel = new JPanel();
        searchtablepanel = new JPanel();
        
        usertablepanel.add(user_scroll);
        itemtablepanel.add(scroll);
        searchtablepanel.add(search_scroll);
        
        scroll.setVisible(false);
        user_scroll.setVisible(false);
        search_scroll.setVisible(false);

        buttonpanel = new JPanel();
        cancelBtn.setVisible(false);
        enterrateBtn.setVisible(false);
        exitBtn.setVisible(true);

        buttonpanel.add(enterrateBtn);
        buttonpanel.add(cancelBtn);
        
        
        centerPanel.add(itemlistpanel);
        centerPanel.add(insertpanel);
        
        centerPanel.add(insertitempanel);
        centerPanel.add(viewcartpanel);
        centerPanel.add(ratepanel);
        centerPanel.add(searchlistpanel);
        centerPanel.add(removeitempanel);
       
        
        centerPanel.add(searchlistpanel);
        centerPanel.add(paypanel);
        centerPanel.add(sizepanel);
        centerPanel.add(messagepanel);
        JPanel fieldpanel = new JPanel();
        searchtext.setVisible(false);
        productfield.setVisible(false);
        searchfield.setVisible(false);
        ratefield.setVisible(false);
        quantityfield.setVisible(false);
        entersearchBtn.setVisible(false);
        enteritemBtn.setVisible(false);
        removeBtn.setVisible(false);
        cancelremoval.setVisible(false);
        usershoplist.setVisible(false);
        removefield.setVisible(false);
        removalnum.setVisible(false);
        fieldpanel.add(searchtext);
        fieldpanel.add(productfield);
        fieldpanel.add(searchfield);
        fieldpanel.add(ratefield);
        fieldpanel.add(quantityfield);
        fieldpanel.add(quantityfield);
        fieldpanel.add(removefield);
        fieldpanel.add(usershoplist);
        fieldpanel.add(removalnum);
        fieldpanel.add(cancelremoval);
        fieldpanel.add(removeBtn);
        fieldpanel.add(enteritemBtn);
        fieldpanel.add(entersearchBtn);
        
 
                
        centerPanel.add(fieldpanel);
        centerPanel.add(buttonpanel);
        centerPanel.add(usertablepanel);   
        centerPanel.add(itemtablepanel); 
        centerPanel.add(searchtablepanel);
        
        this.add(centerPanel, BorderLayout.CENTER);

        //South panel
        JPanel southPanel = new JPanel();

        
        JPanel copyrightpanel = new JPanel();
        JLabel prop = new JLabel("Created by Liam Naidoo");
        copyrightpanel.add(prop);
        copyrightpanel.add(exitBtn);
        southPanel.add(copyrightpanel);

        this.add(southPanel, BorderLayout.SOUTH);
    }

    public void initActionListener() {

        this.exitBtn.addActionListener(this);
        this.itemlistBtn.addActionListener(this);
        this.cancelBtn.addActionListener(this);
        
        this.enterrateBtn.addActionListener(this);

        this.removalnum.addActionListener(this);
        this.rateBtn.addActionListener(this);
       
        this.searchlistBtn.addActionListener(this);
        
        this.entersearchBtn.addActionListener(this);
        this.insertitemBtn.addActionListener(this);
        
        
        this.enteritemBtn.addActionListener(this);
        
        
        this.removeitemBtn.addActionListener(this);
        this.removeBtn.addActionListener(this);
        this.viewcartBtn.addActionListener(this);
        this.cancelremoval.addActionListener(this);
    }

    
   public void searchtable()
   {
 
       DefaultTableModel search_model = new DefaultTableModel();       
       search_model.addColumn("Name");
       search_model.addColumn("Company");
       search_model.addColumn("Rating");
       search_model.addColumn("Price");
       search_model.addColumn("Category");
       
       search_list = new JTable(search_model);
       
       search_list.setSize(1000, 1000);
           
    
       
        search_list.getColumnModel().getColumn(0).setPreferredWidth(210);
        search_list.getColumnModel().getColumn(1).setPreferredWidth(190);
        search_list.getColumnModel().getColumn(4).setPreferredWidth(90);
       
        search_scroll = new JScrollPane(search_list);
        search_scroll.setPreferredSize(new Dimension(490, 165));
        search_list.setEnabled(false);
   }
     
   public void update_searchtable(ArrayList<Product> list)
   {
       this.searchtablepanel.removeAll();
       
       search_model  = new DefaultTableModel();
       
       int row_size = search_model.getRowCount()-1;
        
       
       for(int i = row_size ; i > -1  ; i--)
       {
           search_model.removeRow(i);
       }
       
       search_list = new JTable(search_model);
       
       search_list.setSize(1000, 1000);
       Object rowData[] = new Object[5];
       
       for(int i=0; i < list.size(); i++)
       {
          rowData[0] = list.get(i).getName();
          rowData[1] = list.get(i).getCompany();
          rowData[2] = list.get(i).getRating();
          rowData[3] = list.get(i).getPrice();
          rowData[4] = list.get(i).getCategory();
          search_model.addRow(rowData);
       }
       
        
        search_scroll = new JScrollPane(search_list);
        search_scroll.setPreferredSize(new Dimension(490, 165));
        searchtablepanel.add(search_scroll);
        search_scroll.setEnabled(false);
        
        
        search_list.setVisible(true);
        search_scroll.setVisible(true);
        searchtablepanel.setVisible(true);
   }
   
    public void item_table() {
        
        DefaultTableModel item_model = new DefaultTableModel();
        

        item_model.addColumn("Name");
        item_model.addColumn("Company");
        item_model.addColumn("Rating");
        item_model.addColumn("Price");
        item_model.addColumn("Category");

        item_list = new JTable(item_model);
        
        item_list.setSize(200, 200);
        ArrayList<Product> list = pgs.getInventory();
        Object rowData[] = new Object[5];

        for (int i = 0; i < list.size(); i++) {
            rowData[0] = list.get(i).getName();
            rowData[1] = list.get(i).getCompany();
            rowData[2] = list.get(i).getRating();
            rowData[3] = list.get(i).getPrice();
            rowData[4] = list.get(i).getCategory();
            item_model.addRow(rowData);
        }
        
     
        item_list.getColumnModel().getColumn(0).setPreferredWidth(210);
        item_list.getColumnModel().getColumn(1).setPreferredWidth(190);
        item_list.getColumnModel().getColumn(4).setPreferredWidth(90);
        
        
        scroll = new JScrollPane(item_list);
        scroll.setPreferredSize(new Dimension(490, 165));
        item_list.setEnabled(false);
        
    }

    
    public void UserItemTable() {       
        
        //Table columns
        user_model.addColumn("Name");
        user_model.addColumn("Price");
        user_model.addColumn("Quantity");
        
        //user_model.setRowCount(0);
        user_table = new JTable(user_model);
        
        ArrayList<String> user_items = a.getShopping_list();
        ArrayList<Product> list = pgs.getInventory();
        ArrayList<Double> prices = new ArrayList<Double>();
        ArrayList<Integer> quantity = new ArrayList<Integer>();
        
       
        System.out.println(a.getShopping_list());
        
        if(!a.getShopping_list().contains("NULL"))
        {
        for(Product p: list)
        {
            for(String name : user_items)
            {
                
                String[] split = name.split("X");
                name = split[0];
                int amount = Integer.parseInt(split[1]);
                        
                if(name.trim().equals(p.getName().trim()))
                {
                    double price = p.getPrice();
                    
                    double multiply = price * amount;
                    prices.add(multiply);
                    quantity.add(amount);
                }
            }    
        }
        
        System.out.println("Price list: "+prices.size());
        System.out.println("user list: "+user_items.size());
        System.out.println("inventory list: "+list.size());
        System.out.println("quantity list: "+quantity.size());
        
        Object rowData[] = new Object[5];
        for (int i = 0; i < user_items.size(); i++) {
            
            rowData[0] = user_items.get(i);
            
            
            rowData[1] = prices.get(i);
            
            
            rowData[2] = quantity.get(i);
            user_model.addRow(rowData);

        }
    } 
              
        this.user_scroll = new JScrollPane(user_table);

        user_scroll.setPreferredSize(new Dimension(490, 165));
        user_table.setEnabled(false);         
       
   }
    
    public void updateUsertable()
    {
        
        
        this.usershoplist.removeAllItems();
        
        
        for(String item : a.getShopping_list())
        {
            String[] splitted = item.split("X");
            String value = splitted[0];
            this.usershoplist.addItem(value); 
        }
        
       this.usertablepanel.removeAll();
       
        
       int row_size = user_table.getRowCount() -1;
        
        
       
            for(int i = row_size ; i > -1  ; i--)
            {
                user_model.removeRow(i);
            }
    
        user_table = new JTable(user_model);
        
        ArrayList<String> user_items = a.getShopping_list();
        ArrayList<Product> list = pgs.getInventory();
        ArrayList<Double> prices = new ArrayList<Double>();
        ArrayList<Integer> quantity = new ArrayList<Integer>();
        
        if(user_items.isEmpty())
        {
           System.out.println("List is empty");
        }
        
        
        for(Product p: list)
        {
            for(String name : user_items)
            {
                String[] split = name.split("X");
                name = split[0];
                int amount = Integer.parseInt(split[1]);
                        
                if(name.trim().equals(p.getName().trim()))
                {
                    double price = p.getPrice();
                    
                    double multiply = price * amount;
                    prices.add(multiply);
                    quantity.add(amount);
                }
            }    
        }
        
        
        Object rowData[] = new Object[5];
        for (int i = 0; i < user_items.size(); i++) {
            rowData[0] = user_items.get(i);
            rowData[1] = prices.get(i);
            rowData[2] = quantity.get(i);
            user_model.addRow(rowData);

        }

              
        this.user_scroll = new JScrollPane(user_table);

        user_scroll.setPreferredSize(new Dimension(490, 165));
        user_table.setEnabled(false);  
        
        
        
        usertablepanel.add(user_scroll);
        user_scroll.setVisible(true);

    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.itemlistBtn) {
            System.out.println("Display item_list");

            insertitemBtn.setEnabled(false);
            itemlistBtn.setEnabled(false);
            searchlistBtn.setEnabled(false);
            rateBtn.setEnabled(false);
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            payBtn.setEnabled(false);

            
            cancelBtn.setVisible(true);
            scroll.setVisible(true);
        }

        if (e.getSource() == this.rateBtn) {
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
            scroll.setVisible(true);
        }
        if (e.getSource() == this.enterrateBtn) {
            insertitemBtn.setEnabled(true);
            itemlistBtn.setEnabled(true);
            searchlistBtn.setEnabled(true);
            rateBtn.setEnabled(true);
            removeitemBtn.setEnabled(true);
            viewcartBtn.setEnabled(true);
            payBtn.setEnabled(true);

            scroll.setVisible(false);
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

        
        if (e.getSource() == this.viewcartBtn) {
            System.out.println("View cart");
            insertitemBtn.setEnabled(false);
            itemlistBtn.setEnabled(false);
            searchlistBtn.setEnabled(false);
            rateBtn.setEnabled(false);
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            payBtn.setEnabled(false);

            user_scroll.setVisible(true);
            cancelBtn.setVisible(true);

        }

    
     if(e.getSource() == this.searchlistBtn)
     {
            insertitemBtn.setEnabled(false);
            itemlistBtn.setEnabled(false);
            searchlistBtn.setEnabled(false);
            rateBtn.setEnabled(false);
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            payBtn.setEnabled(false);
         
         
            System.out.println("Search item");
            searchtext.setVisible(true);
            searchfield.setVisible(true);
            entersearchBtn.setVisible(true);
            search_scroll.setVisible(true);
            cancelBtn.setVisible(true);
     }
     

     
     
    if(e.getSource() == entersearchBtn && searchfield.getText().trim().length() > 0)
    {
             ArrayList<Product> searched = pgs.findProduct(searchfield.getText(), pgs.getInventory());
             
             if(searched == null)
             {
                 System.out.println("Nothing");
             }
             else
             {
                System.out.println(searched); 
              
                update_searchtable(searched);
                search_scroll.setVisible(true);
             }
             
    }

     
     
     
    if (e.getSource() == removeitemBtn)
    {
        
        emptylist.setVisible(false);
        insertitemBtn.setEnabled(false);
        itemlistBtn.setEnabled(false);
        searchlistBtn.setEnabled(false);
        rateBtn.setEnabled(false);
        removeitemBtn.setEnabled(false);
        viewcartBtn.setEnabled(false);
        payBtn.setEnabled(false);
        
        user_scroll.setVisible(true);
        removalnum.setVisible(true);
        cancelBtn.setVisible(true);
        usershoplist.setVisible(true);
    } 
     
    
    if(e.getSource() == cancelremoval)
    {
        usershoplist.enable(true);
        removalnum.setVisible(true);
        
        removefield.setVisible(false);
        removeBtn.setVisible(false);
        
    }
    
     if(e.getSource() == removalnum)
     {
         cancelremoval.setVisible(true);
         String selected_item = usershoplist.getSelectedItem().toString();
         usershoplist.enable(false);
         
         String retrieve = "";
         
         for(String items : a.getShopping_list())
         {
             
             if(items.contains(selected_item))
             {
                 retrieve += items;
                 System.out.println("items"+items);
             }
         }
         
         
         String[] item_split =  retrieve.split("X");
         int maximum_size = Integer.parseInt(item_split[1]);
        
         removefield.removeAllItems();
         
        for(int i=1; i< maximum_size+1; i++)
        {
            removefield.addItem(i);
        }
        
        removeBtn.setVisible(true);
        removefield.setVisible(true);
        removalnum.setVisible(true);
        
     
     }
     
     if(e.getSource() == removeBtn)
     {
         
        String removal =  usershoplist.getSelectedItem().toString();
        removefield.setVisible(false);
        removeBtn.setVisible(false);
        cancelremoval.setVisible(false);
        
        usershoplist.enable(true);
        
        
        String word_number = removefield.getSelectedItem().toString();
        int quantity = Integer.parseInt(word_number);
        
        astore.deletion(a, removal, quantity); 
         
        
        cancelBtn.setText("Done");
        
            sizepanel.removeAll();
            cartsize = new JLabel("You currently have "+astore.number_cart(a)+" items in your cart");
            sizepanel.add(cartsize);
        
        updateUsertable();
        

            

        
        
                 
     }
     
        if (e.getSource() == this.cancelBtn) {
            cancelBtn.setText("Cancel");
            insertitemBtn.setEnabled(true);
            itemlistBtn.setEnabled(true);
            searchlistBtn.setEnabled(true);
            rateBtn.setEnabled(true);
            removeitemBtn.setEnabled(true);
            viewcartBtn.setEnabled(true);
            payBtn.setEnabled(true);
            
            cancelremoval.setVisible(false);
            removeBtn.setVisible(false);
            searchfield.setVisible(false);
            cancelBtn.setVisible(false);
            enterrateBtn.setVisible(false);
            productfield.setVisible(false);
            ratefield.setVisible(false);
            searchfield.setVisible(false);
            searchtext.setVisible(false);
            entersearchBtn.setVisible(false);
            quantityfield.setVisible(false);
            enteritemBtn.setVisible(false);
            
            search_scroll.setVisible(false);  
            scroll.setVisible(false);
            user_scroll.setVisible(false);
            usershoplist.setVisible(false);
            removefield.setVisible(false);
            removalnum.setVisible(false);
       
            

        }

        if (e.getSource() == this.insertitemBtn) {
            System.out.println("Insert item to cart");
            System.out.println(a.Shop_listed());
            emptylist.setVisible(false);
            insertitemBtn.setEnabled(false);
            itemlistBtn.setEnabled(false);
            searchlistBtn.setEnabled(false);
            rateBtn.setEnabled(false);
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            payBtn.setEnabled(false);
            
            item_list.setVisible(true); 
            productfield.setVisible(true);
            quantityfield.setVisible(true);
            enteritemBtn.setVisible(true);
            scroll.setVisible(true);
            cancelBtn.setVisible(true);            
        }
        
        
        
        if(e.getSource() == this.enteritemBtn)
        {
            
            String product_name = (String) productfield.getSelectedItem();
            int quantity = (int) quantityfield.getSelectedItem();
            
            
            System.out.println(a.Shop_listed());
            
            astore.insertion(a,product_name, quantity);
            System.out.println(a.Shop_listed());
            
            insertitemBtn.setEnabled(true);
            itemlistBtn.setEnabled(true);
            searchlistBtn.setEnabled(true);
            rateBtn.setEnabled(true);
            removeitemBtn.setEnabled(true);
            viewcartBtn.setEnabled(true);
            payBtn.setEnabled(true);
            
            emptylist.setVisible(false);           
            searchfield.setVisible(false);
            cancelBtn.setVisible(false);
            enterrateBtn.setVisible(false);
            enteritemBtn.setVisible(false);
            productfield.setVisible(false);
            ratefield.setVisible(false);
            searchfield.setVisible(false);
            searchtext.setVisible(false);
            entersearchBtn.setVisible(false);
            quantityfield.setVisible(false);
            search_list.setVisible(false);  
             
            scroll.setVisible(false);
            
            
            updateUsertable();
            user_scroll.setVisible(false);

            
            sizepanel.removeAll();
            cartsize = new JLabel("You currently have "+astore.number_cart(a)+" item in your cart");
            sizepanel.add(cartsize);
        }
        
        if (e.getSource() == this.exitBtn) {
            System.out.println("EXIT");
            this.setVisible(false);

            Menu cf = new Menu(astore);
            cf.setVisible(true);
        }
        
        
        if(a.getShopping_list().isEmpty())
        {
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            emptylist.setVisible(true);
            
            cancelremoval.setVisible(false);
            user_scroll.setVisible(false);
            removalnum.setVisible(false);
            cancelBtn.setVisible(false);
            usershoplist.setVisible(false);
            removeBtn.setVisible(false);
            removefield.setVisible(false);
            cancelBtn.setText("Cancel");
            
            
            
        }
        
        if(e.getSource() == this.cancelBtn && !a.getShopping_list().isEmpty())
        {
            removeitemBtn.setEnabled(true);
            viewcartBtn.setEnabled(true);
            emptylist.setVisible(false);
            
            

        }

    }

}
