package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import errConst.ErrMessage;
import model.user.TmpUser;
import model.user.UserLogic;
import model.util.CreateTmpId;
import model.util.LoginChecker;
import node_activation.SendSMSFromNode;
/**
 * Servlet implementation class UserServlet
 * <hr>
 * ユーザー処理をモデルに命令するCONTROLLER
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String state = request.getParameter("state");
		
		try {
			
			switch(state) {
				//ユーザー登録フォームを表示
			case "register":
				procRegister(request,response);
				break;
				//登録内容確認
			case "register_confirm":
				procRegisterConfirm(request,response,session);
				break;
				//登録処理
			case "add_user":
				procUserAdd(request,response,session);
				break;
				//ユーザー詳細
			case "detail":
				//ログインユーザー情報が保持されているかどうかを確認する
				LoginChecker.loginCheck(session);
				
				procDetail(request,response);
				break;
				//ユーザー情報編集
			case "edit":
				procEdit(request,response);
				break;
				//ユーザー情報編集確認
			case "edit_confirm":
				procEditConfirm(request,response);
				break;
				//ユーザー情報更新
			case "update":
				procUpdate(request,response,session);
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
	 * ユーザー登録フォームへ遷移させる
	 * @param request HTTP request 
	 * @param response HTTP response 
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"#"+"register_form");
		getServletContext().getRequestDispatcher("/WEB-INF/user/register_form.jsp").forward(request, response);
	}
	
	/**
	 * フォームで入力された内容をBEANSに格納し確認画面へ遷移
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 登録前ユーザーの情報を含むセッション
	 * @throws Exception 何らかのエラー
	 */
	protected void procRegisterConfirm(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		System.out.println(getServletName()+"#"+"register_Confirm");
		//認証IDを作成
		int id = CreateTmpId.createTmpId();
		UserLogic logic = new UserLogic();
		//フォームでの入力内容を格納
		logic.setTmpUserFromRequest(request);
		TmpUser tmp = (TmpUser)session.getAttribute("tmpUser");
		//SMS送信開始
		SendSMSFromNode sms = new SendSMSFromNode(tmp.getTel(),id);
		sms.run();
		getServletContext().getRequestDispatcher("/WEB-INF/user/register_confirm.jsp").forward(request, response);
	}
	
	/**
	 * TMPUSERに登録された情報をデータベースに登録する
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session 登録前ユーザーの情報を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procUserAdd(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+"#"+"userAdd");
		TmpUser tmp = (TmpUser)session.getAttribute("tmpUser");
		try {
			UserLogic logic = new UserLogic();
			logic.add(tmp);
			getServletContext().getRequestDispatcher("/WEB-INF/user/register_success.jsp").forward(request, response);
		}catch(SQLException e) {
			request.setAttribute("error",ErrMessage.loginIdErr);
			getServletContext().getRequestDispatcher("/WEB-INF/user/register_fail.jsp").forward(request, response);
		}
	}
	/**
	 * ユーザーの登録情報を表示
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procDetail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"#"+"detail");
		getServletContext().getRequestDispatcher("/WEB-INF/user/detail.jsp").forward(request, response);
	}
	
	/**
	 * ユーザーの情報を編集する
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"#"+"edit");
		getServletContext().getRequestDispatcher("/WEB-INF/user/edit.jsp").forward(request, response);
	}
	
	/**
	 * ユーザー情報編集内容確認画面へ遷移する
	 * @param request HTTP request
	 * @param response HTTP response
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procEditConfirm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getServletName()+"#"+"edit_confirm");
		UserLogic logic = new UserLogic();
		//フォームの入力内容を格納
		logic.setTmpUserFromRequest(request);
		getServletContext().getRequestDispatcher("/WEB-INF/user/edit_confirm.jsp").forward(request, response);
	}
	
	/**
	 * ユーザー情報更新
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session ユーザーの情報を含むセッション
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procUpdate(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		System.out.println(getServletName()+"#"+"update");
		TmpUser user = (TmpUser)session.getAttribute("tmpUser");
		
		UserLogic logic = new UserLogic();
		
		try {
			logic.update(user);
			getServletContext().getRequestDispatcher("/WEB-INF/user/update_success.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error",ErrMessage.loginIdErr);
			getServletContext().getRequestDispatcher("/WEB-INF/user/update_fail.jsp").forward(request, response);
		}finally {
			//ユーザー情報更新が成功しても失敗してもユーザー情報を破棄する
			session.removeAttribute("user");
		}
	}

}