package com.it.app.model.resp;

import java.util.List;

import com.it.app.model.QuestionModel;

public class InspectionRespModel {
	
	private String inspectionId;
	private String inspectionName;
	
	private List<QuestionModel> questions;

	public String getInspectionName() {
		return inspectionName;
	}
	public void setInspectionName(String inspectionName) {
		this.inspectionName = inspectionName;
	}
	public String getInspectionId() {
		return inspectionId;
	}
	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}
	public List<QuestionModel> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionModel> questions) {
		this.questions = questions;
	}
	
	
}
