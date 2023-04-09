package model.user;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import dao.UserDAO;
import model.util.AntiXSS;

/**
 * ユーザー処理を行うLOGIC
 * @author yuta
 *
 */
public class UserLogic extends Thread{

	/**認証メール送信先*/
	private String to;
	/**認証ID*/
	private int id;
	
	public UserLogic() {
		
	}
	
	public UserLogic(String to,int id) {
		this.to = to;
		this.id = id;
	}
	
	/**
	 * Idとパスワードで検証
	 * @param loginId ログインID
	 * @param password 暗証番号
	 * @throws SQLException ログイン処理失敗
	 * @return ユーザー情報
	 */
	public UserInfo login(String loginId,String password) throws SQLException{
		UserDAO login = new UserDAO();
		UserInfo user= login.login(loginId, password);
		
		return user;
	}
	
	/**
	 * ユーザーを登録する
	 * @param tmp 登録したいユーザー情報
	 * @throws SQLException 登録失敗
	 */
	public void add(TmpUser tmp) throws SQLException {
		UserDAO user = new UserDAO();
		user.add(tmp);
	}
	
	/**
	 * ユーザー情報を更新する
	 * @param tmp ユーザー情報更新内容
	 * @throws Exception 更新失敗
	 */
	public void update(TmpUser tmp) throws Exception {
		UserDAO user = new UserDAO();
		user.update(tmp);
	}
	
	/**
	 * ユーザー登録フォームで受けとったメールアドレスに認証番号を送信します
	 * <br>
	 *スレッドなしで順次実行した場合ロード時間がかなりかかるため スレッドで処理を実行する
	 */
	public void run(){

		final String emailAddress = "umemotoyuuta307@gmail.com";
		
		final String password = "xomqwhtchylpxhki";
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setStartTLSEnabled(true);
		email.setAuthentication(emailAddress, password);
		
		try {
			email.setFrom(emailAddress).addTo(to)
			.setSubject("予定管理システム　ユーザー登録")
			.setMsg("認証コードは"+id+"です").send();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * フォームで受け取った情報をBEANSに格納する
	 * @param request フォーム入力内容
	 */
	public void setTmpUserFromRequest(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		TmpUser tmp = new TmpUser();
		
		String userId = AntiXSS.antiXSS(request.getParameter("userId"));
		String loginId = AntiXSS.antiXSS(request.getParameter("loginId"));
		String name = AntiXSS.antiXSS(request.getParameter("name"));
		String password = AntiXSS.antiXSS(request.getParameter("password"));
		String sex = AntiXSS.antiXSS(request.getParameter("sex"));
		String tel = AntiXSS.antiXSS(request.getParameter("tel"));
		String email = AntiXSS.antiXSS(request.getParameter("email"));
		String address = AntiXSS.antiXSS(request.getParameter("address"));
		
		//例外防止用　ユーザー登録時にNULLのUSERIDに変換を処理を行うエラーが発生するため
		if(userId == null || userId.equals("")) {
			
		}else {
			tmp.setUserId(Integer.parseInt(userId));
		}
		
		tmp.setLoginId(loginId);
		tmp.setName(name);
		tmp.setPassword(password);
		tmp.setSex(sex);
		tmp.setTel(tel);
		tmp.setEmail(email);
		tmp.setAddress(address);
		
		session.setAttribute("tmpUser", tmp);
	}

	
	
}