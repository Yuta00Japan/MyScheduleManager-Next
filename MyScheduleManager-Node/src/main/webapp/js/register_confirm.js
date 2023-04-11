var trueId = 0;

//node express sideの値を取得
fetch('http://localhost:3000/node')
  .then(response => response.text())
  .then(data =>{
	trueId = data;
	console.log(data);
  })
  .catch(error => console.error(error));


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

