<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除完了</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/end.css">
</head>
<body>
<h1>削除完了</h1>
<form action="ScheduleServlet" method="post">
<p><button type="submit" name="state" value="search">検索画面に戻る</button></p>
</form>
</body>
</html>