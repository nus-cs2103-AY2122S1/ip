package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] project meeting (at: Oct 8 2021)",
                new Event("project meeting", LocalDate.parse("2021-10-08")).toString());
    }
}
