package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.db.EmpRepository;
import com.example.demo.entity.MST_Employee;
import com.example.demo.model.node.StartNode;
import com.example.demo.model.util.LoginCheck;

import jakarta.servlet.http.HttpSession;

/**
 * ログイン・ログアウト処理などのセッションを管理する
 * <br>
 * コントローラ
 * @author yuta
 *
 */
@Controller
@SessionAttributes(types = MST_Employee.class) 
@RequestMapping("/security")
public class SessionCtrl {

	
	@Autowired
	@Qualifier("employeeRepo")
	EmpRepository employee;
	
	@GetMapping("/login")
	public String logInForm(HttpSession session) {
		
		session.setAttribute("tryLogin",0);
		
		return "/security/login";
	}
	
	/**
	 * メニュー画面に遷移する
	 * @param session session
	 * @return メニュー画面
	 */
	@PostMapping("/menu")
	public String menuForm(HttpSession session) {
		
		if(! LoginCheck.check(session)) {
			return "/security/login";
		}
		return "/menu/menu";
	}
	
	/**
	 * ログアウト処理
	 * @param session ユーザ情報を含むセッション
	 * @return ログイン画面
	 */
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/security/login";
	}
	
	@PostMapping("/login")
	public String tryLogin(
		@RequestParam(value="txtID",required=true) String loginId,
		@RequestParam(value="txtPASS",required=true) String password
		,Model m,HttpSession session) {
		MST_Employee emp = employee.login(loginId, password);
		
		//ログイン成功
		if(emp != null) {
			session.setAttribute("user", emp);
			m.addAttribute("emp",emp);
			session.setAttribute("tryLogin",0);
			
			//node起動
			Thread node = new StartNode();
			node.start();
			
			return "/menu/menu";
		}else {
		//ログイン失敗
			int count = (int)session.getAttribute("tryLogin");
			count++;
			session.setAttribute("tryLogin", count);
			
			if(count > 5) {
				return "/security/logInError";
			}
			return "/security/login";
		}
		
	}
	
}
