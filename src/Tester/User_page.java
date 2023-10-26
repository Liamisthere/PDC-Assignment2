package Tester;

import java.util.*;
import java.io.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Tester.Productstore;
import java.awt.Dimension;

// User_page is a GUI application that is for the user to shop
public class User_page extends JFrame implements ActionListener {

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
    public JButton entersearch;

    public JButton removalnum;
    public JButton cancelremoval;

    public JButton enterpay;
    public JButton cancelpay;

    public JPanel paypanel;
    public JPanel itemlistpanel;
    public JPanel searchlistpanel;
    public JPanel viewcartpanel;
    public JPanel removeitempanel;
    public JPanel ratepanel;
    public JPanel searchfunctions;

    public JPanel messagepanel;
    public JPanel instructpanel;
    public JPanel sizepanel;

    public JPanel itemtablepanel;
    public JPanel usertablepanel;
    public JPanel searchtablepanel;

    public JPanel fieldpanel;

    public JPanel buttonpanel;
    public JPanel copyrightpanel;
    public JPanel insertpanel;

    public JPanel paymessagepanel;
    public JPanel paybuttonpanel;

    public JTextField searchfield;

    public JComboBox<String> productfield;
    public JComboBox<String> searchproductfield;

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
    public JLabel searchwarn;
    public JLabel paytext;
    public JLabel payquestion;
    public JLabel actionmessage;

    public JLabel messagetext;

    Accountstore astore;
    Accounts a;
    Productstore pgs = new Productstore();

    DefaultTableModel search_model = new DefaultTableModel();
    DefaultTableModel item_model;

    DefaultTableModel user_model = new DefaultTableModel();

    public boolean quit;

    //Constructor requires Accounts class and Accountstore
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

    //Assign buttons, labels and comboboxes
    public void initComponents() {

        productfield = new JComboBox();
        for (Product t : pgs.getInventory()) {
            this.productfield.addItem(t.getName());
        }

        ratefield = new JComboBox();

        for (double i = 0; i < 5.25; i += 0.25) {
            this.ratefield.addItem(i);
        }

        quantityfield = new JComboBox();

        for (int i = 1; i < 101; i++) {
            this.quantityfield.addItem(i);
        }

        usershoplist = new JComboBox();

        for (String item : a.getShopping_list()) {
            String[] splitted = item.split("X");
            String value = splitted[0];
            this.usershoplist.addItem(value);
        }

        searchproductfield = new JComboBox();

        removefield = new JComboBox();

        searchtext = new JLabel("Enter keywords:");
        emptylist = new JLabel("Functions are restricted due to list being empty");
        searchwarn = new JLabel("This text doesn't match");

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
        removalnum = new JButton("Select item");
        enterrateBtn = new JButton("Enter");
        entersearchBtn = new JButton("Enter");
        entersearch = new JButton("Add item");

        enteritemBtn = new JButton("Enter");
        removeBtn = new JButton("Remove");
        cancelremoval = new JButton("Change item");
        messagetext = new JLabel("I'm a message");
        actionmessage = new JLabel("I'm an action message");

        enterpay = new JButton("Pay");
        cancelpay = new JButton("Don't pay");

        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("User page - " + a.getName() + " " + a.getSurname());

        paytext = new JLabel("That'll be a total of $" + pgs.totalPrice(a, pgs) + ",  Are you ready to pay now?");
        paytext.setVisible(false);

        payquestion = new JLabel("");
        payquestion.setSize(2, 2);
        payquestion.setVisible(false);

        if (a.getShopping_list().isEmpty()) {
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            payBtn.setEnabled(false);
            emptylist.setVisible(true);
            cartsize = new JLabel("You currently have " + astore.number_cart(a) + " item in your cart");
        } else if (a.getShopping_list().size() == 1) {
            emptylist.setVisible(false);
            cartsize = new JLabel("You currently have " + astore.number_cart(a) + " item in your cart");
        } else {
            removeitemBtn.setEnabled(true);
            viewcartBtn.setEnabled(true);
            emptylist.setVisible(false);
            cartsize = new JLabel("You currently have" + astore.number_cart(a) + " items in your cart");
        }

    }

    //Create panels and add components to panel
    public void intitPanels() {

        //North Panel
        JPanel northPanel = new JPanel();
        JLabel explainLabel = new JLabel("Welcome " + a.getName() + " Click on the following commands");
        northPanel.add(explainLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Center Panel 
        JPanel centerPanel = new JPanel();

        itemlistpanel = new JPanel();
        itemlistBtn.setVisible(true);
        itemlistpanel.add(itemlistBtn);

        viewcartpanel = new JPanel();
        viewcartBtn.setVisible(true);
        viewcartpanel.add(viewcartBtn);

        insertpanel = new JPanel();
        insertitemBtn.setVisible(true);
        insertpanel.add(insertitemBtn);

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
        messagepanel.add(searchwarn);
        searchwarn.setVisible(false);

        sizepanel = new JPanel();
        sizepanel.add(cartsize);

        searchfunctions = new JPanel();

        entersearchBtn.setVisible(false);
        searchtext.setVisible(false);
        searchfield.setVisible(false);
        searchfunctions.add(searchtext);
        searchfunctions.add(searchfield);
        searchfunctions.add(entersearchBtn);

        instructpanel = new JPanel();

        instructpanel.add(messagetext);
        messagetext.setVisible(false);

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

        paymessagepanel = new JPanel();
        paymessagepanel.add(payquestion);

        paybuttonpanel = new JPanel();
        paybuttonpanel.add(enterpay);
        paybuttonpanel.add(cancelpay);

        centerPanel.add(itemlistpanel);
        centerPanel.add(viewcartpanel);
        centerPanel.add(insertpanel);
        centerPanel.add(ratepanel);
        centerPanel.add(searchlistpanel);
        centerPanel.add(removeitempanel);

        centerPanel.add(searchlistpanel);
        centerPanel.add(paypanel);

        centerPanel.add(sizepanel);
        centerPanel.add(instructpanel);
        centerPanel.add(messagepanel);
        centerPanel.add(searchfunctions);

        JPanel fieldpanel = new JPanel();
        productfield.setVisible(false);
        searchproductfield.setVisible(false);

        ratefield.setVisible(false);
        quantityfield.setVisible(false);

        entersearch.setVisible(false);
        enteritemBtn.setVisible(false);
        removeBtn.setVisible(false);
        cancelremoval.setVisible(false);
        usershoplist.setVisible(false);
        removefield.setVisible(false);
        removalnum.setVisible(false);
        paybuttonpanel.setVisible(false);

        actionmessage.setVisible(false);

        fieldpanel.add(productfield);
        fieldpanel.add(ratefield);
        fieldpanel.add(quantityfield);
        fieldpanel.add(searchproductfield);
        fieldpanel.add(removefield);
        fieldpanel.add(usershoplist);
        fieldpanel.add(removalnum);
        fieldpanel.add(cancelremoval);
        fieldpanel.add(removeBtn);
        fieldpanel.add(entersearch);
        fieldpanel.add(enteritemBtn);

        centerPanel.add(fieldpanel);
        centerPanel.add(actionmessage);
        centerPanel.add(buttonpanel);
        centerPanel.add(usertablepanel);
        centerPanel.add(itemtablepanel);
        centerPanel.add(searchtablepanel);
        centerPanel.add(paytext);

        centerPanel.add(paybuttonpanel);

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

    //Enable button to work 
    public void initActionListener() {
        this.exitBtn.addActionListener(this);
        this.itemlistBtn.addActionListener(this);
        this.cancelBtn.addActionListener(this);

        this.enterrateBtn.addActionListener(this);

        this.removalnum.addActionListener(this);
        this.rateBtn.addActionListener(this);

        this.searchlistBtn.addActionListener(this);

        this.entersearch.addActionListener(this);
        this.entersearchBtn.addActionListener(this);
        this.insertitemBtn.addActionListener(this);

        this.enteritemBtn.addActionListener(this);

        this.removeitemBtn.addActionListener(this);
        this.removeBtn.addActionListener(this);
        this.viewcartBtn.addActionListener(this);
        this.cancelremoval.addActionListener(this);

        this.enterpay.addActionListener(this);
        this.cancelpay.addActionListener(this);
        this.payBtn.addActionListener(this);
    }

    //Create a search table that users search the items that they are looking for
    public void searchtable() {

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

    //Remove contents of searchtable and replace it with new batch of searched data
    public void update_searchtable(ArrayList<Product> list) {

        this.searchtablepanel.removeAll();

        DefaultTableModel search_model = new DefaultTableModel();
        search_model.addColumn("Name");
        search_model.addColumn("Company");
        search_model.addColumn("Rating");
        search_model.addColumn("Price");
        search_model.addColumn("Category");

        search_list = new JTable(search_model);

        search_list.setSize(1000, 1000);
        Object rowData[] = new Object[5];

        for (int i = 0; i < list.size(); i++) {
            rowData[0] = list.get(i).getName();
            rowData[1] = list.get(i).getCompany();
            rowData[2] = list.get(i).getRating();
            rowData[3] = list.get(i).getPrice();
            rowData[4] = list.get(i).getCategory();
            search_model.addRow(rowData);
        }

        search_list.getColumnModel().getColumn(0).setPreferredWidth(210);
        search_list.getColumnModel().getColumn(1).setPreferredWidth(190);
        search_list.getColumnModel().getColumn(4).setPreferredWidth(90);

        search_scroll = new JScrollPane(search_list);
        search_scroll.setPreferredSize(new Dimension(490, 165));
        search_list.setEnabled(false);

        this.searchtablepanel.add(search_scroll);

    }

    //Create item table for users to see what they can add
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

    //Update item table by removing all contents in table and then adding it new data
    public void update_item_table() {
        this.itemtablepanel.removeAll();

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

        itemtablepanel.add(scroll);

    }

    //Create User Table so users can see what's in their cart
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

        for (Product p : list) {
            for (String name : user_items) {

                String[] split = name.split("X");
                name = split[0];
                int amount = Integer.parseInt(split[1]);

                if (name.trim().equals(p.getName().trim())) {
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

    }

    //Update user table by removing all contents in table and adding updated information
    public void updateUsertable() {

        this.usershoplist.removeAllItems();

        for (String item : a.getShopping_list()) {
            String[] splitted = item.split("X");
            String value = splitted[0];
            this.usershoplist.addItem(value);
        }

        this.usertablepanel.removeAll();

        int row_size = user_table.getRowCount() - 1;

        for (int i = row_size; i > -1; i--) {
            user_model.removeRow(i);
        }

        user_table = new JTable(user_model);

        ArrayList<String> user_items = a.getShopping_list();
        ArrayList<Product> list = pgs.getInventory();
        ArrayList<Double> prices = new ArrayList<Double>();
        ArrayList<Integer> quantity = new ArrayList<Integer>();

        if (user_items.isEmpty()) {
            System.out.println("List is empty");
        }

        for (Product p : list) {
            for (String name : user_items) {
                String[] split = name.split("X");
                name = split[0];
                int amount = Integer.parseInt(split[1]);

                if (name.trim().equals(p.getName().trim())) {
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

        //If itemlistBtn has been clicked and run the following commands 
        if (e.getSource() == this.itemlistBtn) {
            messagetext.setText("View all items in OnlineShopping");
            System.out.println("Display item_list");

            insertitemBtn.setEnabled(false);
            itemlistBtn.setEnabled(false);
            searchlistBtn.setEnabled(false);
            rateBtn.setEnabled(false);
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            payBtn.setEnabled(false);

            messagetext.setVisible(true);
            cancelBtn.setVisible(true);
            scroll.setVisible(true);

            emptylist.setVisible(false);
            cartsize.setVisible(false);
            cancelBtn.setText("Done");
        }

        //If entersearchBtn has been clicked and searchfield isn't empty, run the following commands 
        if (e.getSource() == this.rateBtn) {

            messagetext.setText("Select Item and Rating and press enter for to confirm rating");

            messagetext.setVisible(true);
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

            emptylist.setVisible(false);
            cartsize.setVisible(false);
        }

        //If enterrateBtn has been clicked run the following commands 
        if (e.getSource() == this.enterrateBtn) {

            actionmessage.setText("Rating has been done");
            actionmessage.setVisible(true);

            System.out.println("Rating has been done");

            String product_name = (String) productfield.getSelectedItem();
            double rate = (double) ratefield.getSelectedItem();

            pgs.ratedProduct(product_name, rate);

            update_item_table();

            cancelBtn.setText("Done");

        }

        //If viewcartBtn has been clicked run the following commands 
        if (e.getSource() == this.viewcartBtn) {
            messagetext.setText("View what's in your shopping cart");
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
            messagetext.setVisible(true);
            emptylist.setVisible(false);

            cartsize.setVisible(false);
            cancelBtn.setText("Done");
        }

        //If searchBtnlistBtn has been clicked run the following commands 
        if (e.getSource() == this.searchlistBtn) {
            messagetext.setText("Type in key words on what item you are looking for");
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
            messagetext.setVisible(true);
            cancelBtn.setVisible(true);

            emptylist.setVisible(false);
            cartsize.setVisible(false);
        }

        //If entersearchBtn has been clicked and searchfield isn't empty, run the following commands 
        if (e.getSource() == entersearchBtn && searchfield.getText().trim().length() > 0) {
            ArrayList<Product> searched = pgs.findProduct(searchfield.getText(), pgs.getInventory());
            cancelBtn.setText("Done");
            if (searched == null) {
                System.out.println("Nothing");
                searchwarn.setText("Sorry but '" + searchfield.getText() + "' doesn't match the items");
                searchwarn.setVisible(true);
            } else {
                searchwarn.setVisible(false);
                update_searchtable(searched);
                search_scroll.setVisible(true);

                searchproductfield.removeAllItems();

                for (Product pd : searched) {
                    searchproductfield.addItem(pd.getName());
                }

                searchproductfield.setVisible(true);
                quantityfield.setVisible(true);
                entersearch.setVisible(true);
            }

        }

        //If entersearch has been clicked, run the following commands 
        if (e.getSource() == entersearch) {

            String selected_item = searchproductfield.getSelectedItem().toString();
            int quantity = (int) quantityfield.getSelectedItem();

            astore.insertion(a, selected_item, quantity);
            updateUsertable();
            this.user_scroll.setVisible(false);
            System.out.println("Searched item has been added");

            actionmessage.setText("item has been added");
            actionmessage.setVisible(true);
        }

        //If removeitemBtn has been clicked, run the following commands 
        if (e.getSource() == removeitemBtn) {
            messagetext.setText("Select the item from your cart you want to remove");
            emptylist.setVisible(false);
            insertitemBtn.setEnabled(false);
            itemlistBtn.setEnabled(false);
            searchlistBtn.setEnabled(false);
            rateBtn.setEnabled(false);
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            payBtn.setEnabled(false);

            usershoplist.enable(true);

            user_scroll.setVisible(true);
            removalnum.setVisible(true);
            cancelBtn.setVisible(true);
            usershoplist.setVisible(true);
            messagetext.setVisible(true);

            emptylist.setVisible(false);
            cartsize.setVisible(false);
        }

        //If cancelremoval has been clicked, run the following commands 
        if (e.getSource() == cancelremoval) {
            messagetext.setText("Select the item from your cart you want to remove");
            usershoplist.enable(true);

            removalnum.setVisible(true);
            removefield.setVisible(false);
            removeBtn.setVisible(false);
            cancelremoval.setVisible(false);

        }

        //If removalnum has been clicked, run the following commands 
        if (e.getSource() == removalnum) {
            messagetext.setText("Select the quantity of items you want to remove");
            cancelremoval.setVisible(true);
            String selected_item = usershoplist.getSelectedItem().toString();
            usershoplist.enable(false);

            String retrieve = "";

            //Loop through user's shopping list
            for (String items : a.getShopping_list()) {
                //if item from user's shopping list contains the selected item
                if (items.contains(selected_item)) {
                    retrieve += items;
                }
            }

            String[] item_split = retrieve.split("X");
            int maximum_size = Integer.parseInt(item_split[1]);

            removefield.removeAllItems();

            //for loop to get maximum number of items.
            for (int i = 1; i < maximum_size + 1; i++) {
                removefield.addItem(i);
            }

            removeBtn.setVisible(true);
            removefield.setVisible(true);

            removalnum.setVisible(false);

        }

        //If removeBtn has been clicked, run the following commands 
        if (e.getSource() == removeBtn) {
            String removal = usershoplist.getSelectedItem().toString();
            removefield.setVisible(false);
            removeBtn.setVisible(false);
            cancelremoval.setVisible(false);
            removalnum.setVisible(true);
            usershoplist.enable(true);

            String word_number = removefield.getSelectedItem().toString();
            int quantity = Integer.parseInt(word_number);

            astore.deletion(a, removal, quantity);

            cancelBtn.setText("Done");

            //If quantity is greater than one, set text 
            if (quantity > 1) {
                actionmessage.setText(quantity + " items has been removed from cart");
            } //Otherwise set text differently
            else {
                actionmessage.setText("Item has been removed");
            }

            actionmessage.setVisible(true);

            sizepanel.removeAll();
            cartsize = new JLabel("You currently have " + astore.number_cart(a) + " items in your cart");
            sizepanel.add(cartsize);

            //Update the users table
            updateUsertable();

        }

        //If payBtn has been clicked, run the following commands       
        if (e.getSource() == this.payBtn) {
            messagetext.setText("Click pay button to pay the items that are currently in your cart");
            paytext.setText("That'll be a total of $" + pgs.totalPrice(a, pgs) + ",  Are you ready to pay now?");
            System.out.println("Paying items");
            cancelpay.setText("Don't pay");
            updateUsertable();
            insertitemBtn.setEnabled(false);
            itemlistBtn.setEnabled(false);
            searchlistBtn.setEnabled(false);
            rateBtn.setEnabled(false);
            payBtn.setEnabled(false);
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            enterpay.setEnabled(true);

            paytext.setVisible(true);
            payquestion.setVisible(true);
            payquestion.setVisible(true);
            paymessagepanel.setVisible(true);
            paybuttonpanel.setVisible(true);
            messagetext.setVisible(true);

            cartsize.setVisible(false);

        }

        //If enterpay has been clicked, run the following commands 
        if (e.getSource() == this.enterpay) {
            messagetext.setText("Click Exit payment to use other functions");
            enterpay.setEnabled(false);
            System.out.println("Paying items");
            actionmessage.setText("Payment was a success");
            actionmessage.setVisible(true);

            payproduct pay = new payproduct(a, astore, pgs);
            pay.payment();
            updateUsertable();
            cancelpay.setText("Exit payment");
            
            paytext.setVisible(false);

        }

        //If cancelpay has been clicked, run the following commands 
        if (e.getSource() == this.cancelpay) {
            messagetext.setText("Message isn't in use");

            System.out.println("cancel Payment");

            actionmessage.setVisible(false);

            insertitemBtn.setEnabled(true);
            itemlistBtn.setEnabled(true);
            searchlistBtn.setEnabled(true);
            rateBtn.setEnabled(true);
            payBtn.setEnabled(true);
            removeitemBtn.setEnabled(true);
            viewcartBtn.setEnabled(true);

            paytext.setVisible(false);
            payquestion.setVisible(false);
            payquestion.setVisible(false);
            paymessagepanel.setVisible(false);
            paybuttonpanel.setVisible(false);
            user_scroll.setVisible(false);
            messagetext.setVisible(false);

            cartsize.setText("You currently have " + astore.number_cart(a) + " item in your cart");
            cartsize.setVisible(true);
            
            emptylist.setVisible(false);

        }

        //If cancelBtn has been clicked, run the following commands 
        if (e.getSource() == this.cancelBtn) {
            cancelBtn.setText("Cancel");
            messagetext.setText("Message isn't in use");
            cartsize.setText("You currently have " + astore.number_cart(a) + " item in your cart");

            actionmessage.setText("Message isn't in use");
            actionmessage.setVisible(false);

            insertitemBtn.setEnabled(true);
            itemlistBtn.setEnabled(true);
            searchlistBtn.setEnabled(true);
            rateBtn.setEnabled(true);
            removeitemBtn.setEnabled(true);
            viewcartBtn.setEnabled(true);
            payBtn.setEnabled(true);

            searchwarn.setVisible(false);
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
            searchproductfield.setVisible(false);
            search_scroll.setVisible(false);
            scroll.setVisible(false);
            user_scroll.setVisible(false);
            usershoplist.setVisible(false);
            removefield.setVisible(false);
            removalnum.setVisible(false);
            entersearch.setVisible(false);

            paytext.setVisible(false);
            payquestion.setVisible(false);
            payquestion.setVisible(false);
            paymessagepanel.setVisible(false);
            paybuttonpanel.setVisible(false);
            messagetext.setVisible(false);

            cartsize.setVisible(true);

        }

        //If insertitemBtn has been clicked, run the following commands 
        if (e.getSource() == this.insertitemBtn) {
            messagetext.setText("Select item and quantity and click enter to confirm selection");

            insertitemBtn.setEnabled(false);
            itemlistBtn.setEnabled(false);
            searchlistBtn.setEnabled(false);
            rateBtn.setEnabled(false);
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            payBtn.setEnabled(false);

            emptylist.setVisible(false);
            cartsize.setVisible(false);

            item_list.setVisible(true);
            productfield.setVisible(true);
            quantityfield.setVisible(true);
            enteritemBtn.setVisible(true);
            scroll.setVisible(true);
            cancelBtn.setVisible(true);
            messagetext.setVisible(true);
        }

        //If enteritemBtn has been clicked, run the following commands 
        if (e.getSource() == this.enteritemBtn) {

            String product_name = (String) productfield.getSelectedItem();
            int quantity = (int) quantityfield.getSelectedItem();

            actionmessage.setText(quantity + " items has been added to cart");

            astore.insertion(a, product_name, quantity);

            updateUsertable();
            user_scroll.setVisible(false);

            sizepanel.removeAll();
            sizepanel.add(cartsize);
            cartsize.setVisible(false);
            cancelBtn.setText("Done");

            //If quantity is greater than one, set text 
            if (quantity > 1) {
                actionmessage.setText(quantity + " items has been added to cart");
            } //Otherwise  set text differently
            else {
                actionmessage.setText("Item has been added to cart");
            }
            actionmessage.setVisible(true);
            cancelBtn.setVisible(true);

        }

        //If Exit Button has been clicked, head back to Menu page and turn off User page
        if (e.getSource() == this.exitBtn) {
            System.out.println("EXIT");
            this.setVisible(false);

            Menu cf = new Menu(astore);
            cf.setVisible(true);
        }

        //If cancelBtn has been clicked and user's shopping list is empty, run the following commands
        if (e.getSource() == cancelBtn && a.getShopping_list().isEmpty()) {
            emptylist.setVisible(true);

            cartsize.setText("You currently have " + astore.number_cart(a) + " item in your cart");
            cartsize.setVisible(true);

            actionmessage.setText("Message isn't in use");
            actionmessage.setVisible(false);
        }

        //If user's shopping list is empty, run the following commands
        if (a.getShopping_list().isEmpty()) {
            removeitemBtn.setEnabled(false);
            viewcartBtn.setEnabled(false);
            payBtn.setEnabled(false);
            cancelremoval.setEnabled(false);
            removalnum.setEnabled(false);
            usershoplist.setEnabled(false);
            removeBtn.setEnabled(false);
            removefield.setEnabled(false);

        }

        //If removeBtn has been clicked and user's shopping list is empty, run the following commands
        if (e.getSource() == removeBtn && a.getShopping_list().isEmpty()) {
            messagetext.setText("Functions are limited due to list being empty");

            actionmessage.setText("Items has been removed");
            actionmessage.setVisible(true);
            cancelBtn.setText("Done");
        }

        //If cancelBtn has been clicked and user's shopping list is NOT empty, run the following commands
        if (e.getSource() == cancelBtn && !a.getShopping_list().isEmpty()) {
            removeitemBtn.setEnabled(true);
            viewcartBtn.setEnabled(true);
            payBtn.setEnabled(true);

            actionmessage.setText("Message isn't in use");
            actionmessage.setVisible(false);

            emptylist.setVisible(false);

            cancelremoval.setEnabled(true);
            removalnum.setEnabled(true);
            usershoplist.setEnabled(true);
            removeBtn.setEnabled(true);
            removefield.setEnabled(true);

            cartsize.setText("You currently have " + astore.number_cart(a) + " item in your cart");
            cartsize.setVisible(true);

        }

    }

}
