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
    
    public JComboBox<Integer> quantityfield;
    public JComboBox<String> usershoplist;

    public JTable item_list;
    public JTable search_list;
    public JTable user_table;
    
   public JScrollPane user_scroll;
    public JScrollPane scroll;

     public JLabel searchtext;
    
    
    Accountstore astore;
    Accounts a;

    public boolean quit;

    public User_page(Accounts a, Accountstore ac) {
        this.a = a;
        this.astore = ac;
        addRowToTable();
        UserItemTable();
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
        
        searchtext = new JLabel("Enter keywords:");
        
        
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

        enterrateBtn = new JButton("Enter");
        entersearchBtn = new JButton("Enter"); 
        enteritemBtn = new JButton("Enter");
        removeBtn = new JButton("Remove");
        
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void intitPanels() {

        //North Panel
        JPanel northPanel = new JPanel();
        JLabel explainLabel = new JLabel("Welcome " + a.getName() + " Click on the following commands");
        northPanel.add(explainLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Center Panel 
        Panel centerPanel = new Panel();

        JPanel itemlistpanel = new JPanel();
        itemlistBtn.setVisible(true);
        itemlistpanel.add(itemlistBtn);
        
        JPanel insertpanel = new JPanel();
        insertitemBtn.setVisible(true);
        insertpanel.add(insertitemBtn);

        JPanel insertitempanel = new JPanel();
        itemlistBtn.setVisible(true);
        insertitempanel.add(itemlistBtn);

        System.out.println(pgs.getInventory());

        JPanel viewcartpanel = new JPanel();
        viewcartBtn.setVisible(true);
        viewcartpanel.add(viewcartBtn);

        JPanel ratepanel = new JPanel();
        rateBtn.setVisible(true);
        ratepanel.add(rateBtn);

        JPanel searchlistpanel = new JPanel();
        searchlistBtn.setVisible(true);
        searchlistpanel.add(searchlistBtn);

        JPanel removeitempanel = new JPanel();
        removeitemBtn.setVisible(true);
        removeitempanel.add(removeitemBtn);

        JPanel paypanel = new JPanel();
        payBtn.setVisible(true);
        paypanel.add(payBtn);
        
        
        
        JPanel tablepanel = new JPanel();
        item_list.setVisible(false);
        user_table.setVisible(false); 
//     search_list.setVisible(false);



        tablepanel.add(user_scroll);
        tablepanel.add(scroll);
        scroll.setVisible(false);
        user_scroll.setVisible(false);


        JPanel buttonpanel = new JPanel();
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
        centerPanel.add(paypanel);

        JPanel fieldpanel = new JPanel();
        searchtext.setVisible(false);
        productfield.setVisible(false);
        searchfield.setVisible(false);
        ratefield.setVisible(false);
        quantityfield.setVisible(false);
        entersearchBtn.setVisible(false);
        enteritemBtn.setVisible(false);
        removeBtn.setVisible(false);
        usershoplist.setVisible(false);
        fieldpanel.add(searchtext);
        fieldpanel.add(productfield);
        fieldpanel.add(searchfield);
        fieldpanel.add(ratefield);
        fieldpanel.add(quantityfield);
        fieldpanel.add(quantityfield);
        fieldpanel.add(usershoplist);
        fieldpanel.add(removeBtn);
        fieldpanel.add(enteritemBtn);
        
 
                
        centerPanel.add(fieldpanel);
        centerPanel.add(buttonpanel);
        centerPanel.add(tablepanel);        
        
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
        this.rateBtn.addActionListener(this);
        this.searchlistBtn.addActionListener(this);
        this.entersearchBtn.addActionListener(this);
        this.insertitemBtn.addActionListener(this);
        this.enteritemBtn.addActionListener(this);
        this.removeitemBtn.addActionListener(this);
        this.removeBtn.addActionListener(this);
    }

    
   public void searchtable(ArrayList<Product> list)
   {
 
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("Name");
       model.addColumn("Company");
       model.addColumn("Rating");
       model.addColumn("Price");
       model.addColumn("Category");
       
       search_list = new JTable(model);
       
       search_list.setSize(1000, 1000);
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
     
   
   
    public void addRowToTable() {
        
        DefaultTableModel model = new DefaultTableModel();


        model.addColumn("Name");
        model.addColumn("Company");
        model.addColumn("Rating");
        model.addColumn("Price");
        model.addColumn("Category");

        item_list = new JTable(model);
        
        item_list.setSize(200, 200);
        ArrayList<Product> list = pgs.getInventory();
        Object rowData[] = new Object[5];

        for (int i = 0; i < list.size(); i++) {
            rowData[0] = list.get(i).getName();
            rowData[1] = list.get(i).getCompany();
            rowData[2] = list.get(i).getRating();
            rowData[3] = list.get(i).getPrice();
            rowData[4] = list.get(i).getCategory();
            model.addRow(rowData);
        }
        
     
        item_list.getColumnModel().getColumn(0).setPreferredWidth(210);
        item_list.getColumnModel().getColumn(1).setPreferredWidth(190);
        item_list.getColumnModel().getColumn(4).setPreferredWidth(90);
        scroll = new JScrollPane(item_list);
        item_list.setEnabled(false);
    }

    
    public void UserItemTable() {

        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"Name", "Price", "Quantity"};

        model.addRow(columnNames);
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Quantity");
        user_table = new JTable(model);

        user_table.setSize(1000, 1000);
        
        ArrayList<String> user_items = a.getShopping_list();
        ArrayList<Product> list = pgs.getInventory();
        
        
        Object rowData[] = new Object[5];

        for (int i = 0; i < list.size(); i++) {
            rowData[0] = list.get(i).getName();
            rowData[1] = list.get(i).getPrice();
            rowData[2] = list.get(i).getCategory();
            model.addRow(rowData);

        }

              
        this.user_scroll = new JScrollPane(user_table);
        user_table.setEnabled(false);         
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

            item_list.setVisible(true);
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
            cancelBtn.setVisible(true);
         
         if(e.getSource() == entersearchBtn && searchfield.getText().trim().length() > 0)
         {
             ArrayList<Product> searched = pgs.findProduct(searchfield.getText(), pgs.getInventory());
             System.out.println(searched);
             
             if(searched == null)
             {
                 System.out.println("Nothing");
             }
             else
             {
                System.out.println(searched); 
                this.searchtable(searched);

                search_list.setVisible(true);      
             }
             
         }
            
     
     }
     
    if (e.getSource() == removeitemBtn)
    {
        insertitemBtn.setEnabled(false);
        itemlistBtn.setEnabled(false);
        searchlistBtn.setEnabled(false);
        rateBtn.setEnabled(false);
        removeitemBtn.setEnabled(false);
        viewcartBtn.setEnabled(false);
        payBtn.setEnabled(false);
        
        user_scroll.setVisible(true);
        removeBtn.setVisible(true);
        cancelBtn.setVisible(true);
        usershoplist.setVisible(true);
    
    
    
    } 
     
     
     
     if(e.getSource() == removeBtn)
     {
         
        String removal =  usershoplist.getSelectedItem().toString();
                
        astore.deletion(a, removal); 
         
        insertitemBtn.setEnabled(true);
        itemlistBtn.setEnabled(true);
        searchlistBtn.setEnabled(true);
        rateBtn.setEnabled(true);
        removeitemBtn.setEnabled(true);
        viewcartBtn.setEnabled(true);
        payBtn.setEnabled(true);
        
        
        user_scroll.setVisible(false);
        removeBtn.setVisible(false);
        cancelBtn.setVisible(false);
        usershoplist.setVisible(false);
     }
     
        if (e.getSource() == this.cancelBtn) {

            insertitemBtn.setEnabled(true);
            itemlistBtn.setEnabled(true);
            searchlistBtn.setEnabled(true);
            rateBtn.setEnabled(true);
            removeitemBtn.setEnabled(true);
            viewcartBtn.setEnabled(true);
            payBtn.setEnabled(true);
            
            removeBtn.setVisible(false);
            searchfield.setVisible(false);
            user_table.setVisible(false);
            item_list.setVisible(false);
            cancelBtn.setVisible(false);
            enterrateBtn.setVisible(false);
            productfield.setVisible(false);
            ratefield.setVisible(false);
            searchfield.setVisible(false);
            searchtext.setVisible(false);
            entersearchBtn.setVisible(false);
            quantityfield.setVisible(false);
            enteritemBtn.setVisible(false);
            //search_list.setVisible(false);  
            scroll.setVisible(false);
            user_scroll.setVisible(false);
            usershoplist.setVisible(false);
            
            

        }

        if (e.getSource() == this.insertitemBtn) {
            System.out.println("Insert item to cart");
            System.out.println(a.Shop_listed());
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
            
            
            searchfield.setVisible(false);
            user_table.setVisible(false);
            item_list.setVisible(false);
            cancelBtn.setVisible(false);
            enterrateBtn.setVisible(false);
            enteritemBtn.setVisible(false);
            productfield.setVisible(false);
            ratefield.setVisible(false);
            searchfield.setVisible(false);
            searchtext.setVisible(false);
            entersearchBtn.setVisible(false);
            quantityfield.setVisible(false);
            //search_list.setVisible(false);  
             
            scroll.setVisible(false);
        }
        
        if (e.getSource() == this.exitBtn) {
            System.out.println("EXIT");
            this.setVisible(false);

            Menu cf = new Menu(astore);
            cf.setVisible(true);
        }

    }

}
