package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.ToDo;

public class ToDoTest {
    @Test
    public void toFileRecord_validFormat_success() {
        Task testToDo = new ToDo("a todo");
        testToDo.markAsDone();
        assertEquals(testToDo.toFileRecord(), "T | 1 | a todo");
    }
}
