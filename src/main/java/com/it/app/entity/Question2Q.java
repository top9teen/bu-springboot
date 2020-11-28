package com.it.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the question2Q database table.
 * 
 */
@Entity
@Table(name="question2q")
@NamedQuery(name="Question2Q.findAll", query="SELECT q FROM Question2Q q")
public class Question2Q implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="question2Q_id")
	private int question2Q_id;

	@Column(name="inspection_id")
	private int inspectionId;

	@Column(name="question2Q_name")
	private String question2Q_name;

	@Column(name="status")
	private String status;

	public Question2Q() {
	}

	public int getQuestion2Q_id() {
		return this.question2Q_id;
	}

	public void setQuestion2Q_id(int question2Q_id) {
		this.question2Q_id = question2Q_id;
	}

	public int getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(int inspectionId) {
		this.inspectionId = inspectionId;
	}

	public String getQuestion2Q_name() {
		return this.question2Q_name;
	}

	public void setQuestion2Q_name(String question2Q_name) {
		this.question2Q_name = question2Q_name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}