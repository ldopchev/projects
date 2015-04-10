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
import javax.swing.text.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class MatrPanel extends MyPanel implements ActionListener{
   JTextField firstName, lastName, mInitial, address, city, zip ;
   JButton submitButton, clearButton;
   JComboBox state, year, degree;
   JCheckBox diploma, imRecord;
   JFormattedTextField ssN, date;
   
    public MatrPanel() throws Exception{  
       
       MaskFormatter mask = new MaskFormatter("###-##-####");
       ssN = new JFormattedTextField(mask);
       ssN.setFocusable(true);       
       firstName = new JTextField("",15);
       lastName = new JTextField("",15);      
       mInitial = new JTextField("",1);       
       address = new JTextField("",50);       
       city = new JTextField("",15);
       state = new JComboBox(states);      
       zip = new JTextField("",11);
       DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
       date = new JFormattedTextField(format);
       date.setValue(new Date(System.currentTimeMillis()));
       year = new JComboBox(new Object[]{"Freshman", "Sophmore", "Junior", "Senior"});
       degree = new JComboBox(new Object[]{"Associate of Science in Computer Programming",
                                                     "Associate of Arts in Humanities"});
       diploma = new JCheckBox("High school Diploma");
       imRecord = new JCheckBox("Immunization Record");
       submitButton = new JButton("Submit");
       clearButton = new JButton("Clear");
       submitButton.addActionListener(this);
       clearButton.addActionListener(this);
     
       setLayout(new GridLayout(7,1,5,5));
       
       add(makePanel(new Object[]{"SS#:", ssN, "First Name:", firstName}));
       add(makePanel(new Object[]{"Last Name:", lastName, "Middle Initial", mInitial}));
       add(makePanel(new Object[]{"Address:", address, "City:", city}));
       add(makePanel(new Object[]{"State", state, "Zip", zip}));
       add(makePanel(new Object[]{"Date:", date, "Year:", year}));
       add(makePanel(new Object[]{"Degree:", degree,diploma, imRecord }));
       
       JPanel buttonPanel = new JPanel(new FlowLayout());
       buttonPanel.add(submitButton);
       buttonPanel.add(clearButton);
       add(buttonPanel);
     
       
    }
    @Override 
    public void actionPerformed(ActionEvent e) throws NullPointerException{
        if(e.getSource()==submitButton){
            System.out.println(firstName.getText() + " state: " + state.getSelectedItem() + " diploma " + diploma.isSelected()+ ssN.getText() +" "  + year.getSelectedItem());
            if(!diploma.isSelected() || !imRecord.isSelected())
                JOptionPane.showMessageDialog(null, "Registration Not Allowed!", "error", JOptionPane.ERROR_MESSAGE);
            
            if(ssN.getText().equals("   -  -    ")){
                JOptionPane.showMessageDialog(null, "Please enter SS#", "Error", JOptionPane.ERROR_MESSAGE);
                throw new NullPointerException();
                }
            
            else
                try{
                    
                    insertStudent.setString(1, ssN.getText());                    
                    insertStudent.setString(2, firstName.getText());
                    insertStudent.setString(3, lastName.getText());
                    insertStudent.setString(4, mInitial.getText());
                    insertStudent.setString(5, address.getText());
                    insertStudent.setString(6, city.getText());
                    insertStudent.setString(7, (String)state.getSelectedItem());
                    insertStudent.setString(8, zip.getText());
                    insertStudent.setString(9, date.getText());
                    insertStudent.setString(10, (String)year.getSelectedItem());
                    insertStudent.setString(11, (String)degree.getSelectedItem());
                    
                    insertStudent.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data uploaded successfully!");
                }
            catch(SQLException sql){
                sql.printStackTrace();
                close();
            }
            catch(NullPointerException exc){
                exc.printStackTrace();
            }
              
        }
        
        else if(e.getSource()==clearButton){
            ssN.setText("");
            firstName.setText("");
            lastName.setText("");
            mInitial.setText("");
            address.setText("");
            city.setText("");
            state.setSelectedIndex(0);
            zip.setText("");
            imRecord.setSelected(false);
            diploma.setSelected(false);
            
        }
    }
    
    public static JPanel makePanel(Object items[]){
        JPanel p = new JPanel();
        for(int i = 0; i < items.length; i++){
            if(items[i] instanceof JLabel )
                p.add((JLabel)items[i]);
            else if(items[i] instanceof JTextField)
                p.add((JTextField)items[i]);
            else if(items[i] instanceof JComboBox)
                p.add((JComboBox)items[i]);
            else if(items[i] instanceof JCheckBox)
                p.add((JCheckBox)items[i]);
            else if(items[i] instanceof String)
                p.add(new JLabel((String)items[i], JLabel.RIGHT));
            else
                return null;
        }
        p.setLayout(new GridLayout(1,4,5,5));
        return p;
    }
    
    
}
