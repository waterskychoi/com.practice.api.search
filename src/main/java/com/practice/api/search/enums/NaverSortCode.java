package com.practice.api.search.enums;

public enum NaverSortCode {

	SIM("sim", "정화도순", "accuracy"), //Default
    DATE("date", "날짜순", "recency")
    ;

    private String code;
    private String name;
    private String searchSortCode;

    NaverSortCode(String code, String name, String searchSortCode) {
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

    public static NaverSortCode getCodeByCodeValue(String code) {
    	NaverSortCode[] items = NaverSortCode.values();
        for (NaverSortCode item : items) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return NaverSortCode.SIM;
    }

    public static NaverSortCode getCodeBySearchSortCode(String searchSortCode) {
    	NaverSortCode[] items = NaverSortCode.values();
        for (NaverSortCode item : items) {
            if (item.getSearchSortCode().equalsIgnoreCase(searchSortCode)) {
                return item;
            }
        }
        return NaverSortCode.SIM;
    }
}
