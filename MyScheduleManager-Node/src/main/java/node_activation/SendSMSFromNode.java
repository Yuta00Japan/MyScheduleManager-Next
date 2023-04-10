package node_activation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class SendSMSFromNode  extends Thread{
	
	private String tel;
	
	private int id;
	
	public SendSMSFromNode(String tel,int id) {
		//国番号と組わせるために先頭の０を以外利用する
		this.tel= tel.substring(1);
		this.id  = id;
	}
	
	public void run() {
		
		try {
		    ProcessBuilder pb = new ProcessBuilder("node", "../../2023-03-java/workspace/MyScheduleManager-Node/src/main/webapp/node/sendSMS.js");
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
