package Tester;

import Tester.Panel;
import java.awt.Font;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;


public class Menu extends JFrame implements ActionListener{
    public JButton enrolBtn;
    public JButton loginBtn;
    public JTextField textfield;
    public JLabel textLabel;
    public JComboBox sizeCombobox;
    public JComboBox<String> fontCombobox;
    
    public String font;
    public String size;
    
    public Accountstore a;
    
    Menu(Accountstore a)
    {
        
        this.a = a;
        initComponents();
        intitPanels();
        initActionListener();
    }
    
    
    public void initComponents(){ 
        
     enrolBtn = new JButton("Enrol");
     loginBtn = new JButton("Login");
     
     textfield = new JTextField(20);
     

     
     this.setSize(400, 400);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setLocationRelativeTo(null);
    }
     
   public void intitPanels()
   {  
       
    //North Panel
     JPanel northPanel = new JPanel();
     JLabel explainLabel = new JLabel("Please select the following buttons");
     northPanel.add(explainLabel);    
     this.add(northPanel, BorderLayout.NORTH);
     
     
    // Center Panel 
     Panel centerPanel =  new Panel();
     centerPanel.add(loginBtn);
     centerPanel.add(enrolBtn);
     this.add(centerPanel, BorderLayout.CENTER);
     
     //South panel
     JPanel southPanel = new JPanel();
     JLabel prop = new JLabel("Created by Liam Naidoo");
     southPanel.add(prop);
     this.add(southPanel, BorderLayout.SOUTH);
   }
   
   
   
   
   public void initActionListener(){
     this.enrolBtn.addActionListener(this);
     this.loginBtn.addActionListener(this);
   }        
   
   
   @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource() == this.loginBtn)
     {
         System.out.println("LOGIN");
         
        System.out.println("ENROL");
         
                
        login log = new login(a);
        log.setVisible(true);   

        
     
     }  
     
     if(e.getSource() == this.enrolBtn)
     {
         System.out.println("ENROL");
         
         Enrol e1 = new Enrol(a);
         e1.setVisible(true);
         
         
     }
     
     
    }
       
    
}
