package com.it.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the menu_sub database table.
 * 
 */
@Entity
@Table(name="menu_sub")
@NamedQuery(name="MenuSub.findAll", query="SELECT m FROM MenuSub m")
public class MenuSub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="menu_sub_id")
	private Integer menuSubId;

	@Column(name="menu_id")
	private Integer menuId;

	@Column(name="menu_sub_name_en")
	private String menuSubNameEn;

	@Column(name="menu_sub_name_th")
	private String menuSubNameTh;

	@Column(name="menu_sub_url")
	private String menuSubUrl;

	@Column(name="order_no")
	private Integer orderNo;

	public MenuSub() {
	}

	public Integer getMenuSubId() {
		return this.menuSubId;
	}

	public void setMenuSubId(Integer menuSubId) {
		this.menuSubId = menuSubId;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuSubNameEn() {
		return this.menuSubNameEn;
	}

	public void setMenuSubNameEn(String menuSubNameEn) {
		this.menuSubNameEn = menuSubNameEn;
	}

	public String getMenuSubNameTh() {
		return this.menuSubNameTh;
	}

	public void setMenuSubNameTh(String menuSubNameTh) {
		this.menuSubNameTh = menuSubNameTh;
	}

	public String getMenuSubUrl() {
		return this.menuSubUrl;
	}

	public void setMenuSubUrl(String menuSubUrl) {
		this.menuSubUrl = menuSubUrl;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}