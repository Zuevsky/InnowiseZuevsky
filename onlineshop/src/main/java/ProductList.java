import java.util.ArrayList;

public class ProductList {
    private ArrayList<Product> allProducts = new ArrayList<>(setAllProductsList());
    private ArrayList<Product> selectedProducts = new ArrayList<>();

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public ArrayList<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void addSelectedProduct(Product product) {
        selectedProducts.add(product);
    }

    private ArrayList<Product> setAllProductsList() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Book", 5.5));
        products.add(new Product("TV", 40.0));
        products.add(new Product("Mobile phone", 25.0));
        products.add(new Product("Textbook", 8.5));
        products.add(new Product("Pencil", 2.0));
        return products;
    }
}
