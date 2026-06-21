package control;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import entity.Student;
import model.StudentModel;
import model.MajorModel;
import model.ClassesModel;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        StudentModel model = new StudentModel();
        MajorModel majorModel = new MajorModel();
        ClassesModel classesModel = new ClassesModel();
        
        if ("add".equals(action)) {
            Student s = new Student();
            s.setStuNo(request.getParameter("stuNo"));
            s.setName(request.getParameter("name"));
            s.setGender(request.getParameter("gender"));
            s.setAge(Integer.parseInt(request.getParameter("age")));
            s.setMajorId(Integer.parseInt(request.getParameter("majorId")));
            s.setClassId(Integer.parseInt(request.getParameter("classId")));
            s.setPhone(request.getParameter("phone"));
            s.setEmail(request.getParameter("email"));
            model.addStudent(s);
            response.sendRedirect(request.getContextPath() + "/StudentServlet?action=list");
        } else if ("update".equals(action)) {
            Student s = new Student();
            s.setId(Integer.parseInt(request.getParameter("id")));
            s.setStuNo(request.getParameter("stuNo"));
            s.setName(request.getParameter("name"));
            s.setGender(request.getParameter("gender"));
            s.setAge(Integer.parseInt(request.getParameter("age")));
            s.setMajorId(Integer.parseInt(request.getParameter("majorId")));
            s.setClassId(Integer.parseInt(request.getParameter("classId")));
            s.setPhone(request.getParameter("phone"));
            s.setEmail(request.getParameter("email"));
            model.updateStudent(s);
            response.sendRedirect(request.getContextPath() + "/StudentServlet?action=list");
        } else if ("delete".equals(action)) {
            model.deleteStudent(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect(request.getContextPath() + "/StudentServlet?action=list");
        } else if ("edit".equals(action)) {
            Student s = model.getStudentById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("student", s);
            request.setAttribute("majorList", majorModel.getAllMajors());
            request.setAttribute("classesList", classesModel.getAllClasses());
            request.getRequestDispatcher("/admin/studentEdit.jsp").forward(request, response);
        } else if ("view".equals(action)) {
            Student s = model.getStudentById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("student", s);
            request.getRequestDispatcher("/admin/studentView.jsp").forward(request, response);
        } else {
            List<Student> list = model.getAllStudents();
            request.setAttribute("studentList", list);
            request.setAttribute("majorList", majorModel.getAllMajors());
            request.setAttribute("classesList", classesModel.getAllClasses());
            request.getRequestDispatcher("/admin/studentList.jsp").forward(request, response);
        }
    }
}
