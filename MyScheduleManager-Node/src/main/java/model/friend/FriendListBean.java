package model.friend;

import java.io.Serializable;
import java.util.List;

/**
 * フレンド検索結果を格納
 * @author yuta
 *
 */
public class FriendListBean implements Serializable{
	/**フレンド検索結果*/
	private List<FriendInfo> friendList;
	
	public FriendListBean() {
		
	}

	public List<FriendInfo> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<FriendInfo> friendList) {
		this.friendList = friendList;
	}
}