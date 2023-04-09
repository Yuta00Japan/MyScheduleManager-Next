package model.util;

import javax.servlet.http.HttpSession;

import controller.BaseServlet;

/**
 * 必須
 * <br>
 * ログインしているかどうかを確認する
 * <hr>
 * 説明：<br>
 * もしログインしてからsessionの値が消えてしまうと<br>
 * ユーザーIDの初期値として０が配置される。この状態のまま<br>
 * 予定を書き込んでしまうとブラウザの設定でsessionを削除するだけで<br>
 * だれでもアクセスできる共有ファイルのような状態になってしまい<br>
 * セキュリティ的に問題がある<br>
 * @author yuta
 *
 */
public class LoginChecker extends BaseServlet{
	
	private LoginChecker() {
		
	}
	
	
	/**
	 * ログインしているかどうかを確認する
	 * @throws Exception ログイン情報欠損
	 */
	public static void loginCheck(HttpSession session) throws Exception {
		
		//ログインユーザー情報が格納されていなければ
		if (session.getAttribute("user") == null) {
			throw new IllegalArgumentException();
		}
	}
}