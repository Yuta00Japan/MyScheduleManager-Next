package com.example.demo.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="TrSales")
public class TrSales {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="salesId",nullable=false)
	/**売上ID*/
	private int salesId;
	/**カテゴリID*/
	@Column(name="categoryId")
	private int categoryId;
	/**商品名*/
	@Column(name="itemName",nullable=false)
	private String itemName;
	/**単価*/
	@Column(name="unitPrice",nullable=false)
	private int unitPrice;
	/**個数*/
	@Column(name="quantity",nullable=false)
	private BigDecimal quantity;
	/**販売日*/
	@Column(name="salesDate",nullable=false)
	private Date salesDate;
	/**更新日時*/
	@Column(name="upDateTime")
	private Timestamp upDateTime;
}
