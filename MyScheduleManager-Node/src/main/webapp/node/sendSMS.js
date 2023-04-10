/**
 * Java側から電話番号とIDを取得し
 * それにSMSを送信する
 */
console.log("activation sendSMS");
	const { Vonage } = require('@vonage/server-sdk')

	const vonage = new Vonage({
  		apiKey: "fe2e99ed",
  		apiSecret: "FDcYu4kUZK3CWcWA"
	})
	
	var to = "81"+process.env.PHONE;
	console.log(to);
	var id = process.env.ID;
	console.log(id);
	const from = "Vonage APIs"
	const text = `MyScheduleManager  Authentication ID =>${id}`

	async function sendSMS() {
    	await vonage.sms.send({to, from, text})
        	.then(resp => { console.log('Message sent successfully'); console.log(resp); })
        	.catch(err => { console.log('There was an error sending the messages.'); console.error(err); });
}

sendSMS();

