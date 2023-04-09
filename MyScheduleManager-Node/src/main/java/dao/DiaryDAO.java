package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import model.diary.DiaryInfo;
import model.diary.DiaryListBean;

/**
 * diary テーブルにデータを読み書きするためのクラス
 * @author yuta
 *
 */
public class DiaryDAO extends BaseDAO{
	
	/**
	 * 入力内容に応じて検索用SQLを作成
	 * @param title 題名
	 * @param startTime 開始日
	 * @param endTime 終了日
	 * @param userId ユーザーID
	 * @return 検索用SQL
	 */
	public String createSQL(String title,String startTime,String endTime,int userId) {
		
		if(title == "") {
			title = null;
		}
		
		if(startTime =="" || endTime =="") {
			startTime = null;
			endTime = null;
		}
		
		//題名のみで検索
		if(title != null && startTime == null && endTime == null) {
			return String.format("select * from diary where title like"+ "'%%"+"%s"+"%%' and userId =%d order by time asc",title,userId);
		//時間帯のみで検索
		}else if(title == null && startTime != null && endTime != null) {
			return String.format("select * from diary where time between %s and %s and userId=%d order by time asc",startTime,endTime,userId);
		//題名と時間帯で検索
		}else if(title != null && startTime != null && endTime != null) {
			return String.format("select * from diary where title like "+"'%%"+"%s"+"%%' and time between %s and %s and userId=%d order by time asc",title,startTime,endTime,userId);
		//何も入力せず検索
		}else {
			return String.format("select * from diary where userId = %d order by time asc",userId);
		}
	}
	
	/**
	 * 日記IDをもとに情報を取得する
	 * @param diaryId 日記ID
	 * @return 日記情報
	 * @throws Exception ロード失敗
	 */
	public DiaryInfo load(String diaryId) throws Exception{
		
		DiaryInfo diary = null;
		
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement("select * from diary where diaryId = ?");
			pps.setInt(1, Integer.parseInt(diaryId));
			rs = pps.executeQuery();
			if(rs.next()) {
				diary = new DiaryInfo();
				diary.setDiaryId(rs.getInt("diaryID"));
				diary.setUserId(rs.getInt("userId"));
				diary.setTitle(rs.getString("title"));
				diary.setTime(rs.getDate("time"));
				diary.setContent(rs.getString("content"));
			}
			return diary;
		}
	}
	
	/**
	 * フォームの入力内容から日記を検索
	 * @param title 題名
	 * @param startTime 開始日
	 * @param endTime 終了日
	 * @param userId ユーザーID
	 * @return 日記検索結果
	 * @throws Exception 検索エラー
	 */
	public DiaryListBean search(String title ,String startTime,String endTime,int userId) throws Exception{
		
		DiaryInfo diary = null;
		DiaryListBean diaryList = new DiaryListBean();
		
		String sql = createSQL(title,startTime,endTime,userId);
		System.out.println("検索　SQL："+ sql);
		
		List<DiaryInfo> list = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement(sql);
			rs = pps.executeQuery();
			while(rs.next()) {
				diary = new DiaryInfo();
				diary.setDiaryId(rs.getInt("diaryId"));
				diary.setUserId(rs.getInt("userId"));
				diary.setTitle(rs.getString("title"));
				diary.setContent(rs.getString("content"));
				diary.setTime(rs.getDate("time"));
				
				list.add(diary);
			}
			//検索結果を格納
			diaryList.setDiaryList(list);
		}
		
		return diaryList;
	}
	
	/**
	 * 新規日記登録
	 * @param diary 登録したい日記情報
	 * @throws Exception 登録失敗
	 */
	public void add(DiaryInfo diary)throws Exception {
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement("insert into diary(userId,title,time,content) values(?,?,?,?)");
			pps.setInt(1, diary.getUserId());
			pps.setString(2, diary.getTitle());
			pps.setString(3,""+diary.getTime());
			pps.setString(4, diary.getContent());
			pps.executeUpdate();
		}
	}
	
	/**
	 * 既存の日記内容を更新
	 * @param diary 更新したい日記情報
	 * @throws Exception 更新失敗
	 */
	public void update(DiaryInfo diary) throws Exception {
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement("update diary set title=?,content=? where diaryId=? and userId=?");
			pps.setString(1, diary.getTitle());
			pps.setString(2, diary.getContent());
			pps.setInt(3, diary.getDiaryId());
			pps.setInt(4, diary.getUserId());
			pps.executeUpdate();
		}
	}
	
	/**
	 * 既存の日記を削除
	 * @param diary 削除したい日記情報
	 * @throws Exception 削除失敗
	 */
	public void delete(DiaryInfo diary)throws Exception {
		try(Connection con = DriverManager.getConnection(url,user,pass)){
			pps = con.prepareStatement("delete from diary where diaryId = ? and userId = ?");
			pps.setInt(1, diary.getDiaryId());
			pps.setInt(2, diary.getUserId());
			pps.executeUpdate();
		}
	}
}
