package com.it.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the menu_main database table.
 * 
 */
@Entity
@Table(name="menu_main")
@NamedQuery(name="MenuMain.findAll", query="SELECT m FROM MenuMain m")
public class MenuMain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="menu_id")
	private Integer menuId;

	@Column(name="menu_name_en")
	private String menuNameEn;

	@Column(name="menu_name_th")
	private String menuNameTh;

	@Column(name="menu_url")
	private String menuUrl;

	@Column(name="order_no")
	private Integer orderNo;

	private String role;

	public MenuMain() {
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuNameEn() {
		return this.menuNameEn;
	}

	public void setMenuNameEn(String menuNameEn) {
		this.menuNameEn = menuNameEn;
	}

	public String getMenuNameTh() {
		return this.menuNameTh;
	}

	public void setMenuNameTh(String menuNameTh) {
		this.menuNameTh = menuNameTh;
	}


	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}



	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}