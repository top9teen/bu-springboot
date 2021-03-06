package com.it.app.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.it.app.manager.RestManager;
import com.it.app.model.AssessmentReqModel;
import com.it.app.model.req.CriterionReqModel;
import com.it.app.model.req.InspectionReqModel;
import com.it.app.model.req.QuestionReqModel;
import com.it.app.model.req.SearchReportReqModel;
import com.it.app.service.AssessService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/assess")
public class AssessController  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private AssessService assessService;

	@GetMapping(value = { "/list-inspection" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object listInspection() {
		RestManager manager = new RestManager();
		manager.addResult(assessService.listInspection());
		return  manager.getResult();
	}
	
	@GetMapping(value = { "/get-inspection-by-id/{inspectionId}" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getInspectionById(@PathVariable("inspectionId") String inspectionId) {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getInspectionById(inspectionId));
		return  manager.getResult();
	}
	
	@PostMapping(value = "/save-inspection")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object saveInspection(@Valid @RequestBody InspectionReqModel inspectionReqModel,
			HttpServletRequest request) throws ParseException {
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
		manager.addResult(assessService.getInspectionById(inspectionId));
		return  manager.getResult();
	}
	
	@GetMapping(value = { "/get-question-by-id/{questionId}" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getQuestionByIdd(@PathVariable("questionId") String questionId) {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getQuestionById(questionId));
		return  manager.getResult();
	}
	
	@PostMapping(value = "/save-question")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object saveQuestion(@Valid @RequestBody QuestionReqModel questionReqModel,
			HttpServletRequest request) throws ParseException {
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
	public Object saveAssessment(@Valid @RequestBody AssessmentReqModel assessmentReqModel,
			HttpServletRequest request) throws ParseException {
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
	public Object saveCriterion(@Valid @RequestBody CriterionReqModel questionReqModel,
			HttpServletRequest request) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.saveCriterion(questionReqModel));
		return manager.getResult();
	}
	
	@GetMapping(value = "/get-assessment-by-userId/{userId}/{inspectionId}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getAssessmentByUserId(@PathVariable("userId") String userId,@PathVariable("inspectionId") String inspectionId) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getAssessmentByUserId(userId,inspectionId));
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
	public Object getAssessmentByInspectionId(@Valid @RequestBody SearchReportReqModel searchReportReqModel) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getAssessmentByInspectionId(searchReportReqModel));
		return manager.getResult();
	}
	
	@PostMapping(value = "/get-datamap")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getDataMapByInspectionId(@Valid @RequestBody SearchReportReqModel searchReportReqModel) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(assessService.getDataMapByInspectionId(searchReportReqModel));
		return manager.getResult();
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
		byte[] bytePdf= assessService.printReport(assessmentId);
		String name = "assessment.pdf";
	return ResponseEntity.ok()
			.contentType(MediaType.APPLICATION_PDF)
			.body(new InputStreamResource(new ByteArrayInputStream(bytePdf)));
	}
	
}
