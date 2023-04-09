package model.user;

import java.io.Serializable;

/**
 * 登録済みのユーザー情報を格納するクラス<br>
 * user のカラム情報が入る
 * @author yuta
 *
 */
public class UserInfo implements Serializable{
	/**ユーザーID*/
	private int id;
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
	
	public UserInfo() {
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int iD) {
		this.id = iD;
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