package com.project.batch.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.*;

class FilePathParameterValidatorTest {

    @Test
    public void existsPath() {
        //given
        String path = "data_source/source.csv";

        Resource resource = new ClassPathResource(path);

        assertTrue(resource.exists());
    }
    @Test
    public void notExistsPath() {
        //given
        String path = "data_source/not.csv";

        Resource resource = new ClassPathResource(path);

        assertFalse(resource.exists());
    }
}