import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(urlPatterns = "/assortment", name = "Assortment")
public class RangeOfProductsServlet extends HttpServlet {
    private static final String PRODUCT_ID = "productId";
    public static final String SHOPPING_CART = "shoppingCart";

    private ProductList products = new ProductList();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        HttpSession session = req.getSession();

        String username = (String) session.getAttribute("username");

        PrintWriter writer = resp.getWriter();
        writer.write(getOrderForm(username));
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        if (Optional.ofNullable(req.getParameter(PRODUCT_ID)).isPresent()) {
            int selectedProduct = Integer.parseInt(req.getParameter(PRODUCT_ID));
            for (Product product : products.getAllProducts()) {
                if (selectedProduct == product.getId()) {
                    products.addSelectedProduct(product);
                    break;
                }
            }
            resp.setIntHeader("Refresh", 1);
        }
    }

    private String getSelectForProduct(ArrayList<Product> products) {
        StringBuilder select = new StringBuilder();

        select.append("<select name=\"productId\" form=\"selectForm\" align=\"center\"><br>");

        for (int i = 0; i < products.size(); i++) {
            String price = Double.toString(products.get(i).getPrice());
            if (products.get(i).getPrice() % 1.0 == 0) {
                price = price.substring(0,price.length()-2);
            }
            select.append("<option value=\"").append(i + 1).append("\">")
                    .append(products.get(i).getName()).append(" ")
                    .append(price).append(" $").append("</option><br>");
        }

        select.append("</select>");
        return select.toString();
    }

    private String getOrderForm(String username) {
        Formatter formatter = new Formatter();

        String alreadyChosenProducts;
        if (!products.getSelectedProducts().isEmpty()) {
            alreadyChosenProducts = getAlreadyChosenProducts(products.getSelectedProducts());
        } else {
            alreadyChosenProducts = """
                    <p>Your shopping cart is empty!</p>
                    <p>Please, add some items!</p>
                    """;
        }

        return String.valueOf(formatter.format("""
                <div align="center">
                <h1 align="center">Hello %s!</h1>
                %s
                <p align="center">Make your order</p>
                <form name="selector" method="post" action="/assortment" id="selectForm" align="center">
                %s <br/>
                </form>
                <input type="submit" name="addItem" value="Add Item" form="selectForm" align="center">
                <form name="submitForm" method="get" action="/order" id="redirectToOrder" align="center">
                <input type="submit" name="submit" value="Submit" form="redirectToOrder" align="center">
                </form>
                </div>
                """, username, alreadyChosenProducts, getSelectForProduct(products.getAllProducts())));
    }

    private String getAlreadyChosenProducts(ArrayList<Product> products) {
        StringBuilder listOfChosenProducts = new StringBuilder();
        listOfChosenProducts.append("<p id=\"shoppingCart\">You have already chosen:</p>");
        for (int i = 1; i <= products.size(); i++) {
            StringBuilder productString = new StringBuilder();
            productString.append("<p>").append(i).append(") ").append(products.get(i-1).getName()).append(" ")
                    .append(products.get(i-1).getPrice()).append(" $</p>");
            listOfChosenProducts.append(productString);
        }
        return listOfChosenProducts.toString();
    }
}