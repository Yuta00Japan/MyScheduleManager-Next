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
<h1>登録完了しました</h1>
<form action="ScheduleServlet" method="post">
<p><button type="submit" name="state" value="search">検索画面</button></p>
</form>
</body>
</html>