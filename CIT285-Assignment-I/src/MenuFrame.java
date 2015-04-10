/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lyubo
 */

//import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MenuFrame extends JFrame implements ActionListener, MenuListener{
    JMenuItem matriculated,
            nonMatriculated,
            quit,
            fullTime,
            partTime,
            nonCredit,
            receivables,
            classSchedule;
    JFrame myMatriculatedFrame, myNonMatriculatedFrame,
            myFullTimeFrame, myPartTimeFrame, myNonCreditFrame, receivablesFrame, scheduleFrame;
    
    public MenuFrame(){
         addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
           int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
           if(response == JOptionPane.YES_OPTION)
               //setDefaultCloseOperation(DISPOSE_ON_CLOSE);//yes
               System.exit(1);
           else
               setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
        });
   
        JMenuBar mbar = new JMenuBar();
        setJMenuBar(mbar);
        JMenu admissionsMenu = new JMenu("Admissions");
        admissionsMenu.addMenuListener(this);
        JMenu registration = new JMenu("Registration");
        JMenu reports = new JMenu("Reports");
        
        matriculated = new JMenuItem("Matriculated");
        matriculated.addActionListener(this);
        nonMatriculated = new JMenuItem("Non-Matriculated");
        nonMatriculated.addActionListener(this);
        quit = new JMenuItem("Quit");
        
        quit.addActionListener(this);
        
        admissionsMenu.add(matriculated);
        admissionsMenu.add(nonMatriculated);
        admissionsMenu.add(quit);
        mbar.add(admissionsMenu);
        mbar.add(makeMenu(registration, new Object[]{new JMenuItem("Full-time"),
                                                     new JMenuItem("Part-time"),
                                                    new JMenuItem("Non-credit")}, this));
        mbar.add(makeMenu(reports, new Object[]{new JMenuItem("Receivables"), "Class Schedule"}, this));
       try{
        myMatriculatedFrame = new JFrame("MATRICULATED STUDENTS REGISTRATION");
        myMatriculatedFrame.add(new MatrPanel());
        myMatriculatedFrame.setSize(650, 300);
        myMatriculatedFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
        });
        myNonMatriculatedFrame = new JFrame("NON-MATRICULATED STUDENTS REGISTRATION");
        myNonMatriculatedFrame.add(new NonMatrPanel());
        myNonMatriculatedFrame.setSize(650, 300);
        myFullTimeFrame = new JFrame("FULL TIME REGISTRATION");
        myFullTimeFrame.add(new FullTimePanel());
        myFullTimeFrame.setSize(650, 200);
        myPartTimeFrame = new JFrame("PART TIME REGISTRATION");
        myPartTimeFrame.add(new PartTimePanel());
        myPartTimeFrame.setSize(650, 200);
        myNonCreditFrame = new JFrame("NON-CREDIT REGISTRATION");
        myNonCreditFrame.add(new NonCreditPanel());
        myNonCreditFrame.setSize(650, 200);
         receivablesFrame = new JFrame("RECEIVABLES");
        receivablesFrame.add(new Receivables());
        receivablesFrame.setSize(650, 400);
        scheduleFrame = new JFrame("SCHEDULE");
        scheduleFrame.add(new Schedule());
        scheduleFrame.setSize(650, 400);
       }
       catch(Exception e){}
    }//end of constructor
    
   
    @Override
    public void actionPerformed(ActionEvent evt){
        String arg = evt.getActionCommand();
        System.out.println(arg);
        if(evt.getSource()==quit){
            int response = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Quit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(response == JOptionPane.YES_OPTION)
                System.exit(0); 
        }
        
        
        else if(arg.equals("Matriculated"))
            myMatriculatedFrame.setVisible(true);
        else if(evt.getSource()==nonMatriculated)
            myNonMatriculatedFrame.setVisible(true);
        else if(arg.equals("Full-time"))
            myFullTimeFrame.setVisible(true);
        else if(arg.equals("Part-time"))
            myPartTimeFrame.setVisible(true);
        else if(arg.equals("Non-credit"))
            myNonCreditFrame.setVisible(true);
         else if(arg.equals("Receivables"))
            receivablesFrame.setVisible(true);
         else if(arg.equals("Class Schedule"))
             scheduleFrame.setVisible(true);
            
        
    }

    @Override
    public void menuSelected(MenuEvent e) {
        
        
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static JMenu makeMenu(Object parent, Object[] items, Object target){
        JMenu m = null;
        if(parent instanceof JMenu)
            m=(JMenu)parent;
        else if(parent instanceof String)
            m = new JMenu((String)parent);
        else return null;
        
        for(int i = 0; i < items.length; i++){
            if(items[i]==null)
                m.addSeparator();
            else
                m.add(makeMenuItem(items[i], target));
        }
        
        return m;
    }
    
    public static JMenuItem makeMenuItem(Object item, Object target){
        JMenuItem r = null;
        if(item instanceof String)
            r = new JMenuItem((String)item);
        else if(item instanceof JMenuItem)
            r = (JMenuItem)item;
        else return null;
        
        if(target instanceof ActionListener)
            r.addActionListener((ActionListener)target);
        return r;
    }
    
    public static void main(String[] args){
        final MenuFrame frame = new MenuFrame();
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
}
