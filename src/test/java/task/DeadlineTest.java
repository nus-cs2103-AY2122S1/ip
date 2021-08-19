package task;

import org.junit.jupiter.api.Test;
import util.DateTimeUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void getByDateTime() {
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        Deadline deadline = new Deadline("Return book", byDateTime);
        LocalDateTime expected = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        assertEquals(expected, deadline.getByDateTime());
    }

    @Test
    void formatTask() {
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        Deadline deadline = new Deadline("Return book", byDateTime);
        String[] expected = new String[] {"D", "1", "Return book", "2021-08-01 13:00"};
        assertArrayEquals(expected, deadline.formatTask());
    }

    @Test
    void markAsDone() {
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        Deadline deadline = new Deadline("Return book", byDateTime).markAsDone();
        Deadline expected = new Deadline("Return book", byDateTime, true);
        assertEquals(expected.toString(), deadline.toString());
    }

    @Test
    void testToString() {
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        Deadline deadline = new Deadline("Return book", byDateTime);
        String expected = "[D][ ] Return book (by: Aug 01 2021 01:00 pm)";
        assertEquals(expected, deadline.toString());
    }
}
