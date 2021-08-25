package duke;

import org.junit.jupiter.api.Test;
import duke.Duke;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    @Test
    public void add_invalidTask_exceptionThrown() {

        try {
            Duke duke = new Duke();
            String[] invalidTask = new String[]{"todo", "", "", "false"};
            duke.add(invalidTask);
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
}
