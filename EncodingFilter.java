package control;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    private String encoding = "UTF-8";
    
    public void init(FilterConfig config) throws ServletException {
        String param = config.getInitParameter("encoding");
        if (param != null) encoding = param;
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=" + encoding);
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getServletPath();
        
        // Allow login page and login servlet without authentication
        if (path.endsWith("login.jsp") || path.endsWith("LoginServlet")) {
            chain.doFilter(request, response);
            return;
        }
        
        // Allow static resources
        if (path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".jar")) {
            chain.doFilter(request, response);
            return;
        }
        
        // Check session
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        
        chain.doFilter(request, response);
    }
    
    public void destroy() {}
}
