<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.schedule.ScheduleBean" id="schedule" scope="session" />
    <jsp:useBean class="model.friend.FriendListBean" id="userName" scope="session"></jsp:useBean>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入力確認</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/scheduleList.css">
</head>
<body>
<h1>入力確認</h1>
<form  method="post">
<table border="1" id="table">
<tr><td >タイトル</td><td class="content"><c:out value="${schedule.title }"/></td></tr>
<tr><td >概要</td><td class="detail"><c:out value="${schedule.summary }"/></td></tr>
<tr><td>種別</td><td class="content"><c:out value="${schedule.how }"/></td></tr>

<c:forEach var="i" begin="0" end="${userName.friendList.size()-1 }" step="1">
<tr>
<td>関係者<c:out value="${i+1 }"/>人目</td>
<td class="content"><c:out value="${userName.friendList.get(i).name }"/>
</tr>
</c:forEach>
<tr><td>場所</td><td class="content"><c:out value="${schedule.location }"/></td></tr>
<tr><td>開始時間</td><td class="content"><c:out value="${schedule.startTime }" /></td></tr>
<tr><td>終了時間</td><td class="content"><c:out value="${schedule.endTime }"/></td></tr>
</table>
<p id="button">
<button type="submit" name="state" value="add" formaction="ScheduleServlet">登録</button>
<button type="submit" name="state" value="new" formaction="ScheduleServlet">戻る</button>
</p>
</form>
</body>
</html>