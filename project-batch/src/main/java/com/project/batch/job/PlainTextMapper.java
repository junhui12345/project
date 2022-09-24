package com.project.batch.job;

import com.project.core.entity.PlainText;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.math.BigInteger;

public class PlainTextMapper implements FieldSetMapper<PlainText> {
    @Override
    public PlainText mapFieldSet(final FieldSet fieldSet) {
        final PlainText payment = new PlainText();
        payment.setInputDateTime(fieldSet.readString(1));
        payment.setTotalMemberCount(fieldSet.readLong(2));
        payment.setExitMemberCount(fieldSet.readLong(3));
        payment.setAmountOfPayment(new BigInteger(fieldSet.readString(4)));
        payment.setAmountUsed(new BigInteger(fieldSet.readString(5)));
        payment.setSalesAmount(new BigInteger(fieldSet.readString(6)));
        return payment;
    }
}
