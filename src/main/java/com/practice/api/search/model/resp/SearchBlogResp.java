package com.practice.api.search.model.resp;

import java.util.List;

import com.practice.api.search.model.resp.kakao.V2SearchBlogResp;

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
	
	public SearchBlogResp(V2SearchBlogResp v2SearchBlogResp){
		this.documents = v2SearchBlogResp.getDocuments().stream()
						.map((x) -> {
							return new BlogRespDocuments(x);
						}).toList();
		
		this.totalCount = v2SearchBlogResp.getMeta().getTotalCount();
		this.isEnd = v2SearchBlogResp.getMeta().getIsEnd();
	};
}
