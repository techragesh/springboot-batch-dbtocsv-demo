package com.techjava.springbootbatchdbtocsvdemo.writer;

import com.techjava.springbootbatchdbtocsvdemo.domain.Ticket;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class TicketWriter {

    @Bean
    public FlatFileItemWriter<Ticket> fileItemWriter(){
        FlatFileItemWriter<Ticket> itemWriter = new FlatFileItemWriter<>();
        itemWriter.setResource(new ClassPathResource("ticket2.csv"));

        DelimitedLineAggregator<Ticket> ticketDelimitedLineAggregator = new DelimitedLineAggregator<>();
        ticketDelimitedLineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<Ticket> wrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        wrapperFieldExtractor.setNames(new String[] {"ticketId","bookingDate", "email", "fromStation", "passengerName", "toStation"});
        ticketDelimitedLineAggregator.setFieldExtractor(wrapperFieldExtractor);

        itemWriter.setLineAggregator(ticketDelimitedLineAggregator);
        return itemWriter;
    }
}
