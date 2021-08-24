package duke.task;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString() {
        Deadline deadline = new Deadline("Return book", LocalDate.parse("2013-12-04"));
        String actual = deadline.toString();

        assertEquals("[D][ ] Return book (by: Dec 4 2013)", actual);
    }

    @Test
    void testToSavedFormat() {
        Deadline deadline = new Deadline("Return book", LocalDate.parse("2017-12-04"));
        String actual = deadline.toSavedFormat();

        assertEquals("Return book/~/2017-12-04", actual);
    }
}