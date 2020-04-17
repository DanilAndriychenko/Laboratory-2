public class Logic {

    public static GroupOfProduct addNewGroupOfProduct(String name, String description, String[] groupsOfProduct) {
        if (Utility.nameIsUniqueInDynamicArray(name, groupsOfProduct) || Utility.lineContainsOnlyLetters(name))
            return null;
        return new GroupOfProduct(name, description);
    }

    public static Product addNewProduct(String name, String description, GroupOfProduct group, Manufacturer manufacturer, double price, GroupOfProduct groupOfProduct) {
        if ((Utility.nameIsUniqueInDynamicArray(name, (String[]) group.getProducts().toArray()) || manufacturerIsUnique(group, manufacturer)) && Utility.lineContainsOnlyLetters(name))
            return new Product(name, description, manufacturer, price, groupOfProduct);
        return null;
    }

    private static boolean manufacturerIsUnique(GroupOfProduct group, Manufacturer manufacturer){
        for(int i = 0; i < group.getProducts().size(); i++){
            if(group.getProducts().get(i).getManufacturer().equals(manufacturer.toString()))
                return false;
        }return true;
    }

    public static void editGroupOfProduct(String oldName, String newName, String newDescription) {
        // TODO: 17.04.2020 firstly create the list of groups of products
    }

    public static void removeGroupOfProduct(String oldName) {
        // TODO: 17.04.2020 firstly create the list of groups of products
    }

    public static void editProduct(String oldName, String newName, String newDescription, Manufacturer newManufacturer, double newPrice, GroupOfProduct groupOfProduct) {
        Product productToEdit = null;
        for (int i = 0; i < groupOfProduct.getProducts().size(); i++) {
            if (groupOfProduct.getProducts().get(i).toString().equals(oldName)) {
                productToEdit = groupOfProduct.getProducts().get(i);
                break;
            }
        }
        if (productToEdit == null)
            return;
        productToEdit.setName(newName);
        productToEdit.setDescription(newDescription);
        productToEdit.setManufacturer(newManufacturer);
        productToEdit.setPrice(newPrice);
    }

    public static boolean removeProduct(String name, GroupOfProduct group) {
        for (int i = 0; i < group.getProducts().size(); i++) {
            if (group.getProducts().get(i).toString().equals(name)) {
                group.getProducts().remove(i);
                return true;
            }
        }
        return false;
    }

    public static boolean minusProduct(Product product, int numToMinus) {
        return product.decreaseNumOfProduct(numToMinus);
    }

    public static void plusProduct(Product product, int numToPlus) {
        product.decreaseNumOfProduct((-1) * numToPlus);
    }

    public static String showAllProducts(GroupOfProduct group) {
        // TODO: 17.04.2020
        return null;
    }

    public static String showAllProducts() {
        // TODO: 17.04.2020
        return null;
    }

    public static double overallPrice() {
        // TODO: 17.04.2020
        return 0;
    }

    public static double overallPrice(GroupOfProduct group) {
        double price = 0;
        for (int i = 0; i < group.getProducts().size(); i++)
            price += group.getProducts().get(i).getPrice();
        return price;
    }

}
