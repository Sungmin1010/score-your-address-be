package com.score.address.service;

import com.score.address.domain.ResponseKakaoAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class KakaoAPIService {

   private WebClient webClient;

    public KakaoAPIService(WebClient.Builder builder){
        this.webClient = builder.build();
    }

    public Mono<ResponseKakaoAddress> kakaoTest(Double x, Double y){
        return webClient.get().uri("/v2/local/geo/coord2regioncode.json?x="+x+"&y="+y)
                .retrieve()
                .bodyToMono(ResponseKakaoAddress.class);

    }

}
