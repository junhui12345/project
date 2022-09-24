package com.project.batch.job;

import com.project.batch.validator.FilePathParameterValidator;
import com.project.batch.validator.PaymentValidator;
import com.project.core.entity.PlainText;
import com.project.core.repository.PlainTextRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class FlatFileJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PlainTextRepository plainTextRepository;

    @Bean
    public Job flatFileJob() {
        return jobBuilderFactory.get("flatFileJob")
                .incrementer(new RunIdIncrementer())
                .validator(new FilePathParameterValidator())
                .start(flatFileStep(null))
                .build();
    }

    @JobScope
    @Bean
    public Step flatFileStep(@Value("#{jobParameters[filePath]}") String filePath) {
        return stepBuilderFactory.get("flatFileStep")
                .<PlainText, PlainText>chunk(5)
                .reader(paymentTextReader(filePath))
                .processor(validatingItemProcessor())
                .writer(paymentWriter())
                .faultTolerant()
                .build();
    }

    @StepScope
    @Bean
    public FlatFileItemReader<PlainText> paymentTextReader(@Value("#{jobParameters[filePath]}") String filePath) {
        return new FlatFileItemReaderBuilder<PlainText>()
                .name("paymentTextReader")
                .delimited()
                .delimiter("|")
                .names("inputDateTime", "totalMemberCount", "exitMemberCount", "amountOfPayment", "amountUsed", "amountSales")
                .fieldSetMapper(new PlainTextMapper())
                .resource(new ClassPathResource(filePath))
                .build();
    }
    @StepScope
    @Bean
    public ValidatingItemProcessor<PlainText> validatingItemProcessor() {
        ValidatingItemProcessor<PlainText> itemProcessor = new ValidatingItemProcessor<>(new PaymentValidator());
        itemProcessor.setFilter(true);
        return itemProcessor;
    }

    @StepScope
    @Bean
    public ItemWriter<PlainText> paymentWriter() {
        return items -> items.forEach(item -> plainTextRepository.save(item));
    }
}
