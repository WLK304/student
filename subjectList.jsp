<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.Subject" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>课程管理</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
    .header h1 { font-size: 18px; } .header a { color: white; text-decoration: none; }
    .container { max-width: 1100px; margin: 20px auto; padding: 0 20px; }
    .toolbar { margin-bottom: 20px; }
    .btn { padding: 8px 16px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; text-decoration: none; display: inline-block; }
    .btn-primary { background: #667eea; color: white; } .btn-danger { background: #e74c3c; color: white; }
    .btn-warning { background: #f39c12; color: white; }
    table { width: 100%; border-collapse: collapse; background: white; box-shadow: 0 2px 8px rgba(0,0,0,0.1); border-radius: 8px; overflow: hidden; }
    th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #eee; }
    th { background: #f8f9fa; font-weight: bold; color: #333; }
    tr:hover { background: #f5f5f5; } .form-inline { display: inline; }
</style>
</head>
<body>
<div class="header"><h1>📖 课程管理</h1><div><a href="${pageContext.request.contextPath}/admin/index.jsp">返回首页</a></div></div>
<div class="container">
    <div class="toolbar"><a href="subjectAdd.jsp" class="btn btn-primary">+ 添加课程</a></div>
    <table>
        <tr><th>ID</th><th>课程编号</th><th>课程名称</th><th>学分</th><th>授课教师</th><th>学期</th><th>操作</th></tr>
        <% List<Subject> list = (List<Subject>) request.getAttribute("subjectList");
           if (list != null) { for (Subject s : list) { %>
        <tr>
            <td><%= s.getId() %></td><td><%= s.getSubjectNo() %></td><td><%= s.getSubjectName() %></td>
            <td><%= s.getCredit() %></td><td><%= s.getTeacherName() != null ? s.getTeacherName() : "" %></td>
            <td><%= s.getSemester() != null ? s.getSemester() : "" %></td>
            <td>
                <a href="${pageContext.request.contextPath}/SubjectServlet?action=edit&id=<%= s.getId() %>" class="btn btn-warning">修改</a>
                <form class="form-inline" method="post" action="${pageContext.request.contextPath}/SubjectServlet?action=delete" onsubmit="return confirm('确定删除？');">
                    <input type="hidden" name="id" value="<%= s.getId() %>">
                    <button type="submit" class="btn btn-danger">删除</button>
                </form>
            </td>
        </tr>
        <% } } %>
    </table>
</div>
</body></html>
