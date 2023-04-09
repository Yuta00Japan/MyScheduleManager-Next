package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import errConst.ErrMessage;
import model.diary.DiaryInfo;
import model.diary.DiaryLogic;
import model.user.UserInfo;
import model.util.LoginChecker;

/**
 * Servlet implementation class DiaryServlet
 * <hr>
 * 日記機能処理をモデル実行させるCONTROLLER
 */
@WebServlet("/DiaryServlet")
public class DiaryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String state[] = request.getParameter("state").split(",");
		try {
			//ログインユーザー情報が保持されているかどうかを確認する
			LoginChecker.loginCheck(session);
			
			switch(state[0]) {
				//日記検索
			case "search":
				procSearch(request,response);
				break;
				//日記検索結果
			case "list":
				procList(request,response,session);
				break;
				//日記検索結果に戻る
			case "listReturn":
				procListReturn(request,response);
				break;
				//日記詳細
			case "detail":
				procDetail(request,response,session,state[1]);
				break;
				//既存の日記を編集
			case "edit":
				procEdit(request,response);
				break;
				//編集内容確認
			case "edit_confirm":
				procEditConfirm(request,response);
				break;
				//編集内容反映
			case "update":
				procUpdate(request,response,session);
				break;
				//新規日記登録フォーム
			case "new":
				procNew(request,response);
				break;
				//日記登録確認
			case "new_confirm":
				procNewConfirm(request,response);
				break;
				//日記登録
			case "add":
				procAdd(request,response,session);
				break;
				//日記削除確認
			case "delete_confirm":
				procDeleteConfirm(request,response,session,state[1]);
				break;
				//日記削除
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
	 * 日記検索画面に遷移させる
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException エラー
	 * @throws IOException エラー
	 */
	protected void procSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"# search");
		
		getServletContext().getRequestDispatcher("/WEB-INF/diary/search.jsp").forward(request, response);
	}
	
	/**
	 * 検索結果を表示する
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 検索結果を含むsession
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procList(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+"# list　検索開始");
		
		UserInfo user = (UserInfo)session.getAttribute("user");
		
		String title = request.getParameter("title");
		//input type dateに付属するーを除去する
		String startTime = request.getParameter("startTime").replaceAll("[-]","");
		String endTime = request.getParameter("endTime").replaceAll("[-]","");
		
		DiaryLogic logic = new DiaryLogic();
		
		try {
			session.setAttribute("diaryList",logic.search(title, startTime, endTime,user.getId()));
			System.out.println("検索成功");
			getServletContext().getRequestDispatcher("/WEB-INF/diary/list.jsp").forward(request, response);
		}catch(Exception e) {
			System.out.println("検索失敗");
			e.printStackTrace();
			request.setAttribute("error", ErrMessage.searchErr);
			getServletContext().getRequestDispatcher("/WEB-INF/diary/fail.jsp").forward(request, response);
		}
		
	}
	
	/**
	 * 検索結果に戻る
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procListReturn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"# listReturn　検索結果に戻る");
		
		getServletContext().getRequestDispatcher("/WEB-INF/diary/list.jsp").forward(request, response);
	}
	
	/**
	 * 	日記IDをもとに日記詳細情報を表示
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 日記情報を含むセッション
	 * @param diaryId 日記ID
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procDetail(HttpServletRequest request, HttpServletResponse response,HttpSession session,String diaryId) throws ServletException, IOException {
		System.out.println(getServletName()+"# detail");
		DiaryLogic logic = new DiaryLogic();
		try {
			session.setAttribute("diary",logic.load(diaryId));
			getServletContext().getRequestDispatcher("/WEB-INF/diary/detail.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error",ErrMessage.loadErr);
			getServletContext().getRequestDispatcher("/WEB-INF/diary/fail.jsp").forward(request, response);
		}
	}
	
	/**
	 * 編集画面を表示
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"# edit");
		getServletContext().getRequestDispatcher("/WEB-INF/diary/edit.jsp").forward(request, response);
	}
	
	/**
	 * 編集フォームで入力された内容をBEANに格納し、確認画面を表示
	 * @param request HTTP request
	 * @param response HTTP response 
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procEditConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"# edit_confirm");
		DiaryLogic logic = new DiaryLogic();
		logic.setDiaryInfoFromRequest(request);
		getServletContext().getRequestDispatcher("/WEB-INF/diary/edit_confirm.jsp").forward(request, response);
	}
	
	/**
	 * 編集内容を反映する
	 * @param request HTTP request
	 * @param response HTTP responose
	 * @param session 編集内容を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procUpdate(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+"# update");
		DiaryInfo diary = (DiaryInfo)session.getAttribute("diary");
		DiaryLogic logic = new DiaryLogic();
		try {
			logic.update(diary);
			getServletContext().getRequestDispatcher("/WEB-INF/diary/update_success.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error",ErrMessage.updateErr);
			getServletContext().getRequestDispatcher("/WEB-INF/diary/fail.jsp").forward(request, response);
		}
	}
	
	/**
	 * 新規日記登録画面を表示
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"# new");
		
		getServletContext().getRequestDispatcher("/WEB-INF/diary/new.jsp").forward(request, response);
	}
	
	/**
	 * 日記登録内容確認画面を表示
	 * @param request HTTP request 
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procNewConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"# new_confirm");
		DiaryLogic logic = new DiaryLogic();
		logic.setDiaryInfoFromRequest(request);
		getServletContext().getRequestDispatcher("/WEB-INF/diary/new_confirm.jsp").forward(request, response);
	}
	
	/**
	 * 登録したい日記を登録する
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 登録内容を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procAdd(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+"# add");
		
		DiaryInfo diary = (DiaryInfo)session.getAttribute("diary");
		
		DiaryLogic logic = new DiaryLogic();
		try {
			logic.add(diary);
			getServletContext().getRequestDispatcher("/WEB-INF/diary/add_success.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error",ErrMessage.addErr);
			getServletContext().getRequestDispatcher("/WEB-INF/diary/fail.jsp").forward(request, response);
		}
	}
	
	/**
	 * 削除確認画面を表示する
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 削除したい日記を保存
	 * @param diaryId 削除したい日記ID
	 * @throws ServletException error 
	 * @throws IOException error
	 */
	protected void procDeleteConfirm(HttpServletRequest request, HttpServletResponse response,HttpSession session,String diaryId) throws ServletException, IOException {
		System.out.println(getServletName()+"# delete_confirm");
		
		DiaryLogic logic = new DiaryLogic();
		try {
			session.setAttribute("diary",logic.load(diaryId));
			getServletContext().getRequestDispatcher("/WEB-INF/diary/delete_confirm.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error",ErrMessage.loadErr);
			getServletContext().getRequestDispatcher("/WEB-INF/diary/fail.jsp").forward(request, response);
		}
		
	}
	
	/**
	 * 日記削除を行う
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 削除したい日記情報を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procDelete(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+"# delete");
		DiaryLogic logic = new DiaryLogic();
		DiaryInfo diary = (DiaryInfo)session.getAttribute("diary");
		try {
			logic.delete(diary);
			getServletContext().getRequestDispatcher("/WEB-INF/diary/delete_success.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", ErrMessage.deleteErr);
			getServletContext().getRequestDispatcher("/WEB-INF/diary/fail.jsp").forward(request, response);
			
		}
	}

}