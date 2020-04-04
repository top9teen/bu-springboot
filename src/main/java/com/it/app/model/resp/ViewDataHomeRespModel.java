package com.it.app.model.resp;

import java.util.List;

import com.it.app.model.SumInspectionModel;

public class ViewDataHomeRespModel {
	public String countUserTotal;
	
	public List<SumInspectionModel> inspectionModels;
	
	public String getCountUserTotal() {
		return countUserTotal;
	}
	public void setCountUserTotal(String countUserTotal) {
		this.countUserTotal = countUserTotal;
	}
	public List<SumInspectionModel> getInspectionModels() {
		return inspectionModels;
	}
	public void setInspectionModels(List<SumInspectionModel> inspectionModels) {
		this.inspectionModels = inspectionModels;
	}
	
}
