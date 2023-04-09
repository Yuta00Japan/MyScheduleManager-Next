package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.user.TmpUser;
import model.user.UserInfo;

/**
 * user tableに読み書きするためのクラス
 * @author yuta
 *
 */
public class UserDAO extends BaseDAO{

	/**
	 * ログイン認証
	 * @param loginId ログインID
	 * @param password 暗証番号
	 * @return ユーザー情報
	 */
	public UserInfo login(String loginId,String password) throws SQLException{
		
		String sql ="select * "
				+ " from user "
				+ " where loginId =? and password = ?";
		
		UserInfo users = null;
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement(sql);
			pps.setString(1, loginId);
			pps.setString(2, password);
			System.out.println(sql);
			rs = pps.executeQuery();
			if(rs.next()) {
				users = new UserInfo();
				users.setId(rs.getInt("userid"));
				users.setLoginId(rs.getString("loginId"));
				users.setName(rs.getString("name"));
				users.setPassword(rs.getString("password"));
				users.setSex(rs.getString("sex"));
				users.setTel(rs.getString("tel"));
				users.setEmail(rs.getString("email"));
				users.setAddress(rs.getString("address"));
			}
		}
		
		return users;
	}
	
	/**
	 * ユーザー登録
	 * @param tmp 登録したいユーザー情報
	 * @SQLexception  登録失敗
	 */
	public void add(TmpUser tmp) throws SQLException{
		try(Connection con =DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement
			("insert into user(loginId,name,password,sex,tel,email,address) values(?,?,?,?,?,?,?)");
			pps.setString(1, tmp.getLoginId());
			pps.setString(2, tmp.getName());
			pps.setString(3,tmp.getPassword());
			pps.setString(4, tmp.getSex());
			pps.setString(5, tmp.getTel());
			pps.setString(6, tmp.getEmail());
			pps.setString(7, tmp.getAddress());
			pps.executeUpdate();
		}
	}
	
	/**
	 * ユーザー情報更新
	 * @param user ユーザー情報更新内容
	 * @throws SQLException 更新失敗
	 */
	public void update(TmpUser users) throws SQLException{
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement
					("update user set loginId=?,name=?,password=?,sex=?,tel=?,email=?,address=?"
					+ " where userId = ?");
			pps.setString(1, users.getLoginId());
			pps.setString(2, users.getName());
			pps.setString(3, users.getPassword());
			pps.setString(4, users.getSex());
			pps.setString(5, users.getTel());
			pps.setString(6, users.getEmail());
			pps.setString(7, users.getAddress());
			pps.setInt(8, users.getUserId());
			
			pps.executeUpdate();
		}
	}
	
	
	
}
