package sora.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class EventTest {
    @Test
    public void constructor_success() {
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = startTime.plusHours(2);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

        Event event = new Event("test event", date, startTime, endTime);

        String expected = "[E][ ] test event (at: " + date.format(dateFormatter) + ", "
                + startTime.format(timeFormatter) + " - " + endTime.format(timeFormatter) + ")";
        assertEquals(expected, event.toString());
    }

    @Test
    public void markAsDone_success() {

    }
}
