package com.project.core.repository;

import com.project.core.entity.PlainText;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigInteger;


@DataJpaTest
public class PlainTextRepositoryTest {

    @Autowired
    private PlainTextRepository plainTextRepository;

    @Test
    public void savePlainText() {
        //given
        PlainText plainText = new PlainText();
        plainText.setInputDateTime("2022-09-21 22");
        plainText.setTotalMemberCount(100L);
        plainText.setExitMemberCount(200L);
        plainText.setAmountOfPayment(new BigInteger("1000000"));
        plainText.setSalesAmount(new BigInteger("2000000"));
        plainText.setAmountUsed(new BigInteger("3000000"));

        //when
        PlainText saved = plainTextRepository.save(plainText);

        //then
        Assert.assertEquals(plainText.getId(), saved.getId());
        Assert.assertEquals(plainText.getInputDateTime(), saved.getInputDateTime());
        Assert.assertEquals(plainText.getTotalMemberCount(), saved.getTotalMemberCount());
        Assert.assertEquals(plainText.getExitMemberCount(), saved.getExitMemberCount());
        Assert.assertEquals(plainText.getAmountOfPayment(), saved.getAmountOfPayment());
        Assert.assertEquals(plainText.getSalesAmount(), saved.getSalesAmount());
        Assert.assertEquals(plainText.getAmountUsed(), saved.getAmountUsed());
    }
}