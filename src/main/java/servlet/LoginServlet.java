package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = userService.getUserByName(name);
        HttpSession httpSession = req.getSession();
        if (userService.checkingUser(name, password)) {
            httpSession.setAttribute("name", name);
            httpSession.setAttribute("password", password);
            if (user.getRole().equals("admin")) {
                httpSession.setAttribute("role", user.getRole());
                resp.sendRedirect("/admin/all");
            } else if (user.getRole().equals("user")) {
                httpSession.setAttribute("role", user.getRole());
                resp.sendRedirect("/user");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
