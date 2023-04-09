package model.util;

import java.util.Random;

public class CreateTmpId {
	
	private CreateTmpId() {
		
	}
	
	/**
	 * ユーザー登録のIDを発行する
	 * @return 生成されたID
	 */
	public static int createTmpId() {
		Random rand = new Random();
		int id = 100000+ rand.nextInt(900000);
		return id;
	}
	
}
