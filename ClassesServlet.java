package control;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import entity.Classes;
import entity.Major;
import model.ClassesModel;
import model.MajorModel;

@WebServlet("/ClassesServlet")
public class ClassesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ClassesModel model = new ClassesModel();
        MajorModel majorModel = new MajorModel();
        
        if ("add".equals(action)) {
            Classes c = new Classes();
            c.setClassNo(request.getParameter("classNo"));
            c.setClassName(request.getParameter("className"));
            c.setMajorId(Integer.parseInt(request.getParameter("majorId")));
            c.setGrade(request.getParameter("grade"));
            model.addClasses(c);
            response.sendRedirect(request.getContextPath() + "/ClassesServlet?action=list");
        } else if ("update".equals(action)) {
            Classes c = new Classes();
            c.setId(Integer.parseInt(request.getParameter("id")));
            c.setClassNo(request.getParameter("classNo"));
            c.setClassName(request.getParameter("className"));
            c.setMajorId(Integer.parseInt(request.getParameter("majorId")));
            c.setGrade(request.getParameter("grade"));
            model.updateClasses(c);
            response.sendRedirect(request.getContextPath() + "/ClassesServlet?action=list");
        } else if ("delete".equals(action)) {
            model.deleteClasses(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect(request.getContextPath() + "/ClassesServlet?action=list");
        } else if ("edit".equals(action)) {
            Classes c = model.getClassById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("classes", c);
            request.setAttribute("majorList", majorModel.getAllMajors());
            request.getRequestDispatcher("/admin/classesEdit.jsp").forward(request, response);
        } else {
            List<Classes> list = model.getAllClasses();
            request.setAttribute("classesList", list);
            request.setAttribute("majorList", majorModel.getAllMajors());
            request.getRequestDispatcher("/admin/classesList.jsp").forward(request, response);
        }
    }
}
