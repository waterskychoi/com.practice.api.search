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
import com.practice.api.search.transfer.naver.NaverTransfer;

import jakarta.validation.Valid;

import com.practice.api.search.enums.KakaoSortCode;
import com.practice.api.search.enums.NaverSortCode;
import com.practice.api.search.enums.SearchSortCode;
import com.practice.api.search.model.req.*;
import com.practice.api.search.model.req.kakao.*;
import com.practice.api.search.model.req.naver.*;
import com.practice.api.search.model.resp.*;
import com.practice.api.search.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

	private final SearchService searchService;
	private final KakaoTransfer kakaoTransfer;
	private final NaverTransfer naverTransfer;

	@Autowired
	SearchController(
			SearchService searchService
			, KakaoTransfer kakaoTransfer
			, NaverTransfer naverTransfer){
		this.searchService = searchService;
		this.kakaoTransfer = kakaoTransfer;
		this.naverTransfer = naverTransfer;
	}

	/**
	 * 블로그 검색
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/blog")
	public SearchBlogResp getBlog(
			@RequestBody @Valid SearchBlogReq req) throws Exception {
		SearchBlogResp returnValue = null;
		var reqSearchSortCode = SearchSortCode.getCodeByCodeValue(req.getSort());

		var reqKakaoSortCode = KakaoSortCode.getCodeBySearchSortCode(reqSearchSortCode.getCode());
		var kakaoV2SearchBlogReq = new KakaoV2SearchBlogReq();
		kakaoV2SearchBlogReq.setQuery(req.getSearchWord());
		kakaoV2SearchBlogReq.setSort(reqKakaoSortCode);
		kakaoV2SearchBlogReq.setPage(req.getPage());
		kakaoV2SearchBlogReq.setSize(req.getSize());
		var kakaoV2SearchBlogResp = kakaoTransfer.getV2SearchBlog(kakaoV2SearchBlogReq);

		if(Objects.nonNull(kakaoV2SearchBlogResp)) {
			returnValue = new SearchBlogResp(kakaoV2SearchBlogResp);
		}
		else {
			var reqNaverSortCode = NaverSortCode.getCodeBySearchSortCode(reqSearchSortCode.getCode());
			var naverV1SearchBlogReq = new NaverV1SearchBlogReq();
			naverV1SearchBlogReq.setQuery(req.getSearchWord());
			naverV1SearchBlogReq.setSort(reqNaverSortCode);
			naverV1SearchBlogReq.setDisplay(req.getSize());
			naverV1SearchBlogReq.setStart(req.getSize() * (req.getPage() - 1) + 1);
			if(naverTransfer.getV1SearcgBlogMaxDisplay().compareTo(naverV1SearchBlogReq.getStart()) < 0 ) {
				//Max 이상
				naverV1SearchBlogReq.setStart(naverTransfer.getV1SearcgBlogMaxDisplay());
			}
			var naverV1SearchBlogResp = naverTransfer.getV1SearchBlog(naverV1SearchBlogReq);
			returnValue = new SearchBlogResp(naverV1SearchBlogResp);
			returnValue.setIsEnd(naverTransfer.getV1SearcgBlogMaxDisplay().compareTo(req.getSize() * req.getPage()) < 0);
		}

		returnValue.setPage(req.getPage());
		returnValue.setSize(req.getSize());
		returnValue.setSort(reqSearchSortCode.getCode());

		//검색어 기록
		searchService.saveSearchWordLog(req.getSearchWord());
		return returnValue;
	}

	/**
	 * 검색어 랭킹 조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/rank")
	public List<SearchWordRank> getRank() throws Exception {
		return searchService.selectSearchWordRank();
	}
}
