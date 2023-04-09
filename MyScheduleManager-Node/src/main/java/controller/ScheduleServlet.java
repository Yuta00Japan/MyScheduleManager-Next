package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import errConst.ErrMessage;
import model.friend.FriendListBean;
import model.friend.FriendLogic;
import model.schedule.ScheduleBean;
import model.schedule.ScheduleListBean;
import model.schedule.ScheduleLogic;
import model.user.UserInfo;
import model.util.AntiXSS;
import model.util.LoginChecker;
/**
 * Servlet implementation class ScheduleServlet
 * <hr>
 * 予定管理機能をモデルに命令するCONTROLLER
 */
@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		try{
			String[] state = request.getParameter("state").split(",");
			//ログインユーザー情報が保持されているかどうかを確認する
			LoginChecker.loginCheck(session);
		
			switch(state[0]){
			//予定検索
			case "search":
				procSearch(request,response);
				break;
			//予定検索結果
			case "list":
				procList(request,response,session);
				break;
			//予定検索結果に戻る
			case "listReturn":
				procListReturn(request,response);
				break;
			//予定新規登録
			case "new":
				procNew(request,response);
				session.removeAttribute("friendList");
				break;
			//予定登録確認
			case "add_Confirm":
				procAddConfirm(request,response);
				break;
			//予定登録
			case "add":
				procAdd(request,response,session);
				break;
			//予定詳細
			case "detail":
				procDetail(request,response,session,state[1]);
				session.removeAttribute("friendList");
				break;
			//予定編集
			case "edit":
				procEdit(request,response);
				break;
			//予定編集確認
			case "edit_confirm":
				procEditConfirm(request,response);
				break;
			//予定編集実行
			case "update":
				procUpdate(request,response,session);
				break;
			//削除実行
			case "delete":
				procDelete(request,response,session);
				break;
			//削除確認
			case "delete_confirm":
				procDeleteConfirm(request,response,session,state[1]);
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
	 * 検索画面に遷移させる
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procSearch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+" #  search");
		getServletContext().getRequestDispatcher("/WEB-INF/schedule/scheduleSearch.jsp").forward(request, response);
	}
	
	/**
	 * 検索画面で入力された条件をもとに検索を行う
	 * @param request HTTP request
	 * @param response HTTP response 
	 * @param session 検索結果を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procList(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+" # list");
		UserInfo user = (UserInfo)session.getAttribute("user");
		
		String scheduleName= AntiXSS.antiXSS(request.getParameter("scheduleName"));
		String type=request.getParameter("type");
		
		//input type date-timeに付属するーやT、：を除去する
		String start = request.getParameter("startDay").replaceAll("[-,T,:]","");
		String end = request.getParameter("endDay").replaceAll("[-,T,:]", "");
		
		ScheduleLogic logic = new ScheduleLogic();
		List<ScheduleBean> list = logic.searchList(user,scheduleName,type,start,end);
		ScheduleListBean schedule = new ScheduleListBean();
		schedule.setList(list);
		session.setAttribute("scheduleList",schedule);
		getServletContext().getRequestDispatcher("/WEB-INF/schedule/scheduleList.jsp").forward(request, response);
		
	}
	
	/**
	 * 予定検索結果に戻る
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procListReturn(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+" # listReturn");
		getServletContext().getRequestDispatcher("/WEB-INF/schedule/scheduleList.jsp").forward(request, response);
	}
	
	/**
	 * 予定詳細情報を表示
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 予定詳細情報を含むセッション
	 * @param id 予定ID
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procDetail(HttpServletRequest request,HttpServletResponse response,HttpSession session,String id) throws ServletException, IOException {
		System.out.println(getServletName()+" # detail");
		ScheduleLogic logic = new ScheduleLogic();
		ScheduleBean schedule = logic.load(id);
		
		FriendListBean list = new FriendListBean();
		FriendLogic logic2 = new FriendLogic();
		list.setFriendList(logic2.loadMultiple(schedule.getWho()));
		
		session.setAttribute("schedule", schedule);
		session.setAttribute("userName", list);
		getServletContext().getRequestDispatcher("/WEB-INF/schedule/detail.jsp").forward(request, response);
	}
	
	/**
	 * 予定編集画面に遷移させる
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+" # edit");
		getServletContext().getRequestDispatcher("/WEB-INF/schedule/edit.jsp").forward(request, response);
	}
	
	/**
	 * 予定編集確認画面を表示
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 * @throws ParseException STRINGをINTにする処理などで失敗した場合
	 */
	protected void procEditConfirm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, ParseException {
		System.out.println(getServletName()+" # editConfirm");
		ScheduleLogic logic = new ScheduleLogic();
		logic.setScheduleFromRequestToScheduleBean(request);
		getServletContext().getRequestDispatcher("/WEB-INF/schedule/edit_Confirm.jsp").forward(request, response);
	}
	
	/**
	 * 予定編集内容を反映させる
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 予定編集内容を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procUpdate(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+" # update");
		ScheduleBean schedule = (ScheduleBean)session.getAttribute("schedule");
		ScheduleLogic logic = new ScheduleLogic();
		try {
			logic.update(schedule);
			getServletContext().getRequestDispatcher("/WEB-INF/schedule/updateSuccess.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error",ErrMessage.updateErr);
			getServletContext().getRequestDispatcher("/WEB-INF/schedule/finish.jsp").forward(request, response);
		}
	}
	
	/**
	 * 予定新規登録画面へ遷移させる
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procNew(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+" # new");
		getServletContext().getRequestDispatcher("/WEB-INF/schedule/new.jsp").forward(request, response);
	}
	
	/**
	 * 予定編集確認画面へ遷移させる
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 * @throws ParseException StringをINTにする処理などが失敗した場合
	 */
	protected void procAddConfirm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, ParseException {
		System.out.println(getServletName()+" # addConfirm");
		ScheduleLogic logic = new ScheduleLogic();
		logic.setScheduleFromRequestToScheduleBean(request);
		getServletContext().getRequestDispatcher("/WEB-INF/schedule/add_confirm.jsp").forward(request, response);
	}
	
	/**
	 * 登録したい予定を登録する
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 登録内容を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procAdd(HttpServletRequest request ,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+" # add");
		ScheduleBean schedule = (ScheduleBean)session.getAttribute("schedule");
		try {
			ScheduleLogic logic = new ScheduleLogic();
			logic.add(schedule);
			getServletContext().getRequestDispatcher("/WEB-INF/schedule/addSuccess.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error",ErrMessage.addErr);
			getServletContext().getRequestDispatcher("/WEB-INF/schedule/finish.jsp").forward(request, response);
		}
	}
	
	/**
	 * 削除確認画面を表示する
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 削除したい予定情報を含むセッション
	 * @param id 削除したい予定ID
	 * @throws ServletException error
	 * @throws IOException error
	 * @throws ParseException 変換処理失敗時
	 */
	protected void procDeleteConfirm(HttpServletRequest request ,HttpServletResponse response,HttpSession session,String id) throws ServletException, IOException, ParseException {
		System.out.println(getServletName()+" # deleteConfirm");
		ScheduleLogic logic = new ScheduleLogic();
		ScheduleBean schedule = logic.load(id);
		
		FriendListBean list = new FriendListBean();
		FriendLogic logic2 = new FriendLogic();
		list.setFriendList(logic2.loadMultiple(schedule.getWho()));
		
		session.setAttribute("schedule", schedule);
		session.setAttribute("userName", list);
		getServletContext().getRequestDispatcher("/WEB-INF/schedule/deleteConfirm.jsp").forward(request, response);
	}
	
	/**
	 * 予定を削除する
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 削除したい予定情報を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procDelete(HttpServletRequest request ,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+" # delete");
		ScheduleBean schedule = (ScheduleBean)session.getAttribute("schedule");
		ScheduleLogic logic = new ScheduleLogic();
		try {
			logic.delete(schedule);
			getServletContext().getRequestDispatcher("/WEB-INF/schedule/deleteSuccess.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error",ErrMessage.deleteErr);
			getServletContext().getRequestDispatcher("/WEB-INF/schedule/finish.jsp").forward(request, response);
		}
	}
	

}