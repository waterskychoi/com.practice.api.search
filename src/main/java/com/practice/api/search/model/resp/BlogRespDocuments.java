package com.practice.api.search.model.resp;

import com.practice.api.search.model.resp.kakao.KakaoRespDocuments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRespDocuments {
	private String title;
	private String contents;
	private String url;
	private String blogname;
	private String thumbnail;
	private String datetime;
	
	BlogRespDocuments(){};
	
	BlogRespDocuments(KakaoRespDocuments kakaoRespDocuments){
		this.title = kakaoRespDocuments.getTitle();
		this.contents = kakaoRespDocuments.getContents();
		this.url = kakaoRespDocuments.getUrl();
		this.blogname = kakaoRespDocuments.getBlogname();
		this.thumbnail = kakaoRespDocuments.getThumbnail();
		this.datetime = kakaoRespDocuments.getDatetime();
	};
}
