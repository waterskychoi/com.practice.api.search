package com.practice.api.search.enums;

public enum ExceptionCode {

	INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR"),
	INVALID_REQUEST("INVALID_REQUEST","INVALID_REQUEST"),
	MESSAGE_NOT_READABLE("MESSAGE_NOT_READABLE", "MESSAGE_NOT_READABLE"),

    ;

    private String code;
    private String name;

    ExceptionCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
