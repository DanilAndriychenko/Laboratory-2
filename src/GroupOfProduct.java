import java.util.ArrayList;

public class GroupOfProduct {

    private String name;
    private ArrayList<Product> products;
    private String description;

    public GroupOfProduct(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getDescription() {
        return description;
    }
}
