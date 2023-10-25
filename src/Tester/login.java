package Tester;

import Tester.Access;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;






public class login extends JFrame implements ActionListener{
    Access access;
    
    public JButton submitBtn;
    public JButton exitBtn;
    
    public JLabel emailwarn;
    public JLabel passwordwarn;
    
    public JLabel notexistswarn;
    
    public JPanel Emailpanel;
    public JPanel Passwordpanel;
    
    public JPanel messagepanel;
    
    public JTextField passwordfield;
    public JTextField emailfield;    
    Accountstore accountants;
    
    public login(Accountstore a)
    {
     this.accountants = a;
     this.access = new Access(a);
     initComponents();
     intitPanels();
     initActionListener();
    }
    
    
    public void initComponents(){  

     
    passwordfield = new JTextField(20);
    emailfield = new JTextField(20);
    
     submitBtn = new JButton("Submit");
     exitBtn = new JButton("Exit");
    
     this.setSize(500, 400);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setLocationRelativeTo(null);
    }
     
   public void intitPanels()
   {  
       
    //North Panel
     JPanel northPanel = new JPanel();
     JLabel explainLabel = new JLabel("Please enter name and password");
     northPanel.add(explainLabel);    
     this.add(northPanel, BorderLayout.NORTH);
     
     
    // Center Panel 
     JPanel centerPanel =  new JPanel();  
     
     Emailpanel = new JPanel();
     Passwordpanel = new JPanel();
     
     messagepanel = new JPanel();
     
     centerPanel.add(Emailpanel);
     centerPanel.add(Passwordpanel);
     centerPanel.add(messagepanel);
     
     JLabel email = new JLabel("Email"); 
     emailwarn = new JLabel("Email field is empty!");
     emailwarn.setVisible(false);
    
     
     Emailpanel.add(email);
     Emailpanel.add(emailfield);
     Emailpanel.add(emailwarn);
     
     
     passwordwarn = new JLabel("Password field is empty!");
     passwordwarn.setVisible(false);
     
     
     JLabel password = new JLabel("Password");

     
     Passwordpanel.add(password);
     Passwordpanel.add(passwordfield);
     Passwordpanel.add(passwordwarn);
     
     
     notexistswarn = new JLabel("Invalid! Either email or password is incorrect");
     messagepanel.add(notexistswarn);
     notexistswarn.setVisible(false);
     
     this.add(centerPanel, BorderLayout.CENTER);
     
     //South panel
     JPanel southPanel = new JPanel();
     southPanel.add(submitBtn);
     southPanel.add(exitBtn);
     JLabel prop = new JLabel("Created by Liam Naidoo");
     southPanel.add(prop);
     this.add(southPanel, BorderLayout.SOUTH);
   }
   
   
   
   
   public void initActionListener(){
     this.emailfield.addActionListener(this);
     this.passwordfield.addActionListener(this);
     this.submitBtn.addActionListener(this);    
     this.exitBtn.addActionListener(this);
   }        
   
   
   @Override
    public void actionPerformed(ActionEvent e) {
     
        
     if(e.getSource() == this.exitBtn)
     {
         System.out.println("EXIT");
         this.setVisible(false);
        
     }
        
     if(e.getSource() == this.submitBtn && this.emailfield.getText().isEmpty())
     {
         System.out.println("Email empty");
         emailwarn.setVisible(true);
         this.notexistswarn.setVisible(false);
     
     }  
     
     if(e.getSource() == this.submitBtn && this.passwordfield.getText().isEmpty())
     {
         System.out.println("Password empty");
         passwordwarn.setVisible(true);
         this.notexistswarn.setVisible(false);
     }
     
     if(e.getSource() == this.submitBtn && this.emailfield.getText().trim().length() > 0 && this.passwordfield.getText().trim().length() > 0)
     {
         
         System.out.println("SUBMIT");
         this.emailwarn.setVisible(false);
         this.passwordwarn.setVisible(false);
         
         String email = this.emailfield.getText();
         String password = this.passwordfield.getText();
         
         
         System.out.println(email);
         System.out.println(password);
         
       
         boolean existed = access.Login(email, password);
         
         if(existed == true)
         {
            Accounts a = accountants.select_accounts(email, password);
            User_page pg = new User_page(a ,accountants);
            
            this.setVisible(false);
            pg.setVisible(true);
            System.out.println("existed");
         
         }
         
         else
         {
             this.notexistswarn.setVisible(true);
         
         }
         
         
         
         
     }
      
     
     
     
     if(e.getSource() == this.exitBtn)
     {
         System.out.println("EXIT");
         
        Menu cf = new Menu(accountants);
        cf.setVisible(true);
         
        this.setVisible(false);
     }
     
     
     
     
    }
    
}
