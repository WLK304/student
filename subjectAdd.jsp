<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.Teacher, model.TeacherModel" %>
<% TeacherModel tm = new TeacherModel();
   List<Teacher> tlist = tm.getAllTeachers(); %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>添加课程</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 30px; }
    .header h1 { font-size: 18px; }
    .container { max-width: 600px; margin: 20px auto; padding: 20px; background: white; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
    .form-group { margin-bottom: 15px; }
    .form-group label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
    .form-group input, .form-group select, .form-group textarea { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; font-size: 14px; }
    .form-group textarea { height: 60px; resize: vertical; }
    .btn { padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; }
    .btn-primary { background: #667eea; color: white; } .btn-back { background: #95a5a6; color: white; margin-left: 10px; }
</style>
</head>
<body>
<div class="header"><h1>📖 添加课程</h1></div>
<div class="container">
    <form action="${pageContext.request.contextPath}/SubjectServlet?action=add" method="post">
        <div class="form-group"><label>课程编号：</label><input type="text" name="subjectNo" required></div>
        <div class="form-group"><label>课程名称：</label><input type="text" name="subjectName" required></div>
        <div class="form-group"><label>学分：</label><input type="number" step="0.1" name="credit" value="3.0" required></div>
        <div class="form-group"><label>授课教师：</label>
            <select name="teacherId" required>
                <% if (tlist != null) { for (Teacher t : tlist) { %>
                <option value="<%= t.getId() %>"><%= t.getName() %></option>
                <% } } %>
            </select>
        </div>
        <div class="form-group"><label>学期：</label><input type="text" name="semester" placeholder="如：2024-2025-1"></div>
        <div class="form-group"><label>描述：</label><textarea name="description"></textarea></div>
        <button type="submit" class="btn btn-primary">保存</button>
        <a href="${pageContext.request.contextPath}/SubjectServlet?action=list" class="btn btn-back">返回</a>
    </form>
</div>
</body></html>
