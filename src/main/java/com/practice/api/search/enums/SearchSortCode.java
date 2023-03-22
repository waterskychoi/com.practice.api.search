package com.practice.api.search.enums;

public enum SearchSortCode {

	ACCURACY("accuracy", "정화도순"), //Default
    RECENCY("recency", "최신순")
    ;

    private String code;
    private String name;

    SearchSortCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static SearchSortCode getCodeByCodeValue(String code) {
    	SearchSortCode[] items = SearchSortCode.values();
        for (SearchSortCode item : items) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return SearchSortCode.ACCURACY;
    }
}
