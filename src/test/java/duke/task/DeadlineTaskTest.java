package duke.task;

import duke.error.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTaskTest {
    @Test
    public void wrongDateFormat_exceptionThrown() {
        try {
            new DeadlineTask("description", "01-01-2021");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!! wrong date format.\nthe format should be:\n\tyyyy-mm-dd", e.getMessage());
        }
    }
}
