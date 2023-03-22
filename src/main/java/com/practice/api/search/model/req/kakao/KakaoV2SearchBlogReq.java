package com.practice.api.search.model.req.kakao;

import com.practice.api.search.enums.KakaoSortCode;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoV2SearchBlogReq {
	@NotBlank
	private String query;
	private KakaoSortCode sort;
	@Max(50)
	@Min(1)
	private Integer page;
	@Max(50)
	@Min(1)
	private Integer size;
}
