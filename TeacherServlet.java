package control;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import entity.Teacher;
import model.TeacherModel;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        TeacherModel model = new TeacherModel();
        
        if ("add".equals(action)) {
            Teacher t = new Teacher();
            t.setTeacherNo(request.getParameter("teacherNo"));
            t.setName(request.getParameter("name"));
            t.setGender(request.getParameter("gender"));
            t.setTitle(request.getParameter("title"));
            t.setPhone(request.getParameter("phone"));
            t.setEmail(request.getParameter("email"));
            model.addTeacher(t);
            response.sendRedirect(request.getContextPath() + "/TeacherServlet?action=list");
        } else if ("update".equals(action)) {
            Teacher t = new Teacher();
            t.setId(Integer.parseInt(request.getParameter("id")));
            t.setTeacherNo(request.getParameter("teacherNo"));
            t.setName(request.getParameter("name"));
            t.setGender(request.getParameter("gender"));
            t.setTitle(request.getParameter("title"));
            t.setPhone(request.getParameter("phone"));
            t.setEmail(request.getParameter("email"));
            model.updateTeacher(t);
            response.sendRedirect(request.getContextPath() + "/TeacherServlet?action=list");
        } else if ("delete".equals(action)) {
            model.deleteTeacher(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect(request.getContextPath() + "/TeacherServlet?action=list");
        } else if ("edit".equals(action)) {
            Teacher t = model.getTeacherById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("teacher", t);
            request.getRequestDispatcher("/admin/teacherEdit.jsp").forward(request, response);
        } else {
            List<Teacher> list = model.getAllTeachers();
            request.setAttribute("teacherList", list);
            request.getRequestDispatcher("/admin/teacherList.jsp").forward(request, response);
        }
    }
}
