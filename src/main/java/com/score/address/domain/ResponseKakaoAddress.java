package com.score.address.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ResponseKakaoAddress implements Serializable{
    private ResponseKakaoAddress.Meta meta;
    private List<document> documents;

    @Getter
    @Setter
    static class Meta{
        int total_count;
    }


    @Getter
    @Setter
    public static class document{
        String region_type;
        String address_name;
        String region_1depth_name;
        String region_2depth_name;
        String region_3depth_name;
        //String region_4depth_name;
        String code;
        Double x;
        Double y;

    }
}
