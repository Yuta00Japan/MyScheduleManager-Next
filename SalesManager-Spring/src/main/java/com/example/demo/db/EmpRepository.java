package com.example.demo.db;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmpList;
import com.example.demo.entity.MST_Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
/**
 * 従業員情報のデータを管理する
 * @author yuta
 */
@Repository(value="employeeRepo")
public class EmpRepository  implements Crud<MST_Employee>{
	
	private static final long serialVersionUID= 1L;

	public EmpRepository () {
		super();
	}
	
	@PersistenceContext
	private EntityManager manager;
	
	/**
	 * ログインIDとパスワードによるログイン認証を行う
	 * @param loginID ログインID
	 * @param password パスワード
	 * @return ログインユーザ情報
	 */
	public MST_Employee login(String loginID,String password) {
		String sql =String.format(" from MST_Employee where loginId='%s' and password='%s'",loginID,password);
		return (MST_Employee)manager.createQuery(sql).getSingleResult();
	}
	
	/**
	 * 従業員検索結果を返す
	 * @param sql 検索用SQL
	 * @return 従業員検索結果
	 */
	public EmpList search(String sql) {
		EmpList result = new EmpList();
		Query query = manager.createQuery(sql);
		@SuppressWarnings("unchecked")
		List<MST_Employee> list = query.getResultList();
		result.setList(list);
		return result;
	}
	
	/**
	 * 現在有効な全従業員情報を取得する
	 */
	@Override
	public List<MST_Employee> getAll() {
		
		Query query = manager.createQuery("from MST_Employee where enable=true");
		@SuppressWarnings("unchecked")
		List<MST_Employee> list = query.getResultList();
		manager.close();
		
		return list;
	}

	@Override
	public void add(Object o) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void update(Object o) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void delete(Object o) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
