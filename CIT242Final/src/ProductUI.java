
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class ProductUI extends JPanel{
    protected static final String names[] = {"Code", "ID", "Description", "Amount", "Price", "Discount", "Weight Pounds", "Color"};
    public static final int CODE = 0, ID = 1, DESCRIPTION = 2, AMOUNT = 3, PRICE = 4, DISCOUNT = 5, WEIGHT = 6, COLOR = 7;
    
        protected JLabel labels[];
	protected JTextField fields[];
	protected JButton doTask1, doTask2;
	protected JPanel innerPanelCenter, innerPanelSouth;
        
        protected int size;
        
        public ProductUI(final int size){
            this.size = size;
       labels = new JLabel[size];
		fields = new JTextField[size];

		// create labels
		for (int count = 0; count < labels.length; count++)
			labels[count] = new JLabel(names[count]);

		
		// create text fields
		for (int count = 0; count < fields.length; count++)
			fields[count] = new JTextField();
		
		// set focus for text fields
		for(int x=0; x < size; x++){
			final int fieldSize = x;
			fields[x].addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent e){
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
						if(fieldSize == (size-1))
							doTask2.requestFocus();
						else fields[fieldSize+1].requestFocus();
				}
			});
		}
	
		// create panel to lay out labels and fields
		innerPanelCenter = new JPanel();
		innerPanelCenter.setLayout(new GridLayout(size, 2));

		// attach labels and fields to innerPanelCenter
		for (int count = 0; count < size; count++) {
			innerPanelCenter.add(labels[count]);
			innerPanelCenter.add(fields[count]);
		}

		// create generic buttons; no labels or event handlers
		doTask1 = new JButton();
		doTask2 = new JButton();

		// create panel to lay out buttons and attach buttons
		innerPanelSouth = new JPanel();
		innerPanelSouth.add(doTask1);
		innerPanelSouth.add(doTask2);

		// set layout of this container and attach panels to it
		setLayout(new BorderLayout());
		add(innerPanelCenter, BorderLayout.CENTER);
		add(innerPanelSouth, BorderLayout.SOUTH);

		// validate layout
		validate(); // make share panels fit.
		// pack(); // make all panels in constant sizes

	} // end constructor

	// return reference to generic task button doTask1
	public JButton getDoTask1Button() {
		return doTask1;
	}

	// return reference to generic task button doTask2
	public JButton getDoTask2Button() {
		return doTask2;
	}

	// return reference to fields array of JTextFields
	public JTextField[] getFields() {
		return fields;
	}

	// clear content of text fields
	public void clearFields() {
		for (int count = 0; count < size; count++)
			fields[count].setText("");
	}

	// set text field values; throw IllegalArgumentException if
	// incorrect number of Strings in argument
	public void setFieldValues(String strings[])
			throws IllegalArgumentException {
		if (strings.length != size)
			throw new IllegalArgumentException("There must be " + size
					+ " Strings in the array");

		for (int count = 0; count < size; count++)
			fields[count].setText(strings[count]);
	}

	// get array of Strings with current text field contents
	public String[] getFieldValues() {
		String values[] = new String[size];

		for (int count = 0; count < size; count++)
			values[count] = fields[count].getText();

		return values; //&values[0]
	} 
        
        
       
}
