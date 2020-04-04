package com.it.app.model.resp;

import java.util.List;

import com.it.app.model.CriterionModel;

public class CriterionRespModel {
	private String inspectionId;
	private String inspectionName;
	private List<CriterionModel> criterionModels;
	public String getInspectionId() {
		return inspectionId;
	}
	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}
	public String getInspectionName() {
		return inspectionName;
	}
	public void setInspectionName(String inspectionName) {
		this.inspectionName = inspectionName;
	}
	public List<CriterionModel> getCriterionModels() {
		return criterionModels;
	}
	public void setCriterionModels(List<CriterionModel> criterionModels) {
		this.criterionModels = criterionModels;
	}
	
	
}
