package com.it.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the question8Q database table.
 * 
 */
@Entity
@NamedQuery(name="Question8q.findAll", query="SELECT q FROM Question8Q q")
public class Question8Q implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="question8Q_id")
	private int question8Q_id;

	@Column(name="inspection_id")
	private int inspectionId;

	@Column(name="question8Q_name")
	private String question8Q_name;

	@Column(name="status")
	private String status;

	public Question8Q() {
	}

	public int getQuestion8Q_id() {
		return this.question8Q_id;
	}

	public void setQuestion8Q_id(int question8Q_id) {
		this.question8Q_id = question8Q_id;
	}

	public int getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(int inspectionId) {
		this.inspectionId = inspectionId;
	}

	public String getQuestion8Q_name() {
		return this.question8Q_name;
	}

	public void setQuestion8Q_name(String question8Q_name) {
		this.question8Q_name = question8Q_name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}