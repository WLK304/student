<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.Subject, entity.Score, model.ScoreModel, model.StudentModel" %>
<% List<Subject> subjects = (List<Subject>) request.getAttribute("subjectList");
   ScoreModel scoreModel = (ScoreModel) request.getAttribute("scoreModel");
   StudentModel studentModel = new StudentModel(); %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>成绩管理</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #4CAF50 0%, #2E7D32 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; }
    .header h1 { font-size: 18px; } .header a { color: white; text-decoration: none; }
    .container { max-width: 1100px; margin: 20px auto; padding: 0 20px; }
    .card { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); margin-bottom: 20px; }
    .card h3 { margin-bottom: 15px; color: #333; border-bottom: 2px solid #4CAF50; padding-bottom: 8px; }
    table { width: 100%; border-collapse: collapse; font-size: 13px; }
    th, td { padding: 10px; text-align: left; border-bottom: 1px solid #eee; }
    th { background: #f8f9fa; }
    .btn { padding: 6px 12px; border: none; border-radius: 4px; cursor: pointer; font-size: 13px; text-decoration: none; display: inline-block; }
    .btn-primary { background: #4CAF50; color: white; }
</style>
</head>
<body>
<div class="header"><h1>📊 成绩录入与管理</h1><div><a href="index.jsp">返回工作台</a></div></div>
<div class="container">
    <% if (subjects != null) { for (Subject sub : subjects) {
        List<Score> scores = scoreModel.getScoresBySubjectId(sub.getId()); %>
    <div class="card">
        <h3><%= sub.getSubjectName() %>（<%= sub.getSubjectNo() %>）- 学分：<%= sub.getCredit() %> | <%= sub.getSemester() %></h3>
        <table>
            <tr><th>学号</th><th>姓名</th><th>班级</th><th>成绩</th><th>操作</th></tr>
            <% if (scores != null) { for (Score sc : scores) { %>
            <tr><td><%= sc.getStuNo() %></td><td><%= sc.getStudentName() %></td>
                <td><%= sc.getClassName() != null ? sc.getClassName() : "" %></td>
                <td><%= sc.getScore() %></td>
                <td></td></tr>
            <% } } %>
        </table>
        <div style="margin-top: 10px;">
            <a href="${pageContext.request.contextPath}/ScoreServlet?action=input&subjectId=<%= sub.getId() %>" class="btn btn-primary">录入/修改成绩</a>
        </div>
    </div>
    <% } } %>
</div>
</body></html>
