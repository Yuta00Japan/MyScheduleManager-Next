<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記検索結果</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/list_diary.css">
</head>
<body>
<h1>日記検索結果</h1>
<form action="DiaryServlet" method="post">

<h1><button name="state" value="search">検索画面に戻る</button></h1>

<c:choose>
<c:when test="${diaryList.diaryList.size() <= 0 }">
<h1>まだ日記が書かれていません</h1>
</c:when>
<c:otherwise>
<table border="1" id="table">
<tr>
<th class="content">日記ID</th>
<th class="content">日付</th>
<th class="content">題名</th>
<th class="content">操作</th>
</tr>
<c:forEach var ="i" begin="0" end="${diaryList.diaryList.size()-1 }" step="1">
<tr>
<td class="content"><c:out value="${diaryList.diaryList.get(i).diaryId }"/></td>
<td class="content"><c:out value="${diaryList.diaryList.get(i).time }"/></td>
<td class="content"><c:out value="${diaryList.diaryList.get(i).title }"/></td>
<td class="content">
<button name="state" value="detail,${diaryList.diaryList.get(i).diaryId }">詳細</button>
<button name="state" value="delete_confirm,${diaryList.diaryList.get(i).diaryId }">削除</button>
</td>
</tr>
</c:forEach>
</table>
</c:otherwise>
</c:choose>
</form>
</body>
</html>