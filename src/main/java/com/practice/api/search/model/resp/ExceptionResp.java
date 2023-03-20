package com.practice.api.search.model.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResp {
	private String code;
	private String message;
	
	public ExceptionResp(String code, String message){
		this.code = code;
		this.message = message;
	}
}
