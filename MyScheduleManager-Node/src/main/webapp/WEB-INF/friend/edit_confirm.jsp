<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.friend.FriendInfo" id="friend" scope="session"></jsp:useBean>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集内容確認</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/confirm_friend.css">
</head>
<body>
<h1>編集内容確認</h1>
<form action="FriendServlet" method="post">
<table border="1" id="table">
<tr><td class="content">顔写真</td><td class="content"><img src="${imageLink }" id="image"></td></tr>
<tr><td class="content">友人詳細</td><td class="content"><c:out value="${friend.introduction }"/></td></tr>
<tr><td class="content">氏名</td><td class="content"><c:out value="${friend.name }"/></td></tr>
<tr><td class="content">職業</td><td class="content"><c:out value="${friend.status }"/></td></tr>
<tr><td class="content">性別</td><td class="content"><c:out value="${friend.sex }"/></td></tr>
<tr><td class="content">Eメール</td><td class="content"><c:out value="${friend.email }"/></td></tr>
<tr><td class="content">住所</td><td class="content"><c:out value="${friend.address }"/></td></tr>
<tr><td class="content">電話番号</td><td class="content"><c:out value="${friend.tel }"/></td></tr>
</table>
<p class="content">
<button type="submit" name="state" value="update">OK</button>
<button type="submit" name="state" value="edit">戻る</button>
</p>
</form>
</body>
</html>