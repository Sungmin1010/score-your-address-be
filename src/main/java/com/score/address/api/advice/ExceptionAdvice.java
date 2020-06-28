package com.score.address.api.advice;

import com.score.address.api.response.CommonResponse;
import com.score.address.api.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice //예외 발생시 json형태로 결과 반환 / 특정 패키지 하위 컨트롤러 지정 가능
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class) //처리할 예외
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse defaultException(HttpServletRequest request, Exception e){
        return responseService.getFailResult();
    }
}
