<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.friend.FriendListBean" id="friendList" scope="session"></jsp:useBean>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>友人検索結果</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/scheduleList.css">
</head>
<body>
<h1>友人検索結果</h1>
<form method="post">
<%--予定新規登録から参照された場合　表示しない --%>
<c:if test="${scheduleList == null }">
<p id="button"><button  name="state" value="search" formaction="FriendServlet">検索画面</button></p>
</c:if>
<%--予定新規登録から参照された場合のみ　表示する --%>
<c:if test="${scheduleList != null }">
<p id="button"><button name="state" value="new" formaction="ScheduleServlet">新規登録画面へ戻る</button></p>
</c:if>

<c:choose>
<c:when test="${friendList.friendList.size() <= 0 }">
<h1>まだ友人が登録されていません</h1>
</c:when>
<c:otherwise>
<table border="1" id="table">
<tr><th>FriendCode</th><th>Name</th><th>TEL</th><th>操作</th></tr>
<c:forEach var="i" begin="0" end="${friendList.friendList.size()-1 }" step="1">
<tr><td class="content">${friendList.friendList.get(i).friendId }</td>
<td class="content">${friendList.friendList.get(i).name }</td>
<td class="content">${friendList.friendList.get(i).tel }</td>
<td class="content">
<button type="submit" name="state" value="detail,${friendList.friendList.get(i).friendId }" formaction="FriendServlet">詳細</button>
<button type="submit" name="state" value="delete_confirm,${friendList.friendList.get(i).friendId }" formaction="FriendServlet">削除</button>
</td>
</tr>
</c:forEach> 
</table>
</c:otherwise>
</c:choose>
</form>
</body>
</html>