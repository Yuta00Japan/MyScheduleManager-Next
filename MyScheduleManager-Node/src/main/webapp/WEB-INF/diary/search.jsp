<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記検索</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/search_diary.css">
</head>
<body>
<h1>日記検索</h1>

<p>
何も入力せず検索ボタンをクリックするとすべて日記が表示されます<br>
時間帯指定は片方入力したらもう片方も入力してください
</p>


<form method="post">

<h2>題名：<input type="text" name="title" ></h2>

<h3>
開始日：<input type="date" name="startTime" id="start">
終了日：<input type="date" name="endTime" id="end">
</h3>

<p id="button">
<button name="state" value="list" id="submit" formaction="DiaryServlet">日記検索</button>
<button name="state" value="new" formaction="DiaryServlet">新規日記登録</button>
<button name="state" value="home" formaction="LoginServlet">メニューに戻る</button>
</p>
</form>

<script>
document.getElementById('submit').addEventListener('click',(event) =>{
		var start =  document.getElementById('start').value;
		var end = document.getElementById('end').value;

		//開始日が入力されていた場合
		if(start != null && start != ""){
			//開始日が入力されているが終了日が入力されていない場合
			if(end == null || end == ""){
				event.preventDefault();
				alert('終了日も入力してください');
			}
		}

		//終了日が入力されていた場合
		if(end != null && end != ""){
			//終了日が入力されているが開始日が入力されていない場合
			if(start == null || start == ""){
				event.preventDefault();
				alert('開始日も入力してください');
			}
		}
});

</script>

</body>
</html>