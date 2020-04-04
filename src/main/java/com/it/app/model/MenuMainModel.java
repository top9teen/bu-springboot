package com.it.app.model;

import java.util.List;

import com.it.app.entity.MenuSub;

public class MenuMainModel {

	private Integer menuId;

	private String menuNameEn;

	private String menuNameTh;

	private String menuUrl;

	private String role;

	private List<MenuSubModel> menuSubs;


	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuNameEn() {
		return menuNameEn;
	}

	public void setMenuNameEn(String menuNameEn) {
		this.menuNameEn = menuNameEn;
	}

	public String getMenuNameTh() {
		return menuNameTh;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<MenuSubModel> getMenuSubs() {
		return menuSubs;
	}

	public void setMenuSubs(List<MenuSubModel> menuSubs) {
		this.menuSubs = menuSubs;
	}


}
