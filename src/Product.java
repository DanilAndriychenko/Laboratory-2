public class Product {
    private String name;
    private String description;
    private Manufacturer manufacturer;
    private int numInStorage;
    private GroupOfProduct groupOfProduct;
    private double price;

    public Product(String name, String description, Manufacturer manufacturer, double price, GroupOfProduct groupOfProduct) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.numInStorage = 0;
        this.price = price;
        this.groupOfProduct = groupOfProduct;
    }

    public boolean decreaseNumOfProduct(int numToMinus){
        if(numToMinus > numInStorage)
            return false;
        numInStorage -= numToMinus;
        return true;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupOfProduct(GroupOfProduct groupOfProduct) {
        this.groupOfProduct = groupOfProduct;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public int getNumInStorage() {
        return numInStorage;
    }

    public double getPrice() {
        return price;
    }
}
