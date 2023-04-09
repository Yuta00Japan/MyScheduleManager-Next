<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.user.UserInfo" scope="session" id="user"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報編集</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/detail_user.css">
</head>
<body>
<h1>ユーザー情報編集</h1>

<p>※は必須項目です。</p>

<form method="post">

<table border="1" id="table">
<input type="hidden" name="userId" value="${user.id }">
<tr><td class="content">※ログインID</td><td class="content"><input type="text" id="loginId"  name="loginId" value="${user.loginId }"></td></tr>
<tr><td class="content">※氏名</td><td class="content"><input type="text" id="name" name="name" value="${user.name }"></td></tr>
<tr><td class="content">※暗証番号</td><td class="content"><input type="text" id="password" name="password" value="${user.password }"></td></tr>
<tr><td class="content">性別</td><td class="content">
男：<input type="radio" name="sex" value="male">
女：<input type="radio" name="sex" value="female">
</td></tr>

<%--JAVASCRIPTにUSERINFOの値を渡すためのもの --%>
<input type="hidden" id="sex" value="${user.sex }">

<tr><td class="content">電話番号</td><td class="content"><input type="tel" name="tel" value="${user.tel }"></td></tr>
<tr><td class="content">Eメール</td><td class="content"><input type="email" name="email" value="${user.email }"></td></tr>
<tr><td class="content">住所</td><td class="content"><input type="text" name="address" value="${user.address }"></td></tr>
</table>
<p id="button">
<button name="state" id="submit" value="edit_confirm" formaction="UserServlet">ユーザー情報編集確認</button>
<button name="state" value="detail" formaction="UserServlet">ユーザー情報詳細に戻る</button>
</p>

<script>
var selectSex=document.getElementsByName('sex');

var sex = document.getElementById('sex').value;

window.addEventListener('load',setSex);

//性別の初期選択をセット
function setSex(){
	switch(sex){
	case "male":
		selectSex[0].checked=true;
		break;
	case "female":
		selectSex[1].checked=true;
		break;
	}
}
//必須項目の入力値確認
document.getElementById('submit').addEventListener('click',(event) =>{
		var loginId = document.getElementById('loginId').value;
		var name =  document.getElementById('name').value;
		var password =	document.getElementById('password').value;

		if(loginId == "" || loginId == null){
			event.preventDefault();
			alert('ログインIDを入力してください');
			return false;
		}

		if(name == "" || name == null){
			event.preventDefault();
			alert('氏名を入力してください');
			return false;
		}

		if(password == "" || password == null){
			event.preventDefault();
			alert('暗証番号を入力してください');
			return false;
		}
});

</script>
</form>
</body>
</html>