package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getDeadline_dateCorrectlyFormatted_returnedDateCorrectlyFormatted() {
        String item = "return books";
        String time = "2021-09-30 18:00";
        LocalDateTime deadline = LocalDateTime.parse(time.replace(' ', 'T'),
                DateTimeFormatter.ISO_DATE_TIME);
        Deadline dl = new Deadline(item, deadline);
        assertEquals("2021-09-30 18:00", dl.getDeadline());
    }
}
