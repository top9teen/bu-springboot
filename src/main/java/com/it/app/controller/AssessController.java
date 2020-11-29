package com.it.app.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.app.entity.Assessment;
import com.it.app.entity.Question2Q;
import com.it.app.entity.Question8Q;
import com.it.app.entity.UserProfile;
import com.it.app.manager.RestManager;
import com.it.app.model.AssessmentModel;
import com.it.app.model.AssessmentReqModel;
import com.it.app.model.ReportConclusionBean;
import com.it.app.model.ReturnAssessStatus;
import com.it.app.model.req.CriterionReqModel;
import com.it.app.model.req.DataGoogleDetailsModel;
import com.it.app.model.req.DataGoogleDetailsModelStr;
import com.it.app.model.req.InspectionReqModel;
import com.it.app.model.req.QuestionReqModel;
import com.it.app.model.req.SearchReportReqModel;
import com.it.app.model.req.SearchReportReqModel2;
import com.it.app.model.resp.DataGoogleMapRespModel2;
import com.it.app.repository.Question2QRepository;
import com.it.app.repository.Question8QRepository;
import com.it.app.repository.UserProfileRepository;
import com.it.app.service.AssessService;
import com.it.app.service.QuestionService;
import com.it.app.service.ReportComplete;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.JRXlsExporter;

@RestController
@RequestMapping("/assess")
public class AssessController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AssessService assessService;
	@Autowired
	UserProfileRepository userProfileRepo;
	@Autowired
	private Question2QRepository question2QRepo;
	@Autowired
	private Question8QRepository question8QRepo;
	@Autowired
	QuestionService questionService;

	@GetMapping(value = { "/list-inspection" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object listInspection() {
		RestManager manager = new RestManager();
		manager.addResult(assessService.listInspection());
		return manager.getResult();

	}

	@GetMapping(value = { "/get-inspection-by-id/{inspectionId}/{Q}" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getInspectionById(@PathVariable("inspectionId") String inspectionId, @PathVariable("Q") Integer Q) {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getInspectionById(inspectionId, Q));
		return manager.getResult();
	}

	@PostMapping(value = "/save-inspection")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object saveInspection(@Valid @RequestBody InspectionReqModel inspectionReqModel, HttpServletRequest request)
			throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.saveInspection(inspectionReqModel));
		return manager.getResult();
	}

	@GetMapping(value = "/delete-inspection/{inspectionId}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object deleteInspectionById(@PathVariable("inspectionId") String inspectionId) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.deleteInspection(inspectionId));
		return manager.getResult();
	}

	@GetMapping(value = { "/list-question-by-inspection-id/{inspectionId}" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object listQuestionByInspectionId(@PathVariable("inspectionId") String inspectionId) {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getInspectionById(inspectionId, 0));
		return manager.getResult();
	}

	@GetMapping(value = { "/get-question-by-id/{questionId}" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getQuestionByIdd(@PathVariable("questionId") String questionId) {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getQuestionById(questionId));
		return manager.getResult();
	}

	@PostMapping(value = "/save-question")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object saveQuestion(@Valid @RequestBody QuestionReqModel questionReqModel, HttpServletRequest request)
			throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.saveQuestion(questionReqModel));
		return manager.getResult();
	}

	@GetMapping(value = "/delete-question/{questionId}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object deleteQuestionById(@PathVariable("questionId") String inspectionId) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.deleteQuestionById(inspectionId));
		return manager.getResult();
	}

	@PostMapping(value = "/save-assessment")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object saveAssessment(@Valid @RequestBody AssessmentReqModel assessmentReqModel, HttpServletRequest request)
			throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.saveAssessment(assessmentReqModel));
		return manager.getResult();
	}

	@GetMapping(value = "/get-criterion-by-inspectionId/{inspectionId}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getCriterionByInspectionId(@PathVariable("inspectionId") String inspectionId) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getCriterionByInspectionId(inspectionId));
		return manager.getResult();
	}

	@PostMapping(value = "/save-criterion")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object saveCriterion(@Valid @RequestBody CriterionReqModel questionReqModel, HttpServletRequest request)
			throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.saveCriterion(questionReqModel));
		return manager.getResult();
	}

	@GetMapping(value = "/get-assessment-by-userId/{userId}/{inspectionId}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getAssessmentByUserId(@PathVariable("userId") String userId,
			@PathVariable("inspectionId") String inspectionId) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getAssessmentByUserId(userId, inspectionId));
		return manager.getResult();
	}

//	@GetMapping(value = "/get-assessment-by-inspectionId/{inspectionId}")
//	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
//	public Object getAssessmentByInspectionId(@PathVariable("inspectionId") String inspectionId) throws ParseException {
//		RestManager manager = new RestManager();
//		manager.addResult(assessService.getAssessmentByInspectionId(inspectionId));
//		return manager.getResult();
//	}
//	
//	@GetMapping(value = "/get-datamap-by-inspectionId/{inspectionId}")
//	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
//	public Object getDataMapByInspectionId(@PathVariable("inspectionId") String inspectionId) throws ParseException {
//		RestManager manager = new RestManager();
//		manager.addResult(assessService.getDataMapByInspectionId(inspectionId));
//		return manager.getResult();
//	}

	@PostMapping(value = "/get-assessment")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getAssessmentByInspectionId(@Valid @RequestBody SearchReportReqModel searchReportReqModel)
			throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getAssessmentByInspectionId(searchReportReqModel));
		return manager.getResult();
	}

	@PostMapping(value = "/get-datamap")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getDataMapByInspectionId(@Valid @RequestBody SearchReportReqModel searchReportReqModel)
			throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getDataMapByInspectionId(searchReportReqModel));
		return manager.getResult();
	}

	@PostMapping(value = "/get-report-conclusion")
	public List<ReportConclusionBean> getReportConclusion(@RequestBody ArrayList<DataGoogleMapRespModel2> data) {
		return assessService.getReportConclusiontoUI(data);
	}
//	@GetMapping(value = "/print-report/{assessmentId}")
//	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
//	public Object printReport(@PathVariable("assessmentId") String assessmentId) throws ParseException {
//		byte[] bytePdf= assessService.printReport(assessmentId);
//		String name = "assessment.pdf";
//	return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
//			.header("Content-Disposition", "attachment; filename=" +name)
//			.body(new InputStreamResource(new ByteArrayInputStream(bytePdf)));
//	}

//	@GetMapping(value = "/print-report/{assessmentId}")
//	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
//	public Object printReport(@PathVariable("assessmentId") String assessmentId) throws ParseException {
//		RestManager manager = new RestManager();
//		manager.addResult(assessService.printReport(assessmentId));
//		return manager.getResult();
//	}

	@GetMapping(value = "/print-report/{assessmentId}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object printReport(@PathVariable("assessmentId") String assessmentId) throws ParseException {
		byte[] bytePdf = assessService.printReport(assessmentId);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(new ByteArrayInputStream(bytePdf)));
	}

	@PostMapping(value = "/get-datamapUser")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getDataMapUser(@Valid @RequestBody SearchReportReqModel2 searchReportReqModel) {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getDataMapUser(searchReportReqModel));
		return manager.getResult();
	}

	@Autowired
	ReportComplete reportComplete;

	JRXlsExporter exporter = new JRXlsExporter();
	HashMap<String, List<DataGoogleDetailsModelStr>> Datamap = new HashMap<>();

	@PostMapping(value = "/completeReport/GenexportData/{userID}", consumes = "application/json", produces = "application/json")
	public String GenexportExcel(@PathVariable("userID") String userID, @RequestBody List<DataGoogleDetailsModel> bean)
			throws JRException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		List<DataGoogleDetailsModelStr> listJson = new ArrayList<>();
		DataGoogleDetailsModelStr dataMock = new DataGoogleDetailsModelStr();
		for (DataGoogleDetailsModel data : bean) {

			/****************************************************************
			 * GET PROFILE USER
			 ****************************************************************/
			UserProfile pro = new UserProfile();
			pro = userProfileRepo.findOneByUserIdLimitOne(getLong(data.getUserId()));
			/****************************************************************
			 * GET PROFILE USER
			 ****************************************************************/
			dataMock = new DataGoogleDetailsModelStr();
			dataMock.setAssessmentDetail((data.getAssessmentDetail() != null && data.getAssessmentDetail() != "")
					? data.getAssessmentDetail()
					: "-");
			dataMock.setCommunity(
					(data.getCommunity() != null && data.getCommunity() != "") ? data.getCommunity() : "-");
			dataMock.setInspectionsName(
					(data.getInspectionsName() != null && data.getInspectionsName() != "") ? data.getInspectionsName()
							: "-");
			dataMock.setLavel((data.getLavel() != null && data.getLavel() != "") ? data.getLavel() : "-");
			dataMock.setName((data.getName() != null && data.getName() != "") ? data.getName() : "-");
			dataMock.setStrdate(
					(formatter.format(data.getStrdate()) != null && formatter.format(data.getStrdate()) != "")
							? formatter.format(data.getStrdate())
							: "-");
			if (pro != null) {
				dataMock.setPhoneNo((pro.getPhoneNo() != null && pro.getPhoneNo() != "") ? pro.getPhoneNo() : "-");
				dataMock.setAddress((pro.getAddress() != null && pro.getAddress() != "") ? pro.getAddress() : "-");
				dataMock.setCardId((pro.getCardId() != null && pro.getCardId() != "") ? pro.getCardId() : "-");
			} else {
				dataMock.setPhoneNo("-");
				dataMock.setAddress("-");
				dataMock.setCardId("-");
			}
			listJson.add(dataMock);
		}
		Datamap.put(userID, listJson);
		return "S";
	}

	@GetMapping(value = "/completeReport/exportExcel/{userID}/{name}")
	public void exportExcel(HttpServletResponse response, @PathVariable("userID") String userID,
			@PathVariable("name") String name) throws JRException, IOException {
		exporter = new JRXlsExporter();
		List<DataGoogleDetailsModelStr> listJson = new ArrayList<>();
		listJson = Datamap.get(userID);
		exporter = reportComplete.exportExcel(response, listJson);
		exporter.exportReport();
		Datamap.remove(userID);
	}

	@GetMapping(value = "/completeReport/exportPDF/{userID}/{name}")
	public void exportPDF(HttpServletResponse response, @PathVariable("userID") String userID,
			@PathVariable("name") String name) throws JRException, IOException {
		exporter = new JRXlsExporter();
		List<DataGoogleDetailsModelStr> listJson = new ArrayList<>();
		listJson = Datamap.get(userID);
		reportComplete.exportPDF(response, listJson, name);
		Datamap.remove(userID);
	}

	private Long getLong(String str) {
		Long result = null;
		if (!"null".equals(str) && null != str && StringUtils.isNotBlank(str)) {
			result = Long.parseLong(str);
		}
		return result;
	}

	@GetMapping(value = "/getQuestion2Q/{inspectionId}")
	public List<Question2Q> getQuestion2Q(@PathVariable("inspectionId") String inspectionId) {
		List<Question2Q> result = new ArrayList<Question2Q>();
		try {
			result = question2QRepo.findByInspectionId(inspectionId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@GetMapping(value = "/getQuestion8Q/{inspectionId}")
	public List<Question8Q> getQuestion8Q(@PathVariable("inspectionId") String inspectionId) {
		List<Question8Q> result = new ArrayList<Question8Q>();
		try {
			result = question8QRepo.findByInspectionId(inspectionId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@PostMapping(value = "/save-assessment2Q")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public ReturnAssessStatus saveAssessment2Q(@Valid @RequestBody List<AssessmentModel> assessmentModel,
			HttpServletRequest request) {
			ReturnAssessStatus result = new ReturnAssessStatus();
			try {
				result = questionService.resultsInterpretation(assessmentModel);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return result;
	}
	
	@PostMapping(value = "/save-assessment9Q")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public ReturnAssessStatus saveAssessment9Q(@Valid @RequestBody List<AssessmentModel> assessmentModel,
			HttpServletRequest request) {
		ReturnAssessStatus result = new ReturnAssessStatus();
		try {
			result = questionService.resultsAssessment9Q(assessmentModel);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
