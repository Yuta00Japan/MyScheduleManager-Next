package model.util;

/**
 * <h2>XSS対策用クラス</h2>
 * フォーム入力内容はRADIOボタンといった文字を入力しない
 * 場合を除いて<br>すべてこのクラスを通してチェックします
 * @author yuta
 *
 */
public class AntiXSS {
	
	private AntiXSS() {
		
	}
	/**
	 * XSS対策　該当する記号がフォーム入力内容に含まれていた場合無害化する
	 * @param enter フォーム入力内容
	 * @return 無害化済み入力内容
	 */
	public static String antiXSS(String enter) {
		
		if(enter == null) {
			return null;
		}
		
		enter = enter.replaceAll("&","xxxx" );
		enter = enter.replaceAll("<","xxxx");
		enter = enter.replaceAll(">","xxxx");
		enter = enter.replaceAll("\'","xxxx");
		enter = enter.replaceAll("\"","xxxx");
		return enter;
	}
}