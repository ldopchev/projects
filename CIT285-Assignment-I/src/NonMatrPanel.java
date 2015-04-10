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




public class NonMatrPanel extends MyPanel implements ActionListener{
   JTextField firstName, lastName, mInitial, address, city, zip ;
   JButton submitButton, clearButton;
   JComboBox state;
   JCheckBox imRecord;
   JFormattedTextField ssN;
   
    public NonMatrPanel() throws Exception{
       setLayout(new GridLayout(6,4,5,5));       
       MaskFormatter mask = new MaskFormatter("###-##-####");
       ssN = new JFormattedTextField(mask);       
       firstName = new JTextField(15);       
       lastName = new JTextField(15);       
       mInitial = new JTextField(1);       
       address = new JTextField(50);       
       city = new JTextField(15);
       state = new JComboBox(states);       
       zip = new JTextField(11);
       imRecord = new JCheckBox("Immunization Record");
       
       submitButton = new JButton("Submit");
       clearButton = new JButton("Clear");
       submitButton.addActionListener(this);
       clearButton.addActionListener(this);
       
       add(MatrPanel.makePanel(new Object[]{"SS#", ssN, "First Name:", firstName}));
       add(MatrPanel.makePanel(new Object[]{"Last Name:", lastName, "Middle Initial", mInitial}));
       add(MatrPanel.makePanel(new Object[]{"Address:", address, "City", city}));
       add(MatrPanel.makePanel(new Object[]{"State", state, "Zip:", zip}));
       JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       checkBoxPanel.add(imRecord);
       add(checkBoxPanel);
       
       
       JPanel buttonPanel = new JPanel(new FlowLayout());
       buttonPanel.add(submitButton);
       buttonPanel.add(clearButton);
       add(buttonPanel);
       
       
}
     @Override 
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==submitButton){
            System.out.println(firstName.getText() + " state: " + state.getSelectedItem() + " diploma " + ssN.getText() +" "  );
            /*if(!diploma.isSelected() || !imRecord.isSelected())
                JOptionPane.showMessageDialog(null, "Registration Not Allowed!", "error", JOptionPane.ERROR_MESSAGE);
            
            else*/
                try{
                    insertStudent.setString(1, ssN.getText());
                    insertStudent.setString(2, firstName.getText());
                    insertStudent.setString(3, lastName.getText());
                    insertStudent.setString(4, mInitial.getText());
                    insertStudent.setString(5, address.getText());
                    insertStudent.setString(6, city.getText());
                    insertStudent.setString(7, (String)state.getSelectedItem());
                    insertStudent.setString(8, zip.getText());                    
                    insertStudent.setString(9, null);
                    insertStudent.setString(10, "Non-Matr.");
                    insertStudent.setString(11, null);
                    insertStudent.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data uploaded successfully!");
                }
            catch(SQLException sql){
                sql.printStackTrace();
                close();
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
            
        }
    }
    
}
