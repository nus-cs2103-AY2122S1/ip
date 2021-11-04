package iris;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import iris.task.ToDo;

public class ToDoTest {
    @Test
    public void constructor_validArguments_success() {
        ToDo todo = new ToDo("test todo");
        assertEquals("[T][ ] test todo", todo.toString());
    }

    @Test
    public void markComplete_success() {
        ToDo todo = new ToDo("test todo");
        todo.markDone();
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
        todo.markDone();
        assertEquals("todo test todo\ndone 1\n", todo.toCommand(1));
    }
}
