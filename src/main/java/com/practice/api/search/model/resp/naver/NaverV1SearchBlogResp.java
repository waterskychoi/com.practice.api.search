package com.practice.api.search.model.resp.naver;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverV1SearchBlogResp {
	private String lastBuildDate;
	private Integer total;
	private Integer start;
	private Integer display;
	private List<NaverSearchBlogRespItem> items;
}
