package com.it.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the user_profile database table.
 * 
 */
@Entity
@Table(name="user_profile")
@NamedQuery(name="UserProfile.findAll", query="SELECT u FROM UserProfile u")
public class UserProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="profile_id")
	@GeneratedValue
//	@SequenceGenerator(name="\"user_profile_id_seq\"", sequenceName="\"profile\".\"user_profile_id_seq\"")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="\"user_profile_id_seq\"")
	private Long profileId;

	@Column(name="age")
	private String age;

	@Column(name="card_id")
	private String cardId;

	@Column(name="fert_name")
	private String fertName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="phone_no")
	private String phoneNo;


	@Column(name="title_name")
	private Integer titleName;

	@Column(name="user_id")
	private Long userId;

	@Column(name="img")
	private String img;
	
	@Column(name="address")
	private String address;

	@Column(name ="community")
	private String community;
	
	@Column(name="lat")
	private String lat;

	@Column(name ="lng")
	private String lng;
	
	public UserProfile() {
	}

	public Long getProfileId() {
		return this.profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}


	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getFertName() {
		return this.fertName;
	}

	public void setFertName(String fertName) {
		this.fertName = fertName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}



	public Integer getTitleName() {
		return this.titleName;
	}

	public void setTitleName(Integer titleName) {
		this.titleName = titleName;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	
}