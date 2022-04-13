package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toString_stringReturned() {
        String des = "event zoom meeting";
        LocalTime start = LocalTime.parse("08:00");
        LocalTime end = LocalTime.parse("23:00");
        LocalDate date = LocalDate.parse("2012-03-12");
        Event task = new Event(des, date, start, end);
        assertEquals("[E][ ] event zoom meeting (at: 2012-03-12 08:00 23:00)", task.toString());
    }

    @Test
    public void formatString_stringReturned() {
        String des = "event zoom meeting";
        LocalTime start = LocalTime.parse("08:00");
        LocalTime end = LocalTime.parse("23:00");
        LocalDate date = LocalDate.parse("2012-03-12");
        Event task = new Event(des, date, start, end);
        assertEquals("[E][ ] event zoom meeting (at: Mar 12 2012 8:00AM to 11:00PM)", task.formatString());
    }
}

