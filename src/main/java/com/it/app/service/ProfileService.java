package com.it.app.service;

import java.util.ArrayList;
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
import com.it.app.entity.Inspection;
import com.it.app.entity.MenuMain;
import com.it.app.entity.MenuSub;
import com.it.app.entity.UserAcount;
import com.it.app.entity.UserProfile;
import com.it.app.model.MenuMainModel;
import com.it.app.model.MenuSubModel;
import com.it.app.model.SumInspectionModel;
import com.it.app.model.req.LoginReqModel;
import com.it.app.model.req.ProfileReqModel;
import com.it.app.model.resp.ChangePasswordReqModel;
import com.it.app.model.resp.DataLoginRespModel;
import com.it.app.model.resp.ProfileRespModel;
import com.it.app.model.resp.ViewDataHomeRespModel;
import com.it.app.repository.AssessmentRepository;
import com.it.app.repository.BaseRepository;
import com.it.app.repository.InspectionRepository;
import com.it.app.repository.MenuMainRepository;
import com.it.app.repository.MenuSubRepository;
import com.it.app.repository.UserAcountRepository;
import com.it.app.repository.UserProfileRepository;

@Service
@EnableAsync
@Transactional
public class ProfileService {

	@Autowired
	private UserAcountRepository userAcountRepository;

	@Autowired
	private MenuMainRepository menuMainRepository;

	@Autowired
	private MenuSubRepository menuSubRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private InspectionRepository inspectionRepository;

	private BaseRepository baseRepository;
	
	@Autowired
	private AssessmentRepository assessmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Object reLoginService(String userId) {
		Optional<UserAcount> opUserAcount = userAcountRepository.findOneByUserId(getLong(userId));
		UserAcount userAcount = opUserAcount.get();
		LoginReqModel loginReqModel = new LoginReqModel();
		loginReqModel.setUserName(userAcount.getUsername());
		loginReqModel.setPassWord(userAcount.getPassword());
		return loginService(loginReqModel);
	}

	public Object loginService(LoginReqModel loginReqModel) {
		Optional<UserAcount> opUserAcount = userAcountRepository
				.findOneByUserNameAndPassWord(loginReqModel.getUserName(), loginReqModel.getPassWord());
		if (opUserAcount.isPresent()) {
			DataLoginRespModel dataLoginRespModel = new DataLoginRespModel();
			UserAcount userAcount = opUserAcount.get();
			dataLoginRespModel.setUserId(userAcount.getUserId());
			dataLoginRespModel.setUserName(userAcount.getUsername());
			dataLoginRespModel.setPassWord(userAcount.getPassword());
			dataLoginRespModel.setRole(userAcount.getRole());
			// setUserProfile
			Optional<UserProfile> opUserProfele = userProfileRepository.findOneByUserId(userAcount.getUserId());
			if (opUserProfele.isPresent()) {
				UserProfile userProfile = opUserProfele.get();
				dataLoginRespModel.setTitleName(String.valueOf(userProfile.getTitleName()));
				dataLoginRespModel.setFertName(userProfile.getFertName());
				dataLoginRespModel.setLastName(userProfile.getLastName());
				dataLoginRespModel.setImg(userProfile.getImg());
			}
			// set Main menu
			List<MenuMain> menuMains = menuMainRepository.findAllByRoue(userAcount.getRole());
			List<MenuMainModel> menuMainModels = new ArrayList<>();
			if (!menuMains.isEmpty()) {
				MenuMainModel menuMainModel = new MenuMainModel();
				for (MenuMain menuM : menuMains) {
					if (0 < menuM.getMenuId() || menuM.getMenuId() != null) {
						menuMainModel = modelMapper.map(menuM, MenuMainModel.class);
						// set sub menu
						List<MenuSub> menuSubs = menuSubRepository.findAllByMenuId(menuM.getMenuId());
						List<MenuSubModel> menuSubModels = new ArrayList<>();
						MenuSubModel menuSubModel = null;
						for (MenuSub menuSub : menuSubs) {
							menuSubModel = modelMapper.map(menuSub, MenuSubModel.class);
							menuSubModels.add(menuSubModel);
						}
						menuMainModel.setMenuSubs(menuSubModels);
					}
					menuMainModels.add(menuMainModel);
				}
			}

			dataLoginRespModel.setMenuMains(menuMainModels);
			return dataLoginRespModel;
		} else {
			return null;
		}
	}

	public Object saveProfile(ProfileReqModel profileReqModel) {
		UserAcount userAcount = null;
		UserProfile userProfile = null;
		Map<String, Object> result = new HashMap<>();
		if (null != profileReqModel.getUserId() && StringUtils.isNotBlank(profileReqModel.getUserId())) {
			Optional<UserAcount> opUserAcount = userAcountRepository
					.findOneByUserId(getLong(profileReqModel.getUserId()));
			if (opUserAcount.isPresent()) {
				userAcount = opUserAcount.get();
				Optional<UserProfile> opUserProfile = userProfileRepository
						.findOneByUserId(userAcount.getUserId());
				userProfile = opUserProfile.get();
			} else {
				userAcount = new UserAcount();
				userProfile = new UserProfile();
			}
		} else {
			userAcount = new UserAcount();
			userProfile = new UserProfile();
		}
		userAcount.setUsername(profileReqModel.getUserName());
		userAcount.setPassword(profileReqModel.getPassWord());
		userAcount.setRole(profileReqModel.getRole());
		userAcount.setStatus("A");

		userAcountRepository.save(userAcount);

		userProfile.setUserId(userAcount.getUserId());
		userProfile.setCardId(profileReqModel.getCardId());
		userProfile.setTitleName(getInt(profileReqModel.getTitleName()));
		userProfile.setFertName(profileReqModel.getFertName());
		userProfile.setLastName(profileReqModel.getLastName());
		userProfile.setAge(profileReqModel.getAge());
		userProfile.setPhoneNo(profileReqModel.getPhoneNo());
		userProfile.setImg(profileReqModel.getImg());
		userProfile.setAddress(profileReqModel.getHouseNo());
		userProfile.setCommunity(profileReqModel.getCommunity());
		userProfile.setLat(profileReqModel.getLat());
		userProfile.setLng(profileReqModel.getLng());
		userProfileRepository.save(userProfile);

		result.put("status", MasterConstant.status.success);

		return result;
	}

	public List<ProfileRespModel> getProfileListByRole(String role) {
		List<UserAcount> acounts = userAcountRepository.findAllByRole(role);
		List<ProfileRespModel> profileRespModels = new ArrayList<>();
		for (UserAcount acount : acounts) {
			ProfileRespModel respModel = new ProfileRespModel();
			respModel.setUserId(String.valueOf(acount.getUserId()));
			respModel.setUserName(acount.getUsername());
			respModel.setRole(getRoleName(acount.getRole()));
			
			Optional<UserProfile> opUserProfile = userProfileRepository.findOneByUserId(acount.getUserId());
			if (opUserProfile.isPresent()) {
				UserProfile userProfile = opUserProfile.get();
				respModel.setFullName(getTitleName(userProfile.getTitleName()) + userProfile.getFertName() + " "
						+ userProfile.getLastName());
				respModel.setCommunity(getCommunity(userProfile.getCommunity()));
			}
			profileRespModels.add(respModel);
		}

		return profileRespModels;
	}

	public Object getProfileByUserId(String userId) {
		ProfileRespModel respModel = new ProfileRespModel();
		// if (userId.equals("null")) {
		// respModel.setUserId("");
		// respModel.setUserName("");
		// respModel.setRole("");
		// respModel.setCardId("");
		// respModel.setTitleName("");
		// respModel.setFertName("");
		// respModel.setLastName("");
		// respModel.setAge("");
		// respModel.setPhoneNo("");
		// respModel.setSex("");
		//
		// respModel.setDistrict("");
		// respModel.setHouseNo("");
		// respModel.setPostal("");
		// respModel.setProvince("");
		// respModel.setSubdistrict("");
		// }else {
		Optional<UserAcount> opUserAcount = userAcountRepository.findOneByUserId(getLong(userId));
		if (opUserAcount.isPresent()) {
			UserAcount acount = opUserAcount.get();
			respModel.setUserId(String.valueOf(acount.getUserId()));
			respModel.setUserName(acount.getUsername());
			respModel.setPassWord(acount.getPassword());
			respModel.setRole(acount.getRole());

			Optional<UserProfile> opUserProfile = userProfileRepository.findOneByUserId(acount.getUserId());
			if (opUserProfile.isPresent()) {
				UserProfile userProfile = opUserProfile.get();
				respModel.setCardId(userProfile.getCardId());
				respModel.setTitleName(userProfile.getTitleName().toString());
				respModel.setFertName(userProfile.getFertName());
				respModel.setLastName(userProfile.getLastName());
				respModel.setAge(userProfile.getAge());
				respModel.setPhoneNo(userProfile.getPhoneNo());
				respModel.setImg(userProfile.getImg());
				respModel.setCommunity(userProfile.getCommunity());
				respModel.setHouseNo(userProfile.getAddress());
				respModel.setLat(userProfile.getLat());
				respModel.setLng(userProfile.getLng());
	
			}
		}

		return respModel;
	}
	
	public Object deleteProfile(String userId) {
		Optional<UserAcount> opUserAcount =  userAcountRepository.findOneByUserId(getLong(userId));
		if(opUserAcount.isPresent()) {
			UserAcount userAcount = opUserAcount.get();
			userAcount.setStatus(MasterConstant.status.inActive);
			userAcountRepository.save(userAcount);
			return MasterConstant.status.success;
		}else {
			return MasterConstant.status.error;
		}
			
	}
	
	public Object changePassword(ChangePasswordReqModel changePasswordReqModel) {
		Optional<UserAcount> opUserAcount =  userAcountRepository.findOneByUserId(getLong(changePasswordReqModel.getUserId()));
		if(opUserAcount.isPresent()) {
			UserAcount userAcount = opUserAcount.get();
			userAcount.setPassword(changePasswordReqModel.getPassWord());
			userAcountRepository.save(userAcount);
			return MasterConstant.status.success;
		}else {
			return MasterConstant.status.error;
		}
	}
	
	public Object viewDataHome() {
		
		ViewDataHomeRespModel resp = new ViewDataHomeRespModel();
		List<SumInspectionModel> inspectionModels =new ArrayList<>();
		List<Inspection> inspections =  inspectionRepository.findAllInspection();
		for(Inspection inspection :inspections) {
			List<Assessment> listAssessment =assessmentRepository.findListAssessmentByInspectionId(inspection.getInspectionId());
			SumInspectionModel sumInspectionModel = new SumInspectionModel();
			sumInspectionModel.setName(inspection.getInspectionName());
			sumInspectionModel.setCount(String.valueOf(listAssessment.size()));
			inspectionModels.add(sumInspectionModel);
		}
		resp.setInspectionModels(inspectionModels);
		List<UserAcount>userAcounts = userAcountRepository.findAllByRole(MasterConstant.role.user);
		resp.setCountUserTotal(String.valueOf(userAcounts.size()));
		
		return resp;
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

	private String getRoleName(String role) {
		String result = "";
		switch (role) {
		case "1":
			result = MasterConstant.role.adminTh;
			break;
		case "2":
			result = MasterConstant.role.userTh;
			break;
		case "3":
			result = MasterConstant.role.empTh;
			break;
		}
		return result;
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
}
