

import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lyubo
 */
public class SlotTest {
    public static void main(String args[]){
        JFrame frame = new JFrame("SLOT");
        frame.getContentPane().add(new SlotMachine());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
        frame.setSize(400, 300);
    }
}
