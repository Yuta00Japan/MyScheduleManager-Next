<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記編集完了</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/end.css">
</head>
<body>
<h1>日記編集成功</h1>

<form action ="DiaryServlet" method="post">
<p><button name="state" value="search">日記検索画面へ戻る</button></p>
</form>
</body>
</html>