package com.project.batch.job;

import com.project.core.entity.PlainText;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.math.BigInteger;

public class PlainTextMapper implements FieldSetMapper<PlainText> {
    @Override
    public PlainText mapFieldSet(final FieldSet fieldSet) {
        final PlainText payment = new PlainText();
        payment.setInputDateTime(fieldSet.readString(0));
        payment.setTotalMemberCount(fieldSet.readLong(1));
        payment.setExitMemberCount(fieldSet.readLong(2));
        payment.setAmountOfPayment(new BigInteger(String.valueOf(fieldSet.readLong(3))));
        payment.setAmountUsed(new BigInteger(String.valueOf(fieldSet.readLong(4))));
        payment.setAmountSales(new BigInteger(String.valueOf(fieldSet.readLong(5))));
        return payment;
    }
}
