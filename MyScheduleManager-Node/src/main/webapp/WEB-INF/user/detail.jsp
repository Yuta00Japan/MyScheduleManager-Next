<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.user.UserInfo" scope="session" id="user"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/detail_user.css">
<body>
<h1>ユーザー情報一覧</h1>

<form method="post">

<table border="1" id="table">
<tr><td class="content">ユーザーID</td><td class="content">${user.id }</td></tr>
<tr><td class="content">ログインID</td><td class="content">${user.loginId }</td></tr>
<tr><td class="content">氏名</td><td class="content">${user.name }</td></tr>
<tr><td class="content">暗証番号</td><td class="content">${user.password }</td></tr>
<tr><td class="content">性別</td><td class="content">${user.sex }</td></tr>
<tr><td class="content">電話番号</td><td class="content">${user.tel }</td></tr>
<tr><td class="content">Eメール</td><td class="content">${user.email }</td></tr>
<tr><td class="content">住所</td><td class="content">${user.address }</td></tr>
</table>
<p id="button">
<button name="state" value="edit" formaction="UserServlet">ユーザー情報編集</button>
<button name="state" value="home" formaction="LoginServlet">メニューに戻る</button>
</p>

</form>
</body>
</html>