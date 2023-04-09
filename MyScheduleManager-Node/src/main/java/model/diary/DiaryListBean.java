package model.diary;

import java.util.List;

/**
 * 日記検索結果を格納するクラス
 * @author yuta
 *
 */
public class DiaryListBean {
	
	/**日記検索結果*/
	private List<DiaryInfo> diaryList;
	

	public List<DiaryInfo> getDiaryList() {
		return diaryList;
	}

	public void setDiaryList(List<DiaryInfo> diaryList) {
		this.diaryList = diaryList;
	}
	
	
}