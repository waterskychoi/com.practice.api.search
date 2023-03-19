package com.practice.api.search.model.resp.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoRespMeta {
	@JsonProperty("total_count")
	private Integer totalCount;
	@JsonProperty("pageable_count")
	private Integer pageableCount;
	@JsonProperty("is_end")
	private Boolean isEnd;
}
