package model.diary;

import java.io.Serializable;
import java.util.Date;

/**
 * diary カラム情報を格納
 * @author yuta
 *
 */
public class DiaryInfo implements Serializable{
	
	/**日記番号*/
	private int diaryId;
	/**ユーザーID*/
	private int userId;
	/**題名*/
	private String title;
	/**書いた日付*/
	private Date time;
	/**内容*/
	private String content;
	
	public DiaryInfo() {
		
	}

	public int getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}