package com.practice.api.search.enums;

public enum KakaoSortCode {

	ACCURACY("accuracy", "정화도순"), //Default
    RECENCY("recency", "최신순")
    ;

    private String code;
    private String name;

    KakaoSortCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static KakaoSortCode getCodeByCodeValue(String code) {
    	KakaoSortCode[] items = KakaoSortCode.values();
        for (KakaoSortCode item : items) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }
}
