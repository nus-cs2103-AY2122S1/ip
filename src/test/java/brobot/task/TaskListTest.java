package brobot.task;

import brobot.exception.BroException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void markDone_noSuchTask_exceptionThrown() {
        try {
            TaskList list = new TaskList();
            list.addTask(new Todo("test"));
            list.markDone(2);
            fail();
        } catch (BroException e) {
            assertEquals("Hey, there is no such task!", e.getMessage());
        }
    }
}
