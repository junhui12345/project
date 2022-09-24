package com.project.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@DynamicUpdate //오버헤드 발생 가능.
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plain_text")
public class PlainText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ISO 형식의 시간표기법이 아니므로 LocalDateTime이 아닌 String으로 저장
    @Column(nullable = false)
    private String inputDateTime; //시간

    private Long totalMemberCount; //가입자수
    private Long exitMemberCount; //탈퇴자수

    private BigInteger amountOfPayment; //결제금액
    private BigInteger amountUsed; //사용금액
    private BigInteger salesAmount; //매출금액
}

