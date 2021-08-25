package duke.test;

import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo todoIncomplete = new Todo("test 1", false);
    Todo todoComplete = new Todo("test 2", true);
    @Test
    public void toString_incompleteTask_success() {
        assertEquals("[T][] test 1", todoIncomplete.toString());
    }

    @Test
    public void toString_completeTask_success() {
        assertEquals("[T][X] test 2", todoComplete.toString());
    }
}
