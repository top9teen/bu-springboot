package com.it.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the criterion database table.
 * 
 */
@Entity
@Table(name="criterion")
@NamedQuery(name="Criterion.findAll", query="SELECT c FROM Criterion c")
public class Criterion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="criterion_id")
	private Long criterionId;

	@Column(name="criterion_detail")
	private String criterionDetail;

	@Column(name="criterion_end")
	private Long criterionEnd;

	@Column(name="criterion_start")
	private Long criterionStart;

	@Column(name="inspection_id")
	private Long inspectionId;

	@Column(name="status")
	private String status;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Long getCriterionId() {
		return criterionId;
	}

	public void setCriterionId(Long criterionId) {
		this.criterionId = criterionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCriterionDetail() {
		return this.criterionDetail;
	}

	public void setCriterionDetail(String criterionDetail) {
		this.criterionDetail = criterionDetail;
	}

	public Long getCriterionEnd() {
		return this.criterionEnd;
	}

	public void setCriterionEnd(Long criterionEnd) {
		this.criterionEnd = criterionEnd;
	}

	public Long getCriterionStart() {
		return this.criterionStart;
	}

	public void setCriterionStart(Long criterionStart) {
		this.criterionStart = criterionStart;
	}

	public Long getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
	}


}