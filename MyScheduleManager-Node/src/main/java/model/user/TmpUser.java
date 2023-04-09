package model.user;

import java.io.Serializable;

/**
 * ユーザー登録前の情報を格納するクラス<br>
 * <hr>
 * USERINFOはすでに登録されているユーザーの情報を格納するクラス
 * なので使用しない
 * @author yuta
 *
 */
public class TmpUser implements Serializable{
	//登録前のユーザー情報
	
	
	/**ユーザーID*/
	private int userId;
	/**ログインID*/
	private String loginId;
	/**氏名*/
	private String name;
	/**暗証番号*/
	private String password;
	/**性別*/
	private String sex;
	/**電話番号*/
	private String tel;
	/**Eメール*/
	private String email;
	/**住所*/
	private String address;
	
	public TmpUser() {
		
	}
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	

	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	



	
}
