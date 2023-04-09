<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>友人検索</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/search.css">
</head>
<body>
<h1>友人検索</h1>
<form method="post">
<h2>名前検索：<input type="text" name="name" formaction="FriendServlet"></h2>
<p id="button">
<button type="submit" name="state" value="list"  formaction="FriendServlet">検索</button>
<button type="submit" name="state" value="new"  formaction="FriendServlet">新規友人登録</button>
<button type="submit" name="state" value="home" formaction="LoginServlet" >戻る</button>
</p>
</form>
</body>
</html>