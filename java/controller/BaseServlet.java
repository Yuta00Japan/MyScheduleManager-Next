package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BaseServlet
 * <hr>
 * CONTROLLERの基底クラス　継承して利用する
 */
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Session Error ");
		HttpSession session = request.getSession();
		
		procSessionError(request,response,session);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * session情報の何らかのエラーや誤ったstateパラメータがPOSTされた際にエラー画面に遷移させる
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param session エラーsession
	 * @throws ServletException error
	 * @throws IOException error
	 */
	protected void procSessionError(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException,IOException{
		session = request.getSession();
		session.invalidate();
		getServletContext().getRequestDispatcher("/WEB-INF/sessionError.jsp").forward(request, response);
	}

}