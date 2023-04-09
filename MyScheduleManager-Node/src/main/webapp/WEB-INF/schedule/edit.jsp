<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.schedule.ScheduleBean" id="schedule" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/new_edit.css">
</head>
<body>
<h1>編集画面</h1>

<form  method="post">
<p>
<input type="hidden" name="name">
<button type="submit" name="state" value="list" formaction="FriendServlet">フレンドコード参照</button>
<button type="submit" name="state" value="search" formaction="ScheduleServlet">戻る</button></p>
</form>

<form action="ScheduleServlet" method="post">
<h2>タイトル：<input type="text" name="title" value="${schedule.title }" maxlength=50 required>

種別：<select name="how" id="howList" required>
<option value="work">仕事</option>
<option value="event">行事</option>
<option value="play">遊び</option>
<option value="date">デート</option>
</select>
<input type="hidden" id="how" value="${schedule.how }">

場所：<input type="text" name="location" value="${schedule.location }" maxlength=50 required></h2>

<%--ボタンのタイプを明示的に指定していない場合はデフォルトでsubmitとなるため
typeを指定する必要がある。
 --%>

<h3>現在のフレンド追加数:<span id="count">0</span><br>
フレンドコード：<select id="code"></select><button type="button" id="add">フレンドコード追加</button>

時間帯：<input type="datetime-local" name="startTime" value="${schedule.startTime }" required>～
<input type="datetime-local" name="endTime" value="${schedule.endTime }" required></h3>

<h4>予定の概要<br>
<textarea name="summary" rows="18" cols="80" maxlength=200 required>
${schedule.summary }
</textarea></h4>
<%--ここにフレンドコードを入れて送信する --%>
<input type="hidden" name="who" id="friendCode" value="${schedule.who }">
<p>
<button type="submit"  name="state" id="finish" value="edit_confirm">予定編集確認</button>
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

window.addEventListener('load',select);
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
function finish(event){
	
	//friendCodeの値をinput type="hidden"へ渡す
	document.getElementById('friendCode').value= friendCode;
	
	var code = document.getElementById('friendCode').value;

	//まだフレンドコードが入力されていない場合は処理をキャンセル
	if(code == null || code ==""){
		alert('まだフレンドコードが登録されていません');
		event.preventDefault();
	}else{
	
	}
}
//selectの初期選択をBEANSの値で指定する
function select(){
	var select = document.getElementById('how').value;
	var box = document.getElementById('howList');
	switch(select){
	case "work":
		box.selectedIndex = 0;
		break;
	case "event":
		box.selectedIndex = 1;
		break;
	case "play":
		box.selectedIndex=2;
		break;
	case "date":
		box.selectedIndex=3;
		break;
	}
}

</script>
</body>
</html>