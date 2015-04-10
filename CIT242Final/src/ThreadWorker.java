

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lyubo
 */
public class ThreadWorker extends SwingWorker<Void, String>{

    Date date;
    SimpleDateFormat sdf;
    JLabel dateTime;
    public ThreadWorker(JLabel dateTime){
        sdf = new SimpleDateFormat("HH:mm:ss");
        this.dateTime = dateTime;
    }
    @Override
    protected Void doInBackground() throws Exception {
        while(true){
        Thread.sleep(1000);
        publish(update());
        }
       
    }
    
    @Override
     protected void process(List<String> chunks){
         dateTime.setText(update());
    }
    
public String update(){
    date = new Date();
    return sdf.format(date);
}    
}
