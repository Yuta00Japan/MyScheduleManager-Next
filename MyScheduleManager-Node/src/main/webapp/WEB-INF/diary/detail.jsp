<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記詳細</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/detail.css">
</head>
<body>
<h1>日記詳細</h1>

<form action="DiaryServlet" method="post">

<table id="table" border="1">
<tr><td class="label">日記ID</td><td class="content">${diary.diaryId }</td></tr>
<tr><td class="label">ユーザーID</td><td class="content">${diary.userId }</td></tr>
<tr><td class="label">題名</td><td class="content">${diary.title }</td></tr>
<tr><td class="label">記述日</td><td class="content">${diary.time }</td></tr>
<tr><td class="label">内容</td><td class="detail">${diary.content }</td></tr>
</table>

<p id="button">
<button name="state" value="edit">編集</button>
<button name="state" value="listReturn">日記検索結果に戻る</button>
</p>
</form>
</body>
</html>