<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.Score, entity.Student, model.ScoreModel" %>
<% Student stu = (Student) session.getAttribute("student");
   ScoreModel sm = new ScoreModel();
   List<Score> list = null;
   double totalCredits = 0, totalPoints = 0;
   if (stu != null) { list = sm.getScoresByStudentId(stu.getId()); } %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>我的成绩</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #2196F3 0%, #1565C0 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; }
    .header h1 { font-size: 18px; } .header a { color: white; text-decoration: none; }
    .container { max-width: 900px; margin: 20px auto; padding: 0 20px; }
    table { width: 100%; border-collapse: collapse; background: white; box-shadow: 0 2px 8px rgba(0,0,0,0.1); border-radius: 8px; overflow: hidden; }
    th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #eee; }
    th { background: #f8f9fa; font-weight: bold; color: #333; }
    .summary { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); margin-top: 20px; }
    .summary h3 { color: #333; margin-bottom: 10px; }
</style>
</head>
<body>
<div class="header"><h1>📊 我的成绩</h1><div><a href="index.jsp">返回首页</a></div></div>
<div class="container">
    <table>
        <tr><th>课程编号</th><th>课程名称</th><th>学分</th><th>成绩</th><th>学期</th></tr>
        <% if (list != null) { for (Score sc : list) { 
           totalCredits += sc.getCredit();
           totalPoints += sc.getCredit() * sc.getScore(); %>
        <tr><td><%= sc.getSubjectNo() %></td><td><%= sc.getSubjectName() %></td><td><%= sc.getCredit() %></td>
            <td><%= sc.getScore() %></td><td><%= sc.getSemester() != null ? sc.getSemester() : "" %></td></tr>
        <% } } %>
    </table>
    <% if (list != null && list.size() > 0) { %>
    <div class="summary">
        <h3>成绩统计</h3>
        <p>总修学分：<%= totalCredits %> | 加权平均分：<%= String.format("%.1f", totalCredits > 0 ? totalPoints / totalCredits : 0) %></p>
    </div>
    <% } %>
</div>
</body></html>
