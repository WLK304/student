<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.User" %>
<% User user = (User) session.getAttribute("user"); %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>教师工作台</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #4CAF50 0%, #2E7D32 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
    .header h1 { font-size: 20px; } .header a { color: white; text-decoration: none; margin-left: 15px; }
    .container { max-width: 1200px; margin: 30px auto; padding: 0 20px; }
    .dashboard { display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px; }
    .card { background: white; border-radius: 8px; padding: 25px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); text-align: center; transition: transform 0.2s; }
    .card:hover { transform: translateY(-3px); }
    .card .icon { font-size: 40px; margin-bottom: 10px; }
    .card h3 { color: #333; margin-bottom: 8px; }
    .card p { color: #888; font-size: 13px; }
    .card a { text-decoration: none; color: inherit; display: block; }
</style>
</head>
<body>
<div class="header">
    <h1>🏫 高校教学管理系统 - 教师工作台</h1>
    <div>欢迎，<%= user.getUsername() %>（教师）<a href="${pageContext.request.contextPath}/LogoutServlet">退出登录</a></div>
</div>
<div class="container">
    <div class="dashboard">
        <div class="card"><a href="mySubjects.jsp">
            <div class="icon">📖</div><h3>我的课程</h3><p>查看本人教授的课程信息</p>
        </a></div>
        <div class="card"><a href="${pageContext.request.contextPath}/ScoreServlet?action=teacherList">
            <div class="icon">📊</div><h3>成绩录入</h3><p>查看课程及学生，录入成绩</p>
        </a></div>
        <div class="card"><a href="viewStudents.jsp">
            <div class="icon">👨‍🎓</div><h3>查看学生</h3><p>查看班级和学生信息</p>
        </a></div>
    </div>
</div>
</body></html>
