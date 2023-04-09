<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.user.TmpUser" scope="session" id="tmpUser"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報編集確認</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/detail_user.css">
</head>
<body>

<h1>ユーザー情報編集確認</h1>

<form method="post">

<table border="1" id="table">
<tr><td class="content">ユーザーID</td><td class="content">${tmpUser.userId }</td></tr>
<tr><td class="content">ログインID</td><td class="content">${tmpUser.loginId }</td></tr>
<tr><td class="content">氏名</td><td class="content">${tmpUser.name }</td></tr>
<tr><td class="content">暗証番号</td><td class="content">${tmpUser.password }</td></tr>
<tr><td class="content">性別</td><td class="content">${tmpUser.sex }</td></tr>
<tr><td class="content">電話番号</td><td class="content">${tmpUser.tel }</td></tr>
<tr><td class="content">Eメール</td><td class="content">${tmpUser.email }</td></tr>
<tr><td class="content">住所</td><td class="content">${tmpUser.address }</td></tr>
</table>
<p id="button">
<button name="state" value="update" formaction="UserServlet">ユーザー情報編集確定</button>
<button name="state" value="edit" formaction="UserServlet">ユーザー情報編集に戻る</button>
</p>

</form>
</body>
</html>