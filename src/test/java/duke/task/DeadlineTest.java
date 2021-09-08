package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ][ ] return book (by: Oct 8 2021)",
                new Deadline("return book", LocalDate.parse("2021-10-08")).toString());
    }
}
