package Tester;

import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.Dimension;
import javax.swing.*;

public class Enrol extends JFrame implements ActionListener {


    public JPanel Namepanel;
    public JPanel Surnamepanel;  
    public JPanel Agepanel;
    public JPanel Emailpanel;
    public JPanel Passwordpanel;
    
    
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
    public JLabel emailcopy;
    
    public JLabel surnamelimit;
    public JLabel namelimit;
    
    

    public JLabel passwordwarn;

    public JLabel emailguide;
    public JLabel passwordguide;

    DBStorages DB;
    Accountstore o;

    
    public Enrol(Accountstore o) {
        this.o = o;
        DB = new DBStorages();
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

        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Enrol page");
    }

    public void intitPanels() {

        //North Panel
        JPanel northPanel = new JPanel();
        JLabel explainLabel = new JLabel("Please fill in the following fileds");
        northPanel.add(explainLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Center Panel 
        JPanel centerPanel = new JPanel();
        
        JPanel Namepanel = new JPanel();
        JPanel Surnamepanel = new JPanel();
        JPanel Agepanel = new JPanel();
        
        Agepanel.setPreferredSize( new Dimension(700, 100));
        
        JPanel Passwordpanel = new JPanel();    
        JPanel Emailpanel = new JPanel();
        
        JPanel Emailstrucpanel = new JPanel();
        JPanel Passwordstrucpanel = new JPanel();
        
        centerPanel.add(Namepanel);
        centerPanel.add(Surnamepanel);
        centerPanel.add(Agepanel);
        centerPanel.add(Emailstrucpanel);
        centerPanel.add(Emailpanel);
        centerPanel.add(Passwordstrucpanel);
        centerPanel.add(Passwordpanel);
        
        
        JLabel name = new JLabel("Name:");
        namewarn = new JLabel("Name field is empty!");
        namelimit = new JLabel("Name must be all letters!");
        namewarn.setVisible(false);
        namelimit.setVisible(false);
        
        Namepanel.add(name);
        
  
        Namepanel.add(namefield);
        Namepanel.add(namewarn);
        Namepanel.add(namelimit);
        
        JLabel surname = new JLabel("\nSurame:");

        surnamewarn = new JLabel("Surname field is empty!");
        surnamelimit = new JLabel("Surname must be all letters!");
        
        surname.setVisible(true);
        surnamewarn.setVisible(false);
        surnamelimit.setVisible(false);
        
        Surnamepanel.add(surname);

        Surnamepanel.add(surnamefield);
        Surnamepanel.add(surnamewarn);
        Surnamepanel.add(surnamelimit);
        

        JLabel age = new JLabel("Age:");
        age.setVisible(true);


        Agepanel.add(age);
        Agepanel.add(agefield);

        
        
        JLabel email = new JLabel("Email:");
        emailwarn = new JLabel("Email field is empty!");
        emailguide = new JLabel("Email must follow this structure: Robin@bankers.co.nz");
        emailexist = new JLabel("This email is already in use in the program, use another email");
        emailcopy = new JLabel("Don't copy the example!");
        

        email.setVisible(true);
        emailexist.setVisible(false);
        emailwarn.setVisible(false);
        emailguide.setVisible(false);
        emailcopy.setVisible(false);
        
        Emailpanel.add(email);
        Emailpanel.add(emailfield);
        Emailpanel.add(emailexist);
        Emailpanel.add(emailguide);
        Emailpanel.add(emailwarn);
        Emailpanel.add(emailcopy);

        Emailstrucpanel.add(emailguide);


        JLabel password = new JLabel("Password:");
        passwordwarn = new JLabel("Password field is empty!");

        passwordguide = new JLabel("Password must contain at least 3 numbers and 3 letters");

        password.setVisible(true);
        passwordguide.setVisible(false);
        passwordwarn.setVisible(false);

        Passwordpanel.add(password);
        Passwordpanel.add(passwordfield);
        Passwordpanel.add(passwordwarn);

        Passwordstrucpanel.add(passwordguide);
        
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

        if (e.getSource() == this.submitBtn && this.namefield.getText().trim().isEmpty()) {
            System.out.println("Name empty");
            namewarn.setVisible(true);
        }
        
        else if(e.getSource() == this.submitBtn && this.namefield.getText().trim().length() > 0 && o.validname(namefield.getText()) == false)
        {
            System.out.println("name limit");
            namewarn.setVisible(false);
            namelimit.setVisible(true);
        }       
        
        else if (e.getSource() == this.submitBtn && this.namefield.getText().trim().length() > 0) {
            namewarn.setVisible(false);
            namelimit.setVisible(false);
        }
        

        if (e.getSource() == this.submitBtn && this.surnamefield.getText().trim().isEmpty()) {
            System.out.println("Surname empty");
            surnamewarn.setVisible(true);
        }
        
        else if(e.getSource() == this.submitBtn && this.surnamefield.getText().trim().length() > 0 && o.validname(surnamefield.getText()) == false)
        {
            System.out.println("Surname limit");
            surnamewarn.setVisible(false);
            surnamelimit.setVisible(true);
        }
        
        else if (e.getSource() == this.submitBtn && this.surnamefield.getText().trim().length() > 0) {
             surnamewarn.setVisible(false);
             surnamelimit.setVisible(false);
             
        } 
        
        

        if (e.getSource() == this.submitBtn && this.emailfield.getText().trim().isEmpty()) {
            System.out.println("Email empty");
            emailwarn.setVisible(true);
            emailcopy.setVisible(false);
            emailguide.setVisible(false);
        }
        
       else if (e.getSource() == this.submitBtn && this.emailfield.getText().trim().length() > 0) {
           emailwarn.setVisible(false);
           if(this.emailfield.getText().trim().equalsIgnoreCase("Robin@Bankers.co.nz"))
           {
               emailcopy.setVisible(true);
               emailguide.setVisible(false);
           }
           
           else if(o.validEmail(emailfield.getText()) == false)
           {
               emailguide.setVisible(true);
               emailcopy.setVisible(false);
           }
           
           else    
           {
            emailcopy.setVisible(false);
            emailguide.setVisible(false);
           }
        }        
        
        

        if (e.getSource() == this.submitBtn && this.passwordfield.getText().trim().isEmpty()) {
            System.out.println("Password empty");
            passwordwarn.setVisible(true);
        }
        
        else if (e.getSource() == this.submitBtn && this.passwordfield.getText().trim().length() > 0) {
            passwordwarn.setVisible(false);
            if(o.validpassword(this.passwordfield.getText()) == false)
            {
                passwordguide.setVisible(true);
            }
            else
            {
                passwordguide.setVisible(false);
            }
            
        }
        
        

        if (e.getSource() == this.submitBtn && this.namefield.getText().trim().length() > 0 && this.surnamefield.getText().trim().length() > 0 && this.emailfield.getText().trim().length() > 0 && this.passwordfield.getText().trim().length() > 0) {
            System.out.println("SUBMIT");

               namewarn.setVisible(false);                
               surnamewarn.setVisible(false);
               emailwarn.setVisible(false);
               passwordwarn.setVisible(false);
               
               
            if(o.validname(this.namefield.getText().trim()) &&  o.validname(this.surnamefield.getText().trim()) &&  o.validEmail(this.emailfield.getText().trim()) && o.validpassword(this.passwordfield.getText().trim())) {
                Accounts ac = new Accounts(this.namefield.getText(), this.surnamefield.getText(), this.emailfield.getText(), (int) this.agefield.getSelectedItem(), this.passwordfield.getText());

                if (o.Existed(ac, o)) {
                    emailexist.setVisible(true);
                } else {
                    emailguide.setVisible(false);
                    passwordguide.setVisible(false);

                    System.out.println(ac);
                    
                    DB.addAccountDB(ac);
                    o.addUsers(ac);
                    
                    this.setVisible(false);
                    
                    
                    
                    Menu cf = new Menu(o);
                    cf.setVisible(true);
                    
                }
            }

        }

        if (e.getSource() == this.exitBtn) {
            
            
            System.out.println("EXIT");
            this.setVisible(false);
            
            Menu cf = new Menu(o);
            cf.setVisible(true);

        }

    }

}
