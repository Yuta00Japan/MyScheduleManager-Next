var trueId = 0;

//node express sideの値を取得
const xhr = new XMLHttpRequest();
xhr.open('GET', 'http://localhost:3000/phone_id');  // リクエスト先のURLを指定
xhr.onload = () => {
  if (xhr.status === 200) {
    console.log("javascript side "+xhr.responseText);  // レスポンスの内容を出力
    trueId = xhr.responseText;
  } else {
    console.error('Error:', xhr.statusText);
  }
};
xhr.send();  // リクエストを送信


function judgePass(){
	//入力したID
	var enter = document.getElementById('enterPass').value;
	
	if(trueId == enter){
		//書き換える内容
		var newButton = document.createElement("button");
		newButton.setAttribute("name","state");
		newButton.setAttribute("value","add_user");
		newButton.innerHTML = "ユーザー登録"

		//書き換え元
		var old = document.getElementById('error');

		old.parentNode.replaceChild(newButton, old);
		
	}else{
		document.getElementById('error').innerHTML = "認証不一致です";
	}
	
}
document.getElementById('enterPass').addEventListener('change',judgePass);

