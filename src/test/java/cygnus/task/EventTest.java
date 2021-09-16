package cygnus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class EventTest {

    private static final String dateTimeInputFormat = "ddMMyy HHmm";

    @Test
    public void stringConversion_doneEvent_success() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeInputFormat);
        Event event = new Event("eventDescription", LocalDateTime.parse("260921 1900", dateTimeFormatter));
        event.setDone();
        assertEquals("[E][X] eventDescription | at: Sun 26 Sep 2021 07:00PM", event.toString());
    }

}
