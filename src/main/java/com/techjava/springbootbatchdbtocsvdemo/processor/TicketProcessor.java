package com.techjava.springbootbatchdbtocsvdemo.processor;

import com.techjava.springbootbatchdbtocsvdemo.domain.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;

@Slf4j
public class TicketProcessor implements ItemProcessor<Ticket, Ticket> {
    @Override
    public Ticket process(Ticket ticket) throws Exception {
        Integer ticketId = ticket.getTicketId();
        String passengerName = ticket. getPassengerName();
        String fromStation = ticket.getFromStation();
        String toStation = ticket.getToStation();
        String email = ticket.getEmail();
        Date bookingDate = ticket.getBookingDate();

        Ticket ticket1 = new Ticket(ticketId,passengerName, fromStation, toStation, email, bookingDate);
        return ticket1;
    }
}
