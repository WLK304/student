<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entity.SubjectStats" %>
<% List<SubjectStats> statsList = (List<SubjectStats>) request.getAttribute("statsList");
   double totalAvg = 0; int totalCount = 0; int totalPass = 0;
   int[] totalDist = new int[5];
   if (statsList != null) {
       for (SubjectStats s : statsList) {
           totalCount += s.getStudentCount();
           totalAvg += s.getAvgScore() * s.getStudentCount();
           totalPass += (int)(s.getPassRate() * s.getStudentCount() / 100);
           totalDist[0] += s.getD0(); totalDist[1] += s.getD1();
           totalDist[2] += s.getD2(); totalDist[3] += s.getD3(); totalDist[4] += s.getD4();
       }
       if (totalCount > 0) totalAvg = Math.round(totalAvg / totalCount * 10.0) / 10.0;
   }
   int maxTotalDist = 1;
   for (int d : totalDist) { if (d > maxTotalDist) maxTotalDist = d; }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教学统计报表 - 高校教学管理系统</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: "Microsoft YaHei", sans-serif; background: #f0f2f5; }
    .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
    .header h1 { font-size: 20px; }
    .header a { color: white; text-decoration: none; margin-left: 15px; }
    .container { max-width: 1100px; margin: 20px auto; padding: 0 20px; }
    .card { background: white; border-radius: 8px; padding: 25px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); margin-bottom: 24px; }
    .card h3 { color: #333; margin-bottom: 16px; border-bottom: 2px solid #667eea; padding-bottom: 8px; display: flex; align-items: center; gap: 8px; }

    /* Summary boxes */
    .summary-row { display: grid; grid-template-columns: repeat(4, 1fr); gap: 16px; margin-bottom: 24px; }
    .summary-box { background: white; border-radius: 8px; padding: 20px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); text-align: center; }
    .summary-box .val { font-size: 32px; font-weight: bold; }
    .summary-box .lbl { font-size: 13px; color: #888; margin-top: 4px; }
    .c-blue .val { color: #2196F3; } .c-green .val { color: #4CAF50; } .c-orange .val { color: #FF9800; } .c-purple .val { color: #9C27B0; }

    /* Table */
    table { width: 100%; border-collapse: collapse; font-size: 13px; }
    th, td { padding: 10px 12px; text-align: left; border-bottom: 1px solid #eee; }
    th { background: #f8f9fa; font-weight: bold; color: #333; white-space: nowrap; }
    tr:hover { background: #f5f5f5; }
    .pass-ok { color: #4CAF50; font-weight: bold; }
    .pass-warn { color: #FF9800; font-weight: bold; }
    .pass-bad { color: #e74c3c; font-weight: bold; }
    .btn { padding: 6px 14px; border: none; border-radius: 4px; cursor: pointer; font-size: 13px; text-decoration: none; display: inline-block; }
    .btn-sm { background: #667eea; color: white; }

    /* Bar chart */
    .chart { display: flex; align-items: flex-end; gap: 24px; height: 200px; padding: 20px 0; border-bottom: 2px solid #ddd; position: relative; justify-content: center; }
    .bar-wrap { display: flex; flex-direction: column; align-items: center; flex: 0 0 80px; }
    .bar { width: 50px; border-radius: 4px 4px 0 0; position: relative; transition: height 0.5s; display: flex; align-items: flex-start; justify-content: center; padding-top: 6px; }
    .bar .num { color: white; font-size: 12px; font-weight: bold; }
    .bar-label { font-size: 11px; color: #666; margin-top: 8px; text-align: center; line-height: 1.3; }
    .chart-title { text-align: center; color: #888; font-size: 13px; margin-top: 8px; }
    .bar-red { background: linear-gradient(180deg, #e74c3c 0%, #c0392b 100%); }
    .bar-orange { background: linear-gradient(180deg, #FF9800 0%, #E65100 100%); }
    .bar-yellow { background: linear-gradient(180deg, #FFEB3B 0%, #F9A825 100%); }
    .bar-yellow .num { color: #333; }
    .bar-blue { background: linear-gradient(180deg, #42A5F5 0%, #1565C0 100%); }
    .bar-green { background: linear-gradient(180deg, #66BB6A 0%, #2E7D32 100%); }

    /* Course detail chart card */
    .course-card { background: white; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); margin-bottom: 20px; overflow: hidden; }
    .course-card-header { padding: 16px 20px; background: #f8f9fa; display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #eee; }
    .course-card-body { padding: 20px; }
    .course-stats { display: grid; grid-template-columns: repeat(4, 1fr); gap: 12px; margin-bottom: 16px; }
    .stat-item { text-align: center; }
    .stat-item .sval { font-size: 22px; font-weight: bold; color: #333; }
    .stat-item .slbl { font-size: 11px; color: #888; }
</style>
</head>
<body>
<div class="header">
    <h1>📊 教学统计报表</h1>
    <div><a href="${pageContext.request.contextPath}/admin/index.jsp">返回首页</a></div>
</div>
<div class="container">

    <!-- ========== Overall Summary ========== -->
    <div class="summary-row">
        <div class="summary-box c-blue">
            <div class="val"><%= totalCount %></div>
            <div class="lbl">总成绩记录数</div>
        </div>
        <div class="summary-box c-green">
            <div class="val"><%= totalCount > 0 ? totalAvg : 0 %></div>
            <div class="lbl">全校平均分</div>
        </div>
        <div class="summary-box c-orange">
            <div class="val"><%= totalCount > 0 ? Math.round(totalPass * 100.0 / totalCount) : 0 %>%</div>
            <div class="lbl">整体通过率</div>
        </div>
        <div class="summary-box c-purple">
            <div class="val"><%= statsList != null ? statsList.size() : 0 %></div>
            <div class="lbl">纳入统计课程数</div>
        </div>
    </div>

    <!-- ========== Overall Distribution Chart ========== -->
    <div class="card">
        <h3>📈 全校成绩分布</h3>
        <div class="chart">
            <% String[] barColors = {"bar-red", "bar-orange", "bar-yellow", "bar-blue", "bar-green"};
               String[] labels = {"不及格\n0-59", "及格\n60-69", "中等\n70-79", "良好\n80-89", "优秀\n90-100"};
               for (int i = 0; i < 5; i++) {
                   int h = maxTotalDist > 0 ? Math.max(10, totalDist[i] * 180 / maxTotalDist) : 10; %>
            <div class="bar-wrap">
                <div class="bar <%= barColors[i] %>" style="height: <%= h %>px">
                    <span class="num"><%= totalDist[i] %></span>
                </div>
                <div class="bar-label"><%= labels[i] %></div>
            </div>
            <% } %>
        </div>
        <div class="chart-title">▲ 各分数段学生人数分布（柱高按最大分段比例缩放）</div>
    </div>

    <!-- ========== Per-Course Stats ========== -->
    <div class="card">
        <h3>📋 各课程成绩统计</h3>
        <table>
            <tr>
                <th>课程名称</th><th>授课教师</th><th>人数</th><th>平均分</th><th>最高分</th><th>最低分</th>
                <th>通过率</th><th>不及格</th><th>60-69</th><th>70-79</th><th>80-89</th><th>90+</th><th>操作</th>
            </tr>
            <% if (statsList != null) { for (SubjectStats s : statsList) {
                String passCls = s.getPassRate() >= 80 ? "pass-ok" : (s.getPassRate() >= 60 ? "pass-warn" : "pass-bad"); %>
            <tr>
                <td><strong><%= s.getSubjectName() %></strong></td>
                <td><%= s.getTeacherName() != null ? s.getTeacherName() : "" %></td>
                <td><%= s.getStudentCount() %></td>
                <td><%= s.getAvgScore() %></td>
                <td><%= s.getMaxScore() %></td>
                <td><%= s.getMinScore() %></td>
                <td class="<%= passCls %>"><%= s.getPassRate() %>%</td>
                <td><%= s.getD0() %></td><td><%= s.getD1() %></td><td><%= s.getD2() %></td>
                <td><%= s.getD3() %></td><td><%= s.getD4() %></td>
                <td><a href="${pageContext.request.contextPath}/StatisticsServlet?action=detail&subjectId=<%= s.getSubjectId() %>" class="btn btn-sm">详情</a></td>
            </tr>
            <% } } %>
        </table>
    </div>
</div>
</body>
</html>