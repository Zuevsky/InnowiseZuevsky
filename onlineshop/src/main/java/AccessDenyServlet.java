import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/access-deny", name = "AccessDeny")
public class AccessDenyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();

        String message = """
                <div align="center">
                <h1>Oops!</h1>
                <p>You shouldn't be here</p>
                <p>Please, agree with the terms of service first.</p>
                <br/>
                <a href="/">Start page</a>
                </div>
                """;

        writer.println(message);
        writer.close();
    }
}
