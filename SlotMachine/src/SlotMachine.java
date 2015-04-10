/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lyubo
 */
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.Random;



public class SlotMachine extends JApplet{
    ImageIcon img1, img2, img3, img4, img5, img6;  
    AudioClip spin, win;
    static int counter, bankTokens, playerTokens;
    JButton play, quit;
    JLabel tokensLabel, playerLabel;
    SoftBevelBorder line;
    AnimationPanel game;
    Font tokensFont; 
    boolean spinning = false;
    
    
    //File audioFileSpin, audioFileWin;
   // AudioInputStream audioInSpin, audioInWin;
    //Clip spin, win;
    
    
   
    public SlotMachine(){
        //adding audio 
        Class metaObject = this.getClass();
        Class metaObject2 = this.getClass();        
        URL audio2 = metaObject.getResource("spin.wav");
        URL audio1 = metaObject.getResource("win.au");        
        spin = JApplet.newAudioClip(audio2);
        win = JApplet.newAudioClip(audio1);
        
        //setting up tokens
        tokensFont = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        bankTokens = 1000;
        playerTokens = 1000;
        JPanel score = new JPanel();
        tokensLabel = new JLabel("Bank: " + bankTokens );
        playerLabel = new JLabel("Player :" + playerTokens);
        score.setBackground(Color.MAGENTA);
        tokensLabel.setFont(tokensFont);
        playerLabel.setFont(tokensFont);
        
        score.setLayout(new GridLayout(1,2,10,10));
        score.add(tokensLabel);
        tokensLabel.setHorizontalAlignment(JLabel.CENTER);
        score.add(playerLabel);
        game = new AnimationPanel();
        game.setPreferredSize(new Dimension(320, 100));
        setLayout(new BorderLayout());
        
        //Load images 
        img1 = new ImageIcon( "img1.png");
        img2 = new ImageIcon( "img2.png");
        img3 = new ImageIcon( "img3.png");
        img4 = new ImageIcon( "img4.png");
        img5 = new ImageIcon( "img5.png");
        img6 = new ImageIcon( "img6.png");
        
        
            
        //play button to start the game
        play = new JButton("PLAY");
        //quit button
        quit = new JButton("QUIT");
        //handle quit button event
        quit.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_CANCEL_OPTION );
                if(i == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
        
        line = new SoftBevelBorder(SoftBevelBorder.LOWERED, Color.GREEN, Color.YELLOW);
        game.setBorder(line);
        
        
        
        
        add(game, BorderLayout.CENTER);
        add(score, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1,10, 10));
        buttonPanel.add(play);
        buttonPanel.add(quit);
        add(buttonPanel, BorderLayout.EAST);
        play.setVerticalAlignment(AbstractButton.CENTER);
        play.addActionListener(new MyButtonHandler());
        game.validate();
     
    }//end of slot machine constructor

     class AnimationPanel extends JPanel{
        int rand1, rand2, rand3, nextRand1, nextRand2, nextRand3, yCoordinate, yCoordinate2; 
        ArrayList<Image> arr1;
        Timer t;
        Random random;
     
       
        
        public AnimationPanel(){
            t = new Timer(10, new TimerListener());
            random = new Random();   
            rand1 = getNext();
            rand2 = getNext();
            rand3 = getNext();
            nextRand1 = getNext();
            nextRand2 = getNext();
            nextRand3 = getNext();
            
            
        }
        
        public void play(){
           
           
           yCoordinate = getHeight()/2-50;
           yCoordinate2 = getHeight()/2-150;      
           counter = 0;
           t.start();
           spin.loop();
           spinning = true;
           yCoordinate +=5;
            
        }
        public int getNext(){
            int next = random.nextInt(6);
            return next;
        }
       
        
  
    
    @Override
    public void paintComponent(Graphics g){        
        
        super.paintComponent(g);            
                       
     
         if(!spinning){
         g.drawImage(getImage(rand1), getWidth()/2 -150, getHeight()/2-50, 100, 100, this);
        g.drawImage(getImage(rand2), getWidth()/2 - 50, getHeight()/2-50, 100, 100, this);
        g.drawImage(getImage(rand3), getWidth()/2+50, getHeight()/2-50, 100, 100, this);
         }   
         
        if(spinning){
        g.drawImage(getImage(rand1), getWidth()/2 -150, yCoordinate, 100, 100, this);
        g.drawImage(getImage(rand2), getWidth()/2 - 50, yCoordinate, 100, 100, this);
        g.drawImage(getImage(rand3), getWidth()/2+50, yCoordinate, 100, 100, this);
        
        
        if(yCoordinate != getHeight()/2-50){
        g.drawImage(getImage(nextRand1), getWidth()/2 -150, yCoordinate2 , 100, 100, this);
        g.drawImage(getImage(nextRand2), getWidth()/2 - 50, yCoordinate2 , 100, 100, this);
        g.drawImage(getImage(nextRand3), getWidth()/2+50, yCoordinate2 , 100, 100, this);
        yCoordinate2 +=5;
        yCoordinate +=5;
        }
        
        if(yCoordinate > 200){
            
        counter ++;
        rand1 = nextRand1;
        rand2 = nextRand2;
        rand3 = nextRand3;
        
        nextRand1 = getNext();
        nextRand2 = getNext();
        nextRand3 = getNext();
        yCoordinate = yCoordinate2;
        yCoordinate2 = getHeight()/2-150;
        }
        if(counter > 11){
        t.stop();
        spin.stop();
        spinning = false;        
        repaint();
        if(rand1 == rand2 && rand2==rand3)
            win();
        else
            lose();
        }
        }//and of if playing
    }
     
    
    public Image getImage(int i){
        
        ArrayList<Image> img = new ArrayList<>();
        img.add(img1.getImage());
        img.add(img2.getImage());
        img.add(img3.getImage());
        img.add(img4.getImage());
        img.add(img5.getImage());
        img.add(img6.getImage());
        
       
        return img.get(i);
    }
    
    public void win(){
        
        win.play();
        playerTokens += bankTokens;   
        bankTokens = 1000;
        tokensLabel.setText("Bank: " + bankTokens );
        playerLabel.setText("Player: " + playerTokens);
        spinning = false;
        //use swingutilities to display joptionpane to avoid bugs
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                JOptionPane.showMessageDialog(null, "Congratulations, you won!");
            }
        });
    }
    
    public void lose(){
        spinning = false;
        System.out.println("You lose");
        bankTokens++;
        playerTokens--;
        tokensLabel.setText("Bank: " + bankTokens);
        playerLabel.setText("Player: " + playerTokens);
        
        if(playerTokens ==0){
            int option = JOptionPane.showConfirmDialog(this, "YOU LOST ALL YOUR MONEY! PLAY AGAIN?", "GAME OVER!!!", 
                    JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
            
            if(option == JOptionPane.YES_OPTION){
                bankTokens = 1000;
                playerTokens = 1000;
                tokensLabel.setText("Bank: " + bankTokens);
                playerLabel.setText("Player: " + playerTokens);
                
                
            }
            if(option == JOptionPane.NO_OPTION)
                System.exit(0);
        } 
    }
    class TimerListener implements ActionListener{
        
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
    }//end of actionlistener
    

       
                        
 
}//end of AnimationPanel
    
 class MyButtonHandler implements ActionListener{
    @Override 
    public void actionPerformed(ActionEvent e){
         if(!spinning)
             game.play();
    }
}
}//end of slotMahine class

