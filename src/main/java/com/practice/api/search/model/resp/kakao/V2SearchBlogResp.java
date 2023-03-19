package com.practice.api.search.model.resp.kakao;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class V2SearchBlogResp {
	private KakaoRespMeta meta;
	private List<KakaoRespDocuments> documents;
}
