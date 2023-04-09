package errConst;


/**
 * エラーメッセージ用クラス
 * 
 * @author yuta
 *
 */
public class ErrMessage {

	private ErrMessage() {
		
	}
	
	/**検索失敗用*/
	public static String searchErr = "検索に失敗しました";
	/**ログインIDがダブった時用*/
	public static String loginIdErr = "そのログインIDはすでに使用されています";
	/**友人顔写真が許容範囲を超えたとき用*/
	public static String imageErr = "画像ファイルが60000バイト以上です！";
	/**更新失敗用*/
	public static String updateErr ="更新できませんでした";
	/**削除失敗用*/
	public static String deleteErr = "削除できませんでした";
	/**登録失敗用*/
	public static String addErr = "登録できませんでした。";
	/**ロード失敗*/
	public static String loadErr="情報取得に失敗しました";
}