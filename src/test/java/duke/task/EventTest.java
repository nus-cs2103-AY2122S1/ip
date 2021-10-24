package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ][ ] project meeting (at: Oct 8 2021)",
                new Event("project meeting", LocalDate.parse("2021-10-08")).toString());
    }
}
