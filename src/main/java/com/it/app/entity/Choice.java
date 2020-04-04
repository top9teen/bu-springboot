package com.it.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the choice database table.
 * 
 */
@Entity
@Table(name="choice")
@NamedQuery(name="Choice.findAll", query="SELECT c FROM Choice c")
public class Choice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="choice_id")
	private Long choiceId;

	@Column(name="choice_criterion")
	private String choiceCriterion;

	@Column(name="choice_name")
	private String choiceName;

	@Column(name="question_id")
	private Long questionId;

	@Column(name="status")
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Choice() {
	}

	public Long getChoiceId() {
		return this.choiceId;
	}

	public void setChoiceId(Long choiceId) {
		this.choiceId = choiceId;
	}

	public String getChoiceCriterion() {
		return this.choiceCriterion;
	}

	public void setChoiceCriterion(String choiceCriterion) {
		this.choiceCriterion = choiceCriterion;
	}

	public String getChoiceName() {
		return this.choiceName;
	}

	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}

	public Long getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

}