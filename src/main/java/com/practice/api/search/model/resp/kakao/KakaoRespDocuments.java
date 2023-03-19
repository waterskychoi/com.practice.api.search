package com.practice.api.search.model.resp.kakao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoRespDocuments {
	private String title;
	private String contents;
	private String url;
	private String blogname;
	private String thumbnail;
	private String datetime;
}
