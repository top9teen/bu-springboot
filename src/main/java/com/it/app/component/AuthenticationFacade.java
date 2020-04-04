package com.it.app.component;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements Serializable{
	private static final long serialVersionUID = 1590403495252059713L;
/*	
	@Autowired
	private ResourceServerTokenServices tokenServices;*/

/*	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	*/

	/*public DataLoginModel getDataLogin() {

		DataLoginModel dataLoginModel = null;
		Authentication userAuthentication = getAuthentication();
		if (!ObjectUtils.isEmpty(userAuthentication.getDetails())
				&& (userAuthentication.getDetails() instanceof OAuth2AuthenticationDetails)) {
			OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) userAuthentication
					.getDetails();
			String jwtToken = oAuth2AuthenticationDetails.getTokenValue();
			if (!ObjectUtils.isEmpty(jwtToken)) {
				Map<String, Object> details = tokenServices.readAccessToken(oAuth2AuthenticationDetails.getTokenValue())
						.getAdditionalInformation();
				dataLoginModel = new DataLoginModel(details);
			}
		}
		return dataLoginModel;

	}

	public String getToken() {
		return (OAuth2AuthenticationDetails.class.cast(SecurityContextHolder.getContext().getAuthentication().getDetails())).getTokenValue();
	}

	public class DataLoginModel {
		private static final String OAUTH_SESSION_ID = "session_id";
		private static final String USER_ID = "01";
	
				
		private String sessionId;
		private String user_id;
		

		DataLoginModel(Map<String, Object> details) {
			this.sessionId = ObjectUtils.nullSafeToString(details.get(OAUTH_SESSION_ID));
			this.user_id = ObjectUtils.nullSafeToString(details.get(USER_ID));
		}

		public String getSessionId() {
			return sessionId;
		}

		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}



	
	}*/
}
