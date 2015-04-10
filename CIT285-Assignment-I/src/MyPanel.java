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
import java.sql.*;


public class MyPanel extends JPanel {
    protected String states[] = {"AL", "AK","AZ","AR","CA", "DC", "CO","CT","DE","FL","GA","GU","HI","ID","IL","IN",
           "IA","KS","KY","LA","MA","MD","MS","MI","MN","MI","MO","MT","NE","NV","NH","NJ","NM","NY","NC",
           "ND","OH","OK","OR","PA","PR","RI","SC","SD","TN","TX","UT","VT","VI","VA","WA","WV","WI","WY"};
    
    protected Courses courses[] = {new Courses("CMP100", "Introduction to Computers", "M 6:30-8:45 D211"), new Courses("OIM220", "Keybording I", "TU 5:00-6:15 B101"),
                   new Courses("ENG111", "English I", "W   8:30 - 11:45 C202"), new Courses("CMP545", "Web Programming", "F 6:30-9:15 D117")};
    
    protected Courses ncCourses[] = {new Courses("NC100", "Basic Cookie Baking", "M 6:00-8:00 E415"), 
                    new Courses("NC200", "Advanced Tire Inflation", "W 6:00 - 8:00 D100"), 
                                     new Courses("NC300", "Intro to Simusitis", "W 6:00 - 8:00 B345"),  new Courses("NC400", "Shoe Polish and You", "TU 6:15 - 8:15 C202"),  
                                     new Courses("NC500", "Gout for Fun and Profit", "F 7:00 - 9:00 A300")};
    
 //String array for all the credit courses
      String[] c = new String[courses.length];
 //string array for non-credit courses
      String[] nc = new String[ncCourses.length];
      
    public static final String URL = "jdbc:mysql://localhost:3306/test";
    public static final String username = "root";
    public static final String password = "azsumlud";
    public static Connection connection;
    protected static PreparedStatement insertStudent;
    protected static PreparedStatement insertCourses;
    protected static PreparedStatement insertFinancial;
    protected static PreparedStatement getReceivables;
    protected static PreparedStatement getTotal;
    protected static PreparedStatement getSchedule;
    protected static Statement defaultReceivables;
    protected static Statement defaultSchedule;
    public static Statement tryStatement;
    public static ResultSet myResultSet;
    public static ResultSet scheduleSet;
    
    public MyPanel(){
        for(int i = 0; i < courses.length; i++)
        {
            c[i] = courses[i].getNumber() + " " + courses[i].getName();
        }
        
         for(int i = 0; i < ncCourses.length; i++)
        {
            nc[i] = ncCourses[i].getNumber() + " " + ncCourses[i].getName();
        }
        
        try{
            connection = DriverManager.getConnection(URL, username, password);
            tryStatement = connection.createStatement();
            tryStatement.executeUpdate("CREATE TABLE IF NOT EXISTS DEMOGRAPHICS "
                    + "(SSN VARCHAR(11) PRIMARY KEY, FIRST_NAME VARCHAR(15), LAST_NAME VARCHAR(15), MIDDLE_I CHAR(1), ADDRESS VARCHAR (60), CITY VARCHAR(15), STATE"
                    + " VARCHAR(3), ZIP VARCHAR(10), DATE VARCHAR(10), YEAR VARCHAR(10), DEGREE VARCHAR(50));");
            
            tryStatement.executeUpdate("CREATE TABLE IF NOT EXISTS COURSES "
                    + "(SSN VARCHAR(11), COURSE_NUMBER VARCHAR(6), COURSE_NAME VARCHAR(40), COURSE_INFO VARCHAR(30));");
            
             tryStatement.executeUpdate("CREATE TABLE IF NOT EXISTS FINANCIAL_INFORMATION "
                     + "(SSN VARCHAR(11), COURSE_NUMBER VARCHAR(6), PRICE INT); ");
             
             insertStudent = connection.prepareStatement("INSERT INTO DEMOGRAPHICS (SSN, FIRST_NAME, LAST_NAME, MIDDLE_I, ADDRESS, CITY, "
                     + "STATE, ZIP, DATE, YEAR, DEGREE) VALUES(?,?,?,?,?,?,?,?,?,?,?);");
             
             insertCourses = connection.prepareStatement("INSERT INTO COURSES (SSN, COURSE_NUMBER, COURSE_NAME, COURSE_INFO) "
                     + "VALUES (?,?,?,?);");
             
             insertFinancial = connection.prepareStatement("INSERT INTO FINANCIAL_INFORMATION (SSN, COURSE_NUMBER, PRICE) "
                     + "VALUES (?,?,?);");
             getReceivables = connection.prepareStatement("SELECT SSN, FIRST_NAME, LAST_NAME, COURSE_NUMBER, PRICE "
                + " FROM DEMOGRAPHICS JOIN FINANCIAL_INFORMATION USING(SSN) "
            + "WHERE SSN = ?;");
             
             getTotal = connection.prepareStatement("SELECT SUM(PRICE) + 5 FROM FINANCIAL_INFORMATION WHERE SSN = ?;");
             
             getSchedule = connection.prepareStatement("SELECT SSN, FIRST_NAME, LAST_NAME, YEAR, COURSE_NUMBER, COURSE_NAME, COURSE_INFO "
                     + " FROM DEMOGRAPHICS JOIN COURSES USING (SSN) "
                     + "WHERE SSN = ?;");
             
             //getSSN = connection.prepareStatement("SELECT * FROM DEMOGRAPHICS WHERE FIRST_NAME = ? AND LAST_NAME = ?;");
        }
        catch(SQLException e){
            e.printStackTrace();
            close();
        }
       
       /* for(int i = 0; i < nonCredit.length; i++)
        {
            nonC[i] = nonCredit[i].getNumber() + " " + nonCredit[i].getName();
        }*/
    }
    public void close(){
        try{
            connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
  
}
