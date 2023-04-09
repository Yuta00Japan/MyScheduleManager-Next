<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン失敗</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/end.css">
</head>
<body>
<h1>ログインに失敗しました</h1>

<p>ログインIDか暗証番号が違います</p>

<form action="LoginServlet" method="Get">
<p><button type="submit">ログイン</button></p>
</form>
</body>
</html>