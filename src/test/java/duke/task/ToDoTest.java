package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class ToDoTest {
    private final String errorHeader = "(O_O;) Oh no!! ";

    @Test
    public void init_emptyDescription_throwException() {
        try {
            new ToDo("");
            fail();
        } catch (DukeException e) {
            assertEquals(errorHeader + "Looks like you forgot to include a description of the todo.", e.toString());
        }
    }

    @Test
    public void testToString() {
        try {
            ToDo todo = new ToDo("task");

            assertEquals("[T][ ] task", todo.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testDataString() {
        try {
            ToDo todo = new ToDo("task");

            assertEquals("T|0|task", todo.toDataString("|"));
        } catch (DukeException e) {
            fail();
        }
    }
}
