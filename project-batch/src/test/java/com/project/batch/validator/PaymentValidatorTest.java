package com.project.batch.validator;


import com.project.core.entity.PlainText;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.validator.ValidationException;

class PaymentValidatorTest {

    private PaymentValidator paymentValidator;

    @BeforeEach
    public void init() {
        paymentValidator = new PaymentValidator();
    }

    @Test
    public void givenPlainTextPayment_whenWrongInputDateTimeNumber_thenThrowValidationException() {
        //given
        PlainText payment = new PlainText();
        payment.setInputDateTime("1234");

        //when //then
        Assertions.assertThrows(ValidationException.class, () -> {
            paymentValidator.validate(payment);
        });
    }

    @Test
    public void givenPlainTextPayment_whenWrongInputDateTime_thenThrowValidationException() {
        //given
        PlainText payment = new PlainText();
        payment.setInputDateTime("2022-09-24");

        //when //then
        Assertions.assertThrows(ValidationException.class, () -> {
            paymentValidator.validate(payment);
        });
    }
    @Test
    public void givenPlainTextPayment_whenInputDateTime_then() {
        //given
        PlainText payment = new PlainText();
        payment.setInputDateTime("2022-09-24 13");

        //when //then
        paymentValidator.validate(payment);
    }
}