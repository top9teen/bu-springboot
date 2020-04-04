package com.it.app.model;

public class ApiResponseModel {
	private static final long serialVersionUID = 2080798628377525336L;

	private Object data;

	public ApiResponseModel(Object resp) {
		this.data = resp;
	}

	public Object getData() {
		return data;
	}
}
