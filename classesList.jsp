<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.Classes, entity.Major" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>班级管理</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
    .header h1 { font-size: 18px; } .header a { color: white; text-decoration: none; }
    .container { max-width: 1000px; margin: 20px auto; padding: 0 20px; }
    .toolbar { margin-bottom: 20px; }
    .btn { padding: 8px 16px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; text-decoration: none; display: inline-block; }
    .btn-primary { background: #667eea; color: white; } .btn-danger { background: #e74c3c; color: white; }
    .btn-warning { background: #f39c12; color: white; }
    table { width: 100%; border-collapse: collapse; background: white; box-shadow: 0 2px 8px rgba(0,0,0,0.1); border-radius: 8px; overflow: hidden; }
    th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #eee; }
    th { background: #f8f9fa; font-weight: bold; color: #333; }
    tr:hover { background: #f5f5f5; }
    .form-inline { display: inline; }
</style>
</head>
<body>
<div class="header"><h1>🏫 班级管理</h1><div><a href="${pageContext.request.contextPath}/admin/index.jsp">返回首页</a></div></div>
<div class="container">
    <div class="toolbar"><a href="classesAdd.jsp" class="btn btn-primary">+ 添加班级</a></div>
    <table>
        <tr><th>ID</th><th>班级编号</th><th>班级名称</th><th>所属专业</th><th>年级</th><th>操作</th></tr>
        <% List<Classes> list = (List<Classes>) request.getAttribute("classesList");
           if (list != null) { for (Classes c : list) { %>
        <tr>
            <td><%= c.getId() %></td><td><%= c.getClassNo() %></td><td><%= c.getClassName() %></td>
            <td><%= c.getMajorName() != null ? c.getMajorName() : "" %></td><td><%= c.getGrade() != null ? c.getGrade() : "" %></td>
            <td>
                <a href="${pageContext.request.contextPath}/ClassesServlet?action=edit&id=<%= c.getId() %>" class="btn btn-warning">修改</a>
                <form class="form-inline" method="post" action="${pageContext.request.contextPath}/ClassesServlet?action=delete" onsubmit="return confirm('确定删除？');">
                    <input type="hidden" name="id" value="<%= c.getId() %>">
                    <button type="submit" class="btn btn-danger">删除</button>
                </form>
            </td>
        </tr>
        <% } } %>
    </table>
</div>
</body></html>
