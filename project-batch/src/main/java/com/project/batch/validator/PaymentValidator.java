package com.project.batch.validator;

import com.project.core.entity.PlainText;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PaymentValidator implements Validator<PlainText> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");

    @Override
    public void validate(PlainText payment) throws ValidationException {
        checkInputDateTime(payment.getInputDateTime());
    }

    private void checkInputDateTime(String text) {
        if(!hasText(text))  throw new ValidationException(text + "가 빈 문자열이거나 존재하지 않습니다.");
        if(!isParseDateHour(text)) throw new ValidationException(text + "가 올바른 날짜 형식이 아닙니다. yyyy-MM-dd HH 이어야 합니다.");
    }

    private boolean hasText(String text) {
        return StringUtils.hasText(text);
    }

    private boolean isParseDateHour(String text) {
        try {
            LocalDate.parse(text, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
