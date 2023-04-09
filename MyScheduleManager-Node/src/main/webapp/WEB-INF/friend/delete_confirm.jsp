<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.friend.FriendInfo" id="friend" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>友人削除</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/detailFriend.css">
</head>
<body>
<h1>友人削除確認画面</h1>

<p>削除内容をご確認ください</p>

<form action="FriendServlet" method="post">
<table border="1" id="table">
<tr><td>フレンドコード</td><td>${friend.friendId }</td></tr>
<tr><td>ユーザーコード</td><td>${friend.userId }</td></tr>
<tr><td>顔写真</td><td><img src="${imageLink }"</td></tr>
<tr><td>友人詳細</td><td>${friend.introduction }</td></tr>
<tr><td>氏名</td><td>${friend.name }</td></tr>
<tr><td>職業</td><td>${friend.status }</td></tr>
<tr><td>性別</td><td>${friend.sex }</td></tr>
<tr><td>Eメール</td><td>${friend.email }</td></tr>
<tr><td>住所</td><td>${friend.address }</td></tr>
<tr><td>電話番号</td><td>${friend.tel }</td></tr>
</table>
<p id="button">
<button type="submit" name ="state" value="delete">削除</button>
<button type="submit" name="state" value="listReturn">戻る</button>
</p>
</body>
</html>