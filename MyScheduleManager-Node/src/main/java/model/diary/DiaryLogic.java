package model.diary;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DiaryDAO;
import model.util.AntiXSS;

/**
 * 日記関連の処理を行うLOGIC
 * @author yuta
 *
 */
public class DiaryLogic {
	
	/**
	 * 日記情報をIDでロードする
	 * @param diaryId 日記ID
	 * @return 日記情報
	 */
	public DiaryInfo load(String diaryId) throws Exception{
		
		DiaryDAO dao = new DiaryDAO();
		
		return dao.load(diaryId);
	}
	
	/**
	 * 日記を検索する
	 * @param title 題名
	 * @param startTime 開始日
	 * @param endTime 終了日
	 * @param userId ユーザーID
	 * @return 検索結果
	 * @throws Exception 検索失敗
	 */
	public DiaryListBean search(String title,String startTime ,String endTime,int userId) throws Exception {
		
		DiaryDAO diary = new DiaryDAO();
		
		return diary.search(title, startTime, endTime,userId);
	}
	
	/**
	 * DAOに日記登録を依頼する
	 * @param diary 登録したい日記情報
	 * @throws Exception 登録失敗
	 */
	public void add(DiaryInfo diary) throws Exception {
		DiaryDAO diarydao = new DiaryDAO();
		diarydao.add(diary);
	}
	
	/**
	 * DAOに既存の日記更新を依頼する
	 * @param diary 日記更新内容
	 * @throws Exception 更新失敗
	 */
	public void update(DiaryInfo diary) throws Exception{
		DiaryDAO diarydao = new DiaryDAO();
		diarydao.update(diary);
	}
	
	/**
	 * DAOに既存の日記の削除を依頼する
	 * @param diary 削除したい日記情報
	 * @throws Exception 削除失敗
	 */
	public void delete(DiaryInfo diary) throws Exception{
		DiaryDAO diarydao = new DiaryDAO();
		diarydao.delete(diary);
	}
	
	/**
	 * 新規登録や編集フォームで入力された内容をBEANSに格納する
	 * @param request フォーム入力された内容
	 */
	public void setDiaryInfoFromRequest(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		DiaryInfo diary = (DiaryInfo)session.getAttribute("diary");
		
		String userId = AntiXSS.antiXSS(request.getParameter("userId"));
		String title = AntiXSS.antiXSS(request.getParameter("title"));
		String content = AntiXSS.antiXSS(request.getParameter("content"));
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		//新規登録時の処理
		if(diary == null) {
			diary = new DiaryInfo();
			diary.setTime(time);
		}else {
		//編集時は時間の設定は変更しない
		}
		
		diary.setUserId(Integer.parseInt(userId));
		diary.setTitle(title);
		diary.setContent(content);
		
		session.setAttribute("diary",diary);
	}
}
