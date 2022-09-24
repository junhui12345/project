package com.project.batch.scheduler;

import com.project.batch.job.FlatFileJobConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class JobScheduler {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private FlatFileJobConfig jobConfiguration;

    @Autowired
    private ApplicationArguments applicationArguments;

    private static final String FILE_PATH = "filePath";

    @Scheduled(cron="0/10 * * * * *")
    public void runJob() {
        final List<String> args=applicationArguments.getOptionValues(FILE_PATH);
        final Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put(FILE_PATH, new JobParameter(args.get(0)));
        final JobParameters jobParameters = new JobParameters(confMap);

        try {

            jobLauncher.run(jobConfiguration.flatFileJob(), jobParameters);

        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {
            log.error(e.getMessage());
        }
    }
}