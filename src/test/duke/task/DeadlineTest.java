package duke.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString_notInDateFormat() {
        assertEquals("[D][ ] Read Book (by: Friday)",
                new Deadline("Read Book", "Friday").toString());
    }

    @Test
    void testToString_inDateFormat() {
        assertEquals("[D][ ] Read Book (by: 25 of DECEMBER 2021, 4pm)",
                new Deadline("Read Book", LocalDate.of(2021,12,25).atTime(16, 0)).toString());
    }
}