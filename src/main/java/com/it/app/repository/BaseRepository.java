package com.it.app.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.it.app.entity.Criterion;
import com.it.app.model.req.SearchReportReqModel2;
import com.it.app.model.resp.DataGoogleMapRespModel;
import com.it.app.model.resp.DataGoogleMapRespModel.DataGoogleDetail;
@Repository
@SuppressWarnings("rawtypes")
public class BaseRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public Object getUserId() {
		Query query = entityManager
				.createNativeQuery("SELECT DISTINCT user_id  FROM assessment");
		List listResult = query.getResultList();
		return listResult;
	}
	
	public Object getUserIdAssess(String param) {
		Query query = entityManager
				.createNativeQuery("SELECT DISTINCT user_id  FROM assessment where inspection_id = " + param);
		List listResult = query.getResultList();
		return listResult;
	}
	public Object getUserIdAssessALL() {
		Query query = entityManager
				.createNativeQuery("SELECT DISTINCT user_id  FROM assessment");
		List listResult = query.getResultList();
		return listResult;
	}
	
	public Object getUserIdAssessAndCreadte(String inspectionId , String dateStart , String dateEnd) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT user_id  FROM assessment WHERE inspection_id = " + inspectionId);
		sql.append(" AND create_date BETWEEN '" + dateStart +"' AND '" + dateEnd +"'");
		Query query = entityManager
				.createNativeQuery(sql.toString());
		List listResult = query.getResultList();
		return listResult;
	}
	
	public Object getUserIdCreadte(String inspectionId ,String date) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT user_id  FROM assessment WHERE inspection_id = " + inspectionId);
		sql.append(" AND create_date LIKE '%" + date + "%'");
		Query query = entityManager
				.createNativeQuery(sql.toString());
		List listResult = query.getResultList();
		return listResult;
	}

	
	
	
	@SuppressWarnings({ "unchecked"})
	public List<Object[]> getDataMapUser(SearchReportReqModel2 searchReportReqModel) {
		String strdate;
		String enddate;
		StringBuffer sql = new StringBuffer();
		 sql.append(" SELECT CONCAT(up.fert_name ,' ', up.last_name ) as name, " + 
				" a.assessment_detail  as assessment_detail, " + 
				" a.create_date  as strdate, " + 
				" a.inspetion_detail  as inspectionname, " + 
				" a.assessment_id  as assessment_id, " + 
				" up.community  as community, " +
				" a.criterion_total as lavel, " + 
				" a.inspection_id as inspection_id,  " + 
				" up.user_id as user_id  " + 
				" FROM assessment a , user_profile up " + 
				" where 1 = 1 " + 
				" and a.user_id = up.user_id ");
		if (searchReportReqModel.getFname() != null && !searchReportReqModel.getFname().equals("")) {
			sql.append(" and CONCAT(up.fert_name ,' ', up.last_name ) like '%"+searchReportReqModel.getFname() +"%'");
		}
		
		if (searchReportReqModel.getDateStart() != null && !searchReportReqModel.getDateStart().equals("")) {
			strdate = searchReportReqModel.getDateStart();
		} else {
			strdate ="2001-01-01";
		}
		if (searchReportReqModel.getDateEnd() != null && !searchReportReqModel.getDateEnd().equals("")) {
			enddate = searchReportReqModel.getDateEnd();
		} else {
			enddate ="2099-12-31";
		}
		sql.append(" and a.create_date BETWEEN '"+strdate+"' and '"+enddate+"'");
		
		if (searchReportReqModel.getInspectionId() != null && !searchReportReqModel.getInspectionId().equals("")) {
			sql.append(" and a.inspection_id ='"+searchReportReqModel.getInspectionId()+"'");
		}
		if (searchReportReqModel.getCommunity() != null && !searchReportReqModel.getCommunity().equals("")) {
			sql.append(" and up.community = '"+searchReportReqModel.getCommunity()+"'");
		}
		
		sql.append(" order by a.create_date DESC ");
		List<Object[]> Bigdata = new ArrayList<>();
//		logger.info("SQL ========:"+SQL);
		Query query = entityManager
				.createNativeQuery(sql.toString());
		
		Bigdata = query.getResultList();
		
		return Bigdata;
	}
}
