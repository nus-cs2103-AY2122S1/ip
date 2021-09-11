package duke.logic.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void createEvent() {
        assertEquals("[E] [ ] run (at: Oct 20 2020)",
                new Event("run", "", LocalDate.parse("2020-10-20")).toString());
    }
}
