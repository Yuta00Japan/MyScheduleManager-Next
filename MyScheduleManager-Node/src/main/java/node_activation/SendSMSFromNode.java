package node_activation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * SendSMS.jsファイルをjava側から実行させます
 * @author yuta
 *
 */
public class SendSMSFromNode  extends Thread{
	/**送信先電話番号*/
	private String tel;
	/**認証ID*/
	private int id;
	
	/**
	 * 送信先、認証IDをセット
	 * @param tel 電話番号
	 * @param id 認証ID
	 */
	public SendSMSFromNode(String tel,int id) {
		//国番号と組わせるために先頭の０を以外利用する
		this.tel= tel.substring(1);
		this.id  = id;
	}
	
	public void run() {
		
		try {
		    ProcessBuilder pb = new ProcessBuilder("node", "../../../Users/yuta/git/MyScheduleManager-Next/MyScheduleManager-Node/src/main/webapp/node/sendSMS.js");
		    Map<String, String> env = pb.environment();
		    //NODE側に送る値をセット
		    env.put("PHONE",this.tel);
		    env.put("ID", ""+this.id);
		    
		    Process p = pb.start();

		    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		    
		    BufferedReader readera = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		    String linea;
		    while ((linea = readera.readLine()) != null) {
		        System.err.println(linea);
		    }
		    
		    int exitCode = p.waitFor();
		    System.out.println("node SMS: "+exitCode);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
}
