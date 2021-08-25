package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void isByeMsgTest() throws DukeException {
        assertEquals(new Deadline("description","2012-12-01").toString(),
                "[D] description (by: Dec 12 2012)");
    }
}
