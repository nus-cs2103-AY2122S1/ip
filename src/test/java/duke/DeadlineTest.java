package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    /**
     * Tests if the deadline representation is generated as expected.
     * 
     * @throws DukeException Thrown if deadline fails to be generated.
     */
    @Test
    public void isByeMsgTest() throws DukeException {
        assertEquals(new Deadline("description","2012-12-01").toString(),
                "[D] description (by: Dec 12 2012)");
    }
}
