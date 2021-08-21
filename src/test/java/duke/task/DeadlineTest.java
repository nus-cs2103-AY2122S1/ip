package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] return book (by: Oct 8 2021)",
                new Deadline("return book", LocalDate.parse("2021-10-08")).toString());
    }
}
