package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test the Event class.
 */
public class EventTest {
    /**
     * Test if the event representation is generated as expected.
     * 
     * @throws DukeException Thrown if event fails to be generated.
     */
    @Test
    public void eventCreationTest() throws DukeException {
        assertEquals(new Event("description","2012-12-01").toString(),
                "[E] description (at: Dec 12 2012)");
    }
}
