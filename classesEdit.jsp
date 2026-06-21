<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.Classes, java.util.List, entity.Major" %>
<% Classes c = (Classes) request.getAttribute("classes");
   List<Major> mlist = (List<Major>) request.getAttribute("majorList"); %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>修改班级</title>
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
<div class="header"><h1>🏫 修改班级</h1></div>
<div class="container">
    <form action="${pageContext.request.contextPath}/ClassesServlet?action=update" method="post">
        <input type="hidden" name="id" value="<%= c.getId() %>">
        <div class="form-group"><label>班级编号：</label><input type="text" name="classNo" value="<%= c.getClassNo() %>" required></div>
        <div class="form-group"><label>班级名称：</label><input type="text" name="className" value="<%= c.getClassName() %>" required></div>
        <div class="form-group"><label>所属专业：</label>
            <select name="majorId" required>
                <% if (mlist != null) { for (Major m : mlist) { %>
                <option value="<%= m.getId() %>" <%= m.getId() == c.getMajorId() ? "selected" : "" %>><%= m.getMajorName() %></option>
                <% } } %>
            </select>
        </div>
        <div class="form-group"><label>年级：</label><input type="text" name="grade" value="<%= c.getGrade() != null ? c.getGrade() : "" %>"></div>
        <button type="submit" class="btn btn-primary">保存</button>
        <a href="${pageContext.request.contextPath}/ClassesServlet?action=list" class="btn btn-back">返回</a>
    </form>
</div>
</body></html>
