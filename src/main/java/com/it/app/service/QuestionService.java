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

	public ReturnAssessStatus resultsAssessment9Q(@Valid List<AssessmentModel> assessmentModel) {
		// TODO Auto-generated method stub
		ReturnAssessStatus result_ = new ReturnAssessStatus();
		int criterionTotal = 0;
		String assessmentId = null;
		String inspectionId = null;
		String userId = null;
		boolean results = false;
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
			// < 7 false
			// 7-12 true
			if (criterionTotal >= 7) {
				results = true;
			}
			System.out.println("criterionTotal : " + criterionTotal);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if (criterionTotal < 7) {
			result_.setDetail("ไม่มีอาการของโรคซึมเศร้าหรือมีอาการของโรคซึมเศร้า ระดับน้อยมาก");
		} else if (criterionTotal <= 12) {
			result_.setDetail("มีอาการของโรคซึมเศร้า ระดับน้อย");
		} else if (criterionTotal <= 18) {
			result_.setDetail("มีอาการของโรคซึมเศร้า ระดับปานกลาง");
		} else if (criterionTotal >= 19) {
			result_.setDetail("มีอาการของโรคซึมเศร้า ระดับรุนแรง  ให้ประเมินแนวโน้มการฆ่าตัวตาย ด้วย 8Q");
		}
		result_.setStatus(results);
		result_.setAssessmentId(
				this.saveAssessment9Q(assessmentId, inspectionId, criterionTotal, userId).getAssessmentId());
		return result_;
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
	
	public ReturnAssessStatus resultsAssessment8Q(@Valid List<AssessmentModel> assessmentModel) {
		// TODO Auto-generated method stub
		int criterionTotal = 0;
		String assessmentId = null;
		try {
			for (AssessmentModel assess : assessmentModel) {
				if (assess.getAnswer() != null) {
					int i = Integer.parseInt(assess.getAnswer());
					criterionTotal += i;
				}
				if (assess.getAssessmentId() != null) {
					assessmentId = assess.getAssessmentId();
				}
			}			
			System.out.println("criterionTotal : " + criterionTotal);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return this.saveAssessment8Q(assessmentId, criterionTotal);
	}
	
	private ReturnAssessStatus saveAssessment8Q(String assessmentId, Integer criterionTotal) {
		// TODO Auto-generated method stub
		try {
			ReturnAssessStatus result = new ReturnAssessStatus();
			Assessment assessment = assessmentRepo.findByAssessmentId(assessmentId);
			assessment.setCreateDate(new Timestamp(new Date().getTime()));
			String name = new String();
			result.setStatus(false);
			result.setAssessmentId(assessment.getAssessmentId());
			if (criterionTotal < 1) {
				name = " และไม่มีแนวโน้มฆ่าตัวตายในปัจจุบัน";
				result.setDetail("ไม่มีแนวโน้มฆ่าตัวตายในปัจจุบัน");
			} else if (criterionTotal <= 12) {
				name = " และมีแนวโน้มฆ่าตัวตายในปัจจุบัน ระดับเล็กน้อย";
				result.setDetail("มีแนวโน้มฆ่าตัวตายในปัจจุบัน ระดับเล็กน้อย");
			} else if (criterionTotal <= 18) {
				name = " และมีแนวโน้มฆ่าตัวตายในปัจจุบัน ระดับปานกลาง";
				result.setDetail("มีแนวโน้มฆ่าตัวตายในปัจจุบัน ระดับปานกลาง");
			} else if (criterionTotal >= 19) {
				name = " และมีแนวโน้มฆ่าตัวตายในปัจจุบัน ระดับรุนแรง";
				result.setDetail("ะมีแนวโน้มฆ่าตัวตายในปัจจุบัน ระดับรุนแรง  ส่งต่อโรงพยาบาลมีจิตเเพทย์ด่วน");
				result.setStatus(true);
			}
			 assessment.setAssessmentDetail(assessment.getAssessmentDetail() + name);
			 assessmentRepo.save(assessment);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	// end class
}
