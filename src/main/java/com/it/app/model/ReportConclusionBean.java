package com.it.app.model;

import java.util.List;

public class ReportConclusionBean {

	private String community;
	private List<String> inspectionId;
	private int member;

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public List<String> getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(List<String> inspectionId) {
		this.inspectionId = inspectionId;
	}

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}

}
