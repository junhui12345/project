package com.project.core.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PaymentServiceTest {
    private PaymentService paymentService;

    @BeforeEach
    public void setup() {
        paymentService = new PaymentService();
    }

    @Test
    public void testCall() {
        //given //when
        boolean call = paymentService.alwaysReturnTrue();

        //then
        Assert.assertTrue(call);
    }
}