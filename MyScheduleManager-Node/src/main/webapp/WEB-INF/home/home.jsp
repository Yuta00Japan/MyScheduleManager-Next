<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.user.UserInfo" id="user" scope="session" />
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ようこそ</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/home.css">
</head>
<body>
<h1>予定管理システム</h1>

<input type="hidden" id="user" value="${user.id }" >
<p>ログインユーザー<br><span id="userName"><c:out value="${user.name }" /></span></p>

<form  method="post">
<div class="select"><button class="button"  name="state" value="search" formaction="ScheduleServlet">予定管理</button></div>
<div class="select"><button class="button"  name="state" value="search" formaction="FriendServlet">友人管理</button></div>
<div class="select"><button class="button"  name="state" value="search" formaction="DiaryServlet">日記管理</button></div>
<div class="select"><button class="button" name="state" value="detail" formaction="UserServlet">ユーザー情報</button></div>
<div class="select"><button class="button"  name="state" value="logout" formaction="LoginServlet">ログアウト</button></div>
</form>
<script>
//USERLDを取得しNODE expressのローカルストレージに格納
const userId = document.getElementById('user').value;

fetch('http://localhost:3000/userid', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({userId:userId})
})
.then(response => response.json())
.then(data => {
  console.log('Success:', data);
})
.catch((error) => {
  console.error('Error:', error);
});
</script>
</body>
</html>