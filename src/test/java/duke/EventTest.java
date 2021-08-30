package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    /**
     * Tests if the event representation is generated as expected.
     * 
     * @throws DukeException Thrown if event fails to be generated.
     */
    @Test
    public void isByeMsgTest() throws DukeException {
        assertEquals(new Event("description","2012-12-01").toString(),
                "[E] description (at: Dec 12 2012)");
    }
}
