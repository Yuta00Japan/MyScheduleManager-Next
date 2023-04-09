package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import model.friend.FriendInfo;

/**
 * friend tableに読み書きするためのクラス
 * @author yuta
 *
 */
public class FriendDAO extends BaseDAO{

	/**
	 * 一つのフレンドコードから情報を取り出す
	 * @param id フレンドコード
	 * @return 一人の友人情報
	 */
	public FriendInfo loadSingle(String id) {
		FriendInfo friend = null;
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement
			("select face,friendId,userId,name,introduction,sex,status,address,email,tel from friend "
				+ " inner join statusTable on statusCode = statusId"
				+ " where friendId= ?");
			pps.setInt(1, Integer.parseInt(id));
			rs = pps.executeQuery();
			if(rs.next()) {
				friend = new FriendInfo();
				friend.setFacePath(rs.getBytes("face"));
				friend.setFriendId(rs.getInt("friendId"));
				friend.setUserId(rs.getInt("userId"));
				friend.setName(rs.getString("name"));
				friend.setIntroduction(rs.getString("introduction"));
				friend.setSex(rs.getString("sex"));
				friend.setStatus(rs.getString("status"));
				friend.setAddress(rs.getString("address"));
				friend.setEmail(rs.getString("email"));
				friend.setTel(rs.getString("tel"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return friend;
	}
	
	/**
	 * 複数のフレンドコードから情報を取り出す
	 * @param ids 複数のフレンドコード
	 * @return 複数の友人情報
	 */
	public List<FriendInfo> loadMultiple(String ids) {
		
		List<FriendInfo> list = new ArrayList<>();
		FriendInfo friend = null;
		String [] friendCodes = ids.split(",");
		
		for(String code : friendCodes) {
			try(Connection con = DriverManager.getConnection(url,user,pass)){
				pps = con.prepareStatement
					("select face,friendId,userId,name,introduction,sex,status,address,email,tel from friend "
					+ " inner join statusTable on statusCode = statusId"
					+ " where friendId= ?");
				pps.setInt(1, Integer.parseInt(code));
				rs = pps.executeQuery();
				while(rs.next()) {
					friend = new FriendInfo();
					friend.setFacePath(rs.getBytes("face"));
					friend.setFriendId(rs.getInt("friendId"));
					friend.setUserId(rs.getInt("userId"));
					friend.setName(rs.getString("name"));
					friend.setSex(rs.getString("sex"));
					friend.setStatus(rs.getString("status"));
					friend.setEmail(rs.getString("email"));
					friend.setAddress(rs.getString("address"));
					friend.setTel(rs.getString("tel"));
					list.add(friend);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/**
	 * 名前から友人情報を検索
	 * @param name 入力された氏名
	 * @param userId ユーザーID
	 * @return 検索条件に一致する友人情報
	 */
	public List<FriendInfo> searchByName(String name,int userId){
		List<FriendInfo> list = new ArrayList<>();
		FriendInfo friend = null;
		String sql =
				"select face,friendId,userId,name,sex,status,email, address,tel "
				+ " from friend inner join statusTable on statusCode = statusId "
				+ "where name like "+"'%%"+"%s"+"%%' and userId = %d";
		sql =String.format(sql, name,userId);
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement(sql);
			rs = pps.executeQuery();
			while(rs.next()) {
				friend = new FriendInfo();
				friend.setFacePath(rs.getBytes("face"));
				friend.setFriendId(rs.getInt("friendId"));
				friend.setUserId(rs.getInt("userId"));
				friend.setName(rs.getString("name"));
				friend.setSex(rs.getString("sex"));
				friend.setStatus(rs.getString("status"));
				friend.setEmail(rs.getString("email"));
				friend.setAddress(rs.getString("address"));
				friend.setTel(rs.getString("tel"));
				list.add(friend);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 新規登録
	 * @param friend　登録する友人情報
	 */
	public void add(FriendInfo friend) throws Exception{
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement("insert into friend"
					+ "(face,userId,name,statusCode,sex,email,address,tel,introduction)"
					+ " values(?,?,?,?,?,?,?,?,?)");
			pps.setBytes(1,friend.getFacePath());
			pps.setInt(2,friend.getUserId());
			pps.setString(3, friend.getName());
			pps.setInt(4,Integer.parseInt(friend.getStatus()));
			pps.setString(5,friend.getSex());
			pps.setString(6,friend.getEmail());
			pps.setString(7,friend.getAddress());
			pps.setString(8,friend.getTel());
			pps.setString(9, friend.getIntroduction());
			pps.executeUpdate();
			
		}
	}
	
	/**
	 * 登録されている友人情報を更新する
	 * @param friend 更新したい友人情報
	 */
	public void update(FriendInfo friend) throws Exception{
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement
				("update friend set face=?,name=?,statusCode=?,sex=?,address=?, "
				+ "tel=?,email=?,introduction=? where friendId=? and userId=?");
			pps.setBytes(1,friend.getFacePath());
			pps.setString(2,friend.getName());
			pps.setInt(3,Integer.parseInt(friend.getStatus()));
			pps.setString(4, friend.getSex());
			pps.setString(5, friend.getAddress());
			pps.setString(6,friend.getTel());
			pps.setString(7,friend.getEmail());
			pps.setString(8, friend.getIntroduction());
			pps.setInt(9, friend.getFriendId());
			pps.setInt(10, friend.getUserId());
			pps.executeUpdate();
		}
	}
	/**
	 * フレンドコードとユーザーコードが一致するフレンドを削除する
	 * @param friend 削除したいフレンド
	 */
	public void delete(FriendInfo friend) throws Exception{
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement
				("delete from friend where friendId=? and userId=?");
			pps.setInt(1, friend.getFriendId());
			pps.setInt(2, friend.getUserId());
			pps.executeUpdate();
		}
	}
}