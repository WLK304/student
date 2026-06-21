<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.Teacher" %>
<% Teacher t = (Teacher) request.getAttribute("teacher"); %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>修改教师</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 30px; }
    .container { max-width: 600px; margin: 20px auto; padding: 20px; background: white; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
    .form-group { margin-bottom: 15px; }
    .form-group label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
    .form-group input, .form-group select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; font-size: 14px; }
    .btn { padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; }
    .btn-primary { background: #667eea; color: white; } .btn-back { background: #95a5a6; color: white; margin-left: 10px; }
</style>
</head>
<body>
<div class="header"><h1>👩‍🏫 修改教师</h1></div>
<div class="container">
    <form action="${pageContext.request.contextPath}/TeacherServlet?action=update" method="post">
        <input type="hidden" name="id" value="<%= t.getId() %>">
        <div class="form-group"><label>教师编号：</label><input type="text" name="teacherNo" value="<%= t.getTeacherNo() %>" required></div>
        <div class="form-group"><label>姓名：</label><input type="text" name="name" value="<%= t.getName() %>" required></div>
        <div class="form-group"><label>性别：</label>
            <select name="gender">
                <option value="男" <%= "男".equals(t.getGender()) ? "selected" : "" %>>男</option>
                <option value="女" <%= "女".equals(t.getGender()) ? "selected" : "" %>>女</option>
            </select>
        </div>
        <div class="form-group"><label>职称：</label><input type="text" name="title" value="<%= t.getTitle() != null ? t.getTitle() : "" %>"></div>
        <div class="form-group"><label>电话：</label><input type="text" name="phone" value="<%= t.getPhone() != null ? t.getPhone() : "" %>"></div>
        <div class="form-group"><label>邮箱：</label><input type="email" name="email" value="<%= t.getEmail() != null ? t.getEmail() : "" %>"></div>
        <button type="submit" class="btn btn-primary">保存</button>
        <a href="${pageContext.request.contextPath}/TeacherServlet?action=list" class="btn btn-back">返回</a>
    </form>
</div>
</body></html>
