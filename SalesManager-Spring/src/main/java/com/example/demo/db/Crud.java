package com.example.demo.db;

import java.io.Serializable;
import java.util.List;

/**
 * データベース操作の共通操作を実装する
 * @author yuta
 * @param <T> 処理対象BEAN
 */
public interface Crud<T> extends Serializable {

	//全情報取得
    public List<T> getAll();
	
	void add(Object o);
	
	void update(Object o);
	
	void delete(Object o);
}
