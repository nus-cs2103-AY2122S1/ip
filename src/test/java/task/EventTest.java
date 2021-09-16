package task;

import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Task event = new Event(TaskType.EVENT, "CS2103 tP Meeting", "2021-09-14 2100");
        assertEquals("[E] [ ] CS2103 tP Meeting (at: Sep 14 2021, 09:00 PM)", event.toString());
    }
}
