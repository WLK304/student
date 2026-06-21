package control;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import entity.User;
import model.UserModel;
import model.StudentModel;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserModel userModel = new UserModel();
        User user = userModel.login(username, password);
        
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if ("admin".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
            } else if ("teacher".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/teacher/index.jsp");
            } else if ("student".equals(user.getRole())) {
                StudentModel studentModel = new StudentModel();
                entity.Student student = studentModel.getStudentById(user.getRoleId());
                session.setAttribute("student", student);
                response.sendRedirect(request.getContextPath() + "/student/index.jsp");
            }
        } else {
            request.setAttribute("error", "用户名或密码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}