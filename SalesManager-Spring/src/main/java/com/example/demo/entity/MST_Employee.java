package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="MST_Employee")
@Getter
@Setter
@Data

//session
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)

public class MST_Employee implements Serializable{

	/**従業員ID*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="empId")
	private int empId;
	/**支店ID*/
	@Column(name="branchId")
	private int branchId;
	/**部署ID*/
	@Column(name="departmentId")
	private int departmentId;
	/**従業員No*/
	@Column(name="empNo")
	private int empNo;
	/**従業員名*/
	@Column(name="fullName",nullable=false)
	private String fullName;
	/**従業員かな*/
	@Column(name="kanaName",nullable=false)
	private String kanaName;
	/**ログインID*/
	@Column(name="loginId",nullable=false)
	private String loginId;
	/**パスワード*/
	@Column(name="password",nullable=false)
	private String password;
	/**有効無効*/
	@Column(name="enable",nullable=false)
	private boolean enable;
	/**メールアドレス*/
	@Column(name="email",nullable=false)
	private String email;
	/**権限*/
	@Column(name="userRole")
	private String userRole;
	/**パスワード更新日時*/
	@Column(name="pwupDay")
	private Timestamp pwupDay;
	/**上司ID*/
	@Column(name="bossId")
	private int bossId;
}
