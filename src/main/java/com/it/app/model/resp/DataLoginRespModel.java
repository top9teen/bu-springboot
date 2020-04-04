package com.it.app.model.resp;

import java.util.List;

import com.it.app.model.MenuMainModel;

public class DataLoginRespModel {

	private Long userId;
	private String userName;
	private String passWord;
	private String role;
	
	private String titleName;
	private String fertName;
	private String lastName;
	
	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	private List<MenuMainModel> menuMains;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFertName() {
		return fertName;
	}

	public void setFertName(String fertName) {
		this.fertName = fertName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public List<MenuMainModel> getMenuMains() {
		return menuMains;
	}

	public void setMenuMains(List<MenuMainModel> menuMains) {
		this.menuMains = menuMains;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


}
