<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予定検索</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/schedulesearch.css">
</head>
<body>

<h1>予定検索</h1>

<p>何も入力せず予定検索をクリックするとすべての予定が表示されます</p>

<form  method="post">

<h2 class="search">スケジュール名：<input type="text" name="scheduleName">
<button type="submit" name="state" value="list" formaction="ScheduleServlet">予定検索</button></h2>

<h3 class="search">仕事：<input type="radio" name="type" value="work">
遊び：<input type="radio" name="type" value="play">
行事：<input type="radio" name="type" value="event">
デート：<input type="radio" name="type" value="date"></h3>

<h4 class="search">期間<input type="datetime-local" name="startDay">～
<input type="datetime-local" name="endDay"></h4>
</form>

<form method="post">
<h5 class="search">
<button type="submit" name="state" value="home" formaction="LoginServlet">メニュー画面</button>
</h5>
</form>
</body>
</html>