package com.hieu.servlets;

import com.hieu.dao.UserDAO;
import com.hieu.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO(User.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsps/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        if ( userDAO.add(new User(name,email,pwd))) {
            HttpSession session = req.getSession();
            session.setAttribute("username", name);
            resp.sendRedirect("./");
        }
        else {
            req.getSession().setAttribute("msg_error", "Có lỗi xảy ra");
            resp.sendRedirect("./register");
        }
    }
}
