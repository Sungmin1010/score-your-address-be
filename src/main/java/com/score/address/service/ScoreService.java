package com.score.address.service;

import com.score.address.domain.ScoreRequestDTO;
import com.score.address.domain.ScoreResponseDTO;


import java.util.List;

public interface ScoreService {

    List<ScoreResponseDTO> findCategoryTotalCount(ScoreRequestDTO scoreRequestDTO);
}
