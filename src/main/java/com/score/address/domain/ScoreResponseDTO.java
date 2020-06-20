package com.score.address.domain;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ScoreResponseDTO {
    private String category;
    private float score;
    private int total_count;

    public void makeScore(int radius) {
        if(this.total_count > 0) this.score = (float) total_count / radius;
    }
}
