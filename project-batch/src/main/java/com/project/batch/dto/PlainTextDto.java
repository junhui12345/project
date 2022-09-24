package com.project.batch.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PlainTextDto {
    private String inputDateTime; //시간
    private Long totalMemberCount; //가입자수
    private Long exitMemberCount; //탈퇴자수
    private BigInteger amountOfPayment; //결제금액
    private BigInteger amountUsed; //사용금액
    private BigInteger salesAmount; //매출금액
}
