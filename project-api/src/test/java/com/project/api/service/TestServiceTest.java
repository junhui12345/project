package com.project.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TestServiceTest {

    private TestService testService;

    @BeforeEach
    public void init() {
        testService = new TestService();
    }


    @Test
    public void test() {
        testService.test();
    }

}