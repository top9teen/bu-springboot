package com.it.app.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.app.entity.UserProfile;
import com.it.app.manager.RestManager;
import com.it.app.model.req.LoginReqModel;
import com.it.app.model.req.ProfileReqModel;
import com.it.app.model.resp.ChangePasswordReqModel;
import com.it.app.model.resp.ProfileRespModel;
import com.it.app.service.ProfileService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/profile")
public class ProfileController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProfileService ProfileController;
	
	@GetMapping(value = { "/reLogin/{userId}" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object reLogin(@PathVariable("userId") String userId) {

		RestManager manager = new RestManager();
		manager.addResult(ProfileController.reLoginService(userId));
		
		return  manager.getResult();
	}
	
	@PostMapping(value = "/login")
/*	@RequestMapping(produces = {"application/json" })*/
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object login(@Valid @RequestBody LoginReqModel loginReqModel,
			HttpServletRequest request) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(ProfileController.loginService(loginReqModel));
		return manager.getResult();
	}
	
	@PostMapping(value = "/save-profile")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object saveProfile(@Valid @RequestBody ProfileReqModel profileReqModel,
			HttpServletRequest request) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(ProfileController.saveProfile(profileReqModel));
		return manager.getResult();
	}
	
	@GetMapping(value = "/delete-profile/{userId}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object deleteProfile(@PathVariable("userId") String userId) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(ProfileController.deleteProfile(userId));
		return manager.getResult();
	}
	
	@GetMapping(value = "/get-profile-by-userId/{userId}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object getProfileByUserId(@PathVariable("userId") String userId) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(ProfileController.getProfileByUserId(userId));
		return manager.getResult();
	}
	
	@GetMapping(value = "/get-profile-list-by-role/{role}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public List<ProfileRespModel> getProfileListByRole(@PathVariable("role") String role) throws ParseException {
		return ProfileController.getProfileListByRole(role);
	}
	
	
	@PostMapping(value = "/change-password")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object changePassword(@Valid @RequestBody ChangePasswordReqModel  changePasswordReqModel,
			HttpServletRequest request) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(ProfileController.changePassword(changePasswordReqModel));
		return manager.getResult();
	}
	
	@GetMapping(value = "/view-data-home")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Object viewDataHomeAdmin(HttpServletRequest request) throws ParseException {
		RestManager manager = new RestManager();
		manager.addResult(ProfileController.viewDataHome());
		return manager.getResult();
	}
}
