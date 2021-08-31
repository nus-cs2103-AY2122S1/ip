package sora.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    public void constructor_success() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

        Deadline deadline = new Deadline("test deadline", dateTime);

        String expected = "[D][ ] test deadline (by: " + dateTime.format(formatter) + ")";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void markAsDone_success() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

        Deadline deadline = new Deadline("test deadline", dateTime);
        deadline.markAsDone();

        String expected = "[D][X] test deadline (by: " + dateTime.format(formatter) + ")";
        assertEquals(expected, deadline.toString());
    }
}
