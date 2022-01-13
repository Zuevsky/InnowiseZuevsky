import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(urlPatterns = "/", name = "ShopMainPage")
public class MainPageServlet extends HttpServlet {

    private static final String AGREEMENT_PARAM = "termsAgreement";
    public static final String USERNAME_PARAM = "username";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter printWriter = resp.getWriter();

        String userForm = """
                <form name="loginForm" method="post" action="/" align="center">
                <h1 align="center">Welcome to Online Shop</h1> <br/>
                <input type="text" name="username" align="center"/> <br/>
                <input type="checkbox" name="termsAgreement" checked align="center"> I agree with the terms of service <br/>
                <input type="submit" name="btn" align="center"/>
                </form>
                """;

        printWriter.write(userForm);
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        String username = "anonymous";
        if (!req.getParameter(USERNAME_PARAM).equals("")) {
            username = req.getParameter(USERNAME_PARAM);
        }

        boolean agreement = Optional.ofNullable(req.getParameter(AGREEMENT_PARAM)).isPresent();

        session.setAttribute(USERNAME_PARAM, username);
        session.setAttribute(AGREEMENT_PARAM, agreement);

        if (agreement) {
            resp.sendRedirect(req.getContextPath() + "/assortment");
        } else {
            resp.sendRedirect(req.getContextPath() + "/access-deny");
        }
    }
}
