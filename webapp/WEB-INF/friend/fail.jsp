<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>友人情報処理失敗</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/end.css">
</head>
<body>

<h1>エラー</h1>

<h1>${error }</h1>

<form action="FriendServlet" method="post">
<p id="button"><button name="state" value="search">友人検索条件に戻る</button></p>
</form>
</body>
</html>