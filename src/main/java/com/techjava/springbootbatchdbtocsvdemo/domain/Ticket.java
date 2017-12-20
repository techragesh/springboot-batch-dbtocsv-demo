package com.techjava.springbootbatchdbtocsvdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {

    private Integer ticketId;

    private String passengerName;

    private String fromStation;

    private String toStation;

    private String email;

    private Date bookingDate;
}
