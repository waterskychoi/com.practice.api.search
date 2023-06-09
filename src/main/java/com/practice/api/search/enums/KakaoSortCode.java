package com.practice.api.search.enums;

public enum KakaoSortCode {

	ACCURACY("accuracy", "정화도순", "accuracy"), //Default
    RECENCY("recency", "최신순", "recency")
    ;

    private String code;
    private String name;
    private String searchSortCode;

    KakaoSortCode(String code, String name, String searchSortCode) {
        this.code = code;
        this.name = name;
        this.searchSortCode = searchSortCode;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getSearchSortCode() {
        return this.searchSortCode;
    }

    public static KakaoSortCode getCodeByCodeValue(String code) {
    	KakaoSortCode[] items = KakaoSortCode.values();
        for (KakaoSortCode item : items) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return KakaoSortCode.ACCURACY;
    }

    public static KakaoSortCode getCodeBySearchSortCode(String searchSortCode) {
    	KakaoSortCode[] items = KakaoSortCode.values();
        for (KakaoSortCode item : items) {
            if (item.getSearchSortCode().equalsIgnoreCase(searchSortCode)) {
                return item;
            }
        }
        return KakaoSortCode.ACCURACY;
    }
}
