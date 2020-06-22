package com.score.address.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ScoreRequestDTO {

        private List<String> category_group;

        private String x;

        private String y;

        private int radius;



}
