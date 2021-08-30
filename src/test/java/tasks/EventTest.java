package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.tasks.Event;


public class EventTest {
    @Test
    public void toString_notDone_correctStringReturned() {
        String description = "sleep";
        LocalDate date = LocalDate.parse("2020/02/02", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalTime timeStart = LocalTime.parse("2230", DateTimeFormatter.ofPattern("HHmm"));
        LocalTime timeEnd = LocalTime.parse("0600", DateTimeFormatter.ofPattern("HHmm"));
        Event event = new Event(description, date, timeStart, timeEnd);
        assertEquals("[E][ ] sleep (at: Feb 2 2020, 10:30 PM - 6:00 AM)", event.toString());
    }

    @Test
    public void toString_done_correctStringReturned() {
        String description = "wake up";
        LocalDate date = LocalDate.parse("2020/02/03", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalTime timeStart = LocalTime.parse("0600", DateTimeFormatter.ofPattern("HHmm"));
        LocalTime timeEnd = LocalTime.parse("2230", DateTimeFormatter.ofPattern("HHmm"));
        Event event = new Event(description, date, timeStart, timeEnd);
        event.markAsDone();
        assertEquals("[E][X] wake up (at: Feb 3 2020, 6:00 AM - 10:30 PM)", event.toString());
    }
}
