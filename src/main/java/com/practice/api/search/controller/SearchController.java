package com.practice.api.search.controller;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.api.search.transfer.kakao.KakaoTransfer;

import jakarta.validation.Valid;

import com.practice.api.search.enums.KakaoSortCode;
import com.practice.api.search.model.req.*;
import com.practice.api.search.model.req.kakao.*;
import com.practice.api.search.model.resp.SearchBlogResp;
import com.practice.api.search.model.resp.SearchWordRank;
import com.practice.api.search.model.resp.kakao.*;
import com.practice.api.search.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

	private final SearchService searchService;
	private final KakaoTransfer kakaoTransfer;

	@Autowired
	SearchController(
			SearchService searchService
			, KakaoTransfer kakaoTransfer){
		this.searchService = searchService;
		this.kakaoTransfer = kakaoTransfer;
	}
	
	@GetMapping("/test")
	public String test() {
		return "JUST TEST";
	}
	
	@GetMapping("/blog")
	public SearchBlogResp getBlog(
			@RequestBody @Valid SearchBlogReq req) throws Exception {
		var reqKakaoSortCode = Optional.ofNullable(KakaoSortCode.getCodeByCodeValue(req.getSort())).orElse(KakaoSortCode.ACCURACY);
		var v2SearchBlogReq = new V2SearchBlogReq();
		v2SearchBlogReq.setQuery(req.getSearchWord());
		v2SearchBlogReq.setSort(reqKakaoSortCode);
		v2SearchBlogReq.setPage(req.getPage());
		v2SearchBlogReq.setSize(req.getSize());
		var v2SearchBlogResp = kakaoTransfer.getV2SearchBlog(v2SearchBlogReq);
		
		var returnValue = new SearchBlogResp(v2SearchBlogResp);
		returnValue.setPage(req.getPage());
		returnValue.setSize(req.getSize());
		returnValue.setSort(reqKakaoSortCode.getCode());
		
		searchService.saveSearchWordLog(req.getSearchWord());
		return returnValue;
	}
	
	@GetMapping("/rank")
	public List<SearchWordRank> getSearchWorkRank() throws Exception {
		return searchService.selectSearchWordRank();
	}
}
