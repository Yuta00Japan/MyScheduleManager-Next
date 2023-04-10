<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="tmpUser" class="model.user.TmpUser" scope="session"></jsp:useBean>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    	String json =(String)request.getAttribute("json");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入力内容確認画面</title>
<%--暗号化ライブラリを読み込む --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/register_confirm.css">
</head>
<body>
<h1>入力内容確認</h1>

<form action="UserServlet" method="post">

<table border="1" id="table">
<tr><td class="content">ログインID</td>
<td class="content">${tmpUser.loginId }</td>
</tr>
<tr>
<td class="content">氏名</td>
<td class="content">${tmpUser.name }</td>
</tr>
<tr>
<td class="content">暗証番号</td>
<td class="content">${tmpUser.password }</td>
</tr>
<tr>
<td class="content">性別</td>
<td class="content">${tmpUser.sex }</td>
</tr>
<tr>
<td class="content">電話番号</td>
<td class="content">${tmpUser.tel }</td>
</tr>
<tr>
<td class="content">Eメール</td>
<td class="content">${tmpUser.email }</td>
</tr>
<tr>
<td class="content">住所</td>
<td class="content">${tmpUser.address }</td>
</tr>
</table>

<h2>メールを送信しました。メールで受け取った番号を入力してください</h2>
<p class="content">認証番号<input type="number" id="enterPass"></p>

<p id="button">
<span id="error"></span>

<button name="state" value="register">入力画面へ戻る</button>
</p>

</form>
<%--javascript読み込み --%>
<script src="js/register_confirm.js"></script>
</body>
</html>