<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.Subject, entity.Classes, model.SubjectModel" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>排课管理</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
    .header h1 { font-size: 18px; } .header a { color: white; text-decoration: none; }
    .container { max-width: 1000px; margin: 20px auto; padding: 0 20px; }
    .card { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); margin-bottom: 20px; }
    .card h3 { color: #333; margin-bottom: 15px; }
    .form-group { margin-bottom: 15px; }
    .form-group label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
    .form-group select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; font-size: 14px; }
    .btn { padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; }
    .btn-primary { background: #667eea; color: white; } .btn-danger { background: #e74c3c; color: white; }
    table { width: 100%; border-collapse: collapse; margin-top: 15px; }
    th, td { padding: 10px; text-align: left; border-bottom: 1px solid #eee; }
    th { background: #f8f9fa; } .form-inline { display: inline; }
</style>
</head>
<body>
<div class="header"><h1>📅 排课管理</h1><div><a href="${pageContext.request.contextPath}/admin/index.jsp">返回首页</a></div></div>
<div class="container">
    <div class="card">
        <h3>为班级安排课程</h3>
        <form action="${pageContext.request.contextPath}/SubjectServlet?action=doSchedule" method="post">
            <div class="form-group"><label>选择班级：</label>
                <select name="classId" required>
                    <% List<Classes> classList = (List<Classes>) request.getAttribute("classList");
                       if (classList != null) { for (Classes c : classList) { %>
                    <option value="<%= c.getId() %>"><%= c.getClassName() %> (<%= c.getMajorName() %>)</option>
                    <% } } %>
                </select>
            </div>
            <div class="form-group"><label>选择课程：</label>
                <select name="subjectId" required>
                    <% List<Subject> subList = (List<Subject>) request.getAttribute("subjectList");
                       if (subList != null) { for (Subject s : subList) { %>
                    <option value="<%= s.getId() %>"><%= s.getSubjectName() %> (<%= s.getTeacherName() %>)</option>
                    <% } } %>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">添加排课</button>
        </form>
    </div>
</div>
</body></html>
