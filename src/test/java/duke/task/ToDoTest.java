package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {
    @Test
    public void init_emptyDescription_throwException() {
        try {
            new ToDo("");
            fail();
        } catch (DukeException e) {
            assertEquals("∑(O_O;) Oh no!! Looks like you forgot to include a description of the todo.", e.toString());
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
