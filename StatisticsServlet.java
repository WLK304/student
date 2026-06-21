package control;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import entity.SubjectStats;
import model.ScoreModel;
import model.SubjectModel;

@WebServlet("/StatisticsServlet")
public class StatisticsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ScoreModel scoreModel = new ScoreModel();

        if ("detail".equals(action)) {
            // Single subject detailed view
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            SubjectStats stats = scoreModel.getSubjectStats(subjectId);
            request.setAttribute("stats", stats);
            request.getRequestDispatcher("/admin/statisticsDetail.jsp").forward(request, response);
        } else {
            // Overview: all subjects
            List<SubjectStats> statsList = scoreModel.getAllSubjectStats();
            request.setAttribute("statsList", statsList);
            request.setAttribute("subjectModel", new SubjectModel());
            request.getRequestDispatcher("/admin/statistics.jsp").forward(request, response);
        }
    }
}