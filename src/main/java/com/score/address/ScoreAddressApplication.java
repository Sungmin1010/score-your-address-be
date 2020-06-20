package com.score.address;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Component
@PropertySource("classpath:/kakao-api.properties")
public class ScoreAddressApplication {

    @Value("${git.api.base.url}")
    private String gitApiBaseUrl;

    @Value("${kakao.api.url}")
    private String kakaoApiBaseUrl;

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    public static void main(String[] args) {
        SpringApplication.run(ScoreAddressApplication.class, args);
    }

    @Bean
    public WebClientCustomizer webClientCustomizer(){
        return new WebClientCustomizer() {
            @Override
            public void customize(WebClient.Builder webClientBuilder) {
                //webClientBuilder.baseUrl(gitApiBaseUrl);
                webClientBuilder.baseUrl(kakaoApiBaseUrl)
                        .defaultHeader("Authorization","KakaoAK "+kakaoApiKey);

            }
        };
    }

}
