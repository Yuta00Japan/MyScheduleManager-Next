package model.schedule;

import java.sql.Timestamp;

/**
 * schedule　のカラム情報を格納
 * @author yuta
 *
 */
public class ScheduleBean {
	/**スケジュールID*/
	private int scheduleId;
	/**ユーザーID*/
	private int userId;
	/**題名*/
	private String title;
	/**概要*/
	private String summary;
	/**種別*/
	private String how;
	/**関係者番号*/
	private String who;
	/**場所*/
	private String location;
	/**開始日時*/
	private Timestamp startTime;
	/**終了日時*/
	private Timestamp endTime;
	
	public ScheduleBean() {
		
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getHow() {
		return how;
	}

	public void setHow(String how) {
		this.how = how;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp timestamp) {
		this.startTime = timestamp;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
}