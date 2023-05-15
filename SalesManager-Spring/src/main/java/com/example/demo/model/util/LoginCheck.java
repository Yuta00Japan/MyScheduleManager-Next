package com.example.demo.model.util;

import jakarta.servlet.http.HttpSession;

/**
 * ログイン情報が保持されているかどうかをチェックする
 * model
 * @author yuta
 */
public class LoginCheck {

	private LoginCheck() {
		
	}
	
	/**
	 * ログインしているユーザのsessionが保持されているかどうかを
	 * 確認する
	 * @param session チェック対象session
	 * @return 検査結果
	 */
	public static boolean check(HttpSession session) {
		
		if(session.getAttribute("user")==null) {
			return false;
		}
		
		return true;
	}
}
