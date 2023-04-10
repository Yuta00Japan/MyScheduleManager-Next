const express = require('express');
const mysql = require('mysql2');
const LocalStorage = require('node-localstorage').LocalStorage;
const localStorage = new LocalStorage('./scratch');
const net = require('net');
const app = express();
//8080へのアクセス許可を設定する
app.use(function(req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', 'http://localhost:8080');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');
    res.setHeader('Access-Control-Allow-Credentials', true);
    next();
});


const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '1013UmeAs5013TrueFalse',
  database: 'sns'
});

const port= 3000; 

const isPortInUse = (port) => {
  return new Promise((resolve, reject) => {
    const server = net.createServer((socket) => {})
      .on('error', (err) => {
        if (err.code === 'EADDRINUSE') {
          resolve(true);
        } else {
          reject(err);
        }
      })
      .on('listening', () => {
        server.close();
        resolve(false);
      })
      .listen(port);
  });
}

// ポートが使用中でなければサーバーを起動
isPortInUse(port)
  .then(inUse => {
    if (inUse) {
      console.log(`Port ${port} is already in use.`);
    } else {
      app.listen(port, () => {
        console.log(`Server is running on port ${port}`);
      });
    }
  })
  .catch(err => {
    console.error(err);
  });
//ここまで前処理

//ユーザーのフレンド検索処理

//userIdをjavascript側から取得しローカルストレージに格納
app.post('/userid',(req,res) =>{
	let data = req.body.userId;
	localStorage.setItem('userId',data);
	res.status(200).send("Data received successfully");
});


//格納したデータを取りだしたUSERLDをもとにデータベースにアクセスしデータを取りだす
app.get('/setFriend',(req,res) =>{
	let userid =localStorage.getItem('userId');
	userid = Number(userid);
	var sql = `select * from friend where userId =${userid}`;
	console.log(sql);
	connection.query(sql,(error,results,field)=>{
		 if (error) throw error;
  		// 結果を処理する
  		console.log(results);
  		res.json(results);
	});
});

//javascriptに電話番号とIDを返します
app.get('/node',(req,res)=>{
	let id = localStorage.getItem('ID');
	res.writeHead(200, { 'Content-Type': 'text/plain' });
 	res.write(id);
  	res.end();
});
 






