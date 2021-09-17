package duke;

import org.junit.jupiter.api.Test;
import duke.task.Event;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void getTaskString_void_success() {
        assertEquals("[T][ ] description (at: time)", new Event("description", "time").getTaskString());
    }
}
