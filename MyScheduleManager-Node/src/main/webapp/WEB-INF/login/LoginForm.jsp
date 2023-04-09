<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css">
</head>
<body id="body">
<h1>ログイン</h1>
 <form  method="post">
            <table border="1" id="table">
                <tr>
                    <td class="title">ログインID</td>
                    <td class="form"><input type="text" name="loginID" maxlength=20></td>
                </tr>
                <tr>
                    <td class="title">パスワード</td>
                    <td class="form"><input type="password" name="password" maxlength=15></td>
                </tr>
           
             </table>
            <p class="button">
                <button name="state" value="login" formaction="LoginServlet">ログイン</button>
            </p>
            
             <h2>ユーザー登録が未完了のかたはこちらのボタンをクリックしてください</h2>
            <p class="button">
            	<button name="state" value="register" formaction="UserServlet">ユーザー登録</button>
            </p>
        </form>
<script>

var body = document.getElementById('body');
/**
 * 月ごとに背景画像を設定する
 */
function seasons(){
	var time = new Date();
	var month =time.getMonth()+1;
	//春
	if(month >= 3 && month <= 5){
		imageColorSet("url('image/cherry.jpg')",'rgb(255, 0, 255)');
	//夏
	}else if(month >= 6 && month <= 8){
		imageColorSet("url('image/summer.jpg')",'rgb(0, 255, 64)');
	//秋
	}else if(month >= 9 && month <= 11){
		imageColorSet("url('image/autumn.jpg')",'rgb(255, 128, 0)');
	//冬
	}else{
		imageColorSet("url('image/winter.jpg')",'rgb(0, 128, 255)');
	}
}
/**
 * 背景画像、色の設定を行う
 */
function imageColorSet(imageUrl,color){
	body.style.backgroundImage= imageUrl;
	body.style.color= color;
}

setInterval(seasons,1000);
</script>
</body>
</html>