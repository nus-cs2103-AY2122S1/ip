package pix.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    @Test
    void getDate() {
        Event event = new Event("attend meeting", LocalDate.parse("2020-01-28"));
        assertEquals("2020-01-28", event.getDate());
    }
}