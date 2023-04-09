<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規予定入力</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/new_edit.css">
</head>
<body>
<h1>予定登録画面</h1>

<form  method="post">
<p>
<input type="hidden" name="name">
<button type="submit" name="state" value="list" formaction="FriendServlet">フレンドコード参照</button>
<button type="submit" name="state" value="search" formaction="ScheduleServlet">戻る</button></p>
</form>

<form action="ScheduleServlet" method="post">
<h2>タイトル：<input type="text" name="title" maxlength=50 required>

種別：<select name="how" required>
<option value="work">仕事</option>
<option value="event">行事</option>
<option value="play">遊び</option>
<option value="date">デート</option>
</select>

場所：<input type="text" name="location" maxlength=50 required></h2>

<%--ボタンのタイプを明示的に指定していない場合はデフォルトでsubmitとなるため
typeを指定する必要がある。
 --%>

<h3>現在のフレンド追加数:<span id="count">0</span><br>
フレンド名：<select id="code"></select><button type="button" id="add">フレンド追加</button>

時間帯：<input type="datetime-local" name="startTime" required>～
<input type="datetime-local" name="endTime" required></h3>

<h4>予定の概要<br>
<textarea name="summary" rows="18" cols="80" maxlength=200 required>
ここに予定の概要を入力してください
</textarea></h4>
<%--ここにフレンドコードを入れて送信する --%>
<input type="hidden" name="who" id="friendCode">
<p>
<button type="submit"  name="state" id="finish" value="add_Confirm">予定入力確認</button>
</p>

</form>
<script>
//USERのフレンド情報をselectにセットする
fetch('http://localhost:3000/setFriend')
.then(response => response.json())
.then(data => {
  console.log("fetch "+data); // レスポンスデータを処理する
  let select = "";
  	for (let i = 0; i < data.length; i++) {
	    select += "<option value=" + data[i].FriendId + ">" + data[i].name + "</option>";
	}
	document.getElementById('code').innerHTML = select;
})
.catch(error => {
  console.error(error); // エラーを処理する
});


document.getElementById('add').addEventListener('click',friendAdd);
document.getElementById('finish').addEventListener('click',finish);
//入力されたフレンドコードを格納
var friendCode = [];
//配列の要素数のカウント
var index = 0;

//フォームに入力されたフレンドコードをfriendCodeへ格納
function friendAdd(){
	var friend = document.getElementById('code').value;
	if(friend == null || friend == ""){
		alert('フレンドコードを入力してください');
	}else{
		//フレンドコード登録処理
		friendCode[index]= friend;
		index++;
		document.getElementById('code').value = null;
		
		//カウント数表示処理
		document.getElementById('count').textContent = index;
	}
	
}
//予定入力確認ボタンをクリックしたらfriendCodeに格納された値をinput type="hidden"
//へ渡す
function finish(){
	
	//friendCodeの値をinput type="hidden"へ渡す
	document.getElementById('friendCode').value= friendCode;
	
	var code = document.getElementById('friendCode').value;
	
	if(code == null || code ==""){
		alert('まだフレンドコードが登録されていません');
	
	}else{
	
	}
}


</script>
</body>
</html>