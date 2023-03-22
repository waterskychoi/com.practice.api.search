package com.practice.api.search.model.req.naver;

import com.practice.api.search.enums.NaverSortCode;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverV1SearchBlogReq {
	@NotBlank
	private String query;
	private NaverSortCode sort;
	@Max(100)
	@Min(1)
	private Integer display;
	@Max(1000)
	@Min(1)
	private Integer start;
}
