/**
 *scheudleList用javasript
 予定の時間ごとに予定の色に変化を加える 
 過ぎた予定は赤にする
 */
 
 /**予定開始終了時間リスト */
 var content = document.querySelectorAll('.content'); 
 var startTimeList = document.querySelectorAll('#list .content .start');
 var endTimeList = document.querySelectorAll('#list .content .end');

/**
 * 時間の.0の表示を除去する
 */
window.addEventListener("load",() =>{
	let startTime = [];
	let endTime = [];
	for(let i= 0; i < startTimeList.length; i++){
		startTime = startTimeList[i].textContent;
		endTime = endTimeList[i].textContent;
		startTime= startTime.split(".");
		endTime = endTime.split(".");
		startTimeList[i].textContent = startTime[0];
		endTimeList[i].textContent = endTime[0];
	}
})

/** 
 *予定の開始終了時間ごとに色を設定する 
*/
function timeColor(){
	var time = new Date();
	var nowYear = time.getFullYear();
	var nowMonth = time.getMonth()+1;
	var nowDate = time.getDate();
	
	var nowHour = time.getHours();
	var nowMinute = time.getMinutes();
	
	var nowYMD = 0;
	
	//年月日時分で桁数をそろえる処理
	if(nowMonth < 10){
		nowMonth = "0"+nowMonth;
	}
	if(nowDate < 10){
		nowDate = "0"+nowDate;
	}
	//年月日
	nowYMD =nowYear+""+nowMonth+""+nowDate;
	
	if(nowHour < 10){
		nowHour = "0"+nowHour;
	}
	if(nowMinute < 10){
		nowMinute = "0"+nowMinute;
	}
	var nowTime =Number(nowYMD+""+nowHour+""+nowMinute+"00");
	
	for(let i = 0; i < endTimeList.length; i++){
		let endTime = endTimeList[i].textContent.replace(/-/g,'');
		endTime = endTime.split('.');
		endTime = endTime[0];
	    endTime = endTime.split(" ");
	    endTime[1] = endTime[1].replace(/:/g,'');
		endTime = Number(endTime[0]+endTime[1]);
		
		//過去かどうかを確認
		if(timeRed(nowTime,endTime)){
			let startTime = startTimeList[i].textContent.replace(/-/g,'');
				startTime = startTime.split('.');
				startTime = startTime[0];
				startTime= startTime.split(" ");
				startTime[1] = startTime[1].replace(/:/g,'');
				startTime = Number(startTime[0]+startTime[1]);
			//現在進行形か２時間前かを判定
			switch(timeYellowBlue(nowTime,startTime,endTime)){
				case "blue":
					for(let x = i*6; x < i*6+6;x++){
						content[x].style.backgroundColor ='#007FFF ';
					}
					break;
				case "yellow":
					for(let x = i*6; x < i*6+6;x++){
						content[x].style.backgroundColor ='#FFFF00 ';
					}
					break;
				
			}
			
		}else{
			for(let x = i*6; x < i*6+6;x++){
				content[x].style.backgroundColor ='#FF0000 ';
			}
		}
	}
}
/** 
 *終了時間が過去の場合falseを出す 
*/
function timeRed(now,end){
	
	//終了時間が過去だった場合
	if(Number(now) > Number(end)){
		return false;
	}
	
	return true;
}

/** 
 *現在時間が開始時間以上で終了時間以下だった場合は blue　済み
  現在時間が開始時間の二時間前だった場合は yellow 未実装
*/
function timeYellowBlue(now,start,end){
//												２０２３年４月１２日1時１１分　ー　２０２３年４月１１日２３時１１分
//日付をまたいだ二時間前の場合7800が二時間前となる　202304120111　ー　202304112311
	let nowD =  Math.trunc(now / 1000000); //年月日だし小数点を切り捨てる
	let scheduleD= Math.trunc (start /1000000);
	//日付が同じならば
	if(nowD == scheduleD){
		//開始時間から二時間前の場合
		if((Number(start)-Number(now)) <= 20000 && Number(now) < Number(start)){
			return "yellow";
		}
		//日付をまたいで二時間前の場合
	}else if((Number(start)-Number(now)) <= 7800 && (Number(start)-Number(now)) >= 0){
			return "yellow"; 
	}
	
		//開始時間以上で終了時間未満だった場合
	if(Number(now) >= Number(start) && Number(now) <= Number(end)){
		return "blue";
	}
	
	
}
 
setInterval(timeColor,1000);
 