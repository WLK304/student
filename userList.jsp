<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.User" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>用户管理</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
    .header h1 { font-size: 18px; } .header a { color: white; text-decoration: none; }
    .container { max-width: 900px; margin: 20px auto; padding: 0 20px; }
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
<div class="header"><h1>👥 用户管理</h1><div><a href="${pageContext.request.contextPath}/admin/index.jsp">返回首页</a></div></div>
<div class="container">
    <div class="toolbar"><a href="userAdd.jsp" class="btn btn-primary">+ 添加用户</a></div>
    <table>
        <tr><th>ID</th><th>用户名</th><th>角色</th><th>状态</th><th>操作</th></tr>
        <% List<User> list = (List<User>) request.getAttribute("userList");
           if (list != null) { for (User u : list) { %>
        <tr>
            <td><%= u.getId() %></td><td><%= u.getUsername() %></td>
            <td><%= "admin".equals(u.getRole()) ? "管理员" : ("teacher".equals(u.getRole()) ? "教师" : "学生") %></td>
            <td><%= u.getStatus() == 1 ? "启用" : "禁用" %></td>
            <td>
                <a href="${pageContext.request.contextPath}/UserServlet?action=edit&id=<%= u.getId() %>" class="btn btn-warning">修改</a>
                <form class="form-inline" method="post" action="${pageContext.request.contextPath}/UserServlet?action=delete" onsubmit="return confirm('确定删除？');">
                    <input type="hidden" name="id" value="<%= u.getId() %>">
                    <button type="submit" class="btn btn-danger">删除</button>
                </form>
            </td>
        </tr>
        <% } } %>
    </table>
</div>
</body></html>
