package com.practice.api.search.transfer.kakao;

import org.springframework.web.reactive.function.client.WebClient;

import com.practice.api.search.config.TransferConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KakaoTransfer {

    private WebClient webClient;

    @Autowired
    public KakaoTransfer(
            WebClient.Builder builder
            , TransferConfig transferConfig) {
        this.webClient = builder.baseUrl(transferConfig.getKakao()).build();
    }
}
