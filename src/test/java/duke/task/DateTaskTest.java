package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DateTaskTest {
    @Test
    public void constructor_invalidDateString_dukeExceptionThrown() {
        try {
            new ConcreteDateTask("test", "05/5/2020", false);
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Oops!!! Date should be in this format: dd/MM/yyyy",
                    e.getMessage());
        }
    }

}

class ConcreteDateTask extends DateTask {
    public ConcreteDateTask(String description, String dateString, boolean isDone) throws DukeException {
        super(description, dateString, isDone);
    }
}
