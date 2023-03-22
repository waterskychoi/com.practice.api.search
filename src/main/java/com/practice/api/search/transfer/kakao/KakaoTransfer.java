package com.practice.api.search.transfer.kakao;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.util.LinkedMultiValueMap;

import com.practice.api.search.config.ApiKeyConfig;
import com.practice.api.search.config.TransferConfig;
import com.practice.api.search.model.req.kakao.KakaoV2SearchBlogReq;
import com.practice.api.search.model.resp.kakao.KakaoV2SearchBlogResp;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KakaoTransfer {

    private WebClient webClient;

    private final String V2SearcgBlogUrl = "/v2/search/blog";

    @Autowired
    public KakaoTransfer(
            WebClient.Builder builder
            , TransferConfig transferConfig
            , ApiKeyConfig apiKeyConfig) {
        this.webClient = builder
        		.defaultHeader("Authorization", "KakaoAK " + apiKeyConfig.getKakao())
        		.baseUrl(transferConfig.getKakao()).build();
    }

    public KakaoV2SearchBlogResp getV2SearchBlog(KakaoV2SearchBlogReq v2SearchBlogReq) throws Exception {
    	try {
    		LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
            parameters.add("query", URLEncoder.encode(v2SearchBlogReq.getQuery(),StandardCharsets.UTF_8));
            if(Objects.nonNull(v2SearchBlogReq.getSort())) {
            	parameters.add("sort", v2SearchBlogReq.getSort().getCode());
            }
            if(Objects.nonNull(v2SearchBlogReq.getPage())) {
            	parameters.add("page", v2SearchBlogReq.getPage().toString());
            }
            if(Objects.nonNull(v2SearchBlogReq.getSize())) {
            	parameters.add("size", v2SearchBlogReq.getSize().toString());
            }

            KakaoV2SearchBlogResp result = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path(V2SearcgBlogUrl).queryParams(parameters).build())
                    .retrieve().bodyToMono(KakaoV2SearchBlogResp.class)
                    .block();
            return result;
    	}
    	catch (Exception ex) {
    		//TODO : Looging 추가
    		return null;
    	}
    }
}
