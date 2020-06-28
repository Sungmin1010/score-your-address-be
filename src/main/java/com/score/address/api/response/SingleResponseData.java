package com.score.address.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponseData<T> extends CommonResponse{
    private T data;
}
