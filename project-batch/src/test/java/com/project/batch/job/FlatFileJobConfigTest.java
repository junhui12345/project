package com.project.batch.job;

import com.project.batch.BatchConfigTest;
import org.assertj.core.util.Maps;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBatchTest
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {FlatFileJobConfig.class, BatchConfigTest.class})
public class FlatFileJobConfigTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void givenInvalidPath_whenLaunchJob_thenThrowJobParametersInvalidException() throws Exception {
        //given
        final String filePath = "NO_FILE.txt";

        //when
        JobParameters parameters = new JobParameters(Maps.newHashMap("filePath", new JobParameter(filePath)));

        //then
        Assertions.assertThrows(JobParametersInvalidException.class, () -> jobLauncherTestUtils.launchJob(parameters));
    }

    @Test
    public void success() throws Exception {
        //given
        final String filePath = "data_source/source.csv";

        //when
        final JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", filePath)
                .toJobParameters();

        final JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        //then
        Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
    }
}