package Tester;

import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;

// Menu class is a GUI main page where users can either log in or enrol 
public class Menu extends JFrame implements ActionListener {

    public JButton enrolBtn;
    public JButton loginBtn;
    public JTextField textfield;
    public JLabel textLabel;
    public JComboBox sizeCombobox;
    public JComboBox<String> fontCombobox;

    public String font;
    public String size;

    public Accountstore a;

    //Constructor requires Accountstore class
    Menu(Accountstore a) {
        this.a = a;
        initComponents();
        intitPanels();
        initActionListener();
    }

    //Assign buttons and page size and name
    public void initComponents() {

        enrolBtn = new JButton("Enrol");
        loginBtn = new JButton("Login");

        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Menu page");
    }

    //Create Panels and add components 
    public void intitPanels() {

        //North Panel
        JPanel northPanel = new JPanel();
        JLabel explainLabel = new JLabel("Please select the following buttons");
        northPanel.add(explainLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Center Panel 
        JPanel centerPanel = new JPanel();
        centerPanel.add(loginBtn);
        centerPanel.add(enrolBtn);
        this.add(centerPanel, BorderLayout.CENTER);

        //South panel
        JPanel southPanel = new JPanel();
        JLabel prop = new JLabel("Created by Liam Naidoo");
        southPanel.add(prop);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    //Enable buttons to work
    public void initActionListener() {
        this.enrolBtn.addActionListener(this);
        this.loginBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //If loginBtn is clicked, open Login class and close this page
        if (e.getSource() == this.loginBtn) {
            System.out.println("LOGIN");

            System.out.println("ENROL");

            login log = new login(a);
            log.setVisible(true);
            this.setVisible(false);

        }

        //If enrolBtn is clicked, open Enrol class and close this page
        if (e.getSource() == this.enrolBtn) {
            System.out.println("ENROL");

            Enrol e1 = new Enrol(a);
            this.setVisible(false);
            e1.setVisible(true);

        }

    }

}
