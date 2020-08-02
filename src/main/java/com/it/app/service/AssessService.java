package com.it.app.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.app.constant.MasterConstant;
import com.it.app.entity.Assessment;
import com.it.app.entity.Choice;
import com.it.app.entity.Criterion;
import com.it.app.entity.Inspection;
import com.it.app.entity.Question;
import com.it.app.entity.UserProfile;
import com.it.app.model.AssessmentReqModel;
import com.it.app.model.ChoiceModel;
import com.it.app.model.CriterionModel;
import com.it.app.model.QuestionModel;
import com.it.app.model.ReportConclusionBean;
import com.it.app.model.ReportConclusionBean.InspectionId;
import com.it.app.model.req.CriterionReqModel;
import com.it.app.model.req.InspectionReqModel;
import com.it.app.model.req.QuestionReqModel;
import com.it.app.model.req.SearchReportReqModel;
import com.it.app.model.req.SearchReportReqModel2;
import com.it.app.model.resp.AssessRespModel;
import com.it.app.model.resp.AssessmentGroupRespModel;
import com.it.app.model.resp.AssessmentRespModel;
import com.it.app.model.resp.CriterionAssessmentRespModel;
import com.it.app.model.resp.CriterionRespModel;
import com.it.app.model.resp.DataGoogleMapRespModel;
import com.it.app.model.resp.DataGoogleMapRespModel.DataCriterionDetail;
import com.it.app.model.resp.DataGoogleMapRespModel.DataGoogleDetail;
import com.it.app.model.resp.DataGoogleMapRespModel2;
import com.it.app.model.resp.InspectionRespModel;
import com.it.app.repository.AssessmentRepository;
import com.it.app.repository.BaseRepository;
import com.it.app.repository.ChoiceRepository;
import com.it.app.repository.CriterionRepository;
import com.it.app.repository.InspectionRepository;
import com.it.app.repository.QuestionRepository;
import com.it.app.repository.UserProfileRepository;
import com.it.app.utils.DateUtil;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

@Service
@EnableAsync
@Transactional
public class AssessService {

	@Autowired
	private InspectionRepository inspectionRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ChoiceRepository choiceRepository;
	
	@Autowired
	private CriterionRepository criterionRepository;
	
	@Autowired
	private AssessmentRepository assessmentRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private BaseRepository baseRepository;

	public Object listInspection() {
		List<InspectionRespModel> inspectionRespModels = new ArrayList<>();
		List<Inspection> inspections = inspectionRepository.findAllInspection();
		for (Inspection obj : inspections) {
			inspectionRespModels.add(modelMapper.map(obj, InspectionRespModel.class));
		}
		return inspectionRespModels;
	}

	public Object getInspectionById(String inspectionId) {
		InspectionRespModel inspectionRespModel = null;
		Optional<Inspection> inspection = inspectionRepository.findOngById(getLong(inspectionId));
		if (inspection.isPresent()) {
			inspectionRespModel = modelMapper.map(inspection.get(), InspectionRespModel.class);
			List<Question> listQuestion = questionRepository.findListQuestionByInspectionId(getLong(inspectionId));
			List<QuestionModel> questions = new ArrayList<>();
			for (Question o : listQuestion) {
				QuestionModel questionModel = new QuestionModel();
				List<Choice> choices = choiceRepository.findListChoiceByQuestionId(o.getQuestionId());
				List<ChoiceModel> choiceModels = new ArrayList<>();
				for (Choice c : choices) {
					choiceModels.add(modelMapper.map(c, ChoiceModel.class));
				}
				questionModel = modelMapper.map(o, QuestionModel.class);
				questionModel.setChoices(choiceModels);
				questions.add(questionModel);
			}
			inspectionRespModel.setQuestions(questions);
		}
		return inspectionRespModel;
	}

	public Object saveInspection(InspectionReqModel inspectionReqModel) {
		Inspection inspection = null;
		InspectionRespModel inspectionRespModel = new InspectionRespModel();
		if (null != inspectionReqModel.getInspectionId()
				&& StringUtils.isNotBlank(inspectionReqModel.getInspectionId())) {
			Optional<Inspection> optional = inspectionRepository
					.findOngById(getLong(inspectionReqModel.getInspectionId()));
			if (optional.isPresent()) {
				inspection = optional.get();
			} else {
				inspection = new Inspection();
			}
		} else {
			inspection = new Inspection();
		}

		inspection = modelMapper.map(inspectionReqModel, Inspection.class);
		inspection.setStatus(MasterConstant.status.active);
		inspectionRepository.save(inspection);

		inspectionRespModel = modelMapper.map(inspection, InspectionRespModel.class);
		return inspectionRespModel;
	}

	public Object deleteInspection(String inspectionId) {
		if (null != inspectionId && StringUtils.isNotBlank(inspectionId)) {
			Optional<Inspection> inspection =inspectionRepository.findOngById(getLong(inspectionId));
			if(inspection.isPresent()) {
				Inspection	in  = inspection.get();
				in.setStatus(MasterConstant.status.inActive);
				inspectionRepository.save(in);
			}
		//	inspectionRepository.deleteById(getLong(inspectionId));
		}
		return "success";
	}

	public Object listQuestionByInspectionId(String inspectionId) {
		List<Question> listQuestion = questionRepository.findListQuestionByInspectionId(getLong(inspectionId));
		if (null != listQuestion) {

		}
		return "success";
	}

	public Object getQuestionById(String questionId) {
		QuestionModel questionModel = new QuestionModel();
		Optional<Question> opQuestion = questionRepository.findQuestionByQuestionId(getLong(questionId));
		if (opQuestion.isPresent()) {
			Question question = opQuestion.get();
			questionModel = modelMapper.map(question, QuestionModel.class);
			List<Choice> choices = choiceRepository.findListChoiceByQuestionId(getLong(questionId));
			List<ChoiceModel> choiceModels = new ArrayList<>();
			for (Choice c : choices) {
				choiceModels.add(modelMapper.map(c, ChoiceModel.class));
			}
			questionModel.setChoices(choiceModels);
		}
		return questionModel;
	}
	public Object saveQuestion(QuestionReqModel reqModel) {
		Question question  = null;
		if(StringUtils.isNotBlank(reqModel.getQuestionId())) {
			Optional<Question> opQuestion = questionRepository.findQuestionByQuestionId(getLong(reqModel.getQuestionId()));
			if(opQuestion.isPresent()) {
				question = opQuestion.get();
			}
		}else if(null == question){
			question = new Question();
		}
		
		question.setInspectionId(getLong(reqModel.getInspectionId()));
		question.setQuestionName(reqModel.getQuestionName());
		question.setStatus(MasterConstant.status.active);
		questionRepository.save(question);
		
	//	choiceRepository.deleteByQuestionId(question.getQuestionId());
		List<Choice> choices = choiceRepository.findListChoiceByQuestionId(question.getQuestionId());
		for(Choice choice : choices) {
			choice.setStatus(MasterConstant.status.inActive);
			choiceRepository.save(choice);
		}
		
		for(ChoiceModel choiceReq :  reqModel.getChoices()) {
			if(StringUtils.isNoneBlank(choiceReq.getChoiceName())) {
				Choice choice = new Choice();
				choice.setChoiceName(choiceReq.getChoiceName());
				choice.setChoiceCriterion(choiceReq.getChoiceCriterion());
				choice.setQuestionId(question.getQuestionId());
				choice.setStatus(MasterConstant.status.active);
				choiceRepository.save(choice);				
			}
		}
		
		return "success";
	}
	
	public Object deleteQuestionById(String questionById) {
		if (null != questionById && StringUtils.isNotBlank(questionById)) {
			Optional<Question> q = 	questionRepository.findQuestionByQuestionId(getLong(questionById));
			if (q.isPresent()) {
				Question question =	q.get();
				question.setStatus(MasterConstant.status.inActive);
				questionRepository.save(question);
			}
	//		questionRepository.deleteById(getLong(questionById));
		}
		return "success";
	}
	
	public Object saveAssessment(AssessmentReqModel assessmentReqModel) {
		List<Criterion> criterions = criterionRepository.findListCriterionByInspectionId(getLong(assessmentReqModel.getInspectionId()));

		Long count = getLong(assessmentReqModel.getCount());
		if(null != count) {
			AssessmentRespModel assessmentRespModel = new AssessmentRespModel();
			for(Criterion criterion : criterions) {
				if(criterion.getCriterionStart() <= count && count <= criterion.getCriterionEnd()) {
					assessmentRespModel.setCriterionDetail(criterion.getCriterionDetail());
					Assessment assessment = new Assessment();
					assessment.setAssessmentDetail(criterion.getCriterionDetail());
					Optional<Inspection> opinspection = inspectionRepository.findOngById(getLong(assessmentReqModel.getInspectionId()));
					assessment.setInspetionDetail(opinspection.get().getInspectionName());
					assessment.setInspectionId(opinspection.get().getInspectionId());
					assessment.setUserId(getLong(assessmentReqModel.getUserId()));
					assessment.setCreateDate(new Timestamp(new Date().getTime()));
					assessment.setCriterionTotal(String.valueOf(count));
					
//					Optional<UserProfile> profile = userProfileRepository.findOneByUserId(getLong(assessmentReqModel.getUserId()));
//					if(profile.isPresent()) {
//						Optional<UserProfileAddress>address =  userProfileAddressRepository.findOneByProfileId(profile.get().getProfileId());
//						if(address.isPresent()) {
//							assessment.setCommunity(address.get().getCommunity());
//						}		
//					}
//			
					assessmentRepository.save(assessment);
					CriterionAssessmentRespModel resp = new CriterionAssessmentRespModel();
					resp.setAssessmentId(String.valueOf(assessment.getAssessmentId()));
					resp.setCriterionDetail(criterion.getCriterionDetail());
					return resp;
				}
			}
		}
		return null;
	}
	
	public Object getCriterionByInspectionId(String inspectionId) {
		CriterionRespModel criterionRespModel = null;
		Optional<Inspection> opinspection = inspectionRepository.findOngById(getLong(inspectionId));
		if (opinspection.isPresent()) {
			criterionRespModel = new CriterionRespModel();
			Inspection inspection = opinspection.get();
			criterionRespModel.setInspectionId(String.valueOf(inspection.getInspectionId()));
			criterionRespModel.setInspectionName(inspection.getInspectionName());
			List<Criterion> criterions = criterionRepository.findListCriterionByInspectionId(inspection.getInspectionId());
			List<CriterionModel> criterionModels = new ArrayList<>(); 
			for(Criterion criterion : criterions) {
				CriterionModel criterionModel = modelMapper.map(criterion, CriterionModel.class); 
				criterionModels.add(criterionModel);
			}
			criterionRespModel.setCriterionModels(criterionModels);
		}
		return criterionRespModel;
	}
	
	public Object saveCriterion(CriterionReqModel criterionReqModel) {
		if(StringUtils.isNotBlank(criterionReqModel.getInspectionId())) {
//			criterionRepository.deleteByInspectionId(getLong(criterionReqModel.getInspectionId()));	
			List<Criterion> criterions = criterionRepository.findListCriterionByInspectionId(getLong(criterionReqModel.getInspectionId()));
//			criterionRepository.deleteAll(criterions);
			for(Criterion criterion : criterions) {
				criterion.setStatus(MasterConstant.status.inActive);
				criterionRepository.save(criterion);
			}
			if(null != criterionReqModel.getCriterionModels()) {
				for(CriterionModel criterionModel : criterionReqModel.getCriterionModels()) {
					if(isCriterion(criterionModel.getCriterionStart()) && isCriterion(criterionModel.getCriterionEnd())) {
						Criterion criterion = new Criterion();
						criterion.setCriterionDetail(criterionModel.getCriterionDetail());
						criterion.setCriterionStart(getLong(criterionModel.getCriterionStart()));
						criterion.setCriterionEnd(getLong(criterionModel.getCriterionEnd()));
						criterion.setInspectionId(getLong(criterionReqModel.getInspectionId()));
						criterion.setStatus(MasterConstant.status.active);
						criterionRepository.save(criterion);
					}

				}
			}
		}
		return null;
	}
	
	public Object getAssessmentByUserId(String userId,String inspectionId) {
		List<AssessRespModel> assessRespModels = new ArrayList<>();
		List<Assessment> assessments = assessmentRepository.findListAssessmentByUserId(getLong(userId),getLong(inspectionId));
		for(Assessment assessment:assessments) {
			AssessRespModel assessRespModel = new AssessRespModel();
			assessRespModel.setAssessmentId(String.valueOf(assessment.getAssessmentId()));
			assessRespModel.setAssessmentDetail(assessment.getAssessmentDetail());
			assessRespModel.setInspetionDetail(assessment.getInspetionDetail());
			assessRespModel.setCreateDate(DateUtil.dateToString(assessment.getCreateDate(), DateUtil.DD_MM_YYYY));
			assessRespModel.setCriterionTotal(assessment.getCriterionTotal());
			assessRespModels.add(assessRespModel);
		}
		return assessRespModels;
	}
	
	public Object getAssessmentByInspectionId(SearchReportReqModel searchReportReqModel) {
		List<AssessmentGroupRespModel> resp = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		//List<Integer> countUserId = (List<Integer>) baseRepository.getUserIdAssess(inspectionId);
		List<Integer> countUserId =  getUserId(searchReportReqModel);
		for(Integer userId : countUserId) {
			Optional<UserProfile> userProfile =	null;
			if(StringUtils.isNotBlank(searchReportReqModel.getCommunity())) {
				userProfile = userProfileRepository.findOneByUserIdAndCommunity(getLong(String.valueOf(userId)), searchReportReqModel.getCommunity());
			}else {
				userProfile =	userProfileRepository.findOneByUserId(Long.valueOf(String.valueOf(userId)));
			}
			if(userProfile.isPresent()) {
					String community = userProfile.get().getCommunity();
					
					if(null != map.get(community)) {
						AssessmentGroupRespModel count = (AssessmentGroupRespModel) map.get("community");
						count.setCount(count.getCount() + 1);
					}else {
						AssessmentGroupRespModel count =new AssessmentGroupRespModel();
						count.setCount(1);
						count.setCommunity(getCommunity(community));
						map.put(community, count);
					}
			}
		}
		
	     for (Map.Entry<String, Object> entry : map.entrySet()) {
	    	 AssessmentGroupRespModel count =  (AssessmentGroupRespModel) entry.getValue();
	    	 resp.add(count);
	        }
		return resp;
	}
	
	@SuppressWarnings({ "unchecked"})
	public Object getDataMapByInspectionId(SearchReportReqModel searchReportReqModel) {
		
		/*
		NEW PARAMETER  ON 2020/07/18
		*/  
		DataGoogleMapRespModel resp = new DataGoogleMapRespModel();
		List<DataGoogleDetail> dataGoogleDetails = new ArrayList<>();
		List<Integer> countUserId = new ArrayList<>();
		List<Criterion> criterionS = new ArrayList<>();
		
		/* 
		NEW FUNCTION IF CHECK DATA AND GET DATA  ON 2020/07/18
		*/  
		
		if (searchReportReqModel.getDateStart() != null && searchReportReqModel.getDateStart().equalsIgnoreCase("")) {
			searchReportReqModel.setDateStart("2001-01-01");
		}
		
		if (searchReportReqModel.getDateEnd() != null && searchReportReqModel.getDateEnd().equalsIgnoreCase("")) {
			searchReportReqModel.setDateEnd("2099-12-31");
		}
		
		if (searchReportReqModel.getInspectionId() != null && searchReportReqModel.getInspectionId().equalsIgnoreCase("")) {
			countUserId = new ArrayList<>();
			criterionS 	= new ArrayList<>();
			List<Inspection> inspections = inspectionRepository.findAllInspection();
			
			DataCriterionDetail dataCriterionDetail = new DataCriterionDetail();
			List<DataCriterionDetail> dataCriterionDetails = new ArrayList<>();
			for (Inspection ins :inspections) {
				countUserId = new ArrayList<>();
				criterionS 	= new ArrayList<>();
				countUserId = (List<Integer>) baseRepository.getUserIdAssess(ins.getInspectionId().toString());
				criterionS 	=  criterionRepository.findListCriterionByInspectionId(ins.getInspectionId());		
				Map<String, String> marker = getMarker(criterionS.size());
				for(Integer userId : countUserId) {
					
					/* 
					NEW FUNCTION IF CHECK DATA AND GET DATA  ON 2020/07/18
					*/  
					
					Optional<Assessment> opAssessment = null;
					if (searchReportReqModel.getInspectionId() != null && searchReportReqModel.getInspectionId().equalsIgnoreCase("")) {
						 opAssessment =  assessmentRepository.findAssessmentByInspectionIdAndUserIdALL(Long.parseLong(userId.toString()), searchReportReqModel.getDateStart(), searchReportReqModel.getDateEnd());
					} else {
						 opAssessment =  assessmentRepository.findAssessmentByInspectionIdAndUserId(Long.parseLong(userId.toString()), getLong(searchReportReqModel.getInspectionId()), searchReportReqModel.getDateStart(), searchReportReqModel.getDateEnd());
					}

					Optional<UserProfile> opUserProfile = null;
					if(StringUtils.isNotBlank(searchReportReqModel.getCommunity())) {
						opUserProfile = userProfileRepository.findOneByUserIdAndCommunity(getLong(String.valueOf(userId)), searchReportReqModel.getCommunity());
					}else {
						opUserProfile =	userProfileRepository.findOneByUserId(Long.valueOf(String.valueOf(userId)));
					}
					if(opUserProfile.isPresent() && opAssessment.isPresent()) {
						DataGoogleDetail dataGoogleDetail = new DataGoogleDetail();
						int markerKey = 1;
						markerKey = mapGetCriterion(criterionS, getLong(opAssessment.get().getCriterionTotal()));
						dataGoogleDetail.setLat(opUserProfile.get().getLat());
						dataGoogleDetail.setLng(opUserProfile.get().getLng());
						dataGoogleDetail.setMarker(marker.get(String.valueOf(markerKey)));
						dataGoogleDetail.setName(getTitleName(opUserProfile.get().getTitleName()) + opUserProfile.get().getFertName() +" "+ opUserProfile.get().getLastName());
						dataGoogleDetail.setUserId(userId.toString());
						dataGoogleDetail.setAssessmentId(String.valueOf(opAssessment.get().getAssessmentId()));
						dataGoogleDetail.setAssessmentDetail(opAssessment.get().getAssessmentDetail());
						dataGoogleDetail.setCommunity(getCommunity(opUserProfile.get().getCommunity()));
						dataGoogleDetail.setLavel(String.valueOf(markerKey));
						dataGoogleDetail.setInspectionsName(ins.getInspectionName());
						dataGoogleDetail.setStrdate(opAssessment.get().getCreateDate());
						if (searchReportReqModel.getLavel() != null && !searchReportReqModel.getLavel().equalsIgnoreCase("")) {
							if (searchReportReqModel.getLavel().equalsIgnoreCase(String.valueOf(markerKey))) {
								dataGoogleDetails.add(dataGoogleDetail);
							}
						} else {
							dataGoogleDetails.add(dataGoogleDetail);
						}
					}
				}
				int is = 1;
		        for (Criterion c :criterionS) {
		        	dataCriterionDetail = new DataCriterionDetail();
		        	dataCriterionDetail.setCriterDetail(ins.getInspectionName()+" => " + c.getCriterionDetail());
		        	dataCriterionDetail.setMarker(marker.get(String.valueOf(is)));
		        	is++;
		        	dataCriterionDetails.add(dataCriterionDetail);
		          }
			}   
			resp.setDataGoogleDetails(dataGoogleDetails);
			resp.setDataCriterionDetails(dataCriterionDetails);
		return resp;
		} else {
			countUserId = new ArrayList<>();
			criterionS 	= new ArrayList<>();
			Optional<Inspection>  inspections = inspectionRepository.findOngById(getLong(searchReportReqModel.getInspectionId()));
			countUserId = (List<Integer>) baseRepository.getUserIdAssess(searchReportReqModel.getInspectionId());
			criterionS 	=  criterionRepository.findListCriterionByInspectionId(getLong(searchReportReqModel.getInspectionId()));			
			Map<String, String> marker = getMarker(criterionS.size());
			for(Integer userId : countUserId) {
				
				/* 
				NEW FUNCTION IF CHECK DATA AND GET DATA  ON 2020/07/18
				*/  
				
				Optional<Assessment> opAssessment = null;
				if (searchReportReqModel.getInspectionId() != null && searchReportReqModel.getInspectionId().equalsIgnoreCase("")) {
					 opAssessment =  assessmentRepository.findAssessmentByInspectionIdAndUserIdALL(Long.parseLong(userId.toString()), searchReportReqModel.getDateStart(), searchReportReqModel.getDateEnd());
				} else {
					 opAssessment =  assessmentRepository.findAssessmentByInspectionIdAndUserId(Long.parseLong(userId.toString()), getLong(searchReportReqModel.getInspectionId()), searchReportReqModel.getDateStart(), searchReportReqModel.getDateEnd());
				}

				Optional<UserProfile> opUserProfile = null;
				if(StringUtils.isNotBlank(searchReportReqModel.getCommunity())) {
					opUserProfile = userProfileRepository.findOneByUserIdAndCommunity(getLong(String.valueOf(userId)), searchReportReqModel.getCommunity());
				}else {
					opUserProfile =	userProfileRepository.findOneByUserId(Long.valueOf(String.valueOf(userId)));
				}
				if(opUserProfile.isPresent() && opAssessment.isPresent()) {
					DataGoogleDetail dataGoogleDetail = new DataGoogleDetail();
					int markerKey = 1;
					markerKey = mapGetCriterion(criterionS, getLong(opAssessment.get().getCriterionTotal()));
					dataGoogleDetail.setLat(opUserProfile.get().getLat());
					dataGoogleDetail.setLng(opUserProfile.get().getLng());
					dataGoogleDetail.setMarker(marker.get(String.valueOf(markerKey)));
					dataGoogleDetail.setName(getTitleName(opUserProfile.get().getTitleName()) + opUserProfile.get().getFertName() +" "+ opUserProfile.get().getLastName());
					dataGoogleDetail.setUserId(userId.toString());
					dataGoogleDetail.setAssessmentId(String.valueOf(opAssessment.get().getAssessmentId()));
					dataGoogleDetail.setAssessmentDetail(opAssessment.get().getAssessmentDetail());
					dataGoogleDetail.setCommunity(getCommunity(opUserProfile.get().getCommunity()));
					dataGoogleDetail.setLavel(String.valueOf(markerKey));
					dataGoogleDetail.setInspectionsName(inspections.get().getInspectionName());
					dataGoogleDetail.setStrdate(opAssessment.get().getCreateDate());
					if (searchReportReqModel.getLavel() != null && !searchReportReqModel.getLavel().equalsIgnoreCase("")) {
						if (searchReportReqModel.getLavel().equalsIgnoreCase(String.valueOf(markerKey))) {
							dataGoogleDetails.add(dataGoogleDetail);
						}
					} else {
						dataGoogleDetails.add(dataGoogleDetail);
					}
					
				}
			}
			resp.setDataGoogleDetails(dataGoogleDetails);
			List<DataCriterionDetail> dataCriterionDetails = new ArrayList<>();
			int is = 1;
	        for (Criterion c :criterionS) {
	        	DataCriterionDetail dataCriterionDetail = new DataCriterionDetail();
	        	dataCriterionDetail.setCriterDetail(c.getCriterionDetail());
	        	dataCriterionDetail.setMarker(marker.get(String.valueOf(is)));
	        	is++;
	        	dataCriterionDetails.add(dataCriterionDetail);
	          }
			
	        resp.setDataCriterionDetails(dataCriterionDetails);
			return resp;
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Integer> getUserId(SearchReportReqModel searchReportReqModel){
		List<Integer> resp = new ArrayList<>();
		
		if(StringUtils.isEmpty(searchReportReqModel.getInspectionId())){
			return resp;
		}
		
		if (StringUtils.isNotEmpty(searchReportReqModel.getDateStart())
				&& StringUtils.isNotEmpty(searchReportReqModel.getDateEnd())) {	
			
			resp = (List<Integer>) baseRepository.getUserIdAssessAndCreadte(searchReportReqModel.getInspectionId(),
					searchReportReqModel.getDateStart(), searchReportReqModel.getDateEnd());
			
		}else if (StringUtils.isEmpty(searchReportReqModel.getDateStart())
				&& StringUtils.isEmpty(searchReportReqModel.getDateEnd())) {
			
			resp = (List<Integer>) baseRepository.getUserIdAssess(searchReportReqModel.getInspectionId());
			
		}else if(StringUtils.isNotEmpty(searchReportReqModel.getDateStart())
				&& StringUtils.isEmpty(searchReportReqModel.getDateEnd())) {
			
			resp = (List<Integer>)  baseRepository.getUserIdCreadte(searchReportReqModel.getInspectionId(),searchReportReqModel.getDateStart());
			
		}else if(StringUtils.isEmpty(searchReportReqModel.getDateStart())
				&& StringUtils.isNotEmpty(searchReportReqModel.getDateEnd())) {
			
			resp = (List<Integer>)  baseRepository.getUserIdCreadte(searchReportReqModel.getInspectionId(),searchReportReqModel.getDateEnd());
			
		}
		return resp;
	}
	
	public byte[] printReport(String assessmentId) {		
		Optional<Assessment> opAssessment = assessmentRepository.findOneByAssessmentId(getLong(assessmentId));
		if(opAssessment.isPresent()) {
			Assessment assessment = opAssessment.get();
			UserProfile userProfile =	userProfileRepository.findOneByUserId(assessment.getUserId()).get();
			Map<String, Object> param = new HashMap<>();
			param.put("name", userProfile.getFertName() +" " +userProfile.getLastName());
			param.put("date", DateUtil.dateToString(new Date(), DateUtil.DD_MM_YYYY));
			param.put("address", userProfile.getAddress());
			param.put("community", getCommunity(userProfile.getCommunity()));
			param.put("inspectionName", assessment.getInspetionDetail());
			param.put("assessmentDetail", assessment.getAssessmentDetail());
			try {
//				System.out.println(this.getClass().getResourceAsStream("/jasper/report1.jasper"));
//				String path = "src/main/resources/jasper/report1.jasper";
				
//				JasperReport jasperReport = JasperCompileManager.compileReport(path);
//				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, new JREmptyDataSource());
				
				InputStream inputStream = null;
				JasperReport jasperReport = null;
//				response.setContentType("application/pdf; charset=UTF-8");
//				response.setHeader("Content-Disposition", String.format("attachment; filename=" + name+".pdf"));
				inputStream = this.getClass().getResourceAsStream("/jasper/report1.jrxml");
				jasperReport = JasperCompileManager.compileReport(inputStream);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, new JREmptyDataSource());
				
//				JasperPrint jasperPrint = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/jasper/report1.jrxml"), param, new JREmptyDataSource());
				byte[] temp = JasperExportManager.exportReportToPdf(jasperPrint);
//				Path tempFile = Files.createTempFile("Assessment", ".pdf");
//				if (Files.exists(tempFile)) {
//					Files.delete(tempFile);
//					Files.write(tempFile, temp);
//
//				} else {
//					Files.write(tempFile, temp);
//				}
//
//				File pdSignFile = tempFile.toFile();
				return temp;
			} catch (JRException e) {
				e.printStackTrace();
			}
//			catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

		return null;
	}
	
	public byte[] genPagePDF(List<JasperPrint> jasperPrint) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrint);
		exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
	
	
	private Integer getInt(String str) {
		Integer result = 0;
		if (!"null".equals(str) && null != str && StringUtils.isNotBlank(str)) {
			result = Integer.parseInt(str);
		}
		return result;
	}

	private Long getLong(String str) {
		Long result = null;
		if (!"null".equals(str) && null != str && StringUtils.isNotBlank(str)) {
			result = Long.parseLong(str);
		}
		return result;
	}
	
	private boolean isCriterion(String criterionStart) {
		if(StringUtils.isNoneBlank(criterionStart) && !"NULL".equals(criterionStart)) {
			try {
				Long l = Long.parseLong(criterionStart);
				if(l >= 0) {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		}else {
			return false;
		}
		
		return false;
	}
	
	private String getCommunity(String community) {
		
		switch (community) {
		case "1":
			return "วัดขุนก้อง";
		case "2":
			return "วัดกลาง";
		case "3":
			return "วัดร่องมันเทศ";
		case "4":
			return "วัดป่าเรไร";
		case "5":
			return "ป่าตาเส็ง";
		case "6":
			return "วัดถนนหัก";
		case "7":
			return "บ้านเก่า";
		case "8":
			return "วัดใหม่เรไรทอง";
		case "9":
			return "หนองโพรง";
		case "10":
			return "ทุ่งแหลม";
		case "11":
			return "หนองรี";
		case "12":
			return "บ้านหนองกราด";
		case "13":
			return "บ้านหนองเสม็ด";
		case "14":
			return "บ้านจะบวก";
		case "15":
			return "โคกหลวงพ่อ";
		case "16":
			return "บ้านดอนแสลงพันธ์";
		case "17":
			return "บ้านถนนหัก";
		case "18":
			return "ถนนหักพัฒนา";
		case "19":
			return "วัดสวนป่ารักน้ำ";
		case "20":
			return "วัดหัวสะพาน";
		default:
			return "";
		}
		  
	}
	
	private int mapGetCriterion (List<Criterion> criterions , Long count) {
		int i = 1;
		for(Criterion criterion:criterions) {
			if(criterion.getCriterionStart() <= count && count <= criterion.getCriterionEnd()) {
				return i;
			}else {
				i++;
			}
		}

		return 0;
	}
	
	private Map<String, String> getMarker(int size){
		Map<String, String> map = new HashMap<>();
		switch (size) {
		case 0:
			map.put("1", "assets/images/marker-red.png");
			break;
		case 1:
			map.put("1", "assets/images/marker-red.png");
			break;
		case 2:
			map.put("1", "assets/images/marker-red.png");
			map.put("2", "assets/images/marker-green2.png");
			break;
		case 3:
			map.put("1", "assets/images/marker-red.png");
			map.put("2", "assets/images/marker-yellow.png");
			map.put("3", "assets/images/marker-green2.png");
			break;
		case 4:
			map.put("1", "assets/images/marker-red.png");
			map.put("2", "assets/images/marker-orange.png");
			map.put("3", "assets/images/marker-yellow.png");
			map.put("4", "assets/images/marker-green2.png");
			break;
		default:
			map.put("1", "assets/images/marker-red.png");
			map.put("2", "assets/images/marker-orange.png");
			map.put("3", "assets/images/marker-yellow.png");
			map.put("4", "assets/images/marker-green.png");
			map.put("5", "assets/images/marker-green2.png");
			break;
		}
		
		return map;
	}
	
	private String getTitleName(Integer title) {
		String result = "";
		switch (title) {
		case 1:
			result = MasterConstant.titleName.mr;
			break;
		case 2:
			result = MasterConstant.titleName.mrs;
			break;
		case 3:
			result = MasterConstant.titleName.miss;
			break;
		}
		return result;
	}

	public List<ReportConclusionBean> getReportConclusion(SearchReportReqModel searchReportReqModel) {
		// TODO Auto-generated method stub
		List<ReportConclusionBean> listBean = new ArrayList<ReportConclusionBean>();
		ReportConclusionBean bean = new ReportConclusionBean();
		List<String> listCommunity = new ArrayList<String>();
		if (StringUtils.isNotBlank(searchReportReqModel.getCommunity())) {
			// not null
			System.out.println("not null : " + searchReportReqModel.getCommunity());
			List<UserProfile> list = userProfileRepository.findByCommunity(searchReportReqModel.getCommunity());
			for (UserProfile userProfile : list) {
				bean = new ReportConclusionBean();
				System.out.println(userProfile.getCommunity() + " : "+ getCommunity(userProfile.getCommunity()));
				List<String> listCom = userProfileRepository.findByUserIdCommunity(userProfile.getCommunity());
				List<String> listsAssess = new ArrayList<String>();
				List<Assessment> assessment = assessmentRepository.findByInUserId(listCom);
				System.out.println("assessment : " + assessment.size());
				for (Assessment valueAssess : assessment) {
					if (!listsAssess.contains(valueAssess.getInspetionDetail())) {
						listsAssess.add(valueAssess.getInspetionDetail());
//						bean.setInspectionId(listsAssess);
					}
				}
				bean.setCommunity(getCommunity(userProfile.getCommunity()));
				bean.setMember(bean.getMember() + listCom.size());
				listBean.add(bean);
			}
		} else {
			// null or ''
			List<UserProfile> list = userProfileRepository.findByIsNotNullCommunity();
			for (UserProfile userProfile : list) {
				if (!listCommunity.contains(userProfile.getCommunity())) {
					listCommunity.add(userProfile.getCommunity());
				}
			}
		}
		for (String value : listCommunity) {
			bean = new ReportConclusionBean();
			System.out.println(value + " : "+ getCommunity(value));
			List<String> listCom = userProfileRepository.findByUserIdCommunity(value);
			List<String> listsAssess = new ArrayList<String>();
			List<Assessment> assessment = assessmentRepository.findByInUserId(listCom);
			System.out.println("assessment : " + assessment.size());
			for (Assessment valueAssess : assessment) {
				if (!listsAssess.contains(valueAssess.getInspetionDetail())) {
					listsAssess.add(valueAssess.getInspetionDetail());
//					bean.setInspectionId(listsAssess);
				}
			}
			bean.setCommunity(getCommunity(value));
			bean.setMember(bean.getMember() + listCom.size());
			listBean.add(bean);
		}
		
		return listBean;
	}
	
	
	// UPDATE
	public List<ReportConclusionBean> getReportConclusiontoUI(ArrayList<DataGoogleMapRespModel2> data) {
		// TODO Auto-generated method stub
		
		List<ReportConclusionBean> listBean = new ArrayList<ReportConclusionBean>();
		ReportConclusionBean setdata = new ReportConclusionBean();
		List<InspectionId> listinsp = new ArrayList<InspectionId>();
		InspectionId insp = new InspectionId();
		
		HashMap<String, HashMap<String, Integer>> setmap = new HashMap<String, HashMap<String,Integer>>();
		HashMap<String, Integer> setmap2 = new HashMap<String, Integer>();
		ArrayList<String> Community = new ArrayList<String>();
		try {
			for (DataGoogleMapRespModel2 ssdata : data) {
				if (setmap.containsKey(ssdata.getCommunity())) {
					setmap2 = new HashMap<String, Integer>();
					setmap2 = setmap.get(ssdata.getCommunity());
					if (setmap2.containsKey(ssdata.getInspectionsName())) {
						setmap2.put(ssdata.getInspectionsName(), setmap2.get(ssdata.getInspectionsName()) + 1);
					} else {
						setmap2.put(ssdata.getInspectionsName(), 1);
						setmap.put(ssdata.getCommunity(), setmap2);
					}
					
				} else {
					setmap2 = new HashMap<String, Integer>();
					setmap2.put(ssdata.getInspectionsName(), 1);
					setmap.put(ssdata.getCommunity(), setmap2);
				}
				
				if (!Community.contains(ssdata.getCommunity())) {
					Community.add(ssdata.getCommunity());
				}
			}
						
			if (setmap.size() > 0) {
				for (String key: setmap.keySet()) {
					setdata = new ReportConclusionBean();
					listinsp = new ArrayList<InspectionId>();
					setmap2 = new HashMap<String, Integer>();
					int member = 0;
					setmap2 = setmap.get(key);
					setdata.setCommunity(key);
					
					for(String key2: setmap2.keySet()){						
				    	insp = new InspectionId();
				    	insp.setName(key2);
				    	insp.setMember(setmap2.get(key2));
				    	member = member + setmap2.get(key2);
				    	listinsp.add(insp);
					}
					setdata.setMember(member);
					setdata.setInspectionId(listinsp);
					
					listBean.add(setdata);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBean;
	}
	
	public Object getDataMapUser(SearchReportReqModel2 searchReportReqModel) {
		DataGoogleMapRespModel resp = new DataGoogleMapRespModel();
		List<DataGoogleDetail> dataGoogleDetails = new ArrayList<>();
		DataGoogleDetail setdata = new DataGoogleDetail();
		List<Object[]> Bigdata = new ArrayList<>();
		Bigdata =  baseRepository.getDataMapUser(searchReportReqModel);
		for (Object[] obj : Bigdata) {
			 setdata = new DataGoogleDetail();
			if(String.valueOf(obj[0]) != null){
				setdata.setName(String.valueOf(obj[0]));
			}
			if(String.valueOf(obj[1]) != null){
				setdata.setAssessmentDetail(String.valueOf(obj[1]));
			}
			if(String.valueOf(obj[2]) != null){
				setdata.setStrdate(Timestamp.valueOf(String.valueOf(obj[2])));
			}
			if(String.valueOf(obj[3]) != null){
				setdata.setInspectionsName(String.valueOf(obj[3]));
			}
			if(String.valueOf(obj[4]) != null){
				setdata.setAssessmentId(String.valueOf(obj[4]));	
			}
			if(String.valueOf(obj[5]) != null){
				setdata.setCommunity(getCommunity(String.valueOf(obj[5])));
			}
			if(String.valueOf(obj[6]) != null){
				int markerKey = 1;
				List<Criterion> criterionS = new ArrayList<>();
				criterionS 	=  criterionRepository.findListCriterionByInspectionId(getLong(String.valueOf(obj[7])));	
				markerKey = mapGetCriterion(criterionS, getLong(String.valueOf(obj[6])));
				setdata.setLavel(String.valueOf(markerKey));
			}
			if(String.valueOf(obj[8]) != null){
				setdata.setUserId(String.valueOf(obj[8]));
			}
			dataGoogleDetails.add(setdata);
		}
		resp.setDataGoogleDetails(dataGoogleDetails);
		return resp;
	}
}

