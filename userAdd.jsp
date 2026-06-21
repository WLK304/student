<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>添加用户</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 30px; }
    .container { max-width: 500px; margin: 20px auto; padding: 20px; background: white; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
    .form-group { margin-bottom: 15px; }
    .form-group label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
    .form-group input, .form-group select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; font-size: 14px; }
    .btn { padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; }
    .btn-primary { background: #667eea; color: white; } .btn-back { background: #95a5a6; color: white; margin-left: 10px; }
</style>
</head>
<body>
<div class="header"><h1>👥 添加用户</h1></div>
<div class="container">
    <form action="${pageContext.request.contextPath}/UserServlet?action=add" method="post">
        <div class="form-group"><label>用户名：</label><input type="text" name="username" required></div>
        <div class="form-group"><label>密码：</label><input type="password" name="password" required></div>
        <div class="form-group"><label>角色：</label>
            <select name="role" required>
                <option value="admin">管理员</option><option value="teacher">教师</option><option value="student">学生</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">保存</button>
        <a href="${pageContext.request.contextPath}/UserServlet?action=list" class="btn btn-back">返回</a>
    </form>
</div>
</body></html>
