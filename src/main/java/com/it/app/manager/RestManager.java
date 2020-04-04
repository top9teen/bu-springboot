package com.it.app.manager;

import com.it.app.model.ApiResponseModel;

public class RestManager {
	private Object resultData;
	
	public void addResult(Object data) {
		this.resultData = new ApiResponseModel(data);
	}
	public Object getResult() {
		return resultData;
	}

}
