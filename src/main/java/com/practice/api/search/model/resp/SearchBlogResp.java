package com.practice.api.search.model.resp;

import java.util.List;

import com.practice.api.search.model.resp.kakao.KakaoV2SearchBlogResp;
import com.practice.api.search.model.resp.naver.NaverV1SearchBlogResp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBlogResp {
	private Integer totalCount;
	private Boolean isEnd;
	private String sort;
	private Integer size;
	private Integer page;

	private List<BlogRespDocuments> documents;

	public SearchBlogResp(){};

	public SearchBlogResp(KakaoV2SearchBlogResp kakaoV2SearchBlogResp){
		this.documents = kakaoV2SearchBlogResp.getDocuments().stream()
						.map((x) -> {
							return new BlogRespDocuments(x);
						}).toList();

		this.totalCount = kakaoV2SearchBlogResp.getMeta().getTotalCount();
		this.isEnd = kakaoV2SearchBlogResp.getMeta().getIsEnd();
	};

	public SearchBlogResp(NaverV1SearchBlogResp naverV1SearchBlogResp){
		this.documents = naverV1SearchBlogResp.getItems().stream()
						.map((x) -> {
							return new BlogRespDocuments(x);
						}).toList();

		this.totalCount = naverV1SearchBlogResp.getTotal();
		//this.isEnd = naverV1SearchBlogResp.getMeta().getIsEnd();
	};
}
