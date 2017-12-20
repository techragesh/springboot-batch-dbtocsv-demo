package com.techjava.springbootbatchdbtocsvdemo.reader;

import com.techjava.springbootbatchdbtocsvdemo.domain.Ticket;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class TicketReader {

    @Autowired
    public DataSource dataSource;

    private static final String SQL = "select ticket_id,booking_date,email,from_station,passenger_name,to_station from ticket";

    @Bean
    public JdbcCursorItemReader<Ticket> reader() {
        JdbcCursorItemReader<Ticket> cursorItemReader = new JdbcCursorItemReader<>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql(SQL);
        cursorItemReader.setRowMapper(new TicketRowMapper());
        return cursorItemReader;
    }
}
