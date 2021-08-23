package duke;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void constructor_validArguments_success() {
        ToDo todo = new ToDo("test todo");
        assertEquals("[T][ ] test todo", todo.toString());
    }

    @Test
    public void markComplete_success() {
        ToDo todo = new ToDo("test todo");
        todo.markComplete();
        assertEquals("[T][X] test todo", todo.toString());
    }

    @Test
    public void toCommand_incomplete_success() {
        ToDo todo = new ToDo("test todo");
        assertEquals("todo test todo\n", todo.toCommand(1));
    }

    @Test
    public void toCommand_completed_success() {
        ToDo todo = new ToDo("test todo");
        todo.markComplete();
        assertEquals("todo test todo\ndone 1\n", todo.toCommand(1));
    }
}