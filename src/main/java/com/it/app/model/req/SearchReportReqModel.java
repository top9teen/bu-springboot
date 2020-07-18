package com.it.app.model.req;

public class SearchReportReqModel {
	
	private String inspectionId;
	private String dateStart;
	private String dateEnd;
	private String community;
	private String lavel;
	
	public String getInspectionId() {
		return inspectionId;
	}
	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getLavel() {
		return lavel;
	}
	public void setLavel(String lavel) {
		this.lavel = lavel;
	}	
	
}
