<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <jsp:useBean class="model.schedule.ScheduleBean" id="schedule" scope="session" />
    <jsp:useBean class="model.friend.FriendListBean" id="userName" scope="session"></jsp:useBean>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/detail.css">
</head>
<body>
<h1>削除確認画面</h1>
<table border="1" id="table">
<tr><td class="label">タイトル</td><td class="content"><c:out value="${schedule.title }"/></td></tr>
<tr><td class="label">概要</td><td class="detail"><c:out value="${schedule.summary }"/></td></tr>
<tr><td class="label">種別</td><td class="content"><c:out value="${schedule.how }"/></td></tr>

<c:forEach var="i" begin="0" end="${userName.friendList.size()-1 }" step="1">
<tr>
<td class="label">関係者<c:out value="${i+1 }"/>人目</td>
<td class="content"><c:out value="${userName.friendList.get(i).name }"/>
<button type="submit" name="state" value="friendDetail,${userName.friendList.get(i).friendId }">詳細</button></td>
</tr>
</c:forEach>
<tr><td class="label">場所</td><td class="content"><c:out value="${schedule.location }"/></td></tr>
<tr><td class="label">開始時間</td><td class="content"><c:out value="${schedule.startTime }" /></td></tr>
<tr><td class="label">終了時間</td><td class="content"><c:out value="${schedule.endTime }"/></td></tr>
</table>
<form action="ScheduleServlet" method="post">
<p id="button">
<button type="submit" name="state" value="delete">削除</button>
<button type="submit" name="state" value="search">検索画面</button>
</p>
</form>
</body>
</html>