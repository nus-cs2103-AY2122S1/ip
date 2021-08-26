package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] zoom meeting (at: Oct 2 2020)",
                new Event("zoom meeting",
                        "2020-10-02").toString());
    }
}
