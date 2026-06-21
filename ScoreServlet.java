package control;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import entity.Score;
import entity.Student;
import entity.Subject;
import model.ScoreModel;
import model.StudentModel;
import model.SubjectModel;

@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ScoreModel model = new ScoreModel();
        StudentModel studentModel = new StudentModel();
        SubjectModel subjectModel = new SubjectModel();
        
        HttpSession session = request.getSession();
        entity.User user = (entity.User) session.getAttribute("user");
        
        if ("add".equals(action)) {
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            String semester = request.getParameter("semester");
            int count = 0;
            // Handle batch: iterate through all parameters with prefix "score_"
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                if (paramName.startsWith("score_")) {
                    String scoreVal = request.getParameter(paramName);
                    if (scoreVal != null && !scoreVal.trim().isEmpty()) {
                        int studentId = Integer.parseInt(paramName.substring(6));
                        Score sc = new Score();
                        sc.setStudentId(studentId);
                        sc.setSubjectId(subjectId);
                        sc.setScore(Double.parseDouble(scoreVal));
                        sc.setSemester(semester);
                        model.addScore(sc);
                        count++;
                    }
                }
            }
            if ("teacher".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/ScoreServlet?action=teacherList");
            } else {
                response.sendRedirect(request.getContextPath() + "/ScoreServlet?action=list");
            }
        } else if ("update".equals(action)) {
            Score sc = new Score();
            sc.setId(Integer.parseInt(request.getParameter("id")));
            sc.setScore(Double.parseDouble(request.getParameter("score")));
            sc.setSemester(request.getParameter("semester"));
            model.updateScore(sc);
            if ("teacher".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/ScoreServlet?action=teacherList");
            } else {
                response.sendRedirect(request.getContextPath() + "/ScoreServlet?action=list");
            }
        } else if ("delete".equals(action)) {
            model.deleteScore(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect(request.getContextPath() + "/ScoreServlet?action=list");
        } else if ("input".equals(action)) {
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            Subject sub = subjectModel.getSubjectById(subjectId);
            List<Student> students = studentModel.getAllStudents();
            request.setAttribute("subject", sub);
            request.setAttribute("studentList", students);
            request.getRequestDispatcher("/teacher/scoreInput.jsp").forward(request, response);
        } else if ("teacherList".equals(action)) {
            int teacherId = user.getRoleId();
            List<Subject> subjects = subjectModel.getSubjectsByTeacherId(teacherId);
            request.setAttribute("subjectList", subjects);
            request.setAttribute("scoreModel", model);
            request.getRequestDispatcher("/teacher/scoreList.jsp").forward(request, response);
        } else {
            List<Score> list = model.getAllScores();
            request.setAttribute("scoreList", list);
            request.setAttribute("studentList", studentModel.getAllStudents());
            request.setAttribute("subjectList", subjectModel.getAllSubjects());
            request.getRequestDispatcher("/admin/scoreList.jsp").forward(request, response);
        }
    }
}
