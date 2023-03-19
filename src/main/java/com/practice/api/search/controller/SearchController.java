package com.practice.api.search.controller;
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
import com.practice.api.search.model.resp.kakao.*;

@RestController
@RequestMapping("/search")
public class SearchController {
	
	private final KakaoTransfer kakaoTransfer;

	@Autowired
	SearchController(KakaoTransfer kakaoTransfer){
		this.kakaoTransfer = kakaoTransfer;
	}
	
	@GetMapping("/test")
	public String test() {
		return "JUST TEST";
	}
	
	@GetMapping("/blog")
	public V2SearchBlogResp getBlog(
			@RequestBody @Valid SearchBlogReq req) throws Exception {
		var v2SearchBlogReq = new V2SearchBlogReq();
		v2SearchBlogReq.setQuery(req.getSearchWork());
		v2SearchBlogReq.setSort(KakaoSortCode.getCodeByCodeValue(req.getSort()));
		v2SearchBlogReq.setPage(req.getPage());
		v2SearchBlogReq.setSize(req.getSize());
		return kakaoTransfer.getV2SearchBlog(v2SearchBlogReq);
	}

}
