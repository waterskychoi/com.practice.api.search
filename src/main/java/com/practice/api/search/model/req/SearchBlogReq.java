package com.practice.api.search.model.req;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBlogReq {
	@NotNull(message = "검색어를 입력해 주세요.")
	private String searchWork;
	private String sort;
	private Integer size;
	private Integer page;
}
