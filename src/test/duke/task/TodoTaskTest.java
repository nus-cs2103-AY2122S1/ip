package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.TodoTask;


public class TodoTaskTest {

    @Test
    public void testToString() {
        assertEquals("[T][ ] read book", new TodoTask("read book").toString());
    }

    @Test
    public void saveString_validInput_success() throws Exception {
        assertEquals("T|false|read book|null", new TodoTask("read book").toSaveString());
    }
}
