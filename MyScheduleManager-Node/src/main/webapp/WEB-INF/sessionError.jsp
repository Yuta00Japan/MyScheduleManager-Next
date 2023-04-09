<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>セッションエラー</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/sessionError.css">
</head>
<body>
<h1>セッションエラーが発生しました<br>再度ログインしてください</h1>
<form action="LoginServlet" method="Get">
<p><button type="submit" name="state" value="">ログイン画面</button></p>
</form>
</body>
</html>