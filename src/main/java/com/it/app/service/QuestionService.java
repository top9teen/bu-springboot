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
//		return this.saveAssessmen(results, assessmentModel);
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
					if (inspection != null && inspection.getInspectionName() != null) {
						assessment.setInspetionDetail(inspection.getInspectionName());
					}
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

	public Assessment resultsAssessment9Q(@Valid List<AssessmentModel> assessmentModel) {
		// TODO Auto-generated method stub
		int criterionTotal = 0;
		String assessmentId = null;
		String inspectionId = null;
		String userId = null;
		try {
			for (AssessmentModel assess : assessmentModel) {
				if (assess.getAnswer() != null) {
					int i = Integer.parseInt(assess.getAnswer());
					criterionTotal += i;
				}
				if (assess.getAssessmentId() != null) {
					assessmentId = assess.getAssessmentId();
				}
				if (assess.getQuestion_id() != null) {
					inspectionId = assess.getQuestion_id();
				}				
				userId = assess.getUserId();
			}
			System.out.println("criterionTotal : " + criterionTotal);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return this.saveAssessment9Q(assessmentId, inspectionId, criterionTotal, userId);
	}

	private Assessment saveAssessment9Q(String assessmentId, String inspectionId, int criterionTotal, String userId) {
		// TODO Auto-generated method stub
		System.out.println("assessmentId : " + assessmentId + " inspectionId : " + inspectionId + " criterionTotal : "
				+ criterionTotal + " userId :" + userId);
		try {
			Assessment assessment = assessmentRepo.findByAssessmentId(assessmentId);
			assessment.setCreateDate(new Timestamp(new Date().getTime()));
			Inspection inspection = inspectionRepo.findByInspectionId(inspectionId);
			assessment.setInspetionDetail(inspection.getInspectionName());
			assessment.setUserId(Long.parseLong(userId));
			String name = "";
			if (criterionTotal < 7) {
				name = " และไม่มีอาการของโรคซึมเศร้าหรือมีอาการของโรคซึมเศร้าระดับน้อยมาก";
				assessment.setAssessmentDetail(assessment.getAssessmentDetail() + name);
			} else if (criterionTotal <= 12) {
				name = " และมีอาการของโรคซึมเศร้า ระดับน้อย";
				assessment.setAssessmentDetail(assessment.getAssessmentDetail() + name);
			} else if (criterionTotal <= 18) {
				name = " และมีอาการของโรคซึมเศร้า ระดับปานกลาง";
				assessment.setAssessmentDetail(assessment.getAssessmentDetail() + name);
			} else if (criterionTotal >= 19) {
				name = " และมีอาการของโรคซึมเศร้า ระดับรุนแรง";
				assessment.setAssessmentDetail(assessment.getAssessmentDetail() + name);
			}
			assessment.setInspectionId(Long.parseLong(inspectionId));
			assessment.setCriterionTotal(String.valueOf(criterionTotal));
			return assessmentRepo.save(assessment);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
