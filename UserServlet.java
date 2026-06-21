package control;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import entity.User;
import model.UserModel;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        UserModel model = new UserModel();
        
        if ("add".equals(action)) {
            User u = new User();
            u.setUsername(request.getParameter("username"));
            u.setPassword(request.getParameter("password"));
            u.setRole(request.getParameter("role"));
            u.setRoleId(0);
            u.setStatus(1);
            model.addUser(u);
            response.sendRedirect(request.getContextPath() + "/UserServlet?action=list");
        } else if ("update".equals(action)) {
            User u = new User();
            u.setId(Integer.parseInt(request.getParameter("id")));
            u.setPassword(request.getParameter("password"));
            u.setRole(request.getParameter("role"));
            u.setStatus(Integer.parseInt(request.getParameter("status")));
            model.updateUser(u);
            response.sendRedirect(request.getContextPath() + "/UserServlet?action=list");
        } else if ("delete".equals(action)) {
            model.deleteUser(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect(request.getContextPath() + "/UserServlet?action=list");
        } else if ("edit".equals(action)) {
            User u = model.getUserById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("userEdit", u);
            request.getRequestDispatcher("/admin/userEdit.jsp").forward(request, response);
        } else {
            List<User> list = model.getAllUsers();
            request.setAttribute("userList", list);
            request.getRequestDispatcher("/admin/userList.jsp").forward(request, response);
        }
    }
}
