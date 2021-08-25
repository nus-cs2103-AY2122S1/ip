package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void isByeMsgTest() throws DukeException {
        assertEquals(new Event("description","2012-12-01").toString(),
                "[E] description (at: Dec 12 2012)");
    }
}
