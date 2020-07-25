package com.it.app.model;

import java.util.List;

public class ReportConclusionBean {

	private String community;
	private List<InspectionId> inspectionId;
	private int member;

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}


	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}

	
	public List<InspectionId> getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(List<InspectionId> inspectionId) {
		this.inspectionId = inspectionId;
	}


	public static class InspectionId {
		private String name;
		private int member;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getMember() {
			return member;
		}
		public void setMember(int member) {
			this.member = member;
		}
		
	}
}
