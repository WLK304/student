<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.SubjectStats" %>
<% SubjectStats s = (SubjectStats) request.getAttribute("stats");
   if (s == null) { response.sendRedirect(request.getContextPath() + "/StatisticsServlet"); return; }
   int[] d = s.getDistribution();
   int maxD = 1; for (int v : d) { if (v > maxD) maxD = v; }
   String[] barColors = {"bar-red", "bar-orange", "bar-yellow", "bar-blue", "bar-green"};
   String[] labels = {"不及格\n0-59", "及格\n60-69", "中等\n70-79", "良好\n80-89", "优秀\n90-100"};
   String[] grades = {"不及格", "及格", "中等", "良好", "优秀"};
   String passCls = s.getPassRate() >= 80 ? "pass-ok" : (s.getPassRate() >= 60 ? "pass-warn" : "pass-bad"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= s.getSubjectName() %> - 成绩统计详情</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
    .header h1 { font-size: 20px; }
    .header a { color: white; text-decoration: none; }
    .container { max-width: 900px; margin: 20px auto; padding: 0 20px; }
    .card { background: white; border-radius: 8px; padding: 25px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); margin-bottom: 20px; }
    .card h3 { color: #333; margin-bottom: 16px; border-bottom: 2px solid #667eea; padding-bottom: 8px; }
    .info-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 10px; margin-bottom: 16px; }
    .info-item { padding: 6px 0; }
    .info-item .lbl { font-weight: bold; color: #555; }
    .info-item .val { color: #333; }
    .stat-row { display: grid; grid-template-columns: repeat(5, 1fr); gap: 12px; margin-bottom: 20px; }
    .stat-box { background: #f8f9fa; border-radius: 8px; padding: 16px; text-align: center; }
    .stat-box .sv { font-size: 24px; font-weight: bold; color: #333; }
    .stat-box .sl { font-size: 11px; color: #888; margin-top: 2px; }
    .pass-ok { color: #4CAF50; font-weight: bold; }
    .pass-warn { color: #FF9800; font-weight: bold; }
    .pass-bad { color: #e74c3c; font-weight: bold; }
    .chart { display: flex; align-items: flex-end; gap: 32px; height: 240px; padding: 30px 0; border-bottom: 2px solid #ddd; justify-content: center; }
    .bar-wrap { display: flex; flex-direction: column; align-items: center; flex: 0 0 90px; }
    .bar { width: 56px; border-radius: 4px 4px 0 0; display: flex; align-items: flex-start; justify-content: center; padding-top: 6px; transition: height 0.6s; }
    .bar .num { color: white; font-size: 14px; font-weight: bold; }
    .bar-label { font-size: 12px; color: #666; margin-top: 10px; text-align: center; line-height: 1.4; }
    .bar-red { background: linear-gradient(180deg, #e74c3c 0%, #c0392b 100%); }
    .bar-orange { background: linear-gradient(180deg, #FF9800 0%, #E65100 100%); }
    .bar-yellow { background: linear-gradient(180deg, #FFEB3B 0%, #F9A825 100%); }
    .bar-yellow .num { color: #333; }
    .bar-blue { background: linear-gradient(180deg, #42A5F5 0%, #1565C0 100%); }
    .bar-green { background: linear-gradient(180deg, #66BB6A 0%, #2E7D32 100%); }
    .pie-summary { display: grid; grid-template-columns: repeat(5, 1fr); gap: 8px; margin-top: 16px; }
    .pie-item { text-align: center; padding: 10px; background: #f8f9fa; border-radius: 6px; }
    .pie-item .pi-num { font-size: 18px; font-weight: bold; color: #333; }
    .pie-item .pi-lbl { font-size: 11px; color: #888; }
    .back-link { margin-top: 10px; }
    .btn { padding: 8px 18px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; text-decoration: none; display: inline-block; }
    .btn-back { background: #95a5a6; color: white; }
</style>
</head>
<body>
<div class="header">
    <h1>📊 <%= s.getSubjectName() %> — 成绩统计详情</h1>
    <div><a href="${pageContext.request.contextPath}/StatisticsServlet" class="btn btn-back">返回统计总览</a></div>
</div>
<div class="container">
    <div class="card">
        <h3>📖 课程信息</h3>
        <div class="info-grid">
            <div class="info-item"><span class="lbl">课程编号：</span><span class="val"><%= s.getSubjectNo() %></span></div>
            <div class="info-item"><span class="lbl">课程名称：</span><span class="val"><%= s.getSubjectName() %></span></div>
            <div class="info-item"><span class="lbl">授课教师：</span><span class="val"><%= s.getTeacherName() != null ? s.getTeacherName() : "-" %></span></div>
            <div class="info-item"><span class="lbl">学期：</span><span class="val"><%= s.getSemester() != null ? s.getSemester() : "-" %></span></div>
        </div>
    </div>

    <div class="card">
        <h3>📊 成绩概况</h3>
        <div class="stat-row">
            <div class="stat-box"><div class="sv"><%= s.getStudentCount() %></div><div class="sl">参考人数</div></div>
            <div class="stat-box"><div class="sv"><%= s.getAvgScore() %></div><div class="sl">平均分</div></div>
            <div class="stat-box"><div class="sv"><%= s.getMaxScore() %></div><div class="sl">最高分</div></div>
            <div class="stat-box"><div class="sv"><%= s.getMinScore() %></div><div class="sl">最低分</div></div>
            <div class="stat-box"><div class="sv <%= passCls %>"><%= s.getPassRate() %>%</div><div class="sl">通过率（≥60）</div></div>
        </div>
    </div>

    <div class="card">
        <h3>📈 成绩分布图</h3>
        <div class="chart">
            <% for (int i = 0; i < 5; i++) {
                int h = maxD > 0 ? Math.max(8, d[i] * 220 / maxD) : 8; %>
            <div class="bar-wrap">
                <div class="bar <%= barColors[i] %>" style="height: <%= h %>px">
                    <span class="num"><%= d[i] %></span>
                </div>
                <div class="bar-label"><%= labels[i] %><br>(<%= s.getStudentCount() > 0 ? Math.round(d[i] * 100.0 / s.getStudentCount()) : 0 %>%)</div>
            </div>
            <% } %>
        </div>
        <div class="pie-summary">
            <% for (int i = 0; i < 5; i++) { %>
            <div class="pie-item" style="border-left: 4px solid <%= i == 0 ? "#e74c3c" : i == 1 ? "#FF9800" : i == 2 ? "#FFEB3B" : i == 3 ? "#42A5F5" : "#66BB6A" %>">
                <div class="pi-num"><%= d[i] %> 人</div>
                <div class="pi-lbl"><%= grades[i] %></div>
            </div>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>