package com.it.app.model;

public class ReturnAssessStatus {

	private Long assessmentId;
	private Boolean status;
	private String detail;
	/**
	 * @return the assessmentId
	 */
	public Long getAssessmentId() {
		return assessmentId;
	}
	/**
	 * @param assessmentId the assessmentId to set
	 */
	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}
	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
