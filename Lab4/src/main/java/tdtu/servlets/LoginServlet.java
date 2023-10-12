package tdtu.servlets;
import java.io.*;
import java.util.HashMap;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class LoginServlet extends HttpServlet {
    private HashMap<String, String> users = new HashMap<>();

    public LoginServlet() {
        users.put("ronaldo", "1234");
        users.put("pessi", "5678");
        users.put("pendu", "9012");
        users.put("neymar", "9012");
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            request.setAttribute("message", "Username/Password Match");
        } else {
            request.setAttribute("message", "Username/Password Don't Match");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
