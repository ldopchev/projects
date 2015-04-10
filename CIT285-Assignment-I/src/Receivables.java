/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lyubo
 */
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.*;

public class Receivables extends MyPanel{
   
   JFormattedTextField ssN;  
   JTable table;
   JTextField totalOwed;
   MyTableModel tableModel;
   ResultSet myResultSet, totalResultSet; 
    public Receivables() throws ParseException{
        
       //setLayout(new BorderLayout());
       
        try{
            setLayout(new GridLayout(4,1,5,5));
       defaultReceivables = connection.createStatement();
       myResultSet = defaultReceivables.executeQuery("SELECT SSN, FIRST_NAME, LAST_NAME, COURSE_NUMBER, PRICE "
                + " FROM DEMOGRAPHICS JOIN FINANCIAL_INFORMATION USING(SSN);");
       tableModel = new MyTableModel(myResultSet);
       
      
       MaskFormatter mask = new MaskFormatter("###-##-####");
       ssN = new JFormattedTextField(mask);
       ssN.setFocusable(true);
       ssN.setColumns(11);
       JLabel ssn = new JLabel("Enter SS# to see result: ");
       JLabel total = new JLabel("Total owed: ");
       totalOwed = new JTextField();
       totalOwed.setEditable(false);
       
       JPanel northPanel = new JPanel(); 
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new GridLayout(2,1,5,5));
       
       JPanel southPanel = new JPanel();
       northPanel.add(ssn);
       northPanel.add(ssN);
       
       table = new JTable(tableModel);  
       
       centerPanel.add(total);
       centerPanel.add(totalOwed);
       
       JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    System.out.println("Refrash");
                    getReceivables.setString(1, ssN.getText());
                    
                    myResultSet = getReceivables.executeQuery();
                    tableModel.setQuery(myResultSet);
                    
                    getTotal.setString(1, ssN.getText());
                    totalResultSet = getTotal.executeQuery();
                    totalResultSet.next();
                    totalOwed.setText(String.valueOf(totalResultSet.getInt(1)));
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
    
   
   /* public void actionPerformed(ActionEvent e){
        if(e.getSource() == submitButton){
            
        }
    }*/
}
