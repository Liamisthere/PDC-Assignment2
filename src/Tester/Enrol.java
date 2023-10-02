package Tester;

import Tester.Panel;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;

public class Enrol extends JFrame implements ActionListener {

    public JButton submitBtn;
    public JButton exitBtn;
    public boolean quit;
    public JTextField namefield;
    public JTextField surnamefield;

    public JComboBox<Integer> agefield;

    public JTextField emailfield;
    public JTextField passwordfield;

    public JLabel namewarn;
    public JLabel surnamewarn;
    public JLabel agewarn;
    public JLabel emailwarn;
    public JLabel emailexist;

    public JLabel passwordwarn;

    public JLabel emailguide;
    public JLabel passwordguide;

    Accountstore o;

    public Enrol(Accountstore o) {
        this.o = o;
        initComponents();
        intitPanels();
        initActionListener();
    }

    public void initComponents() {

        quit = false;
        namefield = new JTextField(20);
        surnamefield = new JTextField(20);
        agefield = new JComboBox();

        for (int i = 1; i < 101; i++) {
            this.agefield.addItem(i);

        }

        emailfield = new JTextField(20);
        passwordfield = new JTextField(20);

        submitBtn = new JButton("Submit");
        exitBtn = new JButton("Exit");

        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void intitPanels() {

        //North Panel
        JPanel northPanel = new JPanel();
        JLabel explainLabel = new JLabel("Please fill in the following fileds");
        northPanel.add(explainLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Center Panel 
        Panel centerPanel = new Panel();

        JLabel name = new JLabel("Name:");
        namewarn = new JLabel("Name field is empty!");
        namewarn.setVisible(false);

        centerPanel.add(name);
        centerPanel.add(namewarn);
        centerPanel.add(namefield);

        JLabel surname = new JLabel("\nSurame:");

        surnamewarn = new JLabel("Surname field is empty!");

        surname.setVisible(true);
        surnamewarn.setVisible(false);

        centerPanel.add(surname);
        centerPanel.add(surnamewarn);
        centerPanel.add(surnamefield);

        JLabel age = new JLabel("Age:");

        age.setVisible(true);
        agewarn = new JLabel("Age field is empty!");
        agewarn.setVisible(false);

        centerPanel.add(age);
        centerPanel.add(agewarn);
        centerPanel.add(agefield);

        JLabel email = new JLabel("Email:");
        emailwarn = new JLabel("Email field is empty!");
        emailguide = new JLabel("Email must contain @ and be at least 6 characters long");
        emailexist = new JLabel("This email is already in use in the program, use another email");

        email.setVisible(true);
        emailexist.setVisible(false);
        emailwarn.setVisible(false);
        emailguide.setVisible(false);

        centerPanel.add(email);
        centerPanel.add(emailexist);
        centerPanel.add(emailguide);
        centerPanel.add(emailwarn);
        centerPanel.add(emailfield);

        JLabel password = new JLabel("Password:");
        passwordwarn = new JLabel("Password field is empty!");

        passwordguide = new JLabel("Password must contain at least 3 numbers and 3 letters");

        password.setVisible(true);
        passwordguide.setVisible(false);
        passwordwarn.setVisible(false);

        centerPanel.add(password);
        centerPanel.add(passwordwarn);
        centerPanel.add(passwordguide);
        centerPanel.add(passwordfield);

        this.add(centerPanel, BorderLayout.CENTER);

        //South panel
        JPanel southPanel = new JPanel();
        southPanel.add(submitBtn);
        southPanel.add(exitBtn);
        JLabel prop = new JLabel("Created by Liam Naidoo");
        southPanel.add(prop);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public void initActionListener() {
        this.namefield.addActionListener(this);
        this.surnamefield.addActionListener(this);
        this.agefield.addActionListener(this);
        this.emailfield.addActionListener(this);
        this.passwordfield.addActionListener(this);

        this.submitBtn.addActionListener(this);

        this.exitBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.submitBtn && this.namefield.getText().isEmpty()) {
            System.out.println("Name empty");
            namewarn.setVisible(true);
        }
        else if (e.getSource() == this.submitBtn && this.namefield.getText().length() > 0) {
            namewarn.setVisible(false);
        }
        

        if (e.getSource() == this.submitBtn && this.surnamefield.getText().isEmpty()) {
            System.out.println("Surname empty");
            surnamewarn.setVisible(true);
        }
         else if (e.getSource() == this.submitBtn && this.surnamefield.getText().length() > 0) {
            surnamewarn.setVisible(false);
        } 
        
        

        if (e.getSource() == this.submitBtn && this.emailfield.getText().isEmpty()) {
            System.out.println("Email empty");
            emailwarn.setVisible(true);
        }
       else if (e.getSource() == this.submitBtn && this.emailfield.getText().length() > 0) {
            emailwarn.setVisible(false);
        }        
        

        if (e.getSource() == this.submitBtn && this.passwordfield.getText().isEmpty()) {
            System.out.println("Password empty");
            passwordwarn.setVisible(true);
        }
        
        else if (e.getSource() == this.submitBtn && this.passwordfield.getText().length() > 0) {
            passwordwarn.setVisible(false);
        }

        if (e.getSource() == this.submitBtn && this.namefield.getText().length() > 0 && this.surnamefield.getText().length() > 0 && this.emailfield.getText().length() > 0 && this.passwordfield.getText().length() > 0) {
            System.out.println("SUBMIT");

               namewarn.setVisible(false);                
               surnamewarn.setVisible(false);
               emailwarn.setVisible(false);
               passwordwarn.setVisible(false);
               
            if (this.emailfield.getText().contains("@") && this.emailfield.getText().length() >= 6 && o.validpassword(this.passwordfield.getText())) {
                Accounts ac = new Accounts(this.namefield.getText(), this.surnamefield.getText(), this.emailfield.getText(), (int) this.agefield.getSelectedItem(), this.passwordfield.getText());

                if (o.Existed(ac, o)) {
                    emailexist.setVisible(true);
                } else {
                    emailguide.setVisible(false);
                    passwordguide.setVisible(false);

                    System.out.println(ac);
                    o.addUsers(ac, o);
                    this.setVisible(false);

                }
            }  if (this.emailfield.getText().contains("@") && this.emailfield.getText().length() >= 6) {
                emailguide.setVisible(false);
            }  if (o.validpassword(this.passwordfield.getText())) {
                passwordguide.setVisible(false);
            }  if (!this.emailfield.getText().contains("@") || this.emailfield.getText().length() < 6) {

                emailguide.setVisible(true);
                System.out.println("EMAIL LIMIT");

            }  if (o.validpassword(this.passwordfield.getText()) == false) {
                System.out.println("PASSWORD LIMIT");
                System.out.println(o.validpassword(this.passwordfield.getText()));
                passwordguide.setVisible(true);
            }

        }

        if (e.getSource() == this.exitBtn) {
            System.out.println("EXIT");
            this.setVisible(false);

        }

    }

}
