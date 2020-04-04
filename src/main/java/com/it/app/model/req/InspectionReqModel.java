package com.it.app.model.req;

import java.util.List;

import com.it.app.model.CriterionModel;

public class InspectionReqModel {

	private String inspectionId;
	private String inspectionName;
	private List<CriterionModel> criterions;

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

	public List<CriterionModel> getCriterions() {
		return criterions;
	}

	public void setCriterions(List<CriterionModel> criterions) {
		this.criterions = criterions;
	}

}
