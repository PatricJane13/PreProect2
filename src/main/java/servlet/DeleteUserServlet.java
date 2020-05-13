package servlet;

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

@WebServlet("/admin/delete")
public class DeleteUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        try {
            if (userService.deleteUser(id)) {
                resp.setStatus(HttpStatus.OK_200);
                resp.sendRedirect("/admin/all");
            } else {
                resp.getWriter().println("Error deleting a user=(");
                resp.setStatus(HttpStatus.BAD_REQUEST_400);
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }
}
