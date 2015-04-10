
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Formatter;
import java.util.Vector;

public class ProductMaintApp extends JPanel implements ProductConstants {

    // declare two class variables
    private static ProductDAO productDAO = null;
    private static Scanner sc = null;
    JButton list, add, del, update, exit;
    static CardLayout card;
    static JTextArea displayArea;
    static JPanel cardPanel;
    static ProductUI productPanel;
    static int iterator;
    static JFrame displayFrame;
    static ThreadWorker timer;
    public ProductMaintApp(){
        //update iterator to 0 when reading the first subscript from the arraylist
        iterator = -1;
        
        JButton list = new JButton("List");
        JButton add = new JButton("Add");
        JButton del = new JButton("Delete");
        JButton update = new JButton("Update");
        JButton clear = new JButton("Clear");
        
        list.addActionListener(new ButtonListener());
        add.addActionListener(new ButtonListener());
        del.addActionListener(new ButtonListener());
        update.addActionListener(new ButtonListener());
        clear.addActionListener(new ButtonListener());
        
        setLayout(new BorderLayout());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1,5,5));
        buttonPanel.add(list);
        buttonPanel.add(add);
        buttonPanel.add(del);
        buttonPanel.add(update);
        buttonPanel.add(clear);
        
        add(buttonPanel, BorderLayout.EAST);
        
        //adding the timer
        JLabel time = new JLabel("");
        JLabel timeIs = new JLabel("Time is:");
        timer = new ThreadWorker(time);
        timer.execute();
        
        JPanel timePanel = new JPanel();
        timePanel.add(timeIs);
        timePanel.add(time);
        
        add(timePanel, BorderLayout.NORTH);
        //my cardlayout is gonna switch between different panels in a single frame
        cardPanel = new JPanel();
        add(cardPanel, BorderLayout.CENTER);
        card = new CardLayout();             
        cardPanel.setLayout(card);
        
        //this panel is taking care of cycling through the file
        productPanel = new ProductUI(8);
        JButton next = productPanel.getDoTask2Button();
        next.setText("Next Record");
        next.setEnabled(true);
        next.setMnemonic('N');
        next.addActionListener(new ButtonListener());
        JButton prev = productPanel.getDoTask1Button();
        prev.setText("Prev");
        prev.setMnemonic('P');
        prev.addActionListener(new ButtonListener());
        
        cardPanel.add(productPanel, "1");
        
        //handle the display of our data 
        displayFrame = new JFrame("Display");               
        displayFrame.setVisible(false);
        
        
        card.show(cardPanel, "1");
        
        
        
    }
    
    class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("Next Record")){
               getNextRecord();
                
            }
            else if(e.getActionCommand().equals("Prev")){
                getPrevRecord();
            }
            else if(e.getActionCommand().equals("List")){
                displayAllProducts();
            }
            else if(e.getActionCommand().equals("Add")){
                addProduct();
            }
            else if(e.getActionCommand().equals("Clear")){
                productPanel.clearFields();
            }
            else if(e.getActionCommand().equals("Delete")){
                deleteProduct();
            }
            else if(e.getActionCommand().equals("Update")){
                updateProduct();
            }
        }
    }
    public static void main(String args[]) {
        // display a welcome message
        System.out.println("Welcome to the Product Maintenance application\n");
        final JFrame frame = new JFrame();
        frame.add(new ProductMaintApp());
        frame.setVisible(true);
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter(){
            @Override public void windowClosing(WindowEvent e){
                int confirm = JOptionPane.showOptionDialog(frame,
                        "Are You Sure You Want to Exit?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(1);
                    timer.cancel(true);   //stop the timer
                }
                else
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
        // set the class variables
        productDAO = DAOFactory.getProductDAO();
        sc = new Scanner(System.in);

        // display the command menu
        displayMenu();

        // perform 1 or more actions
        String action = "";
        while (!action.equalsIgnoreCase("exit")) {
            // get the input from the user
            action = Validator.getString(sc,
                    "Enter a command: ");
            System.out.println();

            if (action.equalsIgnoreCase("list")) {
                displayAllProducts();
            } else if (action.equalsIgnoreCase("add")) {
                addProduct();
            } else if (action.equalsIgnoreCase("del") || action.equalsIgnoreCase("delete")) {
                deleteProduct();
            } else if (action.equalsIgnoreCase("update")) {
                updateProduct();
            } else if (action.equalsIgnoreCase("help") || action.equalsIgnoreCase("menu")) {
                displayMenu();
            } else if (action.equalsIgnoreCase("exit")) {
                System.out.println("Bye.\n");
            } else {
                System.out.println("Error! Not a valid command.\n");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("COMMAND MENU");
        System.out.println("list    - List all products");
        System.out.println("add     - Add a product");
        System.out.println("del     - Delete a product");
        System.out.println("update - update a product");
        System.out.println("help    - Show this menu");
        System.out.println("exit    - Exit this application\n");
    }

    public static void displayAllProducts() {
        System.out.println("PRODUCT LIST");

        ArrayList<Product> products = productDAO.getProducts();
        if (products == null) {
            System.out.println("Error! Unable to get products.\n");
        } else {
            Product p = null;
            StringBuilder sb = new StringBuilder();
            Vector outer = new Vector();
            
            for (int i = 0; i < products.size(); i++) {
                p = products.get(i);
                Vector inner = new Vector();
                inner.add(p.getCode());
                inner.add(p.getId());
                inner.add(p.getDescription());
                inner.add(p.getAmountString());
                inner.add(p.getFormattedPrice());
                inner.add(p.getDiscount());
                inner.add(p.getWeight());
                inner.add(p.getColor());
               /* sb.append(
                         StringUtils.padWithSpaces(
                                p.getCode(), CODE_SIZE + 4)
                        +StringUtils.padWithSpaces(
                                p.getId(), CODE_SIZE + 4)
                        + StringUtils.padWithSpaces(
                                p.getDescription(), DESCRIPTION_SIZE + 4)
                        + StringUtils.padWithSpaces(
                                p.getAmountString(), CODE_SIZE + 4)
                        + p.getFormattedPrice() + "\t"
                        + p.getDiscount() + "\t"
                        + p.getWeight() + "\t"
                        +p.getColor() + "\n"
                );*/
                outer.add(inner);
            }
            Vector columnNames = new Vector();
            String header[] = productPanel.names;
            for(String s : header)            
                columnNames.add(s);         
            
            JTable table = new JTable(outer, columnNames);
            //System.out.println(sb.toString());
            //displayArea.setText(sb.toString());
            JScrollPane tablePane = new JScrollPane(table);
            displayFrame.add(tablePane);
            displayFrame.setVisible(true);
            displayFrame.setSize(400, 350);
        }
    }

    public static void addProduct() {
      
        
            
        JTextField fields[] = productPanel.getFields();
        String code1 = fields[productPanel.CODE].getText();
        String ID1 = fields[productPanel.ID].getText();
        String description1 = fields[productPanel.DESCRIPTION].getText();
        int amount1 = Validator.getInt(fields[productPanel.AMOUNT]);
        double price1 = Validator.getDouble(fields[productPanel.PRICE]);
        char discount1 = Validator.getChar(fields[productPanel.DISCOUNT]);
        double weight1 = Validator.getDouble(fields[productPanel.WEIGHT]);
        String color1 = fields[productPanel.COLOR].getText();
        
        Product product = new Product();
      
        product.setCode(code1); 
        product.setId(ID1);
        product.setDescription(description1);
        product.setAmount(amount1);
        product.setPrice(price1);
        product.setDiscount(discount1);
        product.setWeight(weight1);
        product.setColor(color1);
        boolean success = productDAO.addProduct(product);
       
        System.out.println();
        if (success) {
            String product1 = description1 + " was added to the file";
            System.out.println(description1
                    + " was added to the database.\n");
            JOptionPane.showMessageDialog(null, product1);
            productPanel.clearFields();
        } else {
            System.out.println("Error! Unable to add product\n");
            JOptionPane.showConfirmDialog(null, "Unable to add product!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }//end of try
        


    public static void deleteProduct() {
        String code = "";
        JTextField fields[] = productPanel.getFields();
        if(fields[productPanel.CODE].getText().equals(""))
            code = JOptionPane.showInputDialog(null, "Please enter product CODE:");
            
        else
            code = fields[productPanel.CODE].getText();

        Product p = productDAO.getProduct(code);

        if (p != null) {
            boolean success = productDAO.deleteProduct(p);
            String description = p.getDescription() + " was deleted from the database.";
            if (success) {
                JOptionPane.showMessageDialog(null, description);
                System.out.println(p.getDescription()
                        + " was deleted from the database.\n");
                productPanel.clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Error! Unable to delete");
                //System.out.println("Error! Unable to add product\n");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No product matches that code.");
            System.out.println("No product matches that code.\n");
        }
    }

    public static void updateProduct() {
        JTextField fields[] = productPanel.getFields();
        String code1 = fields[productPanel.CODE].getText();
        String ID1 = fields[productPanel.ID].getText();
        String description1 = fields[productPanel.DESCRIPTION].getText();
        int amount1 = Validator.getInt(fields[productPanel.AMOUNT]);
        double price1 = Validator.getDouble(fields[productPanel.PRICE]);
        char discount1 = Validator.getChar(fields[productPanel.DISCOUNT]);
        double weight1 = Validator.getDouble(fields[productPanel.PRICE]);
        String color1 = fields[productPanel.COLOR].getText();
        
        Product product = new Product();
        
        product.setCode(code1);
        product.setId(ID1);
        product.setDescription(description1);
        product.setAmount(amount1);
        product.setPrice(price1);
        product.setDiscount(discount1);
        boolean success = productDAO.updateProduct(product);

        System.out.println();
        if (success) {
           JOptionPane.showMessageDialog(null, "Product was updated");
           productPanel.clearFields();
        } else {
            JOptionPane.showMessageDialog(null, "Error!");
            System.out.println("Error! Unable to add product\n");
        }
    }
    
    private static void getNextRecord(){
       
         Product p = null;         
         ArrayList<Product> products = productDAO.getProducts();
         if(!products.isEmpty()){
            if(iterator < (products.size())){ 
                 if(iterator < products.size()-1)         //update iterator
                 iterator++;
                  else
                JOptionPane.showMessageDialog(null, "No More Records!", "Error", JOptionPane.ERROR_MESSAGE);
                
                 p = products.get(iterator);
                 String fields [] = { 
                                p.getCode()
                            , p.getId()
                         , p.getDescription()
                        , p.getAmountString()
                        , p.getFormattedPrice(),
                         String.valueOf(p.getDiscount())
                        , String.valueOf(p.getWeight())
                        , p.getColor()};
                 productPanel.setFieldValues(fields);
                 
                 
                 
            }
             
            
         }
    }
    
    private static void getPrevRecord(){
         Product p = null;         
         ArrayList<Product> products = productDAO.getProducts();
         if(!products.isEmpty()){
            if(iterator >=0){                             
                if(iterator > 0)            //update iterator so it never goes < 0
                 iterator--;
                else
                    JOptionPane.showMessageDialog(null, "You've reached the first record!", "No More Records!", JOptionPane.ERROR_MESSAGE);
                                
                p = products.get(iterator);
                String fields [] = { 
                                p.getCode()
                            , p.getId()
                         , p.getDescription()
                        , p.getAmountString()
                        , p.getFormattedPrice(),
                         String.valueOf(p.getDiscount())
                        , String.valueOf(p.getWeight())
                        , p.getColor()};
                 
                productPanel.setFieldValues(fields);
                 
                
            }
            
            
         }
    }

}
