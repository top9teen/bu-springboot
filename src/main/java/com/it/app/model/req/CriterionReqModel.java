package com.it.app.model.req;

import java.util.List;

import com.it.app.model.CriterionModel;

public class CriterionReqModel {
	
	private String inspectionId;
	private List<CriterionModel> criterionModels;
	public String getInspectionId() {
		return inspectionId;
	}
	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}
	public List<CriterionModel> getCriterionModels() {
		return criterionModels;
	}
	public void setCriterionModels(List<CriterionModel> criterionModels) {
		this.criterionModels = criterionModels;
	}
	
	
}
