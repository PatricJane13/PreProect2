package servlet;

import model.User;
import org.eclipse.jetty.http.HttpStatus;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/update")
public class UpdateUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = new User(Long.parseLong(req.getParameter("id")),
                    req.getParameter("newName"),
                    req.getParameter("newPassword"),
                    Long.parseLong(req.getParameter("newAge")));
            if (user.getPassword().trim().length() != 0
                    && user.getName().trim().length() != 0
                    && userService.updateUser(user)) {
                resp.setStatus(HttpStatus.OK_200);
                resp.sendRedirect("/admin/all");
            } else {
                resp.getWriter().println("Error changing the user=(");
                resp.setStatus(HttpStatus.BAD_REQUEST_400);
            }
        } catch (NumberFormatException | SQLException e) {
            resp.getWriter().println("Error changing the user =(");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            e.printStackTrace();
        }
    }
}