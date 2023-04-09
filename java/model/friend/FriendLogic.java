package model.friend;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.FriendDAO;
import model.user.UserInfo;
import model.util.AntiXSS;
/**
 * <hr>
 * フレンド機能の処理を行うLOGIC
 * @author yuta
 *
 */
@MultipartConfig
public class FriendLogic {
	
	/**
	 * 一つのIDから情報を取り出す
	 * @param id フレンドコード
	 * @return フレンド情報単体
	 */
	public FriendInfo loadSingle(String id) {
		
		FriendDAO dao = new FriendDAO();
		
		return dao.loadSingle(id);
	}
	/**
	 * 複数のIDから情報を取り出す
	 * @param id フレンドコード
	 * @return フレンド情報複数
	 */
	public List<FriendInfo> loadMultiple(String id){
		
		FriendDAO friend = new FriendDAO();
		
		return friend.loadMultiple(id);
		
	}
	
	/**
	 * 入力された氏名から友人情報を検索
	 * @param name 入力された友人名
	 * @param userId ユーザーID 
	 * @return 条件に一致する友人一覧
	 */
	public List<FriendInfo> searchByName(String name,int userId){
		FriendDAO friend = new FriendDAO();
		return friend.searchByName(name,userId);
	}
	
	/**
	 * 新規登録用のもの
	 * @param friend 登録したい友人情報
	 */
	public void add(FriendInfo friend) throws Exception{
		FriendDAO dao = new FriendDAO();
		dao.add(friend);
	}
	
	/**
	 * 更新用のもの
	 * @param friend 友人情報の更新データ
	 */
	public void update(FriendInfo friend) throws Exception{
		FriendDAO dao = new FriendDAO();
		dao.update(friend);
	}
	
	/**
	 * 削除用のもの
	 * @param friend 削除したい友人情報
	 */
	public void delete(FriendInfo friend) throws Exception{
		FriendDAO dao = new FriendDAO();
		dao.delete(friend);
	}
	
	/**
	 * バイトデータとなっている画像を画像として表示できるよう処理を施す
	 * @param imageData バイト化した画像
	 * @return 画像リンク
	 */
	public String CreateUrlForImage(byte[] imageData) {
		
		try {
			String imageUrl = "data:image/jpg;base64,";
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			imageData = baos.toByteArray();
			String imageBase64 = Base64.getEncoder().encodeToString(imageData);
			
			return imageUrl+ imageBase64;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * フォームで入力された情報で新規登録、更新するための情報をBEANSに保存する
	 * @param request フォームに入力された情報
	 */
	public void setFriendInfoFromRequest(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		
		FriendInfo friend = (FriendInfo)session.getAttribute("friend");
		UserInfo user = (UserInfo)session.getAttribute("user");
		
		//フォームで選択された画像を処理する
			Part filePart = request.getPart("face");
			InputStream fileContent = filePart.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];
			while ((nRead = fileContent.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			//選択された画像をバイトデータにして格納
			byte[] imageBytes = buffer.toByteArray();
		    
			//バイト数を確認
			System.out.println("画像データバイト数:　"+ imageBytes.length);
			//データベースへの格納限界を超えていた場合
			if(imageBytes.length >= 60000) {
				throw new IllegalArgumentException("画像データのバイト数が60000以上です！");
			}
		
				String introduction = AntiXSS.antiXSS(request.getParameter("introduction"));
				String name = AntiXSS.antiXSS(request.getParameter("name"));
				String sex = AntiXSS.antiXSS(request.getParameter("sex"));
				String status = AntiXSS.antiXSS(request.getParameter("status"));
				String email = AntiXSS.antiXSS(request.getParameter("email"));
				String address = AntiXSS.antiXSS(request.getParameter("address"));
				String tel = AntiXSS.antiXSS(request.getParameter("tel"));
		
				//新規登録場合はNEWする。
			if(friend == null) {
				friend = new FriendInfo();
			}
				friend.setFacePath(imageBytes);
				friend.setUserId(user.getId());
				friend.setIntroduction(introduction);
				friend.setName(name);
				friend.setSex(sex);
				friend.setStatus(status);
				friend.setAddress(address);
				friend.setEmail(email);
				friend.setTel(tel);
		
				session.setAttribute("friend",friend);
	}
}