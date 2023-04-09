<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/register_form.css">
</head>
<body>
<h1>ユーザー登録フォーム</h1>
<p>※は必須入力です</p>

<form method="post">

<table border="1" id="table">
<tr><td class="content">※ログインID</td><td class="content"><input type="text" name="loginId" id="loginId" maxlength=20></td></tr>
<tr><td class="content">※ 氏名</td><td class="content"><input type="text" name="name" maxlength=30 id="name"></td></tr>
<tr><td class="content">※ 暗証番号</td><td class="content"><input type="password" name="password" maxlength=30 id="password"></td></tr>
<tr><td class="content">※性別</td>
<td class="content">
男：<input type="radio" name="sex" value="male" checked>
女：<input type="radio" name="sex" value="female">
</td>
</tr>
<tr><td class="content">電話番号</td><td class="content"><input type="tel" name="tel" maxlength=30></td></tr>
<tr><td class="content">※Eメール</td><td class="content"><input type="email" name="email" maxlength=100 id="email"></td></tr>
<tr><td class="content">住所</td><td class="content"><input type="text" name="address" maxlength=100></td></tr>
</table>
<p id="button">
<button id="submit" name="state" value="register_confirm" formaction="UserServlet">登録確認</button>
<button name="state" value="loginForm" formaction="LoginServlet">ログインフォームに戻る</button>
</p>
</form>
<script>

//必須項目が入力されているかどうか検証
document.getElementById('submit')
.addEventListener('click',function(event){

		var loginId = document.getElementById('loginId');
		var name = document.getElementById('name');
		var password = document.getElementById('password');
		var email = document.getElementById('email');
			
		if(loginId.value == "" || loginId.value == null){
			event.preventDefault();
			alert('ログインIDを入力してください');
		}
		
		if(name.value== "" || name.value== null){
			event.preventDefault();
			alert('氏名を入力してください');
			return ;
		}

		if(password.value=="" || password.value== null){
			event.preventDefault();
			alert('暗証番号を入力してください');
			return ;
		}

		if(email.value=="" || email.value== null){
			event.preventDefault();
			alert('e-mailアドレスを入力してください');
			return ;
		}	
	});


</script>
</body>
</html>