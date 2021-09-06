package brobot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import brobot.exception.BroException;


public class TaskListTest {

    @Test
    public void markDone_noSuchTask_exceptionThrown() {
        try {
            TaskList list = new TaskList();
            list.addTask(new Todo("test"));
            list.markDone(2);
            fail();
        } catch (BroException e) {
            assertEquals("Ey bro, no such task lah!", e.getMessage());
        }
    }
}
