/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lyubo
 */
import java.sql.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class MyTableModel extends AbstractTableModel {
    
    ResultSet defaultResultSet, resultSet;
    ResultSetMetaData metaData;
    int numberOfRows;
    
     public MyTableModel(ResultSet rs)throws SQLException{
         this.defaultResultSet = rs;
        setQuery(defaultResultSet);
    }
     
     public void setQuery(ResultSet rs)throws SQLException{
        resultSet = rs;
        metaData = rs.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        
        fireTableStructureChanged();
        
       
     }
     
     

    @Override
    public int getRowCount() {
        return numberOfRows;
    }

    @Override
    public int getColumnCount() {
        
        try {
            return  metaData.getColumnCount();
        } catch (SQLException ex) {
            
        }
        
        return 0;
    }
    
    public String getColumnName(int column){
        try {
            return metaData.getColumnName(column+1);
        } catch (SQLException ex) {
            
        }
        return "";
    }

    @Override
    public Object getValueAt(int row, int column) {
        try 
      {
         resultSet.absolute( row + 1 );
         return resultSet.getObject( column + 1 );
      } // end try
      catch ( SQLException sqlException ) 
      {
         sqlException.printStackTrace();
       
      } // end catch
      
      return ""; // if problems, return empty string object
    }
}
