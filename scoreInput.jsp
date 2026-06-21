<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.Subject, entity.Student" %>
<% Subject sub = (Subject) request.getAttribute("subject");
   List<Student> students = (List<Student>) request.getAttribute("studentList"); %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>成绩录入</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #4CAF50 0%, #2E7D32 100%); color: white; padding: 15px 30px; }
    .container { max-width: 800px; margin: 20px auto; padding: 20px; background: white; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
    h2 { color: #333; margin-bottom: 10px; }
    .info { color: #888; margin-bottom: 20px; }
    table { width: 100%; border-collapse: collapse; }
    th, td { padding: 10px; text-align: left; border-bottom: 1px solid #eee; }
    th { background: #f8f9fa; }
    .form-group input { width: 80px; padding: 5px; border: 1px solid #ddd; border-radius: 4px; }
    .btn { padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; }
    .btn-primary { background: #4CAF50; color: white; } .btn-back { background: #95a5a6; color: white; margin-left: 10px; }
</style>
</head>
<body>
<div class="header"><h1>📊 成绩录入</h1></div>
<div class="container">
    <h2><%= sub.getSubjectName() %>（<%= sub.getSubjectNo() %>）</h2>
    <p class="info">学分：<%= sub.getCredit() %> | 学期：<%= sub.getSemester() %></p>
    <form action="${pageContext.request.contextPath}/ScoreServlet?action=add" method="post">
        <input type="hidden" name="subjectId" value="<%= sub.getId() %>">
        <table>
            <tr><th>学号</th><th>姓名</th><th>班级</th><th>成绩</th></tr>
            <% if (students != null) { for (Student s : students) { %>
            <tr>
                <td><%= s.getStuNo() %></td><td><%= s.getName() %></td><td><%= s.getClassName() != null ? s.getClassName() : "" %></td>
                <td class="form-group"><input type="number" step="0.1" min="0" max="100" name="score_<%= s.getId() %>"></td>
            </tr>
            <% } } %>
        </table>
        <input type="hidden" name="semester" value="<%= sub.getSemester() %>">
        <div style="margin-top: 20px;">
            <button type="submit" class="btn btn-primary">批量保存成绩</button>
            <a href="${pageContext.request.contextPath}/ScoreServlet?action=teacherList" class="btn btn-back">返回</a>
        </div>
    </form>
</div>
</body></html>
