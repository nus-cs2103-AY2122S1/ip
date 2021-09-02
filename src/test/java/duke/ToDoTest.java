package duke;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toFileRecord_validFormat_success() {
        Task testToDo = new ToDo("a todo");
        testToDo.markAsDone();
        assertEquals(testToDo.toFileRecord(), "T | 1 | a todo");
    }
}
