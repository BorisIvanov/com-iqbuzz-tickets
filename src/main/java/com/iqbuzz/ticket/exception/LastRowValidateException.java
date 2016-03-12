package com.iqbuzz.ticket.exception;

public class LastRowValidateException extends IqbuzzTicketException {
    public LastRowValidateException() {
        super("Seats on last row may sale or reservation by pair");
    }
}