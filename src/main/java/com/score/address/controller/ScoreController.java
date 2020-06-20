package com.score.address.controller;

import com.score.address.domain.ScoreDTO;
import com.score.address.domain.ScoreRequestDTO;
import com.score.address.domain.ScoreResponseDTO;
import com.score.address.service.ScoreService;
import com.score.address.service.ScoreServiceImple;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class ScoreController {

    private ScoreService scoreService;

    @PostMapping("/score")
    public List<ScoreResponseDTO> postScore(@RequestBody ScoreRequestDTO scoreRequestDTO){
        //응답시간 체크
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<ScoreResponseDTO> categoryTotalCount = scoreService.findCategoryTotalCount(scoreRequestDTO);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return categoryTotalCount;

    }
}
