package com.score.address.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResponseData<T> extends CommonResponse {
    private List<T> data;
}
