package com.score.address.service;

import com.score.address.domain.ScoreDTO;
import com.score.address.domain.ScoreRequestDTO;
import com.score.address.domain.ScoreResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImple implements ScoreService{
//데이터 처리 방법에 따라 3가지 방법 중 성능 좋은 것으로 선택
    private WebClient webClient;

    public ScoreServiceImple(WebClient.Builder builder){
        this.webClient = builder.build();
    }

    public List<ScoreResponseDTO> findCategoryTotalCount2(ScoreRequestDTO scoreRequestDTO){
        //3번 방법
        List<ScoreResponseDTO> scoreResponseDTOList = new ArrayList<>();
        List<String> categoryList = scoreRequestDTO.getCategory_group();
        String x = scoreRequestDTO.getX();
        String y = scoreRequestDTO.getY();
        int radius = scoreRequestDTO.getRadius();

        for(String category : categoryList){
            Flux<ScoreDTO> scoreDTOFlux = webClient.get()
                    .uri("/v2/local/search/category.json?"
                            + "category_group_code={category}"
                            + "&x={x}"
                            + "&y={y}"
                            + "&radius={radius}", category, x, y, radius)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToFlux(ScoreDTO.class).log();
            ScoreResponseDTO scoreResponseDTO = new ScoreResponseDTO();
            //scoreResponseDTO 설정...
            //
            //
            scoreResponseDTOList.add(scoreResponseDTO);

            //scoreDTOFlux.mergeWith(scoreDTOFlux)
        }

        return scoreResponseDTOList;
    }


    @Override
    public List<ScoreResponseDTO> findCategoryTotalCount(ScoreRequestDTO scoreRequestDTO) {
        List<String> category_group = scoreRequestDTO.getCategory_group();

        List<ScoreDTO> scoreDTOs = category_group.stream().flatMap( category -> {
            String x = scoreRequestDTO.getX();
            String y = scoreRequestDTO.getY();
            int radius = scoreRequestDTO.getRadius();
            ScoreResponseDTO scoreResponseDTO = new ScoreResponseDTO();

            Flux<ScoreDTO> flus = webClient.get()
                    .uri("/v2/local/search/category.json?"
                            +"category_group_code={category}"
                            +"&x={x}"
                            +"&y={y}"
                            +"&radius={radius}", category, x, y, radius)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToFlux(ScoreDTO.class)
                    .doOnNext( o -> {
                        o.setCategory(category);
                        o.setRadius(radius);

                    }).doFinally( g -> System.out.println(category)).log();
            //1번 방법
            /*Flux<ScoreResponseDTO> scoreResponseDTOFlux = flus.flatMap(scoreDTO -> {
                scoreResponseDTO.setTotal_count(scoreDTO.getMeta().getTotal_count());
                scoreResponseDTO.setCategory(category);
                return Flux.just(scoreResponseDTO);
            });*/


            return flus.toStream() ;
            //return Flux.just(scoreResponseDTO).toStream();
            //return scoreResponseDTOFlux.toStream();
        }).collect(Collectors.toList());
        //2번방법
        /*List<ScoreResponseDTO> scoreResponseDTOList = new ArrayList<>();
        scoreDTOs.forEach( c -> {
            ScoreDTO scoreDTO = (ScoreDTO)c;
            ScoreResponseDTO scoreResponseDTO = new ScoreResponseDTO();
            scoreResponseDTO.setTotal_count(scoreDTO.getMeta().getTotal_count());
            scoreResponseDTO.setCategory(scoreDTO.getCategory());
            scoreResponseDTO.makeScore(scoreDTO.getRadius());
            scoreResponseDTOList.add(scoreResponseDTO);
        });*/
        List<ScoreResponseDTO> scoreResponseDTOList = new ArrayList<>();
        for(ScoreDTO s:scoreDTOs){
            ScoreResponseDTO scoreResponseDTO = new ScoreResponseDTO();
            scoreResponseDTO.setTotal_count(s.getMeta().getTotal_count());
            scoreResponseDTO.setCategory(s.getCategory());
            scoreResponseDTO.makeScore((s.getRadius()));
            scoreResponseDTOList.add(scoreResponseDTO);
        }

        return scoreResponseDTOList;

    }
}
