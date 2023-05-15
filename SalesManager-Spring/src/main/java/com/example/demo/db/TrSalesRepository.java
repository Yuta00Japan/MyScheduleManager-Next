package com.example.demo.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TrSales;

/**
 * TrSalesを操作する
 * @author yuta
 */
@Repository
public interface TrSalesRepository extends JpaRepository<TrSales,Long>{

}
