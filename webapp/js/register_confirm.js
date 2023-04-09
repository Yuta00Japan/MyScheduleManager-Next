/**
 * ユーザー登録確認画面のスクリプト
 主にユーザー登録用IDの暗号を復号化し
 入れた値と復号化した値が正しいかどうかを検証する
*/
 
/**復号化KEY<br>"*/
const KEY = "my-secret-key-123";

/**
復号化method
@param encryptedValue 暗号化ID
 */
function decrypt(encryptedValue) {
   const key = CryptoJS.SHA1(KEY).toString().substring(0, 32);
   const decryptedBytes = CryptoJS.AES.decrypt(
    { ciphertext: CryptoJS.enc.Base64.parse(encryptedValue) },
    CryptoJS.enc.Hex.parse(key),
    { mode: CryptoJS.mode.ECB }
  );
  return decryptedBytes.toString(CryptoJS.enc.Utf8);
} 


const shuffledArray = JSON.parse(document.getElementById('shuffledId').value);

/**暗号化ID(シャッフル済み) */
const shuffledId = shuffledArray.left;
/**シャッフル前のインデックス配列　シャッフルしたIDをもとに戻すのに使用 */
const indexId = shuffledArray.right;
/**シャッフル前のID配列を格納 */
var encryptedList = [];
//シャッフルした配列を元の順番に戻す
for (var i = 0; i < shuffledId.length; i++) {
  encryptedList.push(indexId[i]);
}

/**配列を文字列に変換 */
const encryptedValue = encryptedList.join("");

/**復号化済みID */
var trueId = decrypt(encryptedValue);

/**
入力値IDと正しいIDを比較して
正しい場合のみ登録ボタンを出現させるmethod
 */
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

