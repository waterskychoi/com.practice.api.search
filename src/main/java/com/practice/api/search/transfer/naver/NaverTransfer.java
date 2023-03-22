package com.practice.api.search.transfer.naver;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.http.codec.json.Jackson2JsonDecoder; //원인불명

import com.practice.api.search.config.ApiKeyConfig;
import com.practice.api.search.config.TransferConfig;
import com.practice.api.search.model.req.naver.NaverV1SearchBlogReq;
import com.practice.api.search.model.resp.naver.NaverV1SearchBlogResp;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class NaverTransfer {

    private WebClient webClient;

    private final String V1SearcgBlogUrl = "/v1/search/blog.json";

    private final Integer V1SearcgBlogMaxDisplay = 1000;
    public Integer getV1SearcgBlogMaxDisplay() { return V1SearcgBlogMaxDisplay; }

    @Autowired
    public NaverTransfer(
            WebClient.Builder builder
            , TransferConfig transferConfig
            , ApiKeyConfig apiKeyConfig) {
        this.webClient = builder
        		.defaultHeader("X-Naver-Client-Id", apiKeyConfig.getNaverId())
        		.defaultHeader("X-Naver-Client-Secret", apiKeyConfig.getNaverSecret())
        		.baseUrl(transferConfig.getNaver()).build();
    }

    public NaverV1SearchBlogResp getV1SearchBlog(NaverV1SearchBlogReq req) throws Exception {
    	LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("query", URLEncoder.encode(req.getQuery(),StandardCharsets.UTF_8));
        if(Objects.nonNull(req.getSort())) {
        	parameters.add("sort", req.getSort().getCode());
        }
        if(Objects.nonNull(req.getDisplay())) {
        	parameters.add("display", req.getDisplay().toString());
        }
        if(Objects.nonNull(req.getStart())) {
        	parameters.add("start", req.getStart().toString());
        }

        NaverV1SearchBlogResp result = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(V1SearcgBlogUrl).queryParams(parameters).build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                //.acceptCharset(StandardCharsets.UTF_8)
                .retrieve().bodyToMono(NaverV1SearchBlogResp.class)
                .block();
        return result;
    }
}
