package com.project.batch.dto;

import lombok.Data;

@Data
public class ResultDto {
    private String inputDateTime;
    private boolean isSave;

    //응집도 유지
    public static ResultDto of(PlainTextDto plainText, boolean isSave) {
        ResultDto result =  new ResultDto();
        result.setInputDateTime(plainText.getInputDateTime());
        result.setSave(isSave);
        return result;
    }
}
