package com.practice.api.search.transfer.kakao;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.util.LinkedMultiValueMap;

import com.practice.api.search.config.ApiKeyConfig;
import com.practice.api.search.config.TransferConfig;
import com.practice.api.search.model.req.kakao.V2SearchBlogReq;
import com.practice.api.search.model.resp.kakao.V2SearchBlogResp;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class KakaoTransfer {

    private WebClient webClient;

    private final Charset Charset_UTF8 = Charset.forName("UTF-8");
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

    public V2SearchBlogResp getV2SearchBlog(V2SearchBlogReq v2SearchBlogReq) throws Exception {
    	LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("query", URLEncoder.encode(v2SearchBlogReq.getQuery(),Charset_UTF8));        
        if(Objects.nonNull(v2SearchBlogReq.getSort())) {
        	parameters.add("page", v2SearchBlogReq.getSort().getCode());
        }        
        if(Objects.nonNull(v2SearchBlogReq.getPage())) {
        	parameters.add("page", v2SearchBlogReq.getPage().toString());        	
        }
        if(Objects.nonNull(v2SearchBlogReq.getSize())) {
        	parameters.add("size", v2SearchBlogReq.getSize().toString());        	
        }        

        V2SearchBlogResp result = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(V2SearcgBlogUrl).queryParams(parameters).build())
                .acceptCharset(Charset_UTF8)
                .retrieve().bodyToMono(V2SearchBlogResp.class)
                .block();
        return result;
    }
}
