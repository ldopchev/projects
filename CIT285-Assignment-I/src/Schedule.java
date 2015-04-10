/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lyubo
 */


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
public class Schedule extends MyPanel{
    
    JFormattedTextField ssN;  
   JTable table;
   JTextField totalOwed;
   MyTableModel tableModel;
   
    public Schedule() throws ParseException{
        
       //setLayout(new BorderLayout());
       
        try{
            setLayout(new GridLayout(4,1,5,5));
       defaultSchedule = connection.createStatement();
       scheduleSet = defaultSchedule.executeQuery("SELECT SSN, FIRST_NAME, LAST_NAME, YEAR, COURSE_NUMBER, COURSE_NAME, COURSE_INFO "
                     + " FROM DEMOGRAPHICS JOIN COURSES USING (SSN);");
       tableModel = new MyTableModel(scheduleSet);
       
      
       MaskFormatter mask = new MaskFormatter("###-##-####");
       ssN = new JFormattedTextField(mask);
       ssN.setFocusable(true);
       ssN.setColumns(11);
       JLabel ssn = new JLabel("Enter SS# to see result: ");
       JLabel total = new JLabel("Total owed: ");
      
       
       JPanel northPanel = new JPanel(); 
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new GridLayout(2,1,5,5));
       
       JPanel southPanel = new JPanel();
       northPanel.add(ssn);
       northPanel.add(ssN);
       
       table = new JTable(tableModel);     
             
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    System.out.println("Refrash");
                    getSchedule.setString(1, ssN.getText());
                    
                    scheduleSet = getSchedule.executeQuery();
                    tableModel.setQuery(scheduleSet);
                    
                    
                    
                } 
                catch (SQLException ex) {
                   close();
                }
            }
        });
        
        southPanel.add(refresh);
        add(northPanel);
        add(new JScrollPane(table));
        add(centerPanel);
        add(southPanel);
        
        }
        
        catch (SQLException ex) {
            
        }
        
    
}
}
