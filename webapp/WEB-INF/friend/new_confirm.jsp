<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:useBean class="model.friend.FriendInfo" id="friend" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入力確認</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/confirm_friend.css">
</head>
<body>
<h1>入力確認</h1>
<form action="FriendServlet" method="post" >
<table border="1" id="table">
<tr><td class="content">顔写真</td><td class="content"><div id="imgCenter"><img src="${imageLink }" id="image"/></div></td></tr>
<tr><td class="content">UserId</td><td class="content"><c:out value="${friend.userId }"/></td></tr>
<tr><td class="content">友人詳細</td><td class="content"><c:out value="${friend.introduction }"/></td></tr>
<tr><td class="content">Name</td><td class="content"><c:out value="${friend.name }"/></td></tr>
<tr><td class="content">Sex</td><td class="content"><c:out value="${friend.sex }"/></td></tr>
<tr><td class="content">Status</td><td class="content"><c:out value="${friend.status }"/></td></tr>
<tr><td class="content">Email</td><td class="content"><c:out value="${friend.email }"/></td></tr>
<tr><td class="content">Address</td><td class="content"><c:out value="${friend.address }"/></td></tr>
<tr><td class="content">TEL</td><td class="content"><c:out value="${friend.tel }"/></td></tr>
</table>
<p class="content">
<button type="submit" name="state" value="add">登録</button>
<button type="submit" name="state" value="new">戻る</button>
</p>
</form>
</body>
</html>