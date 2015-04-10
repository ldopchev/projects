/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lyubo
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.sql.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class FullTimePanel extends MyPanel implements ActionListener, ListSelectionListener{
       JTextField ssN, numberOfCredits, cost;
       JList coursesList;
       JButton submitButton, clearButton;
       Object[] values;
       int creditCounter, price, total;
    public FullTimePanel() throws ParseException{
       setLayout(new BorderLayout());
       JPanel infoPanel = new JPanel();
       infoPanel.setLayout(new GridLayout(2,4,5,5));
       JPanel listPanel = new JPanel();
       
       
       MaskFormatter mask = new MaskFormatter("###-##-####");
       ssN = new JFormattedTextField(mask);
       ssN.setFocusable(true);
       numberOfCredits = new JTextField(2);
       numberOfCredits.setEditable(false);
       cost = new JTextField(3);
       cost.setEditable(false);
       coursesList = new JList(c);
       coursesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
       coursesList.setVisibleRowCount(3);
       submitButton = new JButton("Submit");
       clearButton = new JButton("Clear");
       submitButton.addActionListener(this);
       clearButton.addActionListener(this);
       coursesList.addListSelectionListener(this);
        
       infoPanel.add(MatrPanel.makePanel(new Object[]{ "SS#: ", ssN
       }));
       infoPanel.add(MatrPanel.makePanel(new Object[]{"Number of Credits:", numberOfCredits, "Cost", cost}));
       add(infoPanel, BorderLayout.NORTH);
       listPanel.add(new JLabel("Courses"));
       listPanel.add(new JScrollPane(coursesList));
       add(listPanel, BorderLayout.CENTER);
    
       JPanel buttonPanel = new JPanel(new FlowLayout());
       buttonPanel.add(submitButton);
       buttonPanel.add(clearButton);
       add(buttonPanel, BorderLayout.SOUTH);
       
       
    }
    @Override
    public void valueChanged(ListSelectionEvent e){
        values = coursesList.getSelectedValues();
        creditCounter = 0;
        price = 285;
        //total of the price + $5 registration fee
        total = 5;
        for(int i = 0; i<values.length; i++){
            creditCounter += 3;
             if(creditCounter > 9)
                            price = 265;
            total += price;
             
        }
         cost.setText(String.valueOf(total));
         numberOfCredits.setText(String.valueOf(creditCounter));
    }
    @Override
       public void actionPerformed(ActionEvent e){
        if(e.getSource()==submitButton){
            
            
            // show error message if only registering for 2 classes
            if(values.length < 3){
                clearEntry();
                JOptionPane.showMessageDialog(null, "Use Part Time Registration for less than 9 credits!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            
            else{    
                try{
                    creditCounter = 0;
                     price = 285;
                    for(int i = 0; i < values.length; i++){
                        creditCounter +=3;
                        insertCourses.setString(1, ssN.getText());                        
                        insertCourses.setString(2, courses[i].getNumber());
                        insertCourses.setString(3, courses[i].getName());
                        insertCourses.setString(4, courses[i].getInfo());
                      
                        insertCourses.executeUpdate();
                        
                        if(creditCounter > 9)
                            price = 265;
                        
                         insertFinancial.setString(1, ssN.getText());
                         insertFinancial.setString(2, courses[i].getNumber());
                         insertFinancial.setInt(3, price);
                 
                       
                        insertFinancial.executeUpdate();
                    }
                   JOptionPane.showMessageDialog(null, "Data uploaded successfully!");
                   clearEntry();
                }//end of try block
            catch(SQLException exc){
                    exc.printStackTrace();
                    close();
                    }
        }//end of else
        }//end of if...submitButton
        else if(e.getSource()==clearButton){
           clearEntry();
        }
    }
// clears all the entries in the window
    public void clearEntry(){
            ssN.setText("");            
            numberOfCredits.setText("");
            cost.setText("");
            coursesList.clearSelection();
    }
}
