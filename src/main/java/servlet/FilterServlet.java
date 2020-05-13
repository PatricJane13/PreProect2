package servlet;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user", "/admin/*"})
public class FilterServlet implements Filter {

    UserService userService = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();
        String role = httpSession.getAttribute("role") == null ? "" : (String) httpSession.getAttribute("role");
        String path = httpServletRequest.getServletPath();

        if (role.equals("admin") && path.contains("admin")) {
            httpServletRequest.getRequestDispatcher(httpServletRequest.getServletPath()).forward(httpServletRequest, httpServletResponse);
        } else if (role.equals("user") && path.contains("user") || role.equals("admin") && path.contains("user")) {
            httpServletRequest.getRequestDispatcher("/user").forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {
    }
}
