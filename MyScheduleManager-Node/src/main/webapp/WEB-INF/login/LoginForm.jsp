<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>
<h1>ログイン</h1>
 <form  method="post">
            <table border="1" id="table">
                <tr>
                    <td class="title">ログインID</td>
                    <td class="form"><input type="text" name="loginID" maxlength=20></td>
                </tr>
                <tr>
                    <td class="title">パスワード</td>
                    <td class="form"><input type="password" name="password" maxlength=15></td>
                </tr>
            </table>
            
            <p class="button">
                <button name="state" value="login" formaction="LoginServlet">ログイン</button>
            </p>
            
             <h2>ユーザー登録が未完了のかたはこちらのボタンをクリックしてください</h2>
            <p class="button">
            	<button name="state" value="register" formaction="UserServlet">ユーザー登録</button>
            </p>
        </form>
</body>
</html>