package com.techjava.springbootbatchdbtocsvdemo.reader;


import com.techjava.springbootbatchdbtocsvdemo.domain.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setTicketId(resultSet.getInt("ticket_id"));
        ticket.setBookingDate(resultSet.getDate("booking_date"));
        ticket.setEmail(resultSet.getString("email"));
        ticket.setFromStation(resultSet.getString("from_station"));
        ticket.setToStation(resultSet.getString("to_station"));
        ticket.setPassengerName(resultSet.getString("passenger_name"));
        return ticket;
    }
}
