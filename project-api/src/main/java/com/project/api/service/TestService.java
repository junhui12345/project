package com.project.api.service;

import com.project.core.entity.PlainText;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void test() {
        PlainText text = new PlainText();
        text.setInputDateTime("");

        System.out.println(text);
    }
}
