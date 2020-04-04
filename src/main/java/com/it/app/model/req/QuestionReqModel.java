package com.it.app.model.req;

import java.util.List;

import com.it.app.model.ChoiceModel;

public class QuestionReqModel {

	private List<ChoiceModel> choices;
	private String questionName;
	private String questionId;
	private String inspectionId;

	public List<ChoiceModel> getChoices() {
		return choices;
	}

	public void setChoices(List<ChoiceModel> choices) {
		this.choices = choices;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}

}
