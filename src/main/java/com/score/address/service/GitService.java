package com.score.address.service;

import com.score.address.domain.GitRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitService {


    private WebClient webClient;

    /*public GitService(WebClient.Builder builder){
        this.webClient = builder.build();
    }*/

    public Mono<GitRepo[]> getAllRepository(){
        //System.out.println("url : " + gitApiBaseUrl);
        List<String> list = new ArrayList<>();
        //WebClient webClient = WebClient.builder().build();
        Mono<GitRepo[]> monoRepo = webClient.get().uri("/users/Sungmin1010/repos")
                .retrieve()
                .bodyToMono(GitRepo[].class);

        /*monoRepo.doOnSuccess(arr ->{
            Arrays.stream(arr).forEach(r -> {
                System.out.println("name : " +r.getName());
                list.add(r.getName());
            });
                }).subscribe();*/

        return monoRepo;
    }
}
