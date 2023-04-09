package node_activation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;

/**
 * Node.js expressを実行させるためのクラス
 * <br>
 * @author yuta
 *
 */
public class NodeConnectMysql extends Thread {

	/**
	 * connect_mysql.jsファイルをjava側で起動させる
	 * @throws ServletException
	 */
	public void run() {
		try {
		    ProcessBuilder pb = new ProcessBuilder("node", "../../2023-03-java/workspace/Test22/src/main/webapp/node/connect_mysql.js");
		    Process p = pb.start();

		    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		    
		   // errorMessagePrinter(p);
		    
		    int exitCode = p.waitFor();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * エラー出力印刷
	 * @param p エラー元
	 * @throws IOException
	 */
	public static void errorMessagePrinter(Process p) throws IOException {
		  //エラー出力
	    BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	    String errorLine;
	    while ((errorLine = errorReader.readLine()) != null) {
	        System.out.println(errorLine);
	    }

	}
}
