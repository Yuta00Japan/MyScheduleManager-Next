package model.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * ユーザー登録用IDを暗号化するクラス
 * @author yuta
 *
 */
public class Encrypt {
	
	/**暗号化KEY<br>
	 * <hr>
	 * キーの長さは16バイト、24バイト、または32バイトでなければなりません。*/
	public static final String KEY = "my-secret-key-123";
	
	private Encrypt(){
		
	}
	
	/**
	 * 発行した６桁の文字列を暗号化する
	 * @param tmpId ユーザー登録用ID
	 * @return 暗号化されたID
	 * @throws Exception 暗号化失敗
	 */
	public static String encrypt(String tmpId) throws Exception {
		 byte[] key = KEY.getBytes(StandardCharsets.UTF_8);
		 MessageDigest sha = MessageDigest.getInstance("SHA-1");
	     key = sha.digest(key);
	     key = Arrays.copyOf(key, 16); 
	     SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
	     Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	     cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
	     byte[] encryptedBytes = cipher.doFinal(tmpId.getBytes(StandardCharsets.UTF_8));
	     return Base64.getEncoder().encodeToString(encryptedBytes);
	}

}