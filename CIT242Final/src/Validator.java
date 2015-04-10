
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Validator {

    public static String getLine(Scanner sc, String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine();        // read the whole line
        return s;
    }

    public static String getString(Scanner sc, String prompt) {
        System.out.print(prompt);
        String s = sc.next();        // read the first string on the line
        sc.nextLine();               // discard the rest of the line
        return s;
    }

    public static int getInt(Scanner sc, String prompt) {
        boolean isValid = false;
        int i = 0;
        while (isValid == false) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else {
                System.out.println("Error! Invalid integer value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return i;
    }
    
    //overloaded method GUI
    public static int getInt(JTextField field) {
        boolean isValid = false;
        int i = 0;
        String sc = field.getText();
        while (isValid == false) {
            
            if (!sc.isEmpty()) {
                try{
                i = Integer.parseInt(sc);
                isValid = true;
                }
                catch(NumberFormatException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Invalid Integer. Try again.");
                    sc = JOptionPane.showInputDialog(null, "Enter a value: ");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Integer. Try again.");
                sc = JOptionPane.showInputDialog(null, "Enter a value: ");
                    
            }
            
        }
        return i;
    }

    public static int getInt(Scanner sc, String prompt,
            int min, int max) {
        int i = 0;
        boolean isValid = false;
        while (isValid == false) {
            i = getInt(sc, prompt);
            if (i <= min) {
                System.out.println(
                        "Error! Number must be greater than " + min);
            } else if (i >= max) {
                System.out.println(
                        "Error! Number must be less than " + max);
            } else {
                isValid = true;
            }
        }
        return i;
    }

    public static double getDouble(Scanner sc, String prompt) {
        boolean isValid = false;
        double d = 0;
        while (isValid == false) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                d = sc.nextDouble();
                isValid = true;
            } else {
                
                System.out.println("Error! Invalid decimal value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return d;
    }
    
    //Overloaded method for GUI
    public static double getDouble(JTextField field) {
        boolean isValid = false;
        double d = 0;
        String sc = field.getText();
        while (isValid == false) {
            
            if (!sc.isEmpty()) {
                try{
                d = Double.parseDouble(sc);
                isValid = true;
                }
                catch(NumberFormatException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Invalid double value. Try again.");
                    sc = JOptionPane.showInputDialog(null, "Enter a value: ");
                    
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid double value. Try again.");
                    sc = JOptionPane.showInputDialog(null, "Enter a value: ");
                    
            }
            
        }
        return d;
    }

    public static double getDouble(Scanner sc, String prompt,
            double min, double max) {
        double d = 0;
        boolean isValid = false;
        while (isValid == false) {
            d = getDouble(sc, prompt);
            if (d <= min) {
                System.out.println(
                        "Error! Number must be greater than " + min);
            } else if (d >= max) {
                System.out.println(
                        "Error! Number must be less than " + max);
            } else {
                isValid = true;
            }
        }
        return d;
    }

    public static char getChar(Scanner sc, String prompt) {
        char c = ' ';
        boolean isValid = false;
        while (isValid == false) {
            System.out.print(prompt);

            if (sc.hasNext()) {
                c = sc.findInLine(".").charAt(0);
                if (c == 'y' || c == 'n') {
                    isValid = true;
                } else {
                    System.out.println("Error! Invalid decimal value. Try again.");
                }
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return c;
    }
    
    //Overloaded method for gui
     public static char getChar(JTextField field) {
        char c = ' ';
        boolean isValid = false;
        String sc = field.getText().toLowerCase();
        while (isValid == false) {
            
                
            if (!sc.isEmpty()) {
                c =sc.charAt(0);
                
                if (c == 'y' || c == 'n') {
                    isValid = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid character for Discount. y or n");
                    sc = JOptionPane.showInputDialog(null, "Enter a character y ot n");
                    
                    
                }
            }
           
        }
        return c;
    }
    
}