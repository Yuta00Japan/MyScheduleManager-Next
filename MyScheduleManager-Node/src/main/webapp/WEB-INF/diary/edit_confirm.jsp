<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記編集内容確認</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/diary_detail.css">
</head>
<body>
<h1>日記編集内容確認</h1>

<table id="table" border="1">
<tr><td class="label">タイトル</td><td class="content">${diary.title }</td></tr>
<tr><td class="label">日記内容</td><td class="content">${diary.content }</td></tr>
</table>

<form action="DiaryServlet" method="post">
<p>
<button name="state" value="update">日記編集確定</button>
<button name="state" value="edit">日記編集画面へ戻る</button>
</p>
</form>
</body>
</html>