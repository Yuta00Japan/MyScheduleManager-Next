<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>友人追加</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/new_edit_friend.css">
</head>
<body>
<h1>友人追加フォーム</h1>

<p>※は必須項目です</p>

<jsp:include page="./attention.jsp"/>

<form action="FriendServlet" method="post" enctype="multipart/form-data">
<h2>※顔写真:<input type="file" name="face" id="face"></h2>

<h2>※氏名：<input type="text" name="name" id="name">
職業：<select name="status">
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
<h3>
※性別　男<input type="radio" name="sex" value="male" checked>
女<input type="radio" name="sex" value="female"><br>

Email:<input type="email" name="email">
住所 :<input type="text" name="address">
電話番号:<input type ="text" name="tel">
</h3>

<p>友人情報詳細</p>
<h2><textarea rows="18" cols="80" name="introduction">
ここに友人のことについて書き込んでください
</textarea></h2>

<p>
<button type="submit" name="state" value="new_confirm" id="submit">OK</button>
<button type="submit" name="state" value="search">戻る</button>
</p>
</form>
<script>
const sub = document.getElementById('submit');
const img = document.getElementById('face');
const name = document.getElementById("name");


//顔写真が選択されていなければsubmitをキャンセル
sub.addEventListener('click', (event) => {
	if(img.value == "" || img.value== null){
		event.preventDefault();
		alert('まだ画像が選択されていません');
	}
	if(name.value == "" || name.value== null){
		event.preventDefault();
		alert('氏名を入力してください');
	}
});

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