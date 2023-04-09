<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報更新成功</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/end.css">
</head>
<body>
<h1>ユーザー情報更新成功</h1>

<form action="LoginServlet" method="post">
<p><button name="state" value="loginForm">ログイン画面に戻る</button></p>
</form>
</body>
</html>