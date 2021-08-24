package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void testToString() {
        LocalDateTime dateTime = LocalDateTime.parse("12/02/2021 1900",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        LocalDate date = LocalDate.parse("22-06-2021",
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Deadline testDeadline1 = new Deadline("test", dateTime);
        Deadline testDeadline2 = new Deadline("test", date);
        assertEquals("[D][ ] test (by: 12 Feb 2021 07:00 PM)", testDeadline1.toString());
        assertEquals("[D][ ] test (by: 22 Jun 2021)", testDeadline2.toString());
    }
}