package com.it.app.model.resp;

import java.util.List;

public class DataGoogleMapRespModel {
	private List<DataGoogleDetail> dataGoogleDetails;
	private List<DataCriterionDetail> dataCriterionDetails;
	
	public static class DataGoogleDetail {
		private String lat;
		private String lng;
		private String marker;
		private String name;
		private String userId;
		private String assessmentId;
		private String assessmentDetail;
		private String community;
		private String lavel;
		private String inspectionsName;
		
		public String getLavel() {
			return lavel;
		}
		public void setLavel(String lavel) {
			this.lavel = lavel;
		}
		public String getCommunity() {
			return community;
		}
		public void setCommunity(String community) {
			this.community = community;
		}
		public String getAssessmentDetail() {
			return assessmentDetail;
		}
		public void setAssessmentDetail(String assessmentDetail) {
			this.assessmentDetail = assessmentDetail;
		}
		public String getAssessmentId() {
			return assessmentId;
		}
		public void setAssessmentId(String assessmentId) {
			this.assessmentId = assessmentId;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
		public String getMarker() {
			return marker;
		}
		public void setMarker(String marker) {
			this.marker = marker;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getInspectionsName() {
			return inspectionsName;
		}
		public void setInspectionsName(String inspectionsName) {
			this.inspectionsName = inspectionsName;
		}
		
	}
	
	public static class DataCriterionDetail{
		private String marker;
		private String criterDetail;
		public String getMarker() {
			return marker;
		}
		public void setMarker(String marker) {
			this.marker = marker;
		}
		public String getCriterDetail() {
			return criterDetail;
		}
		public void setCriterDetail(String criterDetail) {
			this.criterDetail = criterDetail;
		}
		
	}

	public List<DataGoogleDetail> getDataGoogleDetails() {
		return dataGoogleDetails;
	}

	public void setDataGoogleDetails(List<DataGoogleDetail> dataGoogleDetails) {
		this.dataGoogleDetails = dataGoogleDetails;
	}

	public List<DataCriterionDetail> getDataCriterionDetails() {
		return dataCriterionDetails;
	}

	public void setDataCriterionDetails(List<DataCriterionDetail> dataCriterionDetails) {
		this.dataCriterionDetails = dataCriterionDetails;
	}
	
	
}
