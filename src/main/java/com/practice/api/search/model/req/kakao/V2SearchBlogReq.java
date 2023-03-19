package com.practice.api.search.model.req.kakao;

import com.practice.api.search.enums.KakaoSortCode;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class V2SearchBlogReq {
	@NotNull
	private String query;
	private KakaoSortCode sort;
	private Integer page;
	private Integer size;
}
