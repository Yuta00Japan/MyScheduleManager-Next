<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.diary.DiaryInfo" scope="session" id="diary"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記編集</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/diary_new_edit.css">
</head>
<body>
<h1>日記編集</h1>

<table border="1">
<form action="DiaryServlet" method="post">
<input type="hidden" name="userId" value="${user.id }">
<h2>題名：<input type="text" name="title" value="${diary.title }" maxlength=50></h2>
<h3>
<textarea rows="18" cols="80" name="content" maxlength=500>
${diary.content }
</textarea>
</h3>

<p>
<button name="state" value="edit_confirm">日記編集確認</button>
<button name="state" value="detail,${diary.diaryId }">詳細画面に戻る</button>
</p>
</form>
</table>
</body>
</html>