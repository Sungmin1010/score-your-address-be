package com.score.address.api.service;

import com.score.address.api.response.CommonResponse;
import com.score.address.api.response.ListResponseData;
import com.score.address.api.response.SingleResponseData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    //단일 데이터 처리
    public <T>SingleResponseData getSingleData(T data){
        SingleResponseData result = new SingleResponseData();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }
    //다중건 데이터 처리
    public <T> ListResponseData<T> getListResult(List<T> list){
        ListResponseData<T> result = new ListResponseData<>();
        result.setData(list);
        setSuccessResult(result);
        return result;
    }

    //성공 결과만 처리
    public CommonResponse getSuccessResult(){
        CommonResponse result = new CommonResponse();
        setSuccessResult(result);
        return result;
    }
    //실패 결과만 처리
    public CommonResponse getFailResult(){
        CommonResponse result = new CommonResponse();
        result.setCode(500);
        return result;
    }

    //api요청 성공 데이터를 세팅
    private void setSuccessResult(CommonResponse result){
        result.setCode(200);
    }
}
