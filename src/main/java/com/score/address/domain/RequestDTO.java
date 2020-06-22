package com.score.address.domain;

import lombok.Data;

@Data
public class RequestDTO {
    private String user_id;
    private String some_info;
    private ScoreRequestDTO data;
}
