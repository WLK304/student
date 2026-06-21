package control;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import entity.Subject;
import model.SubjectModel;
import model.TeacherModel;
import model.ClassesModel;

@WebServlet("/SubjectServlet")
public class SubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        SubjectModel model = new SubjectModel();
        TeacherModel teacherModel = new TeacherModel();
        ClassesModel classesModel = new ClassesModel();
        
        if ("add".equals(action)) {
            Subject s = new Subject();
            s.setSubjectNo(request.getParameter("subjectNo"));
            s.setSubjectName(request.getParameter("subjectName"));
            s.setCredit(Double.parseDouble(request.getParameter("credit")));
            s.setTeacherId(Integer.parseInt(request.getParameter("teacherId")));
            s.setSemester(request.getParameter("semester"));
            s.setDescription(request.getParameter("description"));
            model.addSubject(s);
            response.sendRedirect(request.getContextPath() + "/SubjectServlet?action=list");
        } else if ("update".equals(action)) {
            Subject s = new Subject();
            s.setId(Integer.parseInt(request.getParameter("id")));
            s.setSubjectNo(request.getParameter("subjectNo"));
            s.setSubjectName(request.getParameter("subjectName"));
            s.setCredit(Double.parseDouble(request.getParameter("credit")));
            s.setTeacherId(Integer.parseInt(request.getParameter("teacherId")));
            s.setSemester(request.getParameter("semester"));
            s.setDescription(request.getParameter("description"));
            model.updateSubject(s);
            response.sendRedirect(request.getContextPath() + "/SubjectServlet?action=list");
        } else if ("delete".equals(action)) {
            model.deleteSubject(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect(request.getContextPath() + "/SubjectServlet?action=list");
        } else if ("edit".equals(action)) {
            Subject s = model.getSubjectById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("subject", s);
            request.setAttribute("teacherList", teacherModel.getAllTeachers());
            request.getRequestDispatcher("/admin/subjectEdit.jsp").forward(request, response);
        } else if ("schedule".equals(action)) {
            request.setAttribute("subjectList", model.getAllSubjects());
            request.setAttribute("classList", classesModel.getAllClasses());
            request.getRequestDispatcher("/admin/schedule.jsp").forward(request, response);
        } else if ("doSchedule".equals(action)) {
            int classId = Integer.parseInt(request.getParameter("classId"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            model.assignClassSubject(classId, subjectId);
            response.sendRedirect(request.getContextPath() + "/SubjectServlet?action=schedule");
        } else if ("removeSchedule".equals(action)) {
            int classId = Integer.parseInt(request.getParameter("classId"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            model.removeClassSubject(classId, subjectId);
            response.sendRedirect(request.getContextPath() + "/SubjectServlet?action=schedule");
        } else {
            List<Subject> list = model.getAllSubjects();
            request.setAttribute("subjectList", list);
            request.getRequestDispatcher("/admin/subjectList.jsp").forward(request, response);
        }
    }
}
