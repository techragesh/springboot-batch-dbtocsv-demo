package com.techjava.springbootbatchdbtocsvdemo.config;

import com.techjava.springbootbatchdbtocsvdemo.domain.Ticket;
import com.techjava.springbootbatchdbtocsvdemo.processor.TicketProcessor;
import com.techjava.springbootbatchdbtocsvdemo.reader.TicketReader;
import com.techjava.springbootbatchdbtocsvdemo.writer.TicketWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@Slf4j
public class TicketBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private TicketReader ticketReader;

    @Autowired
    private TicketWriter ticketWriter;


    @Bean
    public JdbcCursorItemReader<Ticket> itemReader(){
        return ticketReader.reader();
    }

    @Bean
    public FlatFileItemWriter<Ticket> itemWriter(){
        return ticketWriter.fileItemWriter();
    }

    @Bean
    public TicketProcessor getTicketProcessor(){
        return new TicketProcessor();
    }

    @Bean
    public Job exportTicketJob(){
        return jobBuilderFactory.get("exportTicketJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Ticket,Ticket>chunk(4)
                .reader(itemReader())
                .processor(getTicketProcessor())
                .writer(itemWriter())
                .build();

    }

}
