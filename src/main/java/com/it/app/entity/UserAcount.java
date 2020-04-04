package com.it.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_acount database table.
 * 
 */
@Entity
@Table(name="user_acount")
@NamedQuery(name="UserAcount.findAll", query="SELECT u FROM UserAcount u")
public class UserAcount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	@GeneratedValue
//	@SequenceGenerator(name="\"user_acount_id_seq\"", sequenceName="\"profile\".\"user_acount_id_seq\"")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="\"user_acount_id_seq\"")
	private Long userId;

	@Column(name="password")
	private String password;

	@Column(name="role")
	private String role;

	@Column(name="status")
	private String status;

	@Column(name="username")
	private String username;

	public UserAcount() {
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}