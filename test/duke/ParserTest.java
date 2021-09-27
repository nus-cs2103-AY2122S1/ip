package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void parseDeadlineFromFile() {
        String input = "test (by: 12 Feb 2021 07:00 PM)";
        LocalDateTime dateTime = LocalDateTime.parse("12/02/2021 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

        assertEquals(new Deadline("test", dateTime).toString(),
                Parser.parseDeadlineFromFile(input).toString());
    }

    @Test
    void parseEventFromFile() {
        String input = "test (at: 12 Feb 2020 from: 07:00 PM-08:00 PM)";
        LocalDate date = LocalDate.parse("12/02/2020", DateTimeFormatter.ofPattern("dd/MM/yyy"));
        LocalTime start = LocalTime.parse("07:00 PM", DateTimeFormatter.ofPattern("hh:mm a"));
        LocalTime end = LocalTime.parse("08:00 PM", DateTimeFormatter.ofPattern("hh:mm a"));

        assertEquals(new Event("test", date, start, end).toString(),
                Parser.parseEventFromFile(input).toString());
    }
}