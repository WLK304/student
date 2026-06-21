<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>高校教学管理系统 - 登录</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: "Microsoft YaHei", sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; display: flex; align-items: center; justify-content: center; }
        .login-box { background: white; padding: 40px; border-radius: 10px; box-shadow: 0 15px 35px rgba(0,0,0,0.2); width: 400px; }
        .login-box h2 { text-align: center; color: #333; margin-bottom: 30px; font-size: 24px; }
        .login-box h3 { text-align: center; color: #666; margin-bottom: 20px; font-size: 14px; font-weight: normal; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 5px; color: #555; font-weight: bold; }
        .form-group input, .form-group select { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px; }
        .btn { width: 100%; padding: 12px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; border: none; border-radius: 5px; font-size: 16px; cursor: pointer; }
        .btn:hover { opacity: 0.9; }
        .error { color: red; text-align: center; margin-bottom: 15px; }
        .info { background: #f0f8ff; border: 1px solid #b0d4f1; padding: 12px; border-radius: 5px; margin-top: 20px; font-size: 12px; color: #555; line-height: 1.8; }
    </style>
</head>
<body>
    <div class="login-box">
        <h2>🏫 高校教学管理系统</h2>
        <h3>学生成绩管理平台</h3>
        <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <div class="form-group">
                <label>用户名：</label>
                <input type="text" name="username" placeholder="请输入用户名" required>
            </div>
            <div class="form-group">
                <label>密码：</label>
                <input type="password" name="password" placeholder="请输入密码" required>
            </div>
            <button type="submit" class="btn">登 录</button>
        </form>
        <div class="info">
            <strong>测试账号：</strong><br>
            管理员：admin / admin123<br>
            教师：zhang / 123456 &nbsp; li / 123456 &nbsp; wang / 123456<br>
            学生：zhangsan / 123456 &nbsp; lisi / 123456 &nbsp; wangwu / 123456
        </div>
    </div>
</body>
</html>
