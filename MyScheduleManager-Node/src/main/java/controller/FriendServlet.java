package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import errConst.ErrMessage;
import model.friend.FriendInfo;
import model.friend.FriendListBean;
import model.friend.FriendLogic;
import model.user.UserInfo;
import model.util.AntiXSS;
import model.util.LoginChecker;

/**
 * Servlet implementation class FriendServlet
 * <hr>
 * フレンド機能処理をモデルに命令するCONTROLLER
 */
@WebServlet("/FriendServlet")
@MultipartConfig
public class FriendServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws  
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		
		
		
		String state[] = request.getParameter("state").split(",");
		try{
			//ログインユーザー情報が保持されているかどうかを確認する
			LoginChecker.loginCheck(session);
			
			switch(state[0]) {
				//友人検索
			case "search":
				procSearch(request,response);
				break;
				//検索結果
			case "list":
				procList(request,response,session,user.getId());
				break;
				//検索結果に戻る
			case "listReturn":
				procListReturn(request,response);
				break;
				//友人詳細
			case "detail":
				procDetail(request,response,session,state[1]);
				break;
				//友人情報編集
			case "edit":
				procEdit(request,response);
				break;
				//友人編集確認
			case "edit_confirm":
				procEditConfirm(request,response,session);
				break;
				//友人情報更新
			case "update":
				procUpdate(request,response,session);
				break;
				//新規友人登録フォーム
			case "new":
				procNew(request,response);
				break;
				//新規友人入力情報確認
			case "new_confirm":
				procNewConfirm(request,response,session);
				break;
				//新規友人登録
			case "add":
				procAdd(request,response,session);
				break;
				//友人削除確認
			case "delete_confirm":
				procDeleteConfirm(request,response,session,state[1]);
				break;
				//友人削除
			case "delete":
				procDelete(request,response,session);
				break;
			default:
				procSessionError(request,response,session);
				break;
			}
		}catch(Exception e) {
			e.printStackTrace();
			procSessionError(request,response,session);
		}
}
	/**
	 * 友人情報を氏名から検索
	 * @param request HTTPのリクエスト
	 * @param response HTTPのレスポンス
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procSearch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"# search");
		
		getServletContext().getRequestDispatcher("/WEB-INF/friend/search.jsp").forward(request, response);
	}
	
	/**
	 * 友人情報検索結果を表示
	 * @param request HTTPのリクエスト
	 * @param response HTTPのレスポンス
	 * @param session 友人検索結果を含むセッション
	 * @param userId ユーザーID
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procList(HttpServletRequest request,HttpServletResponse response,HttpSession session,int userId) throws ServletException, IOException {
		System.out.println(getServletName()+"# list");
		
		String name = AntiXSS.antiXSS(request.getParameter("name"));
		FriendLogic logic = new FriendLogic();
		List<FriendInfo> list = logic.searchByName(name,userId);
		FriendListBean listdata = new FriendListBean();
		listdata.setFriendList(list);
		session.setAttribute("friendList", listdata);
		
		getServletContext().getRequestDispatcher("/WEB-INF/friend/list.jsp").forward(request, response);
	}
	
	/**
	 * 友人情報検索結果画面に戻るためのもの
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procListReturn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"# listReturn");
		
		getServletContext().getRequestDispatcher("/WEB-INF/friend/list.jsp").forward(request, response);
	}
	
	/**
	 * 友人情報詳細を表示する
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 友人情報詳細を含むセッション
	 * @param id フレンドID
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procDetail(HttpServletRequest request,HttpServletResponse response,HttpSession session,String id) throws ServletException, IOException{
		System.out.println(getServletName()+"# detail");
		
		FriendInfo list = new FriendInfo();
		FriendLogic logic = new FriendLogic();
		list =logic.loadSingle(id);
		//バイトデータをIMAGE化
		String imgUrl = logic.CreateUrlForImage(list.getFacePath());
		session.setAttribute("imageLink",imgUrl);
		session.setAttribute("friend", list);
		getServletContext().getRequestDispatcher("/WEB-INF/friend/detail.jsp").forward(request, response);
	}
	
	/**
	 * 友人情報を編集する
	 * @param request HTTP request 
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procEdit(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"# edit");
		
		getServletContext().getRequestDispatcher("/WEB-INF/friend/edit.jsp").forward(request, response);
	}
	
	/**
	 * 友人情報編集確認をする
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 編集情報を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procEditConfirm(HttpServletRequest request ,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+"# edit_confirm");
		
		FriendLogic logic = new FriendLogic();
		//画像ファイルがデータベースの許容範囲を超えたら例外を出す
		try {
			logic.setFriendInfoFromRequest(request);
		}catch(Exception e) {
			request.setAttribute("error",ErrMessage.imageErr);
			getServletContext().getRequestDispatcher("/WEB-INF/friend/fail.jsp").forward(request, response);
			return ;
		}
		
		FriendInfo friend = (FriendInfo)session.getAttribute("friend");
		String imageUrl = logic.CreateUrlForImage(friend.getFacePath());
		session.setAttribute("imageLink", imageUrl);
		getServletContext().getRequestDispatcher("/WEB-INF/friend/edit_confirm.jsp").forward(request, response);
	
	}
	/**
	 * 編集した友人情報を反映させる
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 友人情報更新内容を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procUpdate(HttpServletRequest request ,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+"# update");
		
		FriendInfo info =(FriendInfo)session.getAttribute("friend");
		FriendLogic logic = new FriendLogic();
		try {
			logic.update(info);
			getServletContext().getRequestDispatcher("/WEB-INF/friend/update_success.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", ErrMessage.updateErr);
			getServletContext().getRequestDispatcher("/WEB-INF/friend/fail.jsp").forward(request, response);
		}
	}
	
	/**
	 * 新規友人登録を入力するフォームを表示
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procNew(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"# new");
		
		getServletContext().getRequestDispatcher("/WEB-INF/friend/new.jsp").forward(request, response);
	}
	
	/**
	 * 新規友人登録で入力した情報の確認画面を表示
	 * @param request HTTP request
	 * @param response HTTP response 
	 * @throws ServletException error
	 * @throws IOException error
	 * @throws ParseException 画像処理エラー
	 */
	protected void procNewConfirm(HttpServletRequest request ,HttpServletResponse response,HttpSession session) throws ServletException, IOException, ParseException {
		System.out.println(getServletName()+"# new_confirm");
		
		FriendLogic logic = new FriendLogic();
		
		//もしデータベースの許容範囲を超える画像がだった場合例外を投げる
		try {
			logic.setFriendInfoFromRequest(request);
		}catch(Exception e) {
			request.setAttribute("error", ErrMessage.imageErr);
			getServletContext().getRequestDispatcher("/WEB-INF/friend/fail.jsp").forward(request, response);
			return ;
		}
		//バイト配列を画像へ変換する処理
		FriendInfo friend = (FriendInfo)session.getAttribute("friend");
		byte[]imageData = friend.getFacePath();
		//画像を表示するためのURL生成
		String imageUrl = logic.CreateUrlForImage(imageData);
		request.setAttribute("imageLink", imageUrl);
		getServletContext().getRequestDispatcher("/WEB-INF/friend/new_confirm.jsp").forward(request, response);
	}
	
	/**
	 * 新規友人の登録処理を行う
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 登録内容を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procAdd(HttpServletRequest request ,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+"# add");
		
		FriendInfo friend = (FriendInfo)session.getAttribute("friend");
		FriendLogic logic = new FriendLogic();
		try {
			logic.add(friend);
			getServletContext().getRequestDispatcher("/WEB-INF/friend/add_success.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error",ErrMessage.addErr);
			getServletContext().getRequestDispatcher("/WEB-INF/friend/fail.jsp").forward(request, response);
		}
	}
	
	/**
	 * 選択された友人の情報を表示し削除確認を行う
	 * @param request HTTP request 
	 * @param response HTTP response 
	 * @param session 削除したい友人情報を含むセッション
	 * @param id 削除したい友人ID
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procDeleteConfirm(HttpServletRequest request ,HttpServletResponse response,HttpSession session,String id) throws ServletException, IOException {
		System.out.println(getServletName()+"# delete_confirm");
		
		FriendLogic logic = new FriendLogic();
		FriendInfo friend = logic.loadSingle(id);
		session.setAttribute("friend", friend);
		String urlImage = logic.CreateUrlForImage(friend.getFacePath());
		session.setAttribute("imageLink", urlImage);
		getServletContext().getRequestDispatcher("/WEB-INF/friend/delete_confirm.jsp").forward(request, response);
	}
	
	/**
	 * 選択された友人の削除を行う
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 削除したい友人情報を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procDelete(HttpServletRequest request ,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+"# delete");
		
		FriendInfo friend = (FriendInfo)session.getAttribute("friend");
		FriendLogic logic = new FriendLogic();
		try {
			logic.delete(friend);
			getServletContext().getRequestDispatcher("/WEB-INF/friend/delete_success.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error",ErrMessage.deleteErr);
			getServletContext().getRequestDispatcher("/WEB-INF/friend/fail.jsp").forward(request, response);
		}
	}

}