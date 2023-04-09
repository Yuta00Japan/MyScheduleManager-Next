package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import model.schedule.ScheduleBean;
import model.user.UserInfo;

/**
 * schedule tableに読み書きするためのクラス
 * @author yuta
 *
 */
public class ScheduleDAO extends BaseDAO{
	
	/**
	 * 検索用のSQLをフォームの入力内容ごとに作成する
	 * @param userId ユーザーID
	 * @param name タイトル名
	 * @param type 種別
	 * @param startTime 開始時間
	 * @param endTime 終了時間
	 * 
	 * @return 検索用SQL
	 */
	public String createSQL(int userId,String name,String type,String startTime,String endTime) {
		
		//未入力でもNULLにならなかった場合は正常に処理できないためNULLにする
		if(name =="") {
			name=null;
		}
		
		if(startTime=="" || endTime=="") {
			startTime=null;
			endTime= null;
		}
		//何も入力されなかった場合
		if(name== null && type== null && startTime == null && endTime == null) {
			return String.format("select * from schedule where userId = %d order by startTime asc ",userId);
			
			//名前でのみ検索された場合
		}else if(name!=null && type== null &&  startTime == null && endTime == null) {
			return String.format("select * from schedule where title like"+"'%%"+"%s"+"%%' and userId=%d  order by startTime asc",name,userId);
			
			//種別でのみ検索された場合
		}else if(type!=null && name==null && startTime == null && endTime == null) {
			return String.format("select * from schedule where how='%s' and userId=%d order by startTime asc",type,userId);
			
			//時間帯でのみ検索された場合
		}else if(startTime != null && endTime != null && name==null && type== null ) {
			startTime = startTime+"00";
			endTime = endTime +"00";
			return String.format("select * from schedule where startTime between %s and %s and userId=%d order by startTime asc",startTime,endTime,userId);
			
			//名前と種別で検索された場合
		}else if(name != null && type!= null &&  startTime == null && endTime == null) {
			return String.format("select * from schedule where title like "+"'%%"+"%s"+"%%' and how='%s' and userId=%d order by startTime asc",name,type,userId);
			
			//名前と時間帯で検索された場合
		}else if(name != null && startTime != null && endTime != null && type== null) {
			startTime = startTime+"00";
			endTime = endTime +"00";
			return String.format("select * from schedule where title like "+"'%%"+"%s"+"%%' and startTime between %s and %s and userId=%d order by startTime asc",name,startTime,endTime,userId);
			
			//種別と時間帯で検索された場合
		}else if(type!= null && startTime != null && endTime != null && name == null) {
			startTime = startTime+"00";
			endTime = endTime +"00";
			return String.format("select * from schedule where how='%s' and startTime between %s and %s and userId=%d order by startTime asc", type,startTime,endTime,userId);
			
		}else {
			//すべて入力された場合
			startTime = startTime+"00";
			endTime = endTime +"00";
			return String.format("select * from schedule where title like "+"'%%"+"%s"+"%%'" + "and how='%s' and startTime between %s and %s and userId=%d order by startTime asc",name,type,startTime,endTime,userId);
		}
		
	}

	
	/**
	 * 予定IDから情報を取得
	 * @param id 予定ID
	 * @return 予定情報
	 */
	public ScheduleBean load(String id) {
		ScheduleBean schedule = null;
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement("select * from schedule where scheduleid = ?");
			pps.setInt(1, Integer.parseInt(id));
			rs = pps.executeQuery();
			if(rs.next()) {
				schedule =new ScheduleBean();
				schedule.setScheduleId(rs.getInt("scheduleId"));
				schedule.setUserId(rs.getInt("userId"));
				schedule.setSummary(rs.getString("summary"));
				schedule.setTitle(rs.getString("title"));
				schedule.setHow(rs.getString("how"));
				schedule.setLocation(rs.getString("location"));
				schedule.setWho(rs.getString("who"));
				schedule.setStartTime(rs.getTimestamp("startTime"));
				schedule.setEndTime(rs.getTimestamp("endTime"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return schedule;
	}
	
	/**
	 * 予定検索を行う
	 * @param users ユーザー情報　主にIDを使用する
	 * @param title タイトル
	 * @param type 種別
	 * @param startTime 予定開始日
	 * @param endTime 予定終了日
	 * @return 予定情報検索結果
	 */
	public List<ScheduleBean> Search(UserInfo users,String title,String type,String startTime,String endTime) {
		List<ScheduleBean> schedule = new ArrayList<>();
		ScheduleBean list = null;
		String sql = createSQL(users.getId(),title,type,startTime,endTime);
		System.out.println("検索 sql : " + sql);
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement(sql);
			rs = pps.executeQuery();
			while(rs.next()) {
				list = new ScheduleBean();
				list.setScheduleId(rs.getInt("scheduleId"));
				list.setUserId(rs.getInt("userId"));
				list.setTitle(rs.getString("title"));
				list.setSummary(rs.getString("summary"));
				list.setHow(rs.getString("how"));
				list.setWho(rs.getString("who"));
				list.setLocation(rs.getString("location"));
				list.setStartTime(rs.getTimestamp("startTime"));
				list.setEndTime(rs.getTimestamp("endtime"));
				schedule.add(list);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return schedule;
	}
	
	/**
	 * 予定新規登録
	 * @param schedule 登録したい予定情報
	 * @throws Exception 登録失敗
	 */
	public void add(ScheduleBean schedule) throws Exception{
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement
				("insert into schedule(userId,title,summary,how,who,location,starttime,endtime) "
				+ " values(?,?,?,?,?,?,?,?)");
			pps.setInt(1, schedule.getUserId());
			pps.setString(2,schedule.getTitle());
			pps.setString(3, schedule.getSummary());
			pps.setString(4, schedule.getHow());
			pps.setString(5, schedule.getWho());
			pps.setString(6, schedule.getLocation());
			pps.setTimestamp(7, schedule.getStartTime());
			pps.setTimestamp(8, schedule.getEndTime());
			pps.executeUpdate();
		}
	}
	
	/**
	 * 予定内容更新を行う
	 * @param schedule 予定更新内容
	 * @throws Exception 更新失敗
	 */
	public void update(ScheduleBean schedule) throws Exception{
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement
				("update schedule "
				+" set title=?,summary=?,how=?,who=?,location=?,startTime=?,endTime=? "
				+ "where scheduleId=? and userId=?");
			pps.setString(1,schedule.getTitle());
			pps.setString(2, schedule.getSummary());
			pps.setString(3, schedule.getHow());
			pps.setString(4, schedule.getWho());
			pps.setString(5, schedule.getLocation());
			pps.setTimestamp(6, schedule.getStartTime());
			pps.setTimestamp(7, schedule.getEndTime());
			pps.setInt(8, schedule.getScheduleId());
			pps.setInt(9, schedule.getUserId());
			pps.executeUpdate();
		}
	}
	
	/**
	 * 予定削除を行う
	 * @param schedule 削除したい予定
	 * @throws Exception 削除失敗
	 */
	public void delete(ScheduleBean schedule) throws Exception{
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement
				("delete from schedule where scheduleId = ? and userId=?");
			pps.setInt(1, schedule.getScheduleId());
			pps.setInt(2,schedule.getUserId());
			pps.executeUpdate();
		}
	}
}