package Tester;

import Tester.Access;
import Tester.Panel;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;





public class login extends JFrame implements ActionListener{
    Access a;
    
    public JButton submitBtn;
    public JButton exitBtn;
    
    public JLabel emailwarn;
    public JLabel passwordwarn;
    
    public JTextField passwordfield;
    public JTextField emailfield;    
  
    
    public login()
    {
     initComponents();
     intitPanels();
     initActionListener();
    }
    
    
    public void initComponents(){  

     
    passwordfield = new JTextField(20);
    emailfield = new JTextField(20);
    
     submitBtn = new JButton("Submit");
     exitBtn = new JButton("Exit");
    
     this.setSize(400, 400);
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
     Panel centerPanel =  new Panel();  
     
     JLabel email = new JLabel("Email"); 
     emailwarn = new JLabel("Email field is empty!");
     emailwarn.setVisible(false);
    
     centerPanel.add(emailwarn);
     centerPanel.add(email);
     centerPanel.add(emailfield);
     
     
     passwordwarn = new JLabel("Password field is empty!");
     passwordwarn.setVisible(false);
     
     
     JLabel password = new JLabel("Password");
     centerPanel.add(passwordwarn);
     centerPanel.add(password);
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
     
     }  
     
     if(e.getSource() == this.submitBtn && this.passwordfield.getText().isEmpty())
     {
         System.out.println("Password empty");
         passwordwarn.setVisible(true);
     
     }
     
     if(e.getSource() == this.submitBtn && this.emailfield.getText().length() > 0 && this.passwordfield.getText().length() > 0)
     {
         
         System.out.println("SUBMIT");

         
         String email = this.emailfield.getText();
         String password = this.passwordfield.getText();
         
         
         System.out.println(email);
         System.out.println(password);
         
         User_page pg = new User_page();
         
         pg.setVisible(true);
         
         
         
     }
      
     
     
     
     if(e.getSource() == this.exitBtn)
     {
         System.out.println("EXIT");
         
         
        // System.exit(0);
     }
     
     
     
     
    }
    
}
