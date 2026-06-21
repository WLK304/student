<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.Subject, entity.Student, model.SubjectModel" %>
<% Student stu = (Student) session.getAttribute("student");
   List<Subject> list = null;
   if (stu != null) {
       SubjectModel sm = new SubjectModel();
       list = sm.getSubjectsByClassId(stu.getClassId());
   } %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>我的课程</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #2196F3 0%, #1565C0 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; }
    .header h1 { font-size: 18px; } .header a { color: white; text-decoration: none; }
    .container { max-width: 900px; margin: 20px auto; padding: 0 20px; }
    table { width: 100%; border-collapse: collapse; background: white; box-shadow: 0 2px 8px rgba(0,0,0,0.1); border-radius: 8px; overflow: hidden; }
    th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #eee; }
    th { background: #f8f9fa; font-weight: bold; color: #333; }
</style>
</head>
<body>
<div class="header"><h1>📖 我的课程</h1><div><a href="index.jsp">返回首页</a></div></div>
<div class="container">
    <table>
        <tr><th>课程编号</th><th>课程名称</th><th>学分</th><th>授课教师</th><th>学期</th></tr>
        <% if (list != null) { for (Subject s : list) { %>
        <tr><td><%= s.getSubjectNo() %></td><td><%= s.getSubjectName() %></td><td><%= s.getCredit() %></td>
            <td><%= s.getTeacherName() != null ? s.getTeacherName() : "" %></td>
            <td><%= s.getSemester() != null ? s.getSemester() : "" %></td></tr>
        <% } } %>
    </table>
</div>
</body></html>
