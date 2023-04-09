package model.schedule;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.FriendDAO;
import dao.ScheduleDAO;
import model.friend.FriendListBean;
import model.user.UserInfo;
import model.util.AntiXSS;

/**
 * 予定機能の処理を行うLOGIC
 * @author yuta
 *
 */
public class ScheduleLogic {
	
	/**
	 * 予定情報をスケジュールIDで取りだす
	 * @param id スケジュールID
	 * @return スケジュール情報
	 */
	public ScheduleBean load(String id) {
		
		ScheduleDAO sd = new ScheduleDAO();
		return sd.load(id);
	}
	
	/**
	 * 
	 * @param user ユーザー情報
	 * @param scheduleName スケジュール名検索
	 * @param type スケジュール種別
	 * @param startTime 開始日
	 * @param endTime 終了日
	 * @return スケジュール検索結果
	 */
	public List<ScheduleBean> searchList(UserInfo user,String scheduleName,String type,String startTime,String endTime){
		
		ScheduleDAO schedule = new ScheduleDAO();
		
		return schedule.Search(user,scheduleName,type,startTime,endTime);
	}
	
	/**
	 * スケジュール追加
	 * @param sc 追加したいスケジュール情報
	 * @throws Exception 追加失敗
	 */
	public void add(ScheduleBean sc) throws Exception{
		ScheduleDAO schedule = new ScheduleDAO();
		schedule.add(sc);
	}
	
	/**
	 * 既存スケジュール内容更新
	 * @param sc スケジュール更新内容
	 * @throws Exception 更新失敗
	 */
	public void update(ScheduleBean sc) throws Exception{
		ScheduleDAO schedule = new ScheduleDAO();
		schedule.update(sc);
	}
	
	/**
	 * 既存スケジュール削除
	 * @param sc 削除したいスケジュール情報
	 * @throws Exception 削除失敗
	 */
	public void delete(ScheduleBean sc) throws Exception{
		ScheduleDAO schedule = new ScheduleDAO();
		schedule.delete(sc);
	}
	/**
	 * 主に新規登録、詳細・編集機能で使用する
	 * @param request 新規登録情報 OR 編集情報
	 * @throws ParseException フォーマットエラー
	 */
	public void setScheduleFromRequestToScheduleBean(HttpServletRequest request) throws ParseException {
		
		HttpSession session = request.getSession();
		
		String title = AntiXSS.antiXSS(request.getParameter("title"));
		String summary = AntiXSS.antiXSS(request.getParameter("summary"));
		String how = AntiXSS.antiXSS(request.getParameter("how"));
		String[]who = request.getParameterValues("who");
		String location = AntiXSS.antiXSS( request.getParameter("location"));
		String startTime = request.getParameter("startTime").replaceAll("[-,T,:]","")+"00";
		String endTime = request.getParameter("endTime").replaceAll("[-,T,:]","")+"00";
		UserInfo user =(UserInfo)session.getAttribute("user");
		
		ScheduleBean schedule = (ScheduleBean)session.getAttribute("schedule");
		
		if(schedule == null) {
			schedule = new ScheduleBean();
		}
		
		schedule.setUserId(user.getId());
		schedule.setTitle(title);
		schedule.setSummary(summary);
		schedule.setHow(how);
		schedule.setWho(who[0]);
		schedule.setLocation(location);
		
		//TIMESTAMP型に格納するための準備
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
		Timestamp time = new Timestamp(simple.parse(startTime).getTime());
		schedule.setStartTime(time);
				  time = new Timestamp(simple.parse(endTime).getTime());
		schedule.setEndTime(time);
		
		//受け取ったフレンドIDからユーザー名を取り出す
		FriendDAO friend = new FriendDAO();
		FriendListBean list = new FriendListBean();
		list.setFriendList(friend.loadMultiple(who[0]));
		
		session.setAttribute("userName", list);
		session.setAttribute("schedule", schedule);
		
	}
	
}