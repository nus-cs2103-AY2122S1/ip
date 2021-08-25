package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    @Test
    public void markAsDoneTest() {
        Task t = new TodoTask("wash dishes");
        assertFalse(t.isDone);
        t.markAsDone();
        assertTrue(t.isDone);
    }
}
