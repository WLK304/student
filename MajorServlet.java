package control;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import entity.Major;
import model.MajorModel;

@WebServlet("/MajorServlet")
public class MajorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        MajorModel model = new MajorModel();
        
        if ("add".equals(action)) {
            Major m = new Major();
            m.setMajorNo(request.getParameter("majorNo"));
            m.setMajorName(request.getParameter("majorName"));
            m.setDescription(request.getParameter("description"));
            model.addMajor(m);
            response.sendRedirect(request.getContextPath() + "/MajorServlet?action=list");
        } else if ("update".equals(action)) {
            Major m = new Major();
            m.setId(Integer.parseInt(request.getParameter("id")));
            m.setMajorNo(request.getParameter("majorNo"));
            m.setMajorName(request.getParameter("majorName"));
            m.setDescription(request.getParameter("description"));
            model.updateMajor(m);
            response.sendRedirect(request.getContextPath() + "/MajorServlet?action=list");
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            model.deleteMajor(id);
            response.sendRedirect(request.getContextPath() + "/MajorServlet?action=list");
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Major m = model.getMajorById(id);
            request.setAttribute("major", m);
            request.getRequestDispatcher("/admin/majorEdit.jsp").forward(request, response);
        } else {
            List<Major> list = model.getAllMajors();
            request.setAttribute("majorList", list);
            request.getRequestDispatcher("/admin/majorList.jsp").forward(request, response);
        }
    }
}
