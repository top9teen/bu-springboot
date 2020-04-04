package com.it.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inspection database table.
 * 
 */
@Entity
@Table(name="inspection")
@NamedQuery(name="Inspection.findAll", query="SELECT i FROM Inspection i")
public class Inspection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="inspection_id")
	private Long inspectionId;

	@Column(name="inspection_name")
	private String inspectionName;

	@Column(name="status")
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Inspection() {
	}

	public Long getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
	}

	public String getInspectionName() {
		return this.inspectionName;
	}

	public void setInspectionName(String inspectionName) {
		this.inspectionName = inspectionName;
	}

}