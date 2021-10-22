package duke;

import duke.exceptions.InvalidDescriptionException;
import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void testStringConversion() {
        try {
            assertEquals("[E][ ] zoom meeting (at: Oct 2 2020)",
                    new Event("zoom meeting",
                            "2020-10-02").toString());
        } catch (InvalidDescriptionException e) {
            fail();
        }
    }

    @Test
    public void wrongDateInput_exceptionThrown() {
        try {
            new Event("zoom meeting",
                    "2020-10-020");
        } catch (InvalidDescriptionException e) {
            assertEquals("Time Format is wrong, please specify as YYYY-MM-DD",
                    e.getMessage());
        }
    }
}
