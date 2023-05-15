package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.db.EmpRepository;
import com.example.demo.entity.EmpList;
import com.example.demo.model.employee.EmpLogic;

/**
 * @author yuta
 *従業員関係の処理を担当します
 */
@Controller
@RequestMapping("emp")
public class EmpCtrl {

	@Autowired
	@Qualifier("employeeRepo")
	EmpRepository employee;
	
	/**
	 * 全従業員情報を表示する
	 * @return 有効全従業員情報
	 */
	@PostMapping("/list")
	public String list(Model m) {
		EmpList empList = new EmpList();
		empList.setList(employee.getAll());
		m.addAttribute("empList",empList);
		return "/emp/list";
	}
	
	/**
	 * 従業員検索を行う
	 * @param m モデル
	 * @param name 氏名（カナも含む）
	 * @param branch 支社ID
	 * @param department 部署ID
	 * @param enable 無効従業員を含めるか？
	 * @return 従業員検索結果
	 */
	@PostMapping("/search")
	public String search(Model m,
			@RequestParam(value="",required=false) String name,
			@RequestParam(value="",required=false) String branch,
			@RequestParam(value="",required=false) String department,
			@RequestParam(value="",required=false) String enable) {
		
		EmpList result = employee.search(EmpLogic.createSql(name, branch, department, enable));
		m.addAttribute("empList",result);
		
		return "/emp/list";
	}
}
