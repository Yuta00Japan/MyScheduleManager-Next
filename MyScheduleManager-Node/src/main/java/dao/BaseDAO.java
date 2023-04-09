package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 他のDAOがデータベースを利用するための親クラス<br>
 * 継承材料として使用
 * @author yuta
 *
 */
public abstract class BaseDAO {
	
	/**JDBC URL*/
	protected final String url="jdbc:mysql://localhost/sns?useSSL = false";
	/**JDBC USER*/
	protected final String user ="root";
	/**JDBC PASSWORD*/
	protected final String pass= "1013UmeAs5013TrueFalse";
	/**汎用preparedStatement*/
	protected PreparedStatement pps;
	/**データ抽出汎用　ResultSet*/
	protected ResultSet rs;
}