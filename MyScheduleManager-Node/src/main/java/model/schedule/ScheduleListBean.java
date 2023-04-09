package model.schedule;

import java.util.List;

/**
 * 予定検索結果を格納
 * @author yuta
 *
 */
public class ScheduleListBean {
	/**予定検索結果*/
	private List<ScheduleBean> list;
	
	public ScheduleListBean() {
		
	}

	public List<ScheduleBean> getList() {
		return list;
	}

	public void setList(List<ScheduleBean> list) {
		this.list = list;
	}
	
}