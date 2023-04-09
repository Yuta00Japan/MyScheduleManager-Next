<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean class="model.friend.FriendInfo" id="friend" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>友人情報編集</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/new_edit_friend.css">
</head>
<body>
<h1>友人情報編集</h1>

<jsp:include page="./attention.jsp"/>

<form action="FriendServlet" method="post" enctype="multipart/form-data">

<h2>顔写真:<input type="file" name="face" id="face"></h2>

<h2>氏名：<input type="text" name="name" value="${friend.name }">

<%--ステータスの情報をjavascriptで取得するためのもの --%>
<input type="hidden" id="status" value="${friend.status }">

職業：<select name="status" id="select">
<option value="1">代表取締役社長</option>
<option value="2">専務</option>
<option value="3">常務</option>
<option value="4">会社員（部長）</option>
<option value="5">会社員（課長）</option> 
<option value="6">会社員（係長）</option>
<option value="7">会社員</option>
<option value="8">会社員（他社）</option>
<option value="9">フリーター</option>
<option value="10">大学院生</option>
<option value="11">大学生</option>
<option value="12">高校生</option>
<option value="13">中学生</option>
<option value="14">小学生</option>
<option value="15">主婦（主夫）</option>
<option value="16">家事手伝い</option>
<option value="17">自営業</option>
<option value="18">フリーランス</option>
</select>
</h2>

<%--BEANSの性別情報をjavascriptで得るためのもの --%>
<input type="hidden" id="sex" value="${friend.sex }">

<h2>性別　男<input type="radio" name="sex" value="male">
女<input type="radio" name="sex" value="female"></h2>

<h2>Email:<input type="email" name="email" value="${friend.email }">
住所 :<input type="text" name="address" value="${friend.address }">
電話番号:<input type ="text" name="tel" value="${friend.tel }"></h2>

<p>友人情報詳細</p>
<h2><textarea rows="18" cols="80" name="introduction">
${friend.introduction }
</textarea></h2>
<p>
<button type="submit" name="state" value="edit_confirm" id="submit">OK</button>
<button type="submit" name="state" value="listReturn">戻る</button>
</p>
</form>
<script>

window.addEventListener('load',setStatus);
window.addEventListener('load',setSex);

document.getElementById('submit').addEventListener('click',(event) => {
	if(document.getElementById('face').value == "" || document.getElementById('face').value == null){
		event.preventDefault();
		alert('まだ画像が選択されていません');
	}
});
//初期値をセットする
function setStatus(){
	var selectbox = document.getElementById('select');
	var status = document.getElementById('status').value;
	switch(status){
	case "代表取締役社長":
		selectbox.selectedIndex = 0;
		break;
	case "専務":
		selectbox.selectedIndex = 1;
		break;
	case "常務":
		selectbox.selectedIndex = 2;
		break;
	case "会社員（部長）":
		selectbox.selectedIndex = 3;
		break;
	case "会社員（課長）":
		selectbox.selectedIndex = 4;
		break;
	case "会社員（係長）":
		selectbox.selectedIndex = 5;
		break;
	case "会社員":
		selectbox.selectedIndex = 6;
		break;
	case "会社員（他社）":
		selectbox.selectedIndex = 7;
		break;
	case "フリーター":
		selectbox.selectedIndex = 8;
		break;
	case "大学院生":
		selectbox.selectedIndex = 9
		break;
	case "大学生":
		selectbox.selectedIndex = 10;
		break;
	case "高校生":
		selectbox.selectedIndex = 11;
		break;
	case "中学生":
		selectbox.selectedIndex = 12;
		break;
	case "小学生":
		selectbox.selectedIndex = 13;
		break;
	case "主婦（主夫）":
		selectbox.selectedIndex = 14;
		break;
	case "家事手伝い":
		selectbox.selectedIndex = 15;
		break;
	case "自営業":
		selectbox.selectedIndex = 16;
		break;
	case "フリーランス":
		selectbox.selectedIndex = 17;
		break;
	}
}
//初期値をセットする
function setSex(){
	var radio = document.getElementsByName('sex');
	var sex = document.getElementById('sex').value;

	switch(sex){
	case "male":
	radio[0].checked = true;
	break;
	case "female":
	radio[1].checked = true;
	break;
	}
}

//顔写真ファイルの拡張子が.jpg .jpeg .png以外のを拒否する
const input = document.getElementById('face');
input.addEventListener('change', () => {
  const file = input.files[0];
  const allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;
  if (!allowedExtensions.exec(file.name)) {
    alert('Please select a file with a valid extension: .jpg, .jpeg, or .png');
    input.value = '';
    return false;
  }
});
</script>

</body>
</html>