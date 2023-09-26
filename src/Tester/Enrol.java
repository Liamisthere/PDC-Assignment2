package Tester;

import Tester.Panel;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;


public class Enrol extends JFrame implements ActionListener{
    public JButton submitBtn;
    public JButton exitBtn;
    public boolean quit;
    public JTextField namefield;
    public JTextField surnamefield;
    public JTextField agefield;    
    public JTextField emailfield;
    public JTextField passwordfield;

    
    public JLabel namewarn;
    public JLabel surnamewarn;
    public JLabel agewarn;
    public JLabel emailwarn;
    public JLabel passwordwarn;


    
    
    public Enrol()
    {
     initComponents();
     intitPanels();
     initActionListener();
    }
    
    
    public void initComponents(){  

    quit = false; 
    namefield = new JTextField(20);
    surnamefield = new JTextField(20);
    agefield = new JTextField(20);
    emailfield = new JTextField(20);
    passwordfield = new JTextField(20);
    
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
     JLabel explainLabel = new JLabel("Please fill in the following fileds");
     northPanel.add(explainLabel);    
     this.add(northPanel, BorderLayout.NORTH);
     
     
    // Center Panel 
     Panel centerPanel =  new Panel();
     
     JLabel name = new JLabel("Name:");
     namewarn = new JLabel("Name field is empty!");
     namewarn.setVisible(false);
     centerPanel.add(name);
     centerPanel.add(namefield);
     
     JLabel surname = new JLabel("\nSurame:");
     surnamewarn = new JLabel("Surname field is empty!");
     surnamewarn.setVisible(false);
     centerPanel.add(surname);
     centerPanel.add(surnamefield);
     
     JLabel age = new JLabel("Age:");
     agewarn = new JLabel("Age field is empty!");
     agewarn.setVisible(false);
     centerPanel.add(age);
     centerPanel.add(agefield);
     
    JLabel email = new JLabel("Email");
     emailwarn = new JLabel("Email field is empty!");
     emailwarn.setVisible(false);
     centerPanel.add(email);
     centerPanel.add(emailfield);
     
     JLabel password = new JLabel("Password");
     passwordwarn = new JLabel("Password field is empty!");
     passwordwarn.setVisible(false);
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
        
        
      if(e.getSource() == this.submitBtn && this.namefield.getText().isEmpty())
     {
         System.out.println("Name empty");
         namefield.setVisible(true);
     
     }     
        
     if(e.getSource() == this.submitBtn && this.surnamefield.getText().isEmpty())
     {
         System.out.println("Surname empty");
         surnamewarn.setVisible(true);
     
     }
     
     if(e.getSource() == this.submitBtn && this.agefield.getText().isEmpty())
     {
         System.out.println("Age empty");
         agewarn.setVisible(true);
     
     }
      
        
     if(e.getSource() == this.submitBtn && this.emailfield.getText().isEmpty())
     {
         System.out.println("Email empty");
         emailwarn.setVisible(true);
     
     }  
     
     if(e.getSource() == this.submitBtn && this.passwordfield.getText().isEmpty())
     {
         System.out.println("Password empty");
         passwordwarn.setVisible(false);
     
     }    
        
     
     
        
     if(e.getSource() == this.submitBtn && this.namefield.getText().length() > 0 && this.surnamefield.getText().length() >  0&& this.agefield.getText().length() > 0&&this.emailfield.getText().length() > 0 && this.passwordfield.getText().length() > 0)
     {
         System.out.println("SUBMIT");
     
     }  
      
     
     if(e.getSource() == this.exitBtn)
     {
         System.out.println("EXIT");
         this.setVisible(false);
        
     }
     
     
     
     
    }
    
}
