
import java.text.NumberFormat;

public class Product {

    private String code;
    private String id;
    private String description;
    private int amount;
    private double price;
    private char discount;
    private double weight;
    private String color;

    public Product() {
        this("", "", "", 0, 0, ' ',0,"");
    }

    public Product( String code,String id, String description, int amount, double price, char discount, double weight, String color) {
        
        this.code = code;
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.discount = discount;
        this.weight = weight;
        this.code = color;
    }

   

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
 public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    } 
   public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getAmountString() {
        String amountString = Integer.toString(amount);
        return amountString;

    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getFormattedPrice() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }

    public void setDiscount(char discount) {
        this.discount = discount;
    }

    public char getDiscount() {
        return discount;
    }
    
    public void setColor(String color){
        this.color = color;
    }
    
    public String getColor(){return color;}
    
    public void setWeight(double weight){
        this.weight = weight;
    }
    
    public double getWeight(){return weight;}
    
    public boolean equals(Object object) {
        if (object instanceof Product) {
            Product product2 = (Product) object;
            if ( code.equals(product2.getCode())
                    &&id.equals(product2.getId())
                    && description.equals(product2.getDescription())
                    && amount == product2.getAmount()
                    && price == product2.getPrice()
                    && discount == product2.getDiscount()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "Code:        " + code + "\n"
                + "Id:            " + id + "\n"
                + "Description: " + description + "\n"
                + "amount:   " + amount + "\n"
                + "Price:       " + this.getFormattedPrice() + "\n"
                + "discount:    " + discount;
    }
}
