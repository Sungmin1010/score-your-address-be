package com.score.address.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ResponseDTO {
    private Integer code;
    private List<ScoreResponseDTO> data;
}
