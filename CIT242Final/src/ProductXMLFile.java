
import java.util.*;
import java.io.*;
import javax.xml.stream.*;  // StAX API

public class ProductXMLFile implements ProductDAO {

    private String productsFilename = "products.xml";
    private File productsFile = null;

    public ProductXMLFile() {
        productsFile = new File(productsFilename);
    }

    private void checkFile() throws IOException {
        // if the file doesn't exist, create it
        if (!productsFile.exists()) {
            productsFile.createNewFile();
        }
    }

    private boolean saveProducts(ArrayList<Product> products) {
        // create the XMLOutputFactory object
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        try {
            // check the file to make sure it exists
            this.checkFile();

            // create XMLStreamWriter object
            FileWriter fileWriter
                    = new FileWriter(productsFilename);
            XMLStreamWriter writer
                    = outputFactory.createXMLStreamWriter(fileWriter);

            //write the products to the file
            writer.writeStartDocument("1.0");
            writer.writeStartElement("Products");
            for (Product p : products) {

                writer.writeStartElement("Product");
                writer.writeAttribute("Code", p.getCode());

                writer.writeStartElement("ID");
                writer.writeCharacters(p.getId());
                writer.writeEndElement();

                writer.writeStartElement("Description");
                writer.writeCharacters(p.getDescription());
                writer.writeEndElement();

                writer.writeStartElement("Amount");
                int amount = (int) p.getAmount();
                writer.writeCharacters(Integer.toString(amount));
                writer.writeEndElement();

                writer.writeStartElement("Price");
                double price = p.getPrice();
                writer.writeCharacters(Double.toString(price));
                writer.writeEndElement();

                writer.writeStartElement("Discount");
                char discount = p.getDiscount();
                writer.writeCharacters(Character.toString(discount));
                writer.writeEndElement();
                
                writer.writeStartElement("Weight");
                double weight = p.getWeight();
                writer.writeCharacters(Double.toString(weight));
                writer.writeEndElement();
                
                writer.writeStartElement("Color");
                writer.writeCharacters(p.getColor());
                writer.writeEndElement();

                writer.writeEndElement();
            } //for
            writer.writeEndElement();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XMLStreamException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        Product p = null;

        // create the XMLInputFactory object
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            // check the file to make sure it exists
            this.checkFile();

            // create a XMLStreamReader object
            FileReader fileReader
                    = new FileReader(productsFilename);
            XMLStreamReader reader
                    = inputFactory.createXMLStreamReader(fileReader);

            // read the products from the file
            while (reader.hasNext()) {
                int eventType = reader.getEventType();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        String elementName = reader.getLocalName();
                        if (elementName.equals("Product")) {
                            p = new Product();
 
                            String code = reader.getAttributeValue(0);
                            p.setCode(code);
                        }
                        if (elementName.equals("ID")) {
                           
                            String id = reader.getElementText();
                            p.setId(id);
                        }

                        if (elementName.equals("Description")) {
                            String description = reader.getElementText();
                            p.setDescription(description);
                        }
                        if (elementName.equals("Amount")) {
                            String AmountString = reader.getElementText();
                            int amount = Integer.parseInt(AmountString);
                            p.setAmount(amount);
                        }

                        if (elementName.equals("Price")) {
                            String priceString = reader.getElementText();
                            double price = Double.parseDouble(priceString);
                            p.setPrice(price);
                        }
                        if (elementName.equals("Discount")) {
                            String discountString = reader.getElementText();
                            char discount = discountString.charAt(0);
                            p.setDiscount(discount);
                        }
                        
                        if (elementName.equals("Weight")) {
                            String weightString = reader.getElementText();
                            double weight = Double.parseDouble(weightString);
                            p.setWeight(weight);
                        }
                        
                        if (elementName.equals("Color")) {
                            String color = reader.getElementText();
                            p.setColor(color);
                        }

                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        elementName = reader.getLocalName();
                        if (elementName.equals("Product")) {
                            products.add(p);
                        }
                        break;
                    default:
                        break;
                }
                reader.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XMLStreamException e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    public Product getProduct(String code) {
        ArrayList<Product> products = this.getProducts();
        for (Product p : products) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }
        return null;
    }

    public boolean addProduct(Product p) {
        ArrayList<Product> products = this.getProducts();
        products.add(p);
        return this.saveProducts(products);
    }

    public boolean deleteProduct(Product p) {
        ArrayList<Product> products = this.getProducts();
        products.remove(p);
        return this.saveProducts(products);
    }

    public boolean updateProduct(Product newProduct) {
        ArrayList<Product> products = this.getProducts();

        // get the old product and remove it
        Product oldProduct = this.getProduct(newProduct.getCode());
        int i = products.indexOf(oldProduct);
        products.remove(i);

        // add the updated product
        products.add(i, newProduct);

        return this.saveProducts(products);
    }
}
