package com.it.app.model;

import java.util.List;

public class QuestionModel {

	private Long questionId;
	private String questionName;
	private List<ChoiceModel> choices;

	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public List<ChoiceModel> getChoices() {
		return choices;
	}
	public void setChoices(List<ChoiceModel> choices) {
		this.choices = choices;
	}

	
	
}
