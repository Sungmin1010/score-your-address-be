package com.score.address.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.score.address.domain.*;
import com.score.address.service.ScoreService;
import com.score.address.service.ScoreServiceImple;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
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
    public ResponseEntity<ResponseDTO> postScore(@RequestBody RequestDTO requestDTO){
        try{//TODO ControllerAdviser 예외처리
            ScoreRequestDTO scoreRequestDTO = requestDTO.getData();
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            List<ScoreResponseDTO> categoryTotalCount = scoreService.findCategoryTotalCount(scoreRequestDTO);

            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
            return new ResponseEntity<>(new ResponseDTO(200, categoryTotalCount), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }


}
