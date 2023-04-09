<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/end.css">
</head>
<body>
<h1>友人登録完了</h1>
<form action="FriendServlet" method="post">
<p><button type="submit" name="state" value="search">戻る</button></p>
</form>
</body>
</html>