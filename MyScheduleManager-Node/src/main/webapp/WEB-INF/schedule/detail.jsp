<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:useBean class="model.schedule.ScheduleBean" id="schedule" scope="session"></jsp:useBean>
    <jsp:useBean class="model.friend.FriendListBean" id="userName" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予定内容詳細</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/detail.css">
</head>
<body>
<h1>予定内容詳細</h1>
<form method="post">
<table border="1" id="table">
<tr>
<td>予定名</td><td><c:out value="${schedule.title }"/></td>
</tr>
<tr>
<td>概要</td><td class="detail"><c:out value="${schedule.summary }"/></td>
</tr>

<c:forEach var="i" begin="0" end="${userName.friendList.size()-1 }" step="1">
<tr>
<td class="label">関係者<c:out value="${i+1 }"/>人目</td>
<td class="content"><c:out value="${userName.friendList.get(i).name }"/>
<button formaction="FriendServlet" type="submit" name="state" value="detail,${userName.friendList.get(i).friendId }" >詳細</button></td>
</tr>
</c:forEach>

<tr><td>場所</td><td><c:out value="${schedule.location }" /></td></tr>

<tr><td>開始時間</td><td><c:out value="${schedule.startTime }"/></td></tr>
<tr>
<td>終了時間</td><td><c:out value="${schedule.endTime }"/></td>
</tr>
</table>
<div id="button">
<button type="submit" name="state" value="edit" formaction="ScheduleServlet">編集</button>
<button type="submit" name="state" value="listReturn" formaction="ScheduleServlet">戻る</button>
</div>
</form>
</body>
</html>