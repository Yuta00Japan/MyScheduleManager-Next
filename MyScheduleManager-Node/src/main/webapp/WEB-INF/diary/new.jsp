<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.user.UserInfo" scope="session" id="user"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規日記登録</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/diary_new_edit.css">
</head>
<body>
<h1>新規日記登録</h1>

<table border="1">
<form action="DiaryServlet" method="post">
<%--ユーザーID --%>
<input type="hidden" name="userId" value="${user.id }">
<h2>題名：<input type="text" name="title" maxlength=50></h2>
<h3><textarea rows="18" cols="80" name="content" maxlength=500>ここに日記内容を入力してください</textarea></h3>

<p>
<button name="state" value="new_confirm">新規日記登録確認</button>
<button name="state" value="search">検索画面に戻る</button>
</p>
</form>
</table>
</body>
</html>