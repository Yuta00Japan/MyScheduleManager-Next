/**
 * Java側から電話番号とIDを取得し
 * それにSMSを送信する
 * 
 * なおvonage apiはフリーversionのため電話番号を入力してもその電話番号にSMSを送ることはできません。
 * 
 */
const LocalStorage = require('node-localstorage').LocalStorage;
const localStorage = new LocalStorage('./scratch');

console.log("activation sendSMS");
	const { Vonage } = require('@vonage/server-sdk')
	const vonage = new Vonage({
  		apiKey: "??????",
  		apiSecret: "??????????"
	})
	
	var to = "81"+process.env.PHONE;
	console.log(to);
	var id = process.env.ID;
	console.log(id);
	localStorage.setItem('ID',id);
	const from = "Vonage APIs"
	const text = `MyScheduleManager Authentication ID --${id}--please enter this ID `

	async function sendSMS() {
    	await vonage.sms.send({to, from, text})
        	.then(resp => { console.log('Message sent successfully'); console.log(resp); })
        	.catch(err => { console.log('There was an error sending the messages.'); console.error(err); });
}

sendSMS();

