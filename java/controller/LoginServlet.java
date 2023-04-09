package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.user.UserInfo;
import model.user.UserLogic;
import model.util.AntiXSS;
import node_activation.NodeConnectMysql;

/**
 * Servlet implementation class LoginContorler
 * <hr>
 * ログイン・ログアウト処理をモデルに命令するCONTROLLER
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * ドライバー有効化
     */
    public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		//もしログインされていた場合、
		if(user != null) {
			procSessionError(request,response,session);
			return ;
		}
		
		String state = request.getParameter("state");
		
		//NULLか""だった場合ログイン要求とみなしログインフォームへ遷移
		if(state == null || state.equals("")) {
			session.invalidate();
			getServletContext().getRequestDispatcher("/WEB-INF/login/LoginForm.jsp").forward(request, response);
		}else {
			procSessionError(request,response,session);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	HttpSession session = request.getSession();
    	
    	String state = request.getParameter("state");
    	if(state == null) {
    		procSessionError(request,response,session);
    		return;
    	}
    	
    	UserInfo user = (UserInfo)session.getAttribute("user");
    	
    	switch(state) {
    	case "loginForm":
    		procLoginForm(request,response);
    		break;
    	case "login":
    		procLogin(request,response,session,user);
    		break;
    	case "home":
    		procHome(request,response,session,user);
    		break;
    	case "logout":
    		procLogout(request,response,session);
    		break;
    	default:
    		procSessionError(request,response,session);
    		break;
    	}
	}
    
    /**
     * ログイン画面に遷移させる
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException error
     * @throws IOException error
     */
    protected void procLoginForm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	System.out.println(getServletName()+"# loginForm");
    	getServletContext().getRequestDispatcher("/WEB-INF/login/LoginForm.jsp").forward(request, response);
    }
    
    /**
     * ログイン処理を実行する
     * @param request HTTP request
     * @param response HTTP response
     * @param session ログイン成功時にユーザー情報を保存するセッション
     * @param user ユーザー情報
     * @throws ServletException error
     * @throws IOException error
     */
    protected void procLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session,UserInfo user) throws ServletException, IOException {
    	System.out.println(getServletName()+"# login");
    	
    	//もしログインしていたら
    		if(user != null) {
    			procSessionError(request,response,session);
    		}
    		
    		//ログイン名とパスワードで検証を行う。
        	String loginId = AntiXSS.antiXSS(request.getParameter("loginID"));
        	String password = AntiXSS.antiXSS(request.getParameter("password"));
        	UserLogic logic = new UserLogic();
        	
        	try {
        		user = logic.login(loginId, password);
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
    		
    		if(user != null) {
    			session.setAttribute("user", user);
    			//node.jsファイルを実行しサーバー3000番で起動
    			Thread node = new NodeConnectMysql();
    			node.start();
    			getServletContext().getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
    		}else {
    			getServletContext().getRequestDispatcher("/WEB-INF/login/loginfail.jsp").forward(request, response);
    		}
    	
    }
    
    /**
     * ログアウト処理を実行 sessionを破棄
     * @param request HTTP request
     * @param response HTTP response
     * @param session ユーザー情報を含むセッション
     * @throws ServletException error
     * @throws IOException error
     */
    protected void procLogout(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
    	System.out.println(getServletName()+"# logout");
    	session.invalidate();
    	getServletContext().getRequestDispatcher("/WEB-INF/login/LoginForm.jsp").forward(request, response);
    	
    }
    
    /**
     * ホーム画面に遷移させる
     * @param request HTTP request
     * @param response HTTP response
     * @param session ユーザー情報を含むセッション
     * @param user ユーザー情報
     * @throws ServletException error
     * @throws IOException error
     */
    protected void procHome(HttpServletRequest request,HttpServletResponse response,HttpSession session,UserInfo user) throws ServletException, IOException {
    	System.out.println(getServletName()+"# home");
    	
    	user = (UserInfo)session.getAttribute("user");
    	//もしログインしていなければ
    	if(user == null) {
    		procSessionError(request,response,session);
    	}else {
    		getServletContext().getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
    	}
    }
    
    

}