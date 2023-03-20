package com.practice.api.search.model.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBlogReq {
	@NotBlank(message = "REQUIRED_SEARCH_WORD")
	private String searchWord;
	private String sort;
	@Max(value = 50, message = "SEARCH_BLOG_SIZE_OUT_OF_RANGE")
	@Min(value = 1, message = "SEARCH_BLOG_SIZE_OUT_OF_RANGE")
	private Integer size = 20;
	@Max(value = 50, message = "SEARCH_BLOG_PAGE_OUT_OF_RANGE")
	@Min(value = 1, message = "SEARCH_BLOG_PAGE_OUT_OF_RANGE")
	private Integer page = 1;
}
