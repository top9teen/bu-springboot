package com.it.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.it.app.entity.Assessment;
import com.it.app.entity.Inspection;
import com.it.app.model.AssessmentModel;
import com.it.app.model.ReturnAssessStatus;
import com.it.app.repository.AssessmentRepository;
import com.it.app.repository.InspectionRepository;

@Service
@EnableAsync
@Transactional
public class QuestionService {

	@Autowired
	AssessmentRepository assessmentRepo;
	@Autowired
	InspectionRepository inspectionRepo;
	
	public ReturnAssessStatus resultsInterpretation(@Valid List<AssessmentModel> assessmentModel) {
		// TODO Auto-generated method stub
		boolean results = false;
		ReturnAssessStatus result_ = new ReturnAssessStatus();
		for (AssessmentModel value : assessmentModel) {
			if (value.getAnswer().equalsIgnoreCase("0")) {
				results = true;
			}
		}
		result_.setStatus(results);
		result_.setAssessmentId(this.saveAssessmen(results, assessmentModel).getAssessmentId());
		return result_;
	}

	private Assessment saveAssessmen(boolean results, @Valid List<AssessmentModel> assessmentModel) {
		// TODO Auto-generated method stub
		Assessment assessment = new Assessment();
		try {		
			for (AssessmentModel list : assessmentModel) {
				assessment = new Assessment();
				assessment.setCreateDate(new Timestamp(new Date().getTime()));
				if (list.getQuestion_id() != null) {
					Inspection inspection = inspectionRepo.findByInspectionId(list.getQuestion_id());
					assessment.setInspetionDetail(inspection.getInspectionName());
				}			
				assessment.setUserId(Long.parseLong(list.getUserId()));
				if (results) {
					assessment.setAssessmentDetail("เป็นผู้มีความเสี่ยง หรือ มีแนวโน้มที่จะเป็นโรคซึมเศร้า");
				} else {
					assessment.setAssessmentDetail("ไม่เป็นโรคซึมเศร้า");
				}			
				assessment.setInspectionId(Long.parseLong(list.getQuestion_id()));
				assessment.setCriterionTotal("0");			
				break;
			}
			return assessmentRepo.save(assessment);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	// end class
}
