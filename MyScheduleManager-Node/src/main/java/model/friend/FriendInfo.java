package model.friend;

import java.io.Serializable;

/**
 * friend　のカラム情報を格納
 * @author yuta
 *
 */
public class FriendInfo implements Serializable{
	/**顔写真バイトデータ*/
	private byte[] facePath;
	/**フレンドコード*/
	private int friendId;
	/**ユーザーID*/
	private int userId;
	/**友人詳細*/
	private String introduction;
	/**氏名*/
	private String name;
	/**職業*/
	private String status;
	/**性別*/
	private String sex;
	/**Eメール*/
	private String email;
	/**住所*/
	private String address;
	/**電話番号*/
	private String tel;
	
	public FriendInfo() {
		
	}
	
	public byte[] getFacePath() {
		return facePath;
	}

	public void setFacePath(byte[] facePath) {
		this.facePath = facePath;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	

	

}