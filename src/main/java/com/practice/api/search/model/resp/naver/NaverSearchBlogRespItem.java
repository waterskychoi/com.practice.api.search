package com.practice.api.search.model.resp.naver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverSearchBlogRespItem {
	private String title;
	private String link;
	private String description;
	private String bloggername;
	private String bloggerlink;
	private String postdate;
}
