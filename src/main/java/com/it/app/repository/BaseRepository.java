package com.it.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
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

}
