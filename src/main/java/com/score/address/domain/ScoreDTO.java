package com.score.address.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ScoreDTO implements Serializable{

    private ScoreDTO.Meta meta;
    private List<document> documents;

    @Setter
    @Getter
    @ToString
    public class Meta{
        int total_count;

    }

    @Getter
    @Setter
    @ToString
    static class document{
        String category_group_code;

    }

    private String category;

    private Double score;

    private int radius;

}
