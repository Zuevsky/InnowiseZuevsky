import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;

@WebServlet(urlPatterns = "/order", name = "Order")
public class OrderServlet extends HttpServlet {
    ProductList products = new ProductList();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        HttpSession session = req.getSession();

        String username = (String) session.getAttribute("username");

        PrintWriter writer = resp.getWriter();
        writer.write(getTotal(username, products.getSelectedProducts()));
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
    }

    private String getTotal(String username, ArrayList<Product> selectedProducts) {
        Formatter formatter = new Formatter();

        String total = String.valueOf(formatter.format("""
                <div align="center">
                <h1>Dear %s, your order:</h1>
                %s
                <br>
                %s
                </div>
                """, username, getChosenProducts(selectedProducts), getTotalPrice(selectedProducts)));

        return total;
    }

    private String getChosenProducts(ArrayList<Product> products) {
        StringBuilder listOfChosenProducts = new StringBuilder();
        for (int i = 1; i <= products.size(); i++) {
            StringBuilder productString = new StringBuilder();
            productString.append("<p>").append(i).append(") ").append(products.get(i-1).getName()).append(" ")
                    .append(products.get(i-1).getPrice()).append(" $</p>");
            listOfChosenProducts.append(productString);
        }
        return listOfChosenProducts.toString();
    }

    private String getTotalPrice(ArrayList<Product> products) {
        double totalPriceInDouble = 0;
        for (Product product : products) {
            totalPriceInDouble += product.getPrice();
        }
        return "<p>Total: " + totalPriceInDouble + "$</p>";
    }
}
