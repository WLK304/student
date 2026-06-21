<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.Student, model.StudentModel" %>
<% StudentModel sm = new StudentModel();
   List<Student> list = sm.getAllStudents(); %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>查看学生</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #4CAF50 0%, #2E7D32 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; }
    .header h1 { font-size: 18px; } .header a { color: white; text-decoration: none; }
    .container { max-width: 1200px; margin: 20px auto; padding: 0 20px; }
    table { width: 100%; border-collapse: collapse; background: white; box-shadow: 0 2px 8px rgba(0,0,0,0.1); border-radius: 8px; overflow: hidden; font-size: 13px; }
    th, td { padding: 10px 12px; text-align: left; border-bottom: 1px solid #eee; }
    th { background: #f8f9fa; font-weight: bold; color: #333; }
    tr:hover { background: #f5f5f5; }
</style>
</head>
<body>
<div class="header"><h1>👨‍🎓 学生信息</h1><div><a href="index.jsp">返回工作台</a></div></div>
<div class="container">
    <table>
        <tr><th>学号</th><th>姓名</th><th>性别</th><th>年龄</th><th>专业</th><th>班级</th><th>电话</th><th>邮箱</th></tr>
        <% if (list != null) { for (Student s : list) { %>
        <tr><td><%= s.getStuNo() %></td><td><%= s.getName() %></td><td><%= s.getGender() %></td>
            <td><%= s.getAge() %></td><td><%= s.getMajorName() %></td><td><%= s.getClassName() %></td>
            <td><%= s.getPhone() != null ? s.getPhone() : "" %></td><td><%= s.getEmail() != null ? s.getEmail() : "" %></td></tr>
        <% } } %>
    </table>
</div>
</body></html>
