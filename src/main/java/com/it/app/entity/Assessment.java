package com.it.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the assessment database table.
 * 
 */
@Entity
@Table(name="assessment")
@NamedQuery(name="Assessment.findAll", query="SELECT a FROM Assessment a")
public class Assessment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="assessment_detail")
	private String assessmentDetail;


	@Id
	@GeneratedValue
	@Column(name="assessment_id")
	private Long assessmentId;

//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="inspetion_detail")
	private String inspetionDetail;

	@Column(name="user_id")
	private Long userId;
	
	@Column(name="inspection_id")
	private Long inspectionId;

	@Column(name="criterion_total")
	private String criterionTotal;
	
	public Assessment() {
	}

	public String getAssessmentDetail() {
		return this.assessmentDetail;
	}

	public void setAssessmentDetail(String assessmentDetail) {
		this.assessmentDetail = assessmentDetail;
	}

	public Long getAssessmentId() {
		return this.assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}



	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getInspetionDetail() {
		return this.inspetionDetail;
	}

	public void setInspetionDetail(String inspetionDetail) {
		this.inspetionDetail = inspetionDetail;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
	}

	public String getCriterionTotal() {
		return criterionTotal;
	}

	public void setCriterionTotal(String criterionTotal) {
		this.criterionTotal = criterionTotal;
	}

}