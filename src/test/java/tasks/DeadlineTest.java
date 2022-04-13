package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void toString_notDone_correctStringReturned() {
        String description = "sleep";
        LocalDateTime dateTime = LocalDateTime.parse("2020/02/02 2230", DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        Deadline deadline = new Deadline(description, dateTime);
        assertEquals("[D][ ] sleep (by: Feb 2 2020, 10:30 PM)", deadline.toString());
    }

    @Test
    public void toString_done_correctStringReturned() {
        String description = "wake up";
        LocalDateTime dateTime = LocalDateTime.parse("2020/02/03 0600", DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        Deadline deadline = new Deadline(description, dateTime);
        deadline.markAsDone();
        assertEquals("[D][X] wake up (by: Feb 3 2020, 6:00 AM)", deadline.toString());
    }
}
