<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:useBean class="model.schedule.ScheduleListBean" id="scheduleList" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予定検索結果</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/scheduleList.css">
</head>
<body>
<h1>検索結果</h1>

<input type="hidden" id="userId" value="${user.id }">

<p>色判別<br>
<span id="red">赤</span>：終了時間が過ぎた予定<br>
<span id="blue">青</span>：実行中の予定<br>
<span id="green">緑</span>：開始時間も終了時間も過ぎてない予定</p>

<form action="ScheduleServlet" method="post">
<div id="button">
<button name="state" value="search">検索条件</button>
<button name="state" value="new">新規登録</button>
</div>
<c:choose>
<c:when test="${scheduleList.getList().size()<= 0 }">
<h1>指定された期間内に予定は登録されていません</h1>
</c:when>
<c:otherwise>

<table border="3" id="table">
<tr><th>ScheduleId</th><th>開始時間</th><th>終了時間</th><th>タイトル</th><th>タイプ</th><th>操作</th></tr>
<c:forEach var="i" begin="0" end="${scheduleList.getList().size()-1 }" step="1">
<tr id="list">
<td class="content contentColor"><c:out value="${scheduleList.getList().get(i).scheduleId }"/></td>
<td class="content contentColor"><span class="start"><c:out value="${scheduleList.getList().get(i).startTime }"/></span></td>
<td class="content contentColor"><span class="end"><c:out value="${scheduleList.getList().get(i).endTime }"/></span></td>
<td class="content contentColor"><c:out value="${scheduleList.getList().get(i).title }"/></td>
<td class="content contentColor"><c:out value="${scheduleList.getList().get(i).how }" /></td>
<td class="content contentColor">
<button type="submit" name="state" value="detail,${scheduleList.getList().get(i).scheduleId}">詳細</button>
<button type="submit" name="state" value="delete_confirm,${scheduleList.getList().get(i).scheduleId }">削除</button>
</td>
</tr>
</c:forEach>
</table>
</c:otherwise>
</c:choose>
<%--javascript読み込み --%>
<script src="js/scheduleList.js"></script>s
</form>
</body>
</html>